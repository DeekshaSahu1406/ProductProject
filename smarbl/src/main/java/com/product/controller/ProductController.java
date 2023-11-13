package com.product.controller;

import com.product.Request.ProductNameUpdateRequest;
import com.product.Request.ProductRequest;
import com.product.Request.ProductUpdateRequest;
import com.product.Response.GenericResponse;
import com.product.Response.ProductResponse;
import com.product.pojos.Product;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public GenericResponse addProduct(@RequestBody ProductRequest productRequest) {
        GenericResponse genericResponse=new GenericResponse();
        ProductResponse productResponse=productService.add(productRequest);
        genericResponse.setData(productResponse);
        genericResponse.setMessage("Product added sucessfully");
        genericResponse.setStatus(HttpStatus.CREATED.toString());
        return genericResponse;

    }

    @GetMapping("/getAllProducts")
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // Create a PageRequest object for pagination
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Product> paginatedProducts = productService.getAllProductsPaged(pageRequest);

        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setMessage("list fetched successfully");
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setData(paginatedProducts);

        // Call the ProductService method to retrieve paginated products
        return productService.getAllProductsPaged(pageRequest);
    }

    @PutMapping("/updateProduct") //there is some issue with this
    public GenericResponse updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest) {
        ProductResponse productResponse=productService.update(productUpdateRequest);
        GenericResponse genericResponse=new GenericResponse();
        if(productResponse != null){

            genericResponse.setMessage("product Updated successfully");
            genericResponse.setStatus(HttpStatus.OK.toString());
            genericResponse.setData(productResponse);
        }
        else{
            genericResponse.setMessage("product Updation failed product does not exist");
            genericResponse.setStatus(HttpStatus.OK.toString());
        }

        return genericResponse;
    }


    @GetMapping("/{id}")
    public GenericResponse getProductById(@PathVariable Long id) {
        Product product=productService.fetchById(id);
        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setMessage("product fetched successfully");
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setData(product);
        return genericResponse;
    }

    @GetMapping("/searchByName")
    public GenericResponse getProductByName(@RequestParam String name) {
        Product product=productService.getProductByName(name);
        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setMessage("product fetched successfully");
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setData(product);
        return genericResponse;
    }

    @GetMapping("/searchByDesc")
    public GenericResponse getProductByDesc(@RequestParam String desc) {
        Product product=productService.getProductbyDesc(desc);
        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setMessage("product fetched successfully");
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setData(product);
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    public GenericResponse DeleteProduct(@PathVariable Long id) {
        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setMessage(productService.delete(id));
        genericResponse.setStatus(HttpStatus.OK.toString());
        return genericResponse;

    }


    @PutMapping("/updateProductName") //there is some issue with this
    public GenericResponse updateProductName(@RequestBody ProductNameUpdateRequest productNameUpdateRequest) {
        ProductResponse productResponse=productService.updateName(productNameUpdateRequest);
        GenericResponse genericResponse=new GenericResponse();
        if(productResponse != null){

            genericResponse.setMessage("product Name Updated successfully");
            genericResponse.setStatus(HttpStatus.OK.toString());
            genericResponse.setData(productResponse);
        }
        else{
            genericResponse.setMessage("product Name Updation failed product does not exist");
            genericResponse.setStatus(HttpStatus.OK.toString());
        }

        return genericResponse;
    }


}
