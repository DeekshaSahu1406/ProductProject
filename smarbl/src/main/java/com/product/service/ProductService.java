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

    ProductResponse add(ProductRequest transientProduct);

     Page<Product> getAllProductsPaged(Pageable pageable);

    String delete(Long id) throws ValidationException;

    Product fetchById(Long id) throws ValidationException;

    ProductResponse update(ProductUpdateRequest productUpdateRequest) throws ValidationException;

    ProductResponse updateName(ProductNameUpdateRequest productNameUpdateRequest) throws ValidationException;

    Product getProductbyDesc(String details) throws ValidationException;


    Product getProductByName(String name) throws ValidationException;

    Page<Product> searchByNameOrDescription(String query, Pageable pageable) throws ValidationException;
}
