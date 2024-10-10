package com.example.demo.controller;

// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Algorithm;
import com.example.demo.model.MyRequest;
import com.example.demo.model.ResultPath;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MyController {
    @PostMapping("/data")
    public ResultPath receiveData(@RequestBody MyRequest request)
    {
        Algorithm algo = new Algorithm();
        ResultPath res = algo.findPath(request);
        return res;
    }
}
