package com.cricket.info.controller;

import com.cricket.info.models.PlayerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class CricketInfoHtmlController {

    Map<String, PlayerModel> playerModelMap = new HashMap<>();

    @GetMapping("/")
    public String displayAppName(){
        return "home";  // src/main/resource/home.html
    }


    @GetMapping("/player/new")
    public String createNewPlayer(Model model){
        model.addAttribute("player", new PlayerModel()); // model.addAttribute send backend data to frontend
        return "player-form";
    }


    @PostMapping("/create/player")
    public String createPlayer(@ModelAttribute PlayerModel p){
        System.out.println("Inside createPlayer method of CricketInfoJsonController");
        playerModelMap.put(p.getPlayerName(), p);
        return "home";
    }

    @GetMapping("/list/players")
    public String fetchPlayers(Model model){
        System.out.println("Inside createPlayer method of CricketInfoJsonController");
        List<PlayerModel> list = new ArrayList<>();
        for (Map.Entry<String, PlayerModel> entry : playerModelMap.entrySet()) {
            list.add(entry.getValue());
        }
        model.addAttribute("players", list);
        return "player-list";
    }
}




// API -> application program interface
// Spring : Java based framework for building enterprise level application
// Dependency Injection / Inversion of Control
// Web & RESTful API
// makes integration with other frameworks through libraries
// MVC --> Model, V -> View , C-> controller
// Model --> Data
// View -> presentation
// controller -> Request router

// vanilla -> cone(Client) -> (server) Billing counter ->

// vanilla icecream -> refrigerator(repository)
// cone/cup --> presentation skill
// service logic -->

// RESTFul --> BiDirectional, stateless
// @Controller: it always returns html page as response
// @RestController : it returns same value as it is that we send
// View Resolver:
// on every button click, end point is called