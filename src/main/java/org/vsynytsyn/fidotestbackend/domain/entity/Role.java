package org.vsynytsyn.fidotestbackend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(of = {"roleId"})
@ToString(of = {"roleId", "role"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @JsonIgnore
    private Long roleId;

    @Column(name = "role_name", nullable = false, unique = true)
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserEntity> users;


    @Override
    @JsonIgnore
    public String getAuthority() {
        return role;
    }
}
