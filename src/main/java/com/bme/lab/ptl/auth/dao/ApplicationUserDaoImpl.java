package com.bme.lab.ptl.auth.service;

import com.bme.lab.ptl.auth.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/8/21
 */
public class ApplicationUserService implements ApplicationUserRepository {

    @Override
    public Optional<UserDetails> selectApplicationUserByUsername(String username) {
        return Optional.empty();
    }
}
