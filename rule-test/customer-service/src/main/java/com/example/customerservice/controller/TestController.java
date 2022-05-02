package com.example.customerservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/customer/{id}")
    public String getId(@PathVariable("id") Long id) {
        log.info(">>>>>>customer-service getId method<<<<<<<");
        return id.toString();
    }
}
