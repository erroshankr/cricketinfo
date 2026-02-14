package com.cricket.info.services.impl;

import com.cricket.info.enums.Role;
import com.cricket.info.exceptions.UserNotCreatedException;
import com.cricket.info.models.UserModel;
import com.cricket.info.repo.UserRepository;
import com.cricket.info.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserModel user) throws UserNotCreatedException {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // Roles are already set by the controller from form selection
            if (user.getRoles() == null || user.getRoles().isBlank()) {
                user.setRoles(Role.USER.getRoleName());
            }
            userRepository.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UserNotCreatedException("User not created in system");
        }
    }

}
