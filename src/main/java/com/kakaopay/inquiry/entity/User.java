package com.kakaopay.inquiry.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name="id", length = 50)
    private String id;

    @Column(name="authCode", length=500, nullable = false)
    private String authCode;
    @Column(name="name", length=100, nullable = false)
    private String name;
    @Column(name="createDate", nullable = false)
    private Instant createDate;
    @Column(name="role", length=100, nullable = false)
    private String role;
}
