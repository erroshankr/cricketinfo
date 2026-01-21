package com.cricket.info.services;

import com.cricket.info.exceptions.TeamNotFoundException;
import com.cricket.info.models.TeamModel;

import java.util.List;


public interface TeamService {

    TeamModel getTeamById(Long id) throws TeamNotFoundException;
    List<TeamModel> findAllTeams();
    void saveTeam(TeamModel team) throws Exception;
    void deleteTeam(Long id) throws TeamNotFoundException;
}
