package org.vsynytsyn.fidotestbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long bookingId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
    private LocalDate bookingDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private LocalTime bookingStartsAt;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private LocalTime bookingEndsAt;

    private String roomName;

    private Long userId;
}
