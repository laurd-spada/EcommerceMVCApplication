package com.ecommerce.ecommercemvcapplication.controller;

import com.ecommerce.ecommercemvcapplication.model.UsersModel;
import com.ecommerce.ecommercemvcapplication.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcome(){
        return "index";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        UsersModel registeredUser = userService.registerUser(usersModel.getName(), usersModel.getEmail(), usersModel.getMobileNumber(), usersModel.getUsername(), usersModel.getPassword());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, HttpServletRequest httpServletRequest){
        System.out.println("login request: " + usersModel.getEmail() + " and " + usersModel.getPassword());
        UsersModel authenticated = userService.authentication(usersModel.getEmail(), usersModel.getPassword());

        if("admin@gmail.com".equals(usersModel.getEmail()) && "admin".equals(usersModel.getPassword())){
            System.out.println("good");
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("email", usersModel.getEmail());
            return "redirect:/admin";
        }
        else if(authenticated != null){
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("email", usersModel.getEmail());
            return "redirect:/customer";
        }else {
            return "/login_page";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("email");
        return "/login_page";
    }
}
