package org.vsynytsyn.fidotestbackend.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.vsynytsyn.fidotestbackend.domain.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
public class UserDTO {
    private Long userId;
    @Email
    private String email;
    @Size(min = 6, max = 100, message = "Password length must be between 6 and 100")
    private String password;
    @Size(max = 50)
    private String fullName;
    private Set<Role> userRoles;
}
