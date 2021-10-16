package org.vsynytsyn.fidotestbackend.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vsynytsyn.fidotestbackend.domain.entity.UserEntity;
import org.vsynytsyn.fidotestbackend.repository.UserRepository;
import org.vsynytsyn.fidotestbackend.security.user.UserPrincipal;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty())
            throw new UsernameNotFoundException("No such email in database: " + email);
        return new UserPrincipal(userEntity.get());
    }
}
