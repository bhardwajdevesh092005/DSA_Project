package com.example.demo.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Algorithm.AStarAlgorithm;
import com.example.demo.Algorithm.Graph;
import com.example.demo.Algorithm.GraphBuilder;
import com.example.demo.model.Location;
import com.example.demo.model.MyRequest;
import com.example.demo.model.Node;
import com.example.demo.model.ResultPath;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MyController {

    @PostMapping("/data")
    public ResultPath receiveData(@RequestBody MyRequest request) {

        String filePath = "C:/Users/deves/Desktop/DSA_Project/API Folder/src/main/java/com/example/demo/Algorithm/export.geojson"; // Specify your GeoJSON                                                                                   // file path
        Graph graph = GraphBuilder.buildGraphFromGeoJSON(filePath);

        Node start1 = graph.tree.findKNearestNeighbors(new Node("Start", new BigDecimal(request.getloc1().latitude).setScale(6, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(request.getloc1().longitude).setScale(6, RoundingMode.HALF_UP).doubleValue()), 1).get(0);
        Node goal1 =  graph.tree.findKNearestNeighbors(new Node("Start", new BigDecimal(request.getloc2().latitude).setScale(6, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(request.getloc2().longitude).setScale(6, RoundingMode.HALF_UP).doubleValue()), 1).get(0);

        System.out.println(start1 + " " + goal1);

        System.out.println(request.getloc1().latitude + "  " + request.getloc1().longitude);
        System.out.println(request.getloc2().latitude + "  " + request.getloc2().longitude);

        // Node start = graph.getNode(request.getloc1().latitude, request.getloc1().longitude);
        // Node goal = graph.getNode(request.getloc2().latitude, request.getloc2().longitude);

        // System.out.println(start1 == start);
        // System.out.println(goal1 == goal);

        List<Node> path = AStarAlgorithm.aStar(graph, start1, goal1);

        // Double[][] arr = {{77.0432859, 28.450458},{77.0434847, 28.4506002},{77.043639, 28.4507145},{77.0437449, 28.4508148},{77.0437999, 28.4508631},{77.0438348, 28.4509138},{77.0438723, 28.4509834},{77.0439059, 28.4510506},{77.043981, 28.4512581},{77.0440319, 28.4514869},{77.0440546, 28.4516844},{77.0440561, 28.451751},{77.0440437, 28.4517899},{77.0440185, 28.4518288},{77.0439112, 28.4519114},{77.0437478, 28.4520255}};
        ResultPath res = new ResultPath();
        res.result = new ArrayList<>();
        
        if (path == null) {
            System.out.println("Unable to find path");
            return res;
        }
        for (Node x : path) {
            res.result.add(new Location(x.latitude, x.longitude));
        }
        return res;
    }
}
