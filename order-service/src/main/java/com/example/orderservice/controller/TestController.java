package com.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public void test() {
        log.info(">>>>>>order-service test method<<<<<<<");
    }
}
