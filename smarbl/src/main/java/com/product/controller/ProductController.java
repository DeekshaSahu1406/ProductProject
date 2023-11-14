package com.product.controller;

import com.product.Request.ProductNameUpdateRequest;
import com.product.Request.ProductRequest;
import com.product.Request.ProductUpdateRequest;
import com.product.Response.GenericResponse;
import com.product.Response.ProductResponse;
import com.product.Response.ProductResponsemethods;
import com.product.exception.ValidationException;
import com.product.exception.ValidationExceptionHandler;
import com.product.pojos.Product;
import com.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    private  ValidationExceptionHandler validationExceptionHandler;

    private ProductResponsemethods productResponsemethods;




    @PostMapping
    public GenericResponse addProduct(@RequestBody ProductRequest productRequest) {
        GenericResponse genericResponse=new GenericResponse();
        ProductResponse productResponse=productService.add(productRequest);
        genericResponse.setData(productResponse);
        genericResponse.setMessage("Product added sucessfully");
        genericResponse.setStatus(HttpStatus.CREATED.toString());
        return genericResponse;

    }

    @GetMapping
    public GenericResponse getAllProducts(Pageable pageable ) {
        Page<Product> paginatedProducts = productService.getAllProductsPaged(pageable);

        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setMessage("list fetched successfully");
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setTotal(paginatedProducts.getTotalElements());
        genericResponse.setTotalPages(paginatedProducts.getTotalPages());
        genericResponse.setPageNum(paginatedProducts.getNumber());
        genericResponse.setData(paginatedProducts.getContent());

        // Call the ProductService method to retrieve paginated products
        return genericResponse;
    }

    @PutMapping
    public GenericResponse updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest) throws ValidationException {
        try{
        ProductResponse productResponse=productService.update(productUpdateRequest);
        GenericResponse genericResponse=new GenericResponse();
            genericResponse.setMessage("product Updated successfully");
            genericResponse.setStatus(HttpStatus.OK.toString());
            genericResponse.setData(productResponse);
        return genericResponse;
    }
        catch (ValidationException ve) {
            return validationExceptionHandler.handleValidationException(ve);


        } catch (Exception e) {
            return validationExceptionHandler.handleGenericException(e);
        }
    }


    @Operation(description = "get product by id",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))
                    }),
                    @ApiResponse(responseCode = "404")
            }
    )
    @GetMapping("/{id}")
    public GenericResponse getProductById(@PathVariable Long id) throws ValidationException {
        Product product=productService.fetchById(id);
        GenericResponse genericResponse=new GenericResponse();
        if (product != null) {
        genericResponse.setMessage("product fetched successfully");
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setData(product);
        }
        else {
            genericResponse.setStatus(HttpStatus.NOT_FOUND.toString());
            genericResponse.setMessage("Product Not Found");
        }
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

//    @GetMapping("/search")
//    public GenericResponse searchProducts(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String details) {
//        try {
//            if (name != null) {
//                // Search by name
//                Product product = productService.getProductByName(name);
//                return productResponsemethods.createResponse("Product fetched successfully", product);
//            } else if (details != null) {
//                // Search by description
//                Product product = productService.getProductbyDesc(details);
//                return ProductResponsemethods.createResponse("Product fetched successfully", product);
//            } else {
//                // No valid search criteria provided
//                return ProductResponsemethods.createErrorResponse("Invalid search criteria", "Name or description parameter is required");
//            }
//        } catch (Exception e) {
//            return validationExceptionHandler.handleGenericException(e);
//        }
//    }

    @GetMapping("/search")
    public GenericResponse searchProductByNameOrDescription(
            @RequestParam String query,
            Pageable pageable
    ) {
        try {
            // Perform any necessary authentication or authorization checks here if needed

            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setStatus(HttpStatus.OK.toString());
            genericResponse.setMessage("List of products matching the search criteria");

            Page<Product> productResponsePage = productService.searchByNameOrDescription(query, pageable);

            genericResponse.setTotal(productResponsePage.getTotalElements());
            genericResponse.setTotalPages(productResponsePage.getTotalPages());
            genericResponse.setPageNum(productResponsePage.getNumber());
            genericResponse.setData(productResponsePage.getContent());

            return genericResponse;
        } catch (Exception e) {
            // Log the exception or handle it as needed
            // You might want to return a specific error response here
            GenericResponse errorResponse = new GenericResponse();
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            errorResponse.setMessage("Error searching products");
            // Set additional error details if necessary
            return errorResponse;
        }
    }


    @DeleteMapping("/{id}")
    public GenericResponse DeleteProduct(@PathVariable Long id) throws ValidationException {
        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setMessage(productService.delete(id));
        genericResponse.setStatus(HttpStatus.OK.toString());
        return genericResponse;

    }


    @PutMapping("/updateProductName") //there is some issue with this
    public GenericResponse updateProductName(@RequestBody ProductNameUpdateRequest productNameUpdateRequest) throws ValidationException {
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
