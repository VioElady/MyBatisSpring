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
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    private Customer customer;

    @Column(name = "likes")
    private Boolean likes;
}