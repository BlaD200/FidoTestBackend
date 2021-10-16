package org.vsynytsyn.fidotestbackend.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vsynytsyn.fidotestbackend.security.user.dto.AuthUserDTO;
import org.vsynytsyn.fidotestbackend.security.user.dto.UserCredentials;
import org.vsynytsyn.fidotestbackend.domain.dto.UserDTO;
import org.vsynytsyn.fidotestbackend.domain.entity.UserEntity;
import org.vsynytsyn.fidotestbackend.security.user.UserPrincipal;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @Transactional
    public AuthUserDTO login(UserCredentials userCredentials) throws AuthenticationException {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword())
        );
        UserPrincipal principal = (UserPrincipal) authenticate.getPrincipal();
        UserEntity userEntity = principal.getUserEntity();

        String token = tokenService.createToken(principal.getUsername());

        return AuthUserDTO.builder()
                .jwtToken(token)
                .userDTO(
                        UserDTO.builder()
                                .userId(userEntity.getId())
                                .email(userEntity.getEmail())
                                .password(userEntity.getPassword())
                                .fullName(userEntity.getFullName())
                                .userRoles(userEntity.getRoles())
                                .build()
                )
                .build();
    }
}
