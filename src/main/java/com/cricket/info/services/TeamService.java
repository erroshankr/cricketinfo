package com.cricket.info.services;

import com.cricket.info.exceptions.TeamNotFoundException;
import com.cricket.info.models.TeamModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeamService {

    TeamModel getTeamById(Long id) throws TeamNotFoundException;
    List<TeamModel> findAllTeams();
    void addTeam(TeamModel team);
    void updateTeam(TeamModel team) throws TeamNotFoundException;
    void deleteTeam(Long id);
}
