package com.nucleo42.infrastructure.security;

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
