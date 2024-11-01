package com.example.demo.controller;

import java.util.ArrayList;

// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Location;
import com.example.demo.model.MyRequest;
import com.example.demo.model.ResultPath;
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MyController {
    @PostMapping("/data")
    public ResultPath receiveData(@RequestBody MyRequest request)
    {
        Double[][] arr = {{77.0432859, 28.450458},{77.0434847, 28.4506002},{77.043639, 28.4507145},{77.0437449, 28.4508148},{77.0437999, 28.4508631},{77.0438348, 28.4509138},{77.0438723, 28.4509834},{77.0439059, 28.4510506},{77.043981, 28.4512581},{77.0440319, 28.4514869},{77.0440546, 28.4516844},{77.0440561, 28.451751},{77.0440437, 28.4517899},{77.0440185, 28.4518288},{77.0439112, 28.4519114},{77.0437478, 28.4520255}};
        ResultPath res = new ResultPath();
        res.result = new ArrayList<>();
        for(Double[] x:arr)
        {
            res.result.add(new Location(x[1],x[0]));
        }
        return res;
    }
}
