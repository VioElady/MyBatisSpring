package com.example.mybatis.demo.service;

import com.example.mybatis.demo.converter.ProductConverter;
import com.example.mybatis.demo.exceptionhandling.DataBaseException;
import com.example.mybatis.demo.exceptionhandling.ProductNotFoundException;
import com.example.mybatis.demo.model.Customer;
import com.example.mybatis.demo.model.Product;
import com.example.mybatis.demo.dto.product.ProductDto;
import com.example.mybatis.demo.mapper.ProductMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Data
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    private final ProductConverter converter;
    private final CustomerService customerService;

    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product;
        product = productMapper.findById(id);
        isPresent(product);
        return converter.modelToDTO(product.get());
    }

    public List<ProductDto> getAllProducts() throws DataBaseException {
        List<Product> products;
        try {
            products = productMapper.findAll();
        } catch (Exception e) {
            throw new DataBaseException("Data base issue!", INTERNAL_SERVER_ERROR);
        }
        return converter.modelToDTO(products);
    }


    public List<ProductDto> getAllProductsForUser() throws DataBaseException {
        List<Product> products;
        try {
            products = productMapper.findProductsByCustomer(customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        } catch (Exception e) {
            throw new DataBaseException("Data base issue!", INTERNAL_SERVER_ERROR);
        }
        return converter.modelToDTO(products);
    }

    public void deleteProduct(Long id) throws DataBaseException, ProductNotFoundException {
        Optional<Product> product = productMapper.findById(id);
        isPresent(product);
        Product toBeDelete = product.get();
        Customer customer = customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (toBeDelete.getCustomerId() == customer.getId()) {
            productMapper.deleteById(id);
        } else
            throw new DataBaseException("Data Source issue, could not delete product", INTERNAL_SERVER_ERROR);
    }

    public void addProduct(ProductDto productDto) {
        validateProduct(productDto);
        Product product = converter.dtoToModel(productDto);
        product.setCustomerId(customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        productMapper.save(product);
    }

    public void updateProduct(Long id, ProductDto productDto) throws ProductNotFoundException {
        Optional<Product> receivedProduct = productMapper.findById(id);
        isPresent(receivedProduct);
        validateProduct(productDto);

        Product toBeUpdated = receivedProduct.get();
        Customer customer = customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (toBeUpdated.getCustomerId() == customer.getId()) {
            toBeUpdated.setTitle(productDto.getTitle());
            toBeUpdated.setPrice(productDto.getPrice());
            toBeUpdated.setDescription(productDto.getDescription());
        } else
            throw new ProductNotFoundException("Product not found!", INTERNAL_SERVER_ERROR);
        productMapper.save(toBeUpdated);
    }


    private void isPresent(Optional<Product> optionalProduct) throws ProductNotFoundException {
        if (optionalProduct.isPresent()) {
            return;
        }
        throw new ProductNotFoundException("Product not found!", INTERNAL_SERVER_ERROR);
    }

    private void validateProduct(ProductDto productDto) throws IllegalArgumentException {
        validateString(productDto.getTitle(), "You are trying to set invalid value for product name!");
        validateString(productDto.getDescription(), "You are trying to set invalid value for product genre!");
    }

    private void validateString(String string, String errorMessage) throws IllegalArgumentException {
        if (string.isEmpty() || string.contains(" ") || string.length() <= 1 || string.matches(".*\\d.*"))
            throw new IllegalArgumentException(errorMessage);
    }

    public Page<Product> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return productMapper.findByPage();
    }

}
