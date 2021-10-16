package org.vsynytsyn.fidotestbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.vsynytsyn.fidotestbackend.domain.dto.BookingDTO;
import org.vsynytsyn.fidotestbackend.domain.entity.BookingEntity;
import org.vsynytsyn.fidotestbackend.domain.entity.RoomEntity;
import org.vsynytsyn.fidotestbackend.domain.entity.UserEntity;
import org.vsynytsyn.fidotestbackend.repository.BookingRepository;
import org.vsynytsyn.fidotestbackend.repository.RoomRepository;
import org.vsynytsyn.fidotestbackend.repository.UserRepository;
import org.vsynytsyn.fidotestbackend.validation.annotation.ConsistentDateParametersAll;
import org.vsynytsyn.fidotestbackend.validation.annotation.ConsistentDateParametersFuture;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;


    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }


    @ConsistentDateParametersFuture
    public BookingEntity createBooking(LocalDate date, LocalTime startsAt, LocalTime endsAt, String roomName, Long userId) throws Exception {
        Optional<RoomEntity> optionalRoomEntity = roomRepository.findByName(roomName);
        if (optionalRoomEntity.isEmpty())
            throw new Exception("No room with name " + roomName);
        RoomEntity roomEntity = optionalRoomEntity.get();

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty())
            throw new Exception("No user with id " + userId);
        UserEntity userEntity = userEntityOptional.get();

        if (bookingRepository.findBookingsForRoomInPeriod(roomName, date, startsAt, endsAt).size() > 0)
            throw new Exception("Room has already taken");


        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingDate(date)
                .bookingStartsAt(startsAt)
                .bookingEndsAt(endsAt)
                .room(roomEntity)
                .user(userEntity)
                .build();

        return bookingRepository.save(bookingEntity);
    }

    @ConsistentDateParametersAll
    public List<BookingDTO> getAllForPeriod(LocalDate date, LocalTime startsAt, LocalTime endsAt, String roomName) {
        return bookingRepository.findBookingsForRoomInPeriod(roomName, date, startsAt, endsAt)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public BookingDTO mapToDTO(BookingEntity entity){
        return BookingDTO.builder()
                .bookingId(entity.getBookingId())
                .bookingDate(entity.getBookingDate())
                .roomName(entity.getRoom().getName())
                .bookingStartsAt(entity.getBookingStartsAt())
                .bookingEndsAt(entity.getBookingEndsAt())
                .userId(entity.getUser().getId())
                .build();
    }
}
