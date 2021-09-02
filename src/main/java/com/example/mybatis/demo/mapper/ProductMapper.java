package com.example.mybatis.demo.mapper;

import com.example.mybatis.demo.model.Product;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    @Select("select id,description,title,price,customer_id as customerId from products where id = #{id}")
    Optional<Product> findById(Long id);

    @Select("select id,title,price,description,customer_id as customerId from products where customer_id = #{customerId}")
    List<Product> findProductsByCustomer(Long id);

    @Delete("delete from products where id = #{id}")
    void deleteById(Long id);

    @Insert("insert into products(title,price,description,customer_id) values(#{title}, #{price}, #{description}, #{customerId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Product product);

    @Select("select id,title,description,price,customer_id as customerId from products")
    List<Product> findAll();

    @Select("select id,title,description,price,customer_id as customerId from products")
    Page<Product> findByPage();

    @Select("update products set title=#{title}, price=#{price},description=#{description} where id = #{id}")
    void update(Product product);

}
