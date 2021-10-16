package org.vsynytsyn.fidotestbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vsynytsyn.fidotestbackend.domain.dto.UserDTO;
import org.vsynytsyn.fidotestbackend.domain.entity.Role;
import org.vsynytsyn.fidotestbackend.domain.entity.UserEntity;
import org.vsynytsyn.fidotestbackend.repository.RoleRepository;
import org.vsynytsyn.fidotestbackend.repository.UserRepository;
import org.vsynytsyn.fidotestbackend.security.user.UserPrincipal;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    public Optional<UserEntity> getUserById(Long userId){
        return userRepository.findById(userId);
    }

    public UserEntity createUser(UserDTO userDTO) throws Exception {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent())
            throw new Exception("User with specified email already exists");

        Optional<Role> userRole = roleRepository.findByRole("USER");
        if (userRole.isEmpty())
            throw new Exception("No user role found in DB.");

        UserEntity userEntity = UserEntity.builder()
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .password(encoder.encode(userDTO.getPassword()))
                .roles(Collections.singleton(userRole.get()))
                .build();
        return userRepository.save(userEntity);
    }


    public void deleteUser(Long userId, UserPrincipal currentUser) throws Exception {
        if (userId.equals(currentUser.getUserEntity().getId())) {
            Role role = new Role();
            role.setRole("ADMIN");
            UserEntity userEntity = UserEntity.builder().roles(Collections.singleton(role)).build();
            if (userRepository.count(Example.of(userEntity)) < 2)
                throw new Exception("At least 1 admin should be in the system");
        }

        userRepository.deleteById(userId);
    }
}
