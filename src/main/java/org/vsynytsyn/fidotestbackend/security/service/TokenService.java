package org.vsynytsyn.fidotestbackend.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import org.vsynytsyn.fidotestbackend.security.jwt.JwtSecurityConstants;

import java.util.Date;

@Service
public class TokenService {

    public String createToken(String userEmail){
        return JWT.create()
                .withSubject(userEmail)
                .withExpiresAt(new Date(System.currentTimeMillis() * 1000 + JwtSecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JwtSecurityConstants.SECRET));
    }

    public String getTokenSubject(String jwtToken){
        return JWT.require(Algorithm.HMAC256(JwtSecurityConstants.SECRET))
                .build()
                .verify(jwtToken)
                .getSubject();
    }
}
