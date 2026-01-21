package com.cricket.info.validators;

import com.cricket.info.models.TeamModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamDataValidator implements DataValidator{

    @Override
    public List<String> validate(Object data) {
        TeamModel team = (TeamModel) data;
        List<String> errors = new ArrayList<>();
        if (team.getName().length() > 3) {
            errors.add("Team name should not exceed 3 characters");
        }
       return errors;
    }
}
