package com.product.service;

import com.product.Request.ProductNameUpdateRequest;
import com.product.Request.ProductRequest;
import com.product.Request.ProductUpdateRequest;
import com.product.Response.ProductResponse;
import com.product.exception.ValidationException;
import com.product.pojos.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    ProductResponse addProduct(ProductRequest transientProduct);

     Page<ProductResponse> getAllProductsPaged(Pageable pageable);

    String deleteProductById(Long id) throws ValidationException;

    ProductResponse getProductById(Long id) throws ValidationException;

    ProductResponse updateProduct(ProductRequest productRequest) throws ValidationException;

    ProductResponse updateProductName(ProductNameUpdateRequest productNameUpdateRequest) throws ValidationException;

    ProductResponse getProductbyDesc(String details) throws ValidationException;


    ProductResponse getProductByName(String name) throws ValidationException;

    Page<ProductResponse> searchProductByNameOrDescription(String query, Pageable pageable) throws ValidationException;
}
