package org.vsynytsyn.fidotestbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.vsynytsyn.fidotestbackend.controller.dto.ErrorMessage;
import org.vsynytsyn.fidotestbackend.domain.dto.UserDTO;
import org.vsynytsyn.fidotestbackend.domain.entity.UserEntity;
import org.vsynytsyn.fidotestbackend.security.user.UserPrincipal;
import org.vsynytsyn.fidotestbackend.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }


    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserEntity user = userService.createUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteUser(
            @PathVariable(name = "id") Long userId,
            @AuthenticationPrincipal UserPrincipal currentUser
    ) {
        try {
            userService.deleteUser(userId, currentUser);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(e.getMessage()));
        }
    }
}
