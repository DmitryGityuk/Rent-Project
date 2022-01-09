package com.example.rentproject.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    OWNER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
