package org.vsynytsyn.fidotestbackend.security.user.dto;

import lombok.Builder;
import lombok.Data;
import org.vsynytsyn.fidotestbackend.domain.dto.UserDTO;

@Data
@Builder
public class AuthUserDTO {
    private String jwtToken;
    private UserDTO userDTO;
}
