package com.example.demo.entity;

import java.sql.Timestamp;

public class itemMania {
    private int boardNo;
    private String id;
    private String pw;
    private String nickname;
    private String item;
    private int price;
    private Timestamp regDate;

    public int getBoardNo() {
        return boardNo;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getNickname() {
        return nickname;
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
}
