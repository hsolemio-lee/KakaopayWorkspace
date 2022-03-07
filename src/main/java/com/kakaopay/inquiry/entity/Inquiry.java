package com.kakaopay.inquiry.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "inquiry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inquiry {
    @Id
    @GeneratedValue
    Long id;

    @Column(name="registerId", length = 50, nullable = false)
    String registerId;
    @Column(name="title", length = 200, nullable = false)
    String title;
    @Column(name="content", length = 5000, nullable = false)
    String content;
    @Column(name="status", length = 20, nullable = false)
    String status;
    @Column(name="managerId", length = 50)
    String managerId;
    @Column(name="createDate", nullable = false)
    Instant createDate;

    @OneToOne
    @JoinColumn(name = "replyId")
    Reply reply;
}
