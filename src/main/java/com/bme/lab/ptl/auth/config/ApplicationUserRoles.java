package com.bme.lab.ptl.auth.config;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.bme.lab.ptl.auth.config.ApplicationUserPermissions.*;


public enum ApplicationUserRoles {

    CUSTOMER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ROUTE_READ, ROUTE_WRITE, ROUTE_DELETE, COMPANY_WRITE, COMPANY_READ, COMPANY_DELETE));

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }

}
