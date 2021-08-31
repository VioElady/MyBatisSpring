package com.example.mybatis.demo.controller;

import com.example.mybatis.demo.exceptionhandling.ProductNotFoundException;
import com.example.mybatis.demo.service.AssessmentsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Api
@Validated
@RestController
@RequestMapping("")

public class AssessmentsController {

    private final AssessmentsService assessmentsService;

    @PostMapping("/likes/{productId}")
    public ResponseEntity<Object> addLikes(@PathVariable Long productId) throws ProductNotFoundException {
        assessmentsService.addAssessment(productId, true);
        return new ResponseEntity<>("Likes true has been added successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/unlikes/{productId}")
    public ResponseEntity<Object> removeLikes(@PathVariable Long productId) throws ProductNotFoundException {
        assessmentsService.addAssessment(productId, false);
        return new ResponseEntity<>("UnLikes true has been added successfully!", HttpStatus.CREATED);
    }
}