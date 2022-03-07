package com.kakaopay.inquiry.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "reply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title", length = 200, nullable = false)
    String title;
    @Column(name="content", length = 5000, nullable = false)
    String content;
    @Column(name="registerId", length = 50, nullable = false)
    private String registerId;
    @Column(name="createDate", nullable = false)
    Instant createDate;
}
