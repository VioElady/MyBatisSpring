package com.example.mybatis.demo.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class AssessmentID implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "customer_id")
    private Long customerId;
}