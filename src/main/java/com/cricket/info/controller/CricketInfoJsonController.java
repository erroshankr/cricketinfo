package com.cricket.info.controller;

import com.cricket.info.models.PlayerModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/json")
public class CricketInfoJsonController {

    Map<String, PlayerModel> playerModelMap = new HashMap<>();

    @GetMapping("/name")
    public String displayAppName(){
        return "home";
    }

    @PostMapping("/create/player")
    public String createPlayer(PlayerModel p){
        System.out.println("Inside createPlayer method of CricketInfoJsonController");
        playerModelMap.put(p.getPlayerName(), p);
        return "Player created successfully";
    }

    @GetMapping("/get/playerByName/{name}")
    public PlayerModel getPlayerInfo(String name){
        return playerModelMap.get(name);
    }

}


// RequestTYPE + Value must be unique
// GET -> Read operation -> R
// POST -->Write Operation -> C
// PUT -> UPDATE Operation -> U
// DELETE -> DELETE operation -> D


// Main -> entry point
// mapping must be unique for each controller method
// RestController
// User -> Frontend -> URL hit/button click --> backend --> returns data to frontend