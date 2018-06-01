package com.example.todolist.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="/todoapp")
public class MainController {

    @GetMapping(path="")
    public String redirectToTasks(){
        return "redirect:/todoapp/tasks";
    }

    @GetMapping(value = "/mylogin")
    public String getLoginPage() {
        return "loginpage";
    }


    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/todoapp/mylogin?logout";
    }

    @GetMapping(value = "/accessdenied")
    public String accessDeniedPage(HttpServletRequest request, Model model) {
        model.addAttribute("user", request.getRemoteUser());
        return "access_denied";
    }
}


