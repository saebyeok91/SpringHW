package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "userNo")
@ToString
@Entity
// mysql에서 했던 create table member를 하는 것
@Table(name = "member")
public class Member {
    @Id
    //Idnetity가 붙어있으면 user_no를 primary key로 사용하겠다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    //db query not null = NotBlank
    // @NotBlank
    // varchar(50) not null; 이랑 같음
    @Column(length = 50, nullable = false)
    private String userId;

    @Column(length = 100, nullable = false)
    private String userName;

    @Column(length = 300, nullable = false)
    private String userPw;

    @Column(length = 300, nullable = true)
    private String email;

    @Column(length = 16, nullable = false)
    private String job;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm")
    @CreationTimestamp
    private Date regDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm")
    @CreationTimestamp
    private Date updDate;

    // memberAuth에 있는 데이터도 확인할 수 있음.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_no")
    private List<MemberAuth> authList = new ArrayList<MemberAuth>();

    public void addAuth(MemberAuth auth) {
        authList.add(auth);
    }

    public void clearAuth() {
        authList.clear();
    }
}
