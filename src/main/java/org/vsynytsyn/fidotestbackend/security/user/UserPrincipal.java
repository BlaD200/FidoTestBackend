package org.vsynytsyn.fidotestbackend.security.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vsynytsyn.fidotestbackend.domain.entity.UserEntity;

import java.util.Collection;

public class UserPrincipal implements UserDetails {

    @Getter
    private final UserEntity userEntity;


    public UserPrincipal(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getRoles();
    }


    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }


    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
