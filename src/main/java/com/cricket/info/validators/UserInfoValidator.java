package com.cricket.info.validators;

import com.cricket.info.models.UserModel;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserInfoValidator implements DataValidator{

    @Override
    public List<String> validate(Object data) {
        UserModel user = (UserModel) data;
        List<String> errors = new ArrayList<>();

        if(StringUtils.isEmpty(user.getUsername())){
            errors.add("Username can not be null or empty");
        }
        if(user.getUsername() != null && user.getUsername().length() != 5){
            errors.add("Username must be of 5 characters");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            errors.add("Password can not be null or empty");
        }
        if(user.getPassword() != null && user.getPassword().length() <=5){
            errors.add("Password must be greater than 5 characters");
        }
        if(StringUtils.isEmpty(user.getEmail())){
            errors.add("Email can not be null or empty");
        }
        return errors;
    }
}
