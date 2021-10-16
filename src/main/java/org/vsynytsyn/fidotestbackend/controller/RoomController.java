package org.vsynytsyn.fidotestbackend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.vsynytsyn.fidotestbackend.controller.dto.ErrorMessage;
import org.vsynytsyn.fidotestbackend.domain.dto.RoomDTO;
import org.vsynytsyn.fidotestbackend.domain.entity.RoomEntity;
import org.vsynytsyn.fidotestbackend.service.RoomService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;


    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RoomDTO> getById(@PathVariable(name = "id") Long roomId){
        return ResponseEntity.of(roomService.getById(roomId));
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createRoom(@Valid @RequestBody RoomDTO roomDTO) {
        try {
            RoomEntity room = roomService.createRoom(roomDTO);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteRoom(
            @PathVariable(name = "id") Long roomId
    ) {
        try {
            roomService.deleteRoom(roomId);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
