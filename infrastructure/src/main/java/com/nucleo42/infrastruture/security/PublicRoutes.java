package com.nucleo42.infrastruture.security;

import lombok.Getter;

@Getter
public enum PublicRoutes {
    LOGIN("/login"),
    SIGNUP("/signup");

    private final String route;

    PublicRoutes(String route) {
        this.route = route;
    }
}
