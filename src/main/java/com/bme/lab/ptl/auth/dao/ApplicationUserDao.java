package com.bme.lab.ptl.auth.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserDao {
    Optional<UserDetails> selectApplicationUserByUsername(String username);
}