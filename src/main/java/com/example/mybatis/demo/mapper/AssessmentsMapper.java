package com.example.mybatis.demo.mapper;

import com.example.mybatis.demo.model.Assessments;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssessmentsMapper {
    @Insert("insert into assessments(likes, product_id, customer_id) values(#{likes}, #{productId}, #{customerId})" )
    void save(Assessments assessments);
}