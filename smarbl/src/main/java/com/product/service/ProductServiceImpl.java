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
    public Page<ProductResponse> getAllProductsPaged(Pageable pageable) {
        return dtoTransformer.convertPageToProductResponse(productRepo.findAll(pageable));
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        // TODO Auto-generated method stub
        Product product = DtoTransformer.trasformProductRequestToProduct(productRequest);
        Product productSaved= productRepo.save(product);
        return dtoTransformer.trasformProductToResponse(productSaved);
    }

    @Override
    public String deleteProductById(Long id) throws ValidationException {
        // TODO Auto-generated method stub
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Product deleted ....";
        }
        throw new ValidationException("Unable to delete product", " Product not found with id: " + id);
    }

    @Override
    public ProductResponse getProductById(Long id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("product can't be found","Product ID must not be null");
        }
        // Assuming 'productRepo' is an instance of JpaRepository<Product, Long>
        Optional<Product> optionalProduct = productRepo.findById(id);
        // Check if the product with the given ID exists
        if (optionalProduct.isPresent()) {
            return dtoTransformer.trasformProductToResponse(optionalProduct.get());
        } else {
            // Handle the case where the product with the given ID is not found
            throw new ValidationException("Product not found","Product ID does not exist: "+id);
        }
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) throws ValidationException {

        if (productRequest != null) {
            Long productId = productRequest.getId();

            if (productRepo.existsById(productId)) {
                Product existingProduct = productRepo.findById(productId).orElseThrow(() ->
                        new ValidationException("Product can't be updated", "Product not found with id: " + productId)
                );

                // Update only the properties that should be modified
                existingProduct = dtoTransformer.trasformProductRequestToProduct(productRequest);

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
    public ProductResponse updateProductName(ProductNameUpdateRequest productNameUpdateRequest) throws ValidationException {
        if(productRepo.existsById(productNameUpdateRequest.getId())){
            Product updateProduct = new Product();
            updateProduct = productRepo.findById(productNameUpdateRequest.getId()).orElseThrow();
            updateProduct.setName(productNameUpdateRequest.getName());
            return dtoTransformer.trasformProductToResponse(productRepo.save(updateProduct));
        }
        else{
            throw new ValidationException("product can't be updated","product not found with name : "+productNameUpdateRequest.getName());
        }
    }

    @Override
    public ProductResponse getProductByName(String name) throws ValidationException {
        // Validate that the name parameter is not blank
        if (StringUtils.isBlank(name)) {
            throw new ValidationException("Product can't be updated","product not found with name: "+name);
        }
        // Assuming 'productRepo' is an instance of JpaRepository<Product, Long>
        return dtoTransformer.trasformProductToResponse(productRepo.findByNameContainingIgnoreCase(name));
    }

    @Override
    public Page<ProductResponse> searchProductByNameOrDescription(String query, Pageable pageable) throws ValidationException {
        if ( query != null) {
            // If both name and description are provided, perform OR search
            return dtoTransformer.convertPageToProductResponse(productRepo.findByNameOrDetails(query, pageable));
        }
        else {
            throw new ValidationException("product not found","Name or description can not be null");
        }
    }

    @Override
    public ProductResponse getProductbyDesc(String details) throws ValidationException {
        // Validate that the name parameter is not blank
        if (StringUtils.isBlank(details)) {
            throw new ValidationException("product not found","description can not be null");
        }

        // Assuming 'productRepo' is an instance of JpaRepository<Product, Long>
        return dtoTransformer.trasformProductToResponse(productRepo.findByDetails(details));
    }


}
