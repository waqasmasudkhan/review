package com.udacity.jwdnd.c1.review.controller;


import com.udacity.jwdnd.c1.review.model.Users;
import com.udacity.jwdnd.c1.review.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;


    public SignupController(UserService userService){
        this.userService=userService;

    }


    @GetMapping
    public String signupView(@ModelAttribute("user") Users user){
        return "signup";
    }

    @PostMapping
    public void addUser(@ModelAttribute("user") Users user, Model model){
        String signUpError=null;

        if(!userService.isUsernameAvailable(user.getUserName())){
            signUpError="The username is already being used";
        }
        else{
            userService.createUser(user);
        }


    }

}
