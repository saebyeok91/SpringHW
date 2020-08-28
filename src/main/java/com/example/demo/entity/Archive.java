package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = "boardNo")
@ToString
@Entity
@Table(name = "archive_board")
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private long boardNo;

    @Column(length = 500, nullable = false)
    private String artist;

    @Column(length = 500, nullable = false)
    private String link;

    @Column(length = 500, nullable = false)
    private String social;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm")
    @CreationTimestamp
    private Date Date;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm")
    @CreationTimestamp
    private Date updDate;
}
