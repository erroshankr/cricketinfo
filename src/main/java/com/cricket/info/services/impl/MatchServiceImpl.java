package com.cricket.info.services.impl;

import com.cricket.info.exceptions.MatchNotFoundException;
import com.cricket.info.models.MatchModel;
import com.cricket.info.repo.MatchRepository;
import com.cricket.info.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    // add & update
    @Override
    public void saveMatch(MatchModel match) throws Exception{
        if(match.getId() == null) {
            matchRepository.save(match);
        }else{
            Optional<MatchModel> opt = matchRepository.findById(match.getId());
            if(opt.isEmpty()){
                throw new MatchNotFoundException("Match not found with ID: " + match.getId());
            }
            MatchModel match2 = opt.get();
            match2.setTeam1(match.getTeam1());
            match2.setTeam2(match.getTeam2());
            match2.setVenue(match.getVenue());
            match2.setDate(match.getDate());
            match2.setManOfTheMatch(match.getManOfTheMatch());
            match2.setTossWinner(match.getTossWinner());
            match2.setWinner(match.getWinner());
            match2.setStatus(match.getStatus());
            matchRepository.save(match2);
        }
    }

    @Override
    public void deleteMatch(Long id) throws Exception {
        Optional<MatchModel> opt = matchRepository.findById(id);
        if(opt.isEmpty()){
            throw new MatchNotFoundException("Match not found for deletion with ID: " + id);
            /**
             * // Exception ex = new MatchNotFoundException();
             * ex.setMessage("Match not found for deletion with ID: " + id)
             */
        }
        matchRepository.deleteById(id);
    }
}
