package org.vsynytsyn.fidotestbackend.security.jwt;

import java.time.Duration;

public class JwtSecurityConstants {
    public static final String SECRET = System.getenv("JWTSecret");
    public static final String HEADER_NAME = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final Long EXPIRATION_TIME = Duration.ofDays(2).getSeconds();
}
