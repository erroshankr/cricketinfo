package com.cricket.info.services.impl;

import com.cricket.info.exceptions.UserNotCreatedException;
import com.cricket.info.models.UserModel;
import com.cricket.info.repo.UserRepository;
import com.cricket.info.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
