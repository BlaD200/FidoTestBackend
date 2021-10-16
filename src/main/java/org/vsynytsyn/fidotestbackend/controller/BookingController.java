package org.vsynytsyn.fidotestbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vsynytsyn.fidotestbackend.controller.dto.ErrorMessage;
import org.vsynytsyn.fidotestbackend.domain.dto.BookingDTO;
import org.vsynytsyn.fidotestbackend.domain.entity.BookingEntity;
import org.vsynytsyn.fidotestbackend.service.BookingService;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@Validated
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("/all")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Object> getAllForPeriod(@RequestBody BookingDTO bookingDTO) {
        try {
            List<BookingDTO> allForPeriod = bookingService.getAllForPeriod(
                    bookingDTO.getBookingDate(),
                    bookingDTO.getBookingStartsAt(), bookingDTO.getBookingEndsAt(),
                    bookingDTO.getRoomName()
            );
            return ResponseEntity.ok(allForPeriod);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
        }

    }


    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Object> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            BookingEntity booking = bookingService.createBooking(
                    bookingDTO.getBookingDate(),
                    bookingDTO.getBookingStartsAt(), bookingDTO.getBookingEndsAt(),
                    bookingDTO.getRoomName(), bookingDTO.getUserId()
            );
            return ResponseEntity.ok(bookingService.mapToDTO(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(e.getMessage()));
        }
    }
}
