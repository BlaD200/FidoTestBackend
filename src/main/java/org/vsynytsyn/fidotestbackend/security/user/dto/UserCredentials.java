package org.vsynytsyn.fidotestbackend.security.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCredentials {
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 6, max = 100, message = "Password length must be between 6 and 100")
    private String password;
}
