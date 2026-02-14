package com.cricket.info.services;

import com.cricket.info.exceptions.UserNotCreatedException;
import com.cricket.info.exceptions.UserNotFoundException;
import com.cricket.info.models.UserModel;

public interface UserService {

    void registerUser(UserModel user) throws UserNotCreatedException;
    void loginUser(String username, String password) throws UserNotFoundException;
    void logout(String username);
}
