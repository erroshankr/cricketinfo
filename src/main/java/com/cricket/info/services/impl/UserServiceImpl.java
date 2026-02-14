package com.cricket.info.services.impl;

import com.cricket.info.exceptions.UserNotCreatedException;
import com.cricket.info.exceptions.UserNotFoundException;
import com.cricket.info.models.UserModel;
import com.cricket.info.repo.UserRepository;
import com.cricket.info.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(UserModel user) throws UserNotCreatedException {
        try {
            userRepository.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UserNotCreatedException("User not created in system");
        }
    }

    @Override
    public void loginUser(String username, String password) throws UserNotFoundException {
        Optional<UserModel> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User with username: " + username + " not found");
        }
        UserModel user = userOpt.get();
        if(!user.getPassword().equals(password)){
            throw new UserNotFoundException("Please enter valid login details");
        }
    }

    @Override
    public void logout(String username) {

    }
}
