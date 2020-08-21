package com.example.demo.repository;

import com.example.demo.entity.overlap;
import com.example.demo.entity.signIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardRepository {
    static final Logger log = LoggerFactory.getLogger(BoardRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean overlap(overlap info) throws Exception{
        log.info("Repository overlap()");

        String query = "select * from UserInfo where id=?";

        List<overlap> list = jdbcTemplate.query(query, new RowMapper<overlap>() {
            @Override
            public overlap mapRow(ResultSet rs, int rowNum) throws SQLException {
                overlap info = new overlap();
                info.setId(rs.getString("id"));
                return info;
            }
        }, info.getId());
        return list.isEmpty() ? true: false;
        // 조건 ? 조건이 참일: 거짓일때;
    }

    public boolean signIn(signIn info) throws Exception{
        log.info("Repository signIn()");

        // 1. Query를 만든다
        // select, insert, delete, update
        String query = "select * from UserInfo where id=? and pw=?";

        // 2. Query를 실행한다.
        // 3. (select 한정) 각 줄별로 해야할 행동을 정의
        List<signIn> list = jdbcTemplate.query(query, new RowMapper<signIn>(){
            @Override
            public signIn mapRow(ResultSet rs, int rowNum) throws SQLException {
                signIn info = new signIn();
                info.setId(rs.getString("id"));
                info.setPw(rs.getString("pw"));
                return info;
            }
        }, info.getId(), info.getPw());

        // 4. list가 비어있는지 판단하여 비어있으면 로그인 X
        //    list가 1개 있으면 로그인 O
        return list.isEmpty() ? false: true;
    }
}

    /* 실제로 db에 저장.
    public void create(overlap info) throws Exception{
        log.info("Repository create()");

        String query = "insert into UserInfo(" +
                "id, pw, nickname, email) values(?, ?, ?, ?)";
        jdbcTemplate.update(query, info.getId(), info.getPw(), info.getNickname(), info.getEmail());
    } */


