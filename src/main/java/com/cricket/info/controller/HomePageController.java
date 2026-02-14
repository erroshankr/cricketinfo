package com.cricket.info.controller;

import com.cricket.info.exceptions.UserNotCreatedException;
import com.cricket.info.models.UserModel;
import com.cricket.info.services.UserService;
import com.cricket.info.validators.UserInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/user-create")
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
    public String getLoginPage(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout){
       if(error != null){
           model.addAttribute("error","Invalid username OR password");
       }
       if(logout != null){
           model.addAttribute("success", "You have been logged out successfully");
       }

       return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}

// signup--> login -> logout
// logout -> login Page
// signup --> login page