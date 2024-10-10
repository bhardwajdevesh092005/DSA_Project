package com.example.demo;

import com.example.demo.model.MyRequest;
import com.example.demo.model.ResultPath;

import java.util.ArrayList;

import com.example.demo.model.Location;

public class Algorithm {
    public ResultPath findPath(MyRequest input)
    {
        ResultPath res = new ResultPath();
        Location loc1 = input.getloc1();
        Location loc2 = input.getloc2();
        System.out.println(loc1);
        System.out.println(loc2);
        res.result = new ArrayList<>();
        res.result.add(loc2);
        res.result.add(loc1);
        return res;
    }
}
