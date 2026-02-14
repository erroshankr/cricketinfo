package com.cricket.info.auth;

import com.cricket.info.models.UserModel;
import com.cricket.info.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserModel user = userOpt.get();
        String[] rolesArray = user.getRoles().split(",");

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(rolesArray)
                .build();

    }
}
