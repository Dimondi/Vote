package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class AuthController {

    ExecutorService threadExecutor = Executors.newCachedThreadPool();

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @GetMapping("/login")
    public String loginPage(){
        return "auth";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(name = "firstname") String firstname,
                               @RequestParam(name = "lastname") String lastname,
                               @RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "phone") String phone,
    Model model) throws ExecutionException, InterruptedException {
        User userForm = new User(username,new BCryptPasswordEncoder().encode(password),firstname,lastname,email,phone);
        User isExist = userRepository.findByUsername(username);
        Future<Boolean> futureCall1 = threadExecutor.submit(new EmailChecker(email));
        Future<Boolean> futureCall2 = threadExecutor.submit(new UsernamePasswordChecker(username,password));
        boolean result1 =  futureCall1.get();
        boolean result2 = futureCall2.get();
        if(isExist != null){
            model.addAttribute("error","User exists!");
            return "auth";
        }
        if(!result1){
            model.addAttribute("error","Invalid format of email!");
            return "auth";
        }
        if(!result2){
            model.addAttribute("error","Invalid format of username or password!");
            return "auth";
        }
        userForm.setRoles(Collections.singleton(roleRepository.findRoleByName("USER")));
        model.addAttribute("success","You have been registered!");
        userRepository.save(userForm);
        return "auth";
    }

    @GetMapping("/logout-success")
    public String logoutPage(){
        return "auth";
    }

    @GetMapping("/auth")
    public String auth(){
        return "auth";
    }
}
