package com.bme.lab.ptl.auth.dao;

import com.bme.lab.ptl.auth.user.ApplicationUser;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.bme.lab.ptl.auth.config.ApplicationUserRoles.*;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/8/21
 */
@Repository
@RequiredArgsConstructor
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDetails> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<UserDetails> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser(
                        CUSTOMER.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "henry",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("admin"),
                        "admin",
                        true,
                        true,
                        true,
                        true
                )
        );
    }


}