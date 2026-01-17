package com.cricket.info.controller;

import com.cricket.info.models.PlayerModel;
import com.cricket.info.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepo;

    Map<String, PlayerModel> playerModelMap = new HashMap<>();

    @GetMapping("/")
    public String displayAppName(Model model){
        return "redirect:/list/players";
    }


    @GetMapping("/player/new")
    public String createNewPlayer(Model model){
        model.addAttribute("player", new PlayerModel()); // model.addAttribute send backend data to frontend
        return "player-form";
    }


    @PostMapping("/save/player")
    public String savePlayer(@ModelAttribute PlayerModel p, RedirectAttributes model){
        if(p.getId() == null){
            // create
            p.setAverage((double) (p.getTotalRuns()/p.getTotalMatches()));
            playerRepo.save(p); // db insert into players table, returns void
            model.addAttribute("success", "Player created successfully");
        }else{
           Optional<PlayerModel> opt =  playerRepo.findById(p.getId());
           if(opt.isPresent()){
               PlayerModel p2 = opt.get();
               p2.setPlayerName(p.getPlayerName());
               p2.setAge(p.getAge());
          //     p2.setTeam();
               p2.setTotalMatches(p.getTotalMatches());
               p2.setAverage((double) (p.getTotalRuns()/p.getTotalMatches()));
               p2.setTotalRuns(p.getTotalRuns());
               p2.setCenturies(p.getCenturies());
               p2.setHalfCenturies(p.getHalfCenturies());
               p2.setGender(p.getGender());
       //        p2.setJerseyNum(p.getJerseyNum());
               playerRepo.save(p2);
               model.addAttribute("success", "Player updated successfully");
           }
        }
        return "redirect:/list/players";

    }

    @GetMapping("/player/edit/{id}")
    public String getPlayerById(@PathVariable Long id, Model model){
      Optional<PlayerModel> opt = playerRepo.findById(id);
      if(opt.isEmpty()){
          List<PlayerModel> li = (List<PlayerModel>) playerRepo.findAll();
          model.addAttribute("players", li);
          model.addAttribute("error", "No player found for with given ID: " + id);
          return "home";
      }
      PlayerModel p = opt.get();
      model.addAttribute("player", p);
      model.addAttribute("success", "Player found");
      return "player-form";
    }

    @GetMapping("/list/players")
    public String fetchPlayers(Model model){
        List<PlayerModel> players = (List<PlayerModel>) playerRepo.findAll();
        if(players.isEmpty()){
            model.addAttribute("error", "No players found");
        }else{
            model.addAttribute("success", players.size() + " Players found");
        }
        model.addAttribute("players", players);
        return "home";
    }

    @DeleteMapping("/player/delete/{id}")
    public String removePlayerById(@PathVariable Long id, RedirectAttributes model){
        Optional<PlayerModel> opt = playerRepo.findById(id);
        if(opt.isPresent()) {
            playerRepo.deleteById(id);
            model.addFlashAttribute("success", "Player deleted successfully");
        }else{
            model.addFlashAttribute("error", "Player not found for deletion");
        }

        return "redirect:/list/players";

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

// GET -> GetByID(ID required) & GET All (nothing to be sent to backend from UI) :
// DELETE -> DeleteById --> only unique keys are enough
// POST -> Create/Update --> full form data

// @ModelAttribute -> used to handle form data mapping with java object