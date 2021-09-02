package com.example.mybatis.demo.service;

import com.example.mybatis.demo.converter.ProductConverter;
import com.example.mybatis.demo.exceptionhandling.ProductNotFoundException;
import com.example.mybatis.demo.model.Assessments;
import com.example.mybatis.demo.model.Customer;
import com.example.mybatis.demo.model.Product;
import com.example.mybatis.demo.mapper.AssessmentsMapper;
import com.example.mybatis.demo.mapper.ProductMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;


@Service
@RequiredArgsConstructor
@Data
public class AssessmentsService {

    private final AssessmentsMapper assessmentsMapper;
    private final ProductMapper productMapper;
    private final CustomerService customerService;
    private ProductConverter converter;

    public void addAssessment(Long productId, Boolean likes) throws ProductNotFoundException {
        Optional<Product> product = productMapper.findById(productId);
        Customer customer = customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (product.isPresent()) {
            if (product.get().getCustomerId().equals(customer.getId())) {
                throw new ProductNotFoundException("The product belongs to you, don't appreciate it.", INTERNAL_SERVER_ERROR);
            }
                {
                    Assessments assessments = new Assessments();
                    assessments.setProductId(product.get().getId());
                    assessments.setCustomerId(customer.getId());
                    assessments.setLikes(likes);
                    assessmentsMapper.save(assessments);
                }

        } else
            throw new ProductNotFoundException("Product not found, you entered the wrong product!", INTERNAL_SERVER_ERROR);
    }
}