package org.vsynytsyn.fidotestbackend.service;

import org.springframework.stereotype.Service;
import org.vsynytsyn.fidotestbackend.domain.dto.RoomDTO;
import org.vsynytsyn.fidotestbackend.domain.entity.RoomEntity;
import org.vsynytsyn.fidotestbackend.repository.RoomRepository;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;


    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public Optional<RoomDTO> getById(Long roomId) {
        Optional<RoomEntity> byId = roomRepository.findById(roomId);
        if (byId.isPresent()) {
            RoomEntity roomEntity = byId.get();
            return Optional.of(RoomDTO.builder()
                    .id(roomEntity.getId())
                    .name(roomEntity.getName())
                    .location(roomEntity.getLocation())
                    .seatsCount(roomEntity.getSeatsCount())
                    .build());
        } else return Optional.empty();
    }


    public RoomEntity createRoom(RoomDTO roomDTO) throws Exception {
        if (roomRepository.findByName(roomDTO.getName()).isPresent())
            throw new Exception("Room with specified name already exists");

        RoomEntity roomEntity = RoomEntity.builder()
                .name(roomDTO.getName())
                .location(roomDTO.getLocation())
                .seatsCount(roomDTO.getSeatsCount())
                .build();
        return roomRepository.save(roomEntity);
    }


    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }
}

