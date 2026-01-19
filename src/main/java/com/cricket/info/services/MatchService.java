package com.cricket.info.services;

import com.cricket.info.exceptions.MatchNotCreatedException;
import com.cricket.info.exceptions.MatchNotDeletedException;
import com.cricket.info.exceptions.MatchNotFoundException;
import com.cricket.info.exceptions.MatchNotUpdatedException;
import com.cricket.info.models.MatchModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MatchService {

    MatchModel getMatchById(Long id) throws MatchNotFoundException, MatchNotFoundException;
    List<MatchModel> findAllMatches();
    void addMatch(MatchModel match) throws MatchNotCreatedException;
    void updateMatch(MatchModel match) throws MatchNotUpdatedException;
    void deleteMatch(Long id) throws MatchNotFoundException, MatchNotDeletedException;
}
