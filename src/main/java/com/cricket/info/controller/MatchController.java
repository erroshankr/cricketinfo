package com.cricket.info.controller;

import com.cricket.info.models.MatchModel;
import com.cricket.info.repo.MatchRepository;
import com.cricket.info.repo.PlayerRepository;
import com.cricket.info.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/")
    public String getMatchHome(){
        return "redirect:/match/list";
    }

    @GetMapping("/list")
    public String fetchMatchs(Model model){
        List<MatchModel> matches = matchRepository.findAll();
        if(matches.isEmpty()){
            model.addAttribute("error", "No matchs found");
        }else{
            model.addAttribute("success", matches.size() + " Matchs found");
        }
        model.addAttribute("matchs", matches);
        return "match";
    }

    @GetMapping("/new")
    public String createNewMatch(Model model){
        model.addAttribute("match", new MatchModel()); // model.addAttribute send backend data to frontend
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("players", playerRepository.findAll());
        return "match-form";
    }


    @PostMapping("/save")
    public String saveMatch(@ModelAttribute MatchModel p, RedirectAttributes model){
        if(p.getId() == null){
            // create
            matchRepository.save(p); // db insert into matchs table, returns void
            model.addAttribute("success", "Match created successfully");
        }else{
            Optional<MatchModel> opt =  matchRepository.findById(p.getId());
            if(opt.isPresent()){
                MatchModel p2 = opt.get();
                p2.setTeam1(p.getTeam1());
                p2.setTeam2(p.getTeam2());
                p2.setVenue(p.getVenue());
                p2.setDate(p.getDate());
                p2.setManOfTheMatch(p.getManOfTheMatch());
                p2.setWinner(p.getWinner());
                p2.setStatus(p.getStatus());
                p2.setTossWinner(p.getTossWinner());

                matchRepository.save(p2);
                model.addAttribute("success", "Match updated successfully");
            }
        }
        return "redirect:/match/";

    }

    @GetMapping("/edit/{id}")
    public String getMatchById(@PathVariable Long id, Model model){
        Optional<MatchModel> opt = matchRepository.findById(id);
        if(opt.isEmpty()){
            List<MatchModel> li = (List<MatchModel>) matchRepository.findAll();
            model.addAttribute("matchs", li);
            model.addAttribute("error", "No match found for with given ID: " + id);
            return "match";
        }
        MatchModel p = opt.get();
        model.addAttribute("match", p);
        model.addAttribute("success", "Match found");
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("players", playerRepository.findAll());
        return "match-form";
    }

    @DeleteMapping("/delete/{id}")
    public String removeMatchById(@PathVariable Long id, RedirectAttributes model){
        Optional<MatchModel> opt = matchRepository.findById(id);
        if(opt.isPresent()) {
            matchRepository.deleteById(id);
            model.addFlashAttribute("success", "Match deleted successfully");
        }else{
            model.addFlashAttribute("error", "Match not found for deletion");
        }

        return "redirect:/match/";

    }
}
