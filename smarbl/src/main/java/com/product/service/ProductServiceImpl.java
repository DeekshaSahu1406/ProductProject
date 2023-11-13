package com.product.service;

import com.product.Request.ProductNameUpdateRequest;
import com.product.Request.ProductRequest;
import com.product.Request.ProductUpdateRequest;
import com.product.Response.ProductResponse;
import com.product.dto.DtoTransformer;
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
    public String delete(Long id) {
        // TODO Auto-generated method stub
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Product deleted ....";
        }
        return "Deletion Failed : Invalid Id !!!!!!!!!!!";
    }

    @Override
    public Product fetchById(Long id) {
        // Validate that the id parameter is not null using javax.validation
        // This annotation is optional and depends on your application's requirements

        // Validate the id parameter
        if (id == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }

        // Assuming 'productRepo' is an instance of JpaRepository<Product, Long>
        Optional<Product> optionalProduct = productRepo.findById(id);

        // Check if the product with the given ID exists
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            // Handle the case where the product with the given ID is not found
            throw new EntityNotFoundException("Product with ID " + id + " not found");
        }
    }
    @Override
    public ProductResponse update(ProductUpdateRequest productUpdateRequest) {
        if(productRepo.existsById(productUpdateRequest.getId())){
            Product updateProduct = new Product();
            Product oldProduct = new Product();
            oldProduct = productRepo.findById(productUpdateRequest.getId()).orElseThrow();
            updateProduct=dtoTransformer.transformUpdateRequestToProduct(productUpdateRequest);
            updateProduct.setName(oldProduct.getName());

            return dtoTransformer.trasformProductToResponse(productRepo.save(updateProduct));

        }


        return null;
    }

    @Override
    public ProductResponse updateName(ProductNameUpdateRequest productNameUpdateRequest) {
        if(productRepo.existsById(productNameUpdateRequest.getId())){
            Product updateProduct = new Product();
            updateProduct = productRepo.findById(productNameUpdateRequest.getId()).orElseThrow();
            updateProduct.setName(productNameUpdateRequest.getName());

            return dtoTransformer.trasformProductToResponse(productRepo.save(updateProduct));

        }


        return null;
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
    public Product getProductbyDesc(String description) {
        // Validate that the name parameter is not blank
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("Product description must not be blank");
        }

        // Assuming 'productRepo' is an instance of JpaRepository<Product, Long>
        return productRepo.findByNameContainingIgnoreCase(description);
    }


}
