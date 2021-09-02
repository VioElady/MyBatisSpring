package com.example.mybatis.demo.mapper;

import com.example.mybatis.demo.model.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CustomerMapper {

    @Select("SELECT COALESCE ((SELECT 1 FROM customer WHERE username=#{username}), 0) ")
    boolean existsByUsername(@Param("username") String username);

    @Select("SELECT EXISTS(SELECT 1 FROM customer WHERE email=#{email})")
    boolean existsByEmail(String email);

    @Insert("insert into customer(username,password, email) values( #{username}, #{password}, #{email})" )
    @Options(useGeneratedKeys=true, keyProperty="id")
    int save(Customer customer);

    @Select("select * from customer where email = #{email}")
    Customer findByEmail(@Param("email") String email);

    @Select("select * from customer where username = #{username}")
    Customer findByUsername(@Param("username") String username);
}
