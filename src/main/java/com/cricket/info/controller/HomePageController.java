package com.cricket.info.controller;

import com.cricket.info.exceptions.UserNotCreatedException;
import com.cricket.info.exceptions.UserNotFoundException;
import com.cricket.info.models.UserModel;
import com.cricket.info.services.UserService;
import com.cricket.info.validators.UserInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoValidator userInfoValidator;

    @GetMapping("/home")
    public String getHome(Model model){
        return "home";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new UserModel());
        return "signup";  // signup.html
    }

    @PostMapping("/user/save")
    public String registerUser(Model model, @ModelAttribute UserModel userModel){
        List<String> errors =  userInfoValidator.validate(userModel);
        if(!errors.isEmpty()) {
          model.addAttribute("error", errors);
          model.addAttribute("user", new UserModel());
          return "signup";
        }

        try {
            userService.registerUser(userModel);
        } catch (UserNotCreatedException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new UserModel());
            return "signup";
        }
        model.addAttribute("success", "User with username " + userModel.getUsername() + " registered successfully");
        model.addAttribute("user", new UserModel());
        return "login";  // login.html
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new UserModel());
        return "login";  // login.html
    }

    @PostMapping("/user/auth")
    public String loginUser(Model model, @ModelAttribute UserModel userModel){

        try {
            userService.loginUser(userModel.getUsername(), userModel.getPassword());
            model.addAttribute("success", "User with username " + userModel.getUsername() + " logged in successfully");
        }catch (UserNotFoundException ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("user", new UserModel());
            return "login";
        }
        return "home";
    }

    @GetMapping("/logout")
    public String logoutUser(){
        return "redirect:/login";
    }
}

// signup--> login -> logout
// logout -> login Page
// signup --> login page