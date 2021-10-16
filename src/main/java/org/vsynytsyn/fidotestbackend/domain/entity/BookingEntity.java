package org.vsynytsyn.fidotestbackend.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "bookings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"bookingId"})
@ToString(exclude = {"user", "room"})
public class BookingEntity {

    @Id
    @GeneratedValue
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "booking_starts_at", nullable = false)
    private LocalTime bookingStartsAt;

    @Column(name = "booking_ends_at", nullable = false)
    private LocalTime bookingEndsAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id", nullable=false)
    private RoomEntity room;
}
