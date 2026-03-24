package com.example.SecureProxy.API.service;


import com.example.SecureProxy.API.entity.Users;

public interface userService {
    public Users validateUser(String username, String password);
    public Users registerUser(String username,String password,String role);
}
