package com.example.WatchlistAppServer.controller;


import com.example.WatchlistAppServer.modul.Medium;
import com.example.WatchlistAppServer.service.MediumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MediumController {

    @Autowired
    private MediumService mediumService;

    @GetMapping("medium/get-all")
    public List<Medium> getAll(){
        System.out.println("List ist zuruek");
        List<Medium> m = mediumService.getAll();
        for(Medium s: m)
            System.out.println(s);
        return m;
    }

}
