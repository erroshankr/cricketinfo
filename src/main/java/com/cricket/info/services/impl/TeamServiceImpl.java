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
    public void saveTeam(TeamModel team) throws Exception{
       if(team.getId() == null){
           teamRepository.save(team);
       }else{
           Optional<TeamModel> opt = teamRepository.findById(team.getId());
           if(opt.isEmpty()){
               throw new TeamNotFoundException("Team not found for given ID: " + team.getId());
           }
           TeamModel t2 = opt.get();
           t2.setName(team.getName());
           t2.setCountry(team.getCountry());
           teamRepository.save(t2);
       }
    }

    @Override
    public void deleteTeam(Long id) throws TeamNotFoundException{
        Optional<TeamModel> opt = teamRepository.findById(id);
        if(opt.isEmpty()) {
            throw new TeamNotFoundException("Team not found for deletion with ID: " + id);
        }
        teamRepository.deleteById(id);
    }
}
