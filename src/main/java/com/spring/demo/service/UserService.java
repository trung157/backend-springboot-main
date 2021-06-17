package com.spring.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.spring.demo.entity.User;

public interface UserService extends GeneralService<User>, UserDetailsService {
    Optional<User> findByEmail(String email);
}