package com.bme.lab.ptl.auth.config;

public enum ApplicationUserPermissions {
    ROUTE_READ("route:read"),
    ROUTE_WRITE("route:write"),
    ROUTE_DELETE("route:delete"),
    COMPANY_DELETE("company:delete"),
    COMPANY_WRITE("company:write"),
    COMPANY_READ("company:read");

    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
