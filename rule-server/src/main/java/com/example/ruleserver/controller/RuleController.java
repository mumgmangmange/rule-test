package com.example.ruleserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class RuleController {

    @Autowired
    RestTemplate template;

    @GetMapping("/rule/{id}")
    public String findById(@PathVariable("id") Long id, HttpServletRequest request) {

        String result = template.getForObject("http://customer-service/customer/{id}", String.class, id);
        log.info("호출을 하였어요!>>>> result:{}, id:{}, localPort:{}", result, id, request.getLocalPort());
        return result;
    }
}
