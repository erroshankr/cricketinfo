package com.cricket.info.services.impl;

import com.cricket.info.exceptions.TeamNotFoundException;
import com.cricket.info.models.TeamModel;
import com.cricket.info.repo.TeamRepository;
import com.cricket.info.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamModel getTeamById(Long id) throws TeamNotFoundException {
        Optional<TeamModel> opt = teamRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }else{
            throw new TeamNotFoundException("Team not found for given ID: " + id);
        }
    }

    @Override
    public List<TeamModel> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public void addTeam(TeamModel team) {
        try {
            if(team.getName().length() > 3){
                throw new RuntimeException("Team name should be more than 3 characters");
            }
            team.setName(team.getName().toUpperCase());
            teamRepository.save(team);
        }catch (Exception e){
            throw new RuntimeException("Error while adding team" + e.getMessage());
        }
    }

    @Override
    public void updateTeam(TeamModel team) throws TeamNotFoundException{
        Optional<TeamModel> opt = teamRepository.findById(team.getId());
        if (opt.isPresent()) {
            TeamModel t2 = opt.get();
            t2.setName(team.getName());
            t2.setCountry(team.getCountry());

            teamRepository.save(t2);
        }else{
            throw new TeamNotFoundException("Team not found for given ID: " + team.getId());
        }
    }

    @Override
    public void deleteTeam(Long id) {
       try{
           teamRepository.deleteById(id);
       }catch (Exception e){
           throw new RuntimeException("Error while deleting team: " + e.getMessage());
       }
    }
}
