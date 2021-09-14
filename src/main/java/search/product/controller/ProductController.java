package search.product.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import search.product.model.Product;
import search.product.model.bean.ProductBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import search.product.service.ProductService;

import java.util.Collection;

@RequestMapping(ApiPath.PRODUCT)
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @ApiOperation(value = "Get Product By ID", notes ="Returns product with HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Product> getByProductId(@PathVariable("id") Long productId) {
        if(productId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(productService.getProductById(productId), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all Products", notes ="Returns all products with HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @GetMapping(path = "/all")
    @ResponseBody
    public ResponseEntity<Collection<Product>> getAllProducts() {
        return new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create Product By ID", notes ="Returns product with HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @PostMapping(path = "/create")
    @ResponseBody
    public ResponseEntity<Product> createProduct(@RequestBody ProductBean productBean) {
        if(productBean == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(productService.createProduct(productBean), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Product By ID", notes ="Returns product with HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @PutMapping(path = "/update")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@RequestBody ProductBean productBean) {
        if(productBean == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(productService.updateProduct(productBean), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Product By ID", notes ="Returns HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @DeleteMapping(path = "/delete/{id}")
    @ResponseBody
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        if(id == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}