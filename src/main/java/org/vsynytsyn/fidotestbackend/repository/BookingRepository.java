package org.vsynytsyn.fidotestbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vsynytsyn.fidotestbackend.domain.entity.BookingEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(value = "SELECT b from BookingEntity b " +
            "WHERE (" +
            "  b.room.name = :roomName AND " +
            "  b.bookingDate = :date AND ( " +
            "       (b.bookingStartsAt <= :startsAt AND b.bookingEndsAt > :startsAt) " +
            "           OR " +
        "           (b.bookingStartsAt >= :startsAt AND b.bookingEndsAt <= :endsAt) " +
            "           OR " +
        "           (b.bookingStartsAt < :endsAt AND b.bookingEndsAt >= :endsAt) " +
            "   )" +
            ")"
    )
    List<BookingEntity> findBookingsForRoomInPeriod(
            @Param("roomName") String roomName,
            @Param("date") LocalDate date,
            @Param("startsAt") LocalTime startsAt, @Param("endsAt") LocalTime endsAt
    );
}