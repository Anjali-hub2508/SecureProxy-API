package com.example.SecureProxy.API.service;

import com.example.SecureProxy.API.entity.Users;
import com.example.SecureProxy.API.repository.userRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    private userRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public userServiceImpl(userRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users validateUser(String username, String password) {
        Users users = userRepo.findByUsername(username).orElseThrow(()-> new RuntimeException("username not found"));
        System.out.println("row password "+ users.getPassword());
        System.out.println("RAW PASSWORD = "+password);
        System.out.println("MATCH RESULT = "+passwordEncoder.matches(password,users.getPassword()));
        if(!passwordEncoder.matches(password,users.getPassword())){
            throw new RuntimeException("password not match");
        }
        return users;
    }

    @Override
    public Users registerUser(String username,String password,String role){

        Users user = new Users();

        user.setUsername(username);

        // ⭐ VERY IMPORTANT — encode here
        user.setPassword(passwordEncoder.encode(password));

        user.setRole(role);

        return userRepo.save(user);
    }
}
