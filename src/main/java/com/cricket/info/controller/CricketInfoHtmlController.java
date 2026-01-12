package com.cricket.info.controller;

import com.cricket.info.models.PlayerModel;
import com.cricket.info.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/app")
public class CricketInfoHtmlController {

    @Autowired
    private PlayerRepository playerRepo;

    Map<String, PlayerModel> playerModelMap = new HashMap<>();

    @GetMapping("/")
    public String displayAppName(Model model){
        List<PlayerModel> list = (List<PlayerModel>) playerRepo.findAll();
        model.addAttribute("players", list);
        return "home";  // src/main/resource/home.html
    }


    @GetMapping("/player/new")
    public String createNewPlayer(Model model){
        model.addAttribute("player", new PlayerModel()); // model.addAttribute send backend data to frontend
        return "player-form";
    }


    @PostMapping("/save/player")
    public String savePlayer(@ModelAttribute PlayerModel p, Model model){
        if(p.getPlayerId() == null){
            // create
            playerRepo.save(p); // db insert into players table, returns void
        }else{
           Optional<PlayerModel> opt =  playerRepo.findById(p.getPlayerId());
           if(opt.isPresent()){
               PlayerModel p2 = opt.get();
               p2.setPlayerName(p.getPlayerName());
               p2.setAge(p.getAge());
               p2.setTeamName(p.getTeamName());
               p2.setAverage(p.getAverage());
               p2.setTotalRuns(p.getTotalRuns());
               p2.setCenturies(p.getCenturies());
               p2.setHalfCenturies(p.getHalfCenturies());
               p2.setGender(p.getGender());
               p2.setJerseyNum(p.getJerseyNum());
               playerRepo.save(p2);
           }
        }
        List<PlayerModel> list = (List<PlayerModel>) playerRepo.findAll();
        model.addAttribute("players", list);
        return "home";

    }

    @GetMapping("/player/edit/{id}")
    public String getPlayerById(@PathVariable Long id, Model model){
      Optional<PlayerModel> opt = playerRepo.findById(id);
      if(opt.isEmpty()){
          List<PlayerModel> li = (List<PlayerModel>) playerRepo.findAll();
          model.addAttribute("players", li);
          return "home";
      }
      PlayerModel p = opt.get();
      model.addAttribute("player", p);
      return "player-form";
    }

    @GetMapping("/list/players")
    public String fetchPlayers(Model model){
        List<PlayerModel> list = new ArrayList<>();
        System.out.println("Inside createPlayer method of CricketInfoJsonController");
        for (Map.Entry<String, PlayerModel> entry : playerModelMap.entrySet()) {
            list.add(entry.getValue());
        }
        model.addAttribute("players", list);
        return "home";
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
// right click -? inspect -> network -> call -> headers --? URL & MethodType -> response -> mapping of object in html
// spring --> start--> spring container -->
// <bean id="cricketInfoHtmlController" class ="com.cricket.info.controller.CricketInfoHtmlController" scope="singleton"/>
// <bean id="playerModel" class="com.cricket.info.models.PlayerModel" scope="prototype">
//       <property name="playerName" value="Sachin Tendulkar"/>
//   </bean>