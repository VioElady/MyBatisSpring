package com.example.mybatis.demo.controller;

import com.example.mybatis.demo.exceptionhandling.DataBaseException;
import com.example.mybatis.demo.exceptionhandling.ProductNotFoundException;
import com.example.mybatis.demo.model.Product;
import com.example.mybatis.demo.dto.product.ProductDto;
import com.example.mybatis.demo.service.ProductService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/products")
@Api
@Validated
@RestController

public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() throws DataBaseException {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ProductDto>> getProductForUser() throws DataBaseException {
        return new ResponseEntity<>(productService.getAllProductsForUser(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity<>("Product \"" + productDto.getTitle() + "\" has been added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        productService.updateProduct(id, productDto);
        return new ResponseEntity<>("Product with id \"" + id + "\" has been updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) throws DataBaseException, ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id \"" + id + "\" has been deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam("page-number") @Min(0) int pageNumber,
                                                         @RequestParam("page-size") @Min(1) int pageSize) {

        List<Product> pageableProducts = productService.findByPage(pageNumber, pageSize);
        Iterator<Product> iterator = pageableProducts.stream().iterator();
        List<Product> products = new ArrayList<>();
        iterator.forEachRemaining(products::add);
        return new  ResponseEntity<>(products, HttpStatus.OK);
    }

}
