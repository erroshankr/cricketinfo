package com.cricket.info.services;

import com.cricket.info.exceptions.MatchNotFoundException;
import com.cricket.info.models.MatchModel;

import java.util.List;

public interface MatchService {

    MatchModel getMatchById(Long id) throws MatchNotFoundException;
    List<MatchModel> findAllMatches();
    void saveMatch(MatchModel match) throws Exception;
    void deleteMatch(Long id) throws Exception;
}
