package com.example.mybatis.demo.service;

import com.example.mybatis.demo.dto.customer.CustomerRequestDto;
import com.example.mybatis.demo.dto.customer.CustomerResponseDto;
import com.example.mybatis.demo.model.Customer;
import com.example.mybatis.demo.mapper.CustomerMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Data
public class CustomerService {

    public final CustomerMapper customerMapper;

    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {
        customerMapper.findByEmail(customerRequestDto.getEmail());

        Customer customer = Customer.builder()
                .email(customerRequestDto.getEmail())
                .username(customerRequestDto.getUsername())
                .build();

        customer.setPassword(new BCryptPasswordEncoder(12).encode(customerRequestDto.getPassword()));
        Customer customerResponseDto = null;

        return CustomerResponseDto.builder()
                .id(customerResponseDto.getId())
                .email(customerResponseDto.getEmail())
                .username(customerResponseDto.getUsername())
                .build();
    }

    public Customer findUserByUsername(String username) throws UsernameNotFoundException {
        return customerMapper.findByUsername(username);
    }
}