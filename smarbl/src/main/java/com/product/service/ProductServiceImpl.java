package com.product.service;

import com.product.Request.ProductNameUpdateRequest;
import com.product.Request.ProductRequest;
import com.product.Request.ProductUpdateRequest;
import com.product.Response.ProductResponse;
import com.product.dto.DtoTransformer;
import com.product.exception.ValidationException;
import com.product.pojos.Product;
import com.product.repository.ProductRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private DtoTransformer dtoTransformer;

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        return productRepo.findAll();
    }

    @Override
    public Page<Product> getAllProductsPaged(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public ProductResponse add(ProductRequest productRequest) {
        // TODO Auto-generated method stub
        Product product = DtoTransformer.trasformProductRequestToProduct(productRequest);
        Product productSaved= productRepo.save(product);
        return dtoTransformer.trasformProductToResponse(productSaved);
    }

    @Override
    public String delete(Long id) throws ValidationException {
        // TODO Auto-generated method stub
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Product deleted ....";
        }
        throw new ValidationException("product can't be deleted","product id not found");
    }

    @Override
    public Product fetchById(Long id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("product can't be found","Product ID must not be null");
        }
        // Assuming 'productRepo' is an instance of JpaRepository<Product, Long>
        Optional<Product> optionalProduct = productRepo.findById(id);
        // Check if the product with the given ID exists
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            // Handle the case where the product with the given ID is not found
            throw new ValidationException("product not found","Product ID not found");
        }
    }

    @Override
    public ProductResponse update(ProductUpdateRequest productUpdateRequest) throws ValidationException {

        if (productUpdateRequest != null) {
            Long productId = productUpdateRequest.getId();

            if (productRepo.existsById(productId)) {
                Product existingProduct = productRepo.findById(productId).orElseThrow(() ->
                        new ValidationException("Product can't be updated", "Product not found with id: " + productId)
                );

                // Update only the properties that should be modified
                existingProduct = dtoTransformer.transformUpdateRequestToProduct(productUpdateRequest);

                // Save the updated product
                Product updatedProduct = productRepo.save(existingProduct);

                return dtoTransformer.trasformProductToResponse(updatedProduct);
            } else {
                throw new ValidationException("Product can't be updated", "Product not found with id: " + productId);
            }
        } else {
            throw new ValidationException("Product can't be updated", "Product cannot be null");
        }
    }

    @Override
    public ProductResponse updateName(ProductNameUpdateRequest productNameUpdateRequest) throws ValidationException {
        if(productRepo.existsById(productNameUpdateRequest.getId())){
            Product updateProduct = new Product();
            updateProduct = productRepo.findById(productNameUpdateRequest.getId()).orElseThrow();
            updateProduct.setName(productNameUpdateRequest.getName());
            return dtoTransformer.trasformProductToResponse(productRepo.save(updateProduct));
        }
        else{
            throw new ValidationException("product name can't be updated","product not found");
        }
    }

    @Override
    public Product getProductByName(String name) {
        // Validate that the name parameter is not blank
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Product name must not be blank");
        }
        // Assuming 'productRepo' is an instance of JpaRepository<Product, Long>
        return productRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Page<Product> searchByNameOrDescription(String query, Pageable pageable) {
        if ( query != null) {
            // If both name and description are provided, perform OR search
            return productRepo.findByNameOrDetails(query, pageable);
        }
        else {
            return null;
        }
    }

    @Override
    public Product getProductbyDesc(String details) {
        // Validate that the name parameter is not blank
        if (StringUtils.isBlank(details)) {
            throw new IllegalArgumentException("Product description must not be blank");
        }

        // Assuming 'productRepo' is an instance of JpaRepository<Product, Long>
        return productRepo.findByDetails(details);
    }


}
