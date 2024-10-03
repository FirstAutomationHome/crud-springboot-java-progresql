package com.fdymain.crud_springboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;

    }
    @GetMapping
    public List<Products> getProduct(){
        return productService.getProducts();
    }
    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody Products products){
        return this.productService.newProduct(products);
    }
    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Products products){
        return this.productService.newProduct(products);
    }
    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") Long productId){
        return  this.productService.deleteProduct(productId);
    }

}
