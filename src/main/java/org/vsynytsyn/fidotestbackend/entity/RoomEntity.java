package org.vsynytsyn.fidotestbackend.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(256)")
    private String location;

    @Column(name = "seats_count", nullable = false)
    private Integer seatsCount;
}
