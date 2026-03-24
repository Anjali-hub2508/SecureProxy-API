package com.example.SecureProxy.API.controller;

import com.example.SecureProxy.API.config.JwtUtil;
import com.example.SecureProxy.API.entity.RegisterRequest;
import com.example.SecureProxy.API.entity.Users;
import com.example.SecureProxy.API.service.userService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

   private userService userService;
   private JwtUtil jwtUtil;


    public LoginController(userService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;

    }

    @PostMapping("/register")
    public Users register(@RequestBody RegisterRequest request){

        return userService.registerUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String,String> req) {

        Users users = userService.validateUser(
                req.get("username"),
                req.get("password")
        );

        String token = jwtUtil.generateToken(
                users.getUsername(),
                users.getRole()
        );
        return Map.of("token",token);
    }
}
