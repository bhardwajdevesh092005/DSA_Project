package com.example.demo.controller;

// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MyRequest;

@RestController
public class MyController {

    @PostMapping("/data")
    public String receiveData(@RequestBody MyRequest request) {
        return "Received param1: " + request.getParam1() + ", param2: " + request.getParam2();
    }
}
