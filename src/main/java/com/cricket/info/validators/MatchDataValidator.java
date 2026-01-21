package com.cricket.info.validators;

import com.cricket.info.models.MatchModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchDataValidator implements DataValidator{


    @Override
    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<>();
        MatchModel match = (MatchModel) data;
        if(match.getTeam1() == match.getTeam2()){
            errors.add("Team1 and Team2 cannot be same");
        }
        if(match.getManOfTheMatch().getTeam()  != match.getTeam1() || match.getManOfTheMatch().getTeam() != match.getTeam2()){
            errors.add("Man of the Match should be from either of the team");
        }

        if(match.getWinner()  != match.getTeam1() || match.getWinner() != match.getTeam2()){
            errors.add("Winner the Match should be from either of the team");
        }

        if(match.getTossWinner()  != match.getTeam1() || match.getTossWinner()!= match.getTeam2()){
            errors.add("Toss-Winner the Match should be from either of the team");
        }

        if(match.getDate().isBefore(LocalDateTime.now())){
            errors.add("Match date cannot be older than today");
        }

        return errors;
    }
}
