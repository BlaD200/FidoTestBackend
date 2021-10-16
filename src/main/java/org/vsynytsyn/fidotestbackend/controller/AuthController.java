package org.vsynytsyn.fidotestbackend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vsynytsyn.fidotestbackend.security.user.dto.AuthUserDTO;
import org.vsynytsyn.fidotestbackend.security.user.dto.UserCredentials;
import org.vsynytsyn.fidotestbackend.domain.dto.UserDTO;
import org.vsynytsyn.fidotestbackend.security.service.AuthService;

import javax.validation.Valid;

import static org.vsynytsyn.fidotestbackend.security.jwt.JwtSecurityConstants.JWT_TOKEN_PREFIX;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserCredentials credentials){
        try {
            AuthUserDTO authUserDTO = authService.login(credentials);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + authUserDTO.getJwtToken())
                    .body(authUserDTO.getUserDTO());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
