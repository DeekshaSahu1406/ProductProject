package com.product.dto;

import com.product.Request.ProductRequest;
import com.product.Request.ProductUpdateRequest;
import com.product.Response.ProductResponse;
import com.product.pojos.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class DtoTransformer {
    public static Product trasformProductRequestToProduct(ProductRequest productRequest) {
        Product product =new Product();
        BeanUtils.copyProperties(productRequest,product);
        return product;
    }
    public ProductResponse trasformProductToResponse(Product product) {
        ProductResponse productResponse =new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }

    public static Product transformUpdateRequestToProduct(ProductUpdateRequest productUpdateRequest) {
        Product product =new Product();
        BeanUtils.copyProperties(productUpdateRequest,product);
        return product;
    }

    public Page<ProductResponse> convertPageToProductResponse(Page<Product> productPage) {
        return productPage.map(this::trasformProductToResponse);
    }

}
