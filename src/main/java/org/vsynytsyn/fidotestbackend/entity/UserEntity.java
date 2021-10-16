package org.vsynytsyn.fidotestbackend.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "full_name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String fullName;

    @NaturalId
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}
