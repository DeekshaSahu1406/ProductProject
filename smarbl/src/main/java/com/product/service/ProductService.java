package com.product.service;

import com.product.Request.ProductNameUpdateRequest;
import com.product.Request.ProductRequest;
import com.product.Request.ProductUpdateRequest;
import com.product.Response.ProductResponse;
import com.product.pojos.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    ProductResponse add(ProductRequest transientProduct);

     Page<Product> getAllProductsPaged(Pageable pageable);

    String delete(Long id);

    Product fetchById(Long id);

    ProductResponse update(ProductUpdateRequest productUpdateRequest);

    ProductResponse updateName(ProductNameUpdateRequest productNameUpdateRequest);

    Product getProductbyDesc(String description);


    Product getProductByName(String name);
}
