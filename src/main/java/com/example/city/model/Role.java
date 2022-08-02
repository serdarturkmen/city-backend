package com.example.city.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ALLOW_EDIT;

    public String getAuthority() {
        return name();
    }

}