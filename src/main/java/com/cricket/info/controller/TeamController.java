package com.cricket.info.controller;

import com.cricket.info.exceptions.TeamNotFoundException;
import com.cricket.info.models.TeamModel;
import com.cricket.info.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

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
        try {
            TeamModel t1 = teamService.getTeamById(id);
            model.addAttribute("team", t1);
            model.addAttribute("success", "Team found");
            return "team-form";
        } catch (TeamNotFoundException e) {
            model.addAttribute("teams", teamService.findAllTeams());
            model.addAttribute("error", "No team found for with given ID: " + id);
            return "team";
        }
    }

    @GetMapping("/find/{id}")
    public String findTeamById(@PathVariable Long id, Model model){
        try {
            TeamModel t1 = teamService.getTeamById(id);
            model.addAttribute("teams", List.of(t1));
            model.addAttribute("success", "Team found");
            return "team";
        } catch (TeamNotFoundException e) {
            model.addAttribute("teams", null);
            model.addAttribute("error", "No team found for with given ID: " + id);
            return "team";
        }

    }

    @GetMapping("/list")
    public String fetchTeams(Model model){
        List<TeamModel> teams =teamService.findAllTeams();
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
       try {
           teamService.deleteTeam(id);
           model.addFlashAttribute("success", "Team deleted successfully");
       }catch (Exception e){
           model.addFlashAttribute("error", e.getMessage());
       }
        return "redirect:/team/list";

    }

    @PostMapping("/save")
    public String saveTeam(@ModelAttribute TeamModel t, Model model){

        try {
            if (t.getId() == null) {
                // create
                teamService.addTeam(t); // db insert into teams table, returns void
                model.addAttribute("success", "Team created successfully");
            } else {
                teamService.updateTeam(t);
                model.addAttribute("success", "Team updated successfully");
            }
        } catch (TeamNotFoundException | RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("teams", teamService.findAllTeams());
        return "team";

    }

}
