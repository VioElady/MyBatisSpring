package com.example.mybatis.demo.model;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "assessments")
public class Assessments {

    @EmbeddedId
    private AssessmentID id = new AssessmentID();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customer_id")
    private Customer customer;

    @Column(name = "likes")
    private Boolean likes;
}