package com.example.city.constant;

public final class SecurityConstants {

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String AUTHORITIES_KEY = "auth";
    public static final String USER = "ROLE_USER";
    public static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";
    public static final String ANONYMOUS_USER = "anonymoususer";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}