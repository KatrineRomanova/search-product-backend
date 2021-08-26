package controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.math.BigInteger;

@RequestMapping(ApiPath.PRODUCT)
@RestController
//@RequiredArgsConstructor
public class ProductController {

   // private final ProductService productService;


    @ApiOperation(value = "Get Product By ID", notes ="Returns product with HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Product> getByPositionId(@PathVariable("id") BigInteger positionId) {
        if(positionId == null)
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        ProductService productService = new ProductService();
        return new ResponseEntity<Product>(productService.getProductById(positionId), HttpStatus.OK);
    }
}
