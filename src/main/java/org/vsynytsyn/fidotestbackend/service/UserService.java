package org.vsynytsyn.fidotestbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vsynytsyn.fidotestbackend.domain.entity.UserEntity;
import org.vsynytsyn.fidotestbackend.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<UserEntity> getUserById(Long userId){
        return userRepository.findById(userId);
    }
}
