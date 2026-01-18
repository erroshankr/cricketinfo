package com.cricket.info.controller;

import com.cricket.info.models.TeamModel;
import com.cricket.info.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/")
    public String getTeams(RedirectAttributes model){
        return "redirect:/team/list";
    }

    @GetMapping("/new")
    public String createNewTeam(Model model){
        model.addAttribute("team", new TeamModel()); // model.addAttribute send backend data to frontend
        return "team-form";
    }

    @GetMapping("/edit/{id}")
    public String getTeamById(@PathVariable Long id, Model model){
        Optional<TeamModel> opt = teamRepository.findById(id);
        if(opt.isEmpty()){
            List<TeamModel> li = (List<TeamModel>) teamRepository.findAll();
            model.addAttribute("teams", li);
            model.addAttribute("error", "No team found for with given ID: " + id);
            return "team";
        }
        TeamModel p = opt.get();
        model.addAttribute("team", p);
        model.addAttribute("success", "Team found");
        return "team-form";
    }

    @GetMapping("/list")
    public String fetchTeams(Model model){
        List<TeamModel> teams = (List<TeamModel>) teamRepository.findAll();
        if(teams.isEmpty()){
            model.addAttribute("error", "No teams found");
        }else{
            model.addAttribute("success", teams.size() + " Teams found");
        }
        model.addAttribute("teams", teams);
        return "team";
    }

    @DeleteMapping("/delete/{id}")
    public String removeTeamById(@PathVariable Long id, RedirectAttributes model){
        Optional<TeamModel> opt = teamRepository.findById(id);
        if(opt.isPresent()) {
            teamRepository.deleteById(id);
            model.addFlashAttribute("success", "Team deleted successfully");
        }else{
            model.addFlashAttribute("error", "Team not found for deletion");
        }

        return "redirect:/team/list";

    }

    @PostMapping("/save")
    public String saveTeam(@ModelAttribute TeamModel t, RedirectAttributes model){
        if(t.getId() == null){
            // create
            teamRepository.save(t); // db insert into teams table, returns void
            model.addAttribute("success", "Team created successfully");
        }else{
            Optional<TeamModel> opt =  teamRepository.findById(t.getId());
            if(opt.isPresent()){
                TeamModel t2 = opt.get();
                t2.setName(t.getName());
                t2.setCountry(t.getCountry());

                teamRepository.save(t2);
                model.addAttribute("success", "Team updated successfully");
            }
        }
        return "redirect:/team/list";

    }

}
