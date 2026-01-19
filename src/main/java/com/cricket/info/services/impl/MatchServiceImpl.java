package com.cricket.info.services.impl;

import com.cricket.info.exceptions.MatchNotCreatedException;
import com.cricket.info.exceptions.MatchNotDeletedException;
import com.cricket.info.exceptions.MatchNotFoundException;
import com.cricket.info.exceptions.MatchNotUpdatedException;
import com.cricket.info.models.MatchModel;
import com.cricket.info.repo.MatchRepository;
import com.cricket.info.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public MatchModel getMatchById(Long id) throws MatchNotFoundException {
       Optional<MatchModel> opt = matchRepository.findById(id);
       if(opt.isEmpty()){
           throw new MatchNotFoundException("Match not found with ID: " + id);
       }
       return opt.get();
    }

    @Override
    public List<MatchModel> findAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public void addMatch(MatchModel match) throws MatchNotCreatedException {
       if(match.getTeam1() == match.getTeam2()){
           throw new MatchNotCreatedException("Team1 and Team2 cannot be same");
       }
      if(match.getManOfTheMatch().getTeam()  != match.getTeam1() || match.getManOfTheMatch().getTeam() != match.getTeam2()){
           throw new MatchNotCreatedException("Man of the Match should be from either of the team");
       }

       if(match.getWinner()  != match.getTeam1() || match.getWinner() != match.getTeam2()){
           throw new MatchNotCreatedException("Winner the Match should be from either of the team");
       }

       if(match.getTossWinner()  != match.getTeam1() || match.getTossWinner()!= match.getTeam2()){
        throw new MatchNotCreatedException("Toss-Winner the Match should be from either of the team");
       }

       if(match.getDate().isBefore(LocalDateTime.now())){
           throw new MatchNotCreatedException("Match date cannot be older than today");
       }

       matchRepository.save(match);

    }

    @Override
    public void updateMatch(MatchModel match) throws MatchNotUpdatedException {

    }

    @Override
    public void deleteMatch(Long id) throws MatchNotFoundException, MatchNotDeletedException {

    }
}
