package com.example.mybatis.demo;

import com.example.mybatis.demo.model.Assessments;
import com.example.mybatis.demo.model.Customer;
import com.example.mybatis.demo.model.Product;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MappedTypes({Customer.class, Product.class, Assessments.class})
@MapperScan("com.example.mybatis.demo.mapper")
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
