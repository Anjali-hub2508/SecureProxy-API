package com.example.SecureProxy.API.repository;

import com.example.SecureProxy.API.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<Users,Integer> {

    Optional<Users> findByUsername(String username);
}
