package com.cricket.info.controller;

import com.cricket.info.exceptions.MatchNotFoundException;
import com.cricket.info.models.MatchModel;
import com.cricket.info.services.MatchService;
import com.cricket.info.services.PlayerService;
import com.cricket.info.services.TeamService;
import com.cricket.info.validators.MatchDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {    // controller -> service -> repository -> Model(data -> DB)

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchDataValidator matchDataValidator;

    @GetMapping("/")
    public String getMatchHome(){
        return "redirect:/match/list";
    }

    @GetMapping("/list")
    public String fetchMatches(Model model){
        List<MatchModel> matches = matchService.findAllMatches();
        if(matches.isEmpty()){
            model.addAttribute("error", "No matchs found");
        }else{
            model.addAttribute("success", matches.size() + " Matchs found");
        }
        model.addAttribute("matches", matches);
        return "match";
    }

    @GetMapping("/new")
    public String createNewMatch(Model model){
        model.addAttribute("match", new MatchModel()); // model.addAttribute send backend data to frontend
        model.addAttribute("teams", teamService.findAllTeams());
        model.addAttribute("players", playerService.findAllPlayers());
        return "match-form";
    }


    @PostMapping("/save")
    public String saveMatch(@ModelAttribute MatchModel p, Model model){
        if(p.getId() == null){
            // create
            List<String> errors = matchDataValidator.validate(p);
            if(!errors.isEmpty()){
                model.addAttribute("error", errors);
            }else {
                try {
                    matchService.saveMatch(p); // db insert into matches table, returns void
                    model.addAttribute("success", "Match created successfully");
                } catch (Exception e) {
                    model.addAttribute("error", "Error during match data creation");
                }
            }

        }
        model.addAttribute("matches", matchService.findAllMatches());
        return "match";
    }

    @GetMapping("/edit/{id}")
    public String getMatchById(@PathVariable Long id, Model model){

        MatchModel match = null;
        try {
             match= matchService.getMatchById(id);
        } catch (MatchNotFoundException e) {
            List<MatchModel> li = matchService.findAllMatches();
            model.addAttribute("matches", li);
            model.addAttribute("error", "No match found for with given ID: " + id);
            return "match";
        }

        model.addAttribute("match", match);
        model.addAttribute("teams", teamService.findAllTeams());
        model.addAttribute("players",playerService.findAllPlayers());
        return "match-form";
    }

    @DeleteMapping("/delete/{id}")
    public String removeMatchById(@PathVariable Long id, Model model){
        try {
            matchService.deleteMatch(id);
            model.addAttribute("success", "Match deleted successfully");
        }catch (Exception ex){
            if(ex instanceof MatchNotFoundException) {
                model.addAttribute("error", ex.getMessage());
            }else{
                model.addAttribute("error", "Error during match data deletion");
            }
        }

        model.addAttribute("matches", matchService.findAllMatches());
        return "match";
    }
}
