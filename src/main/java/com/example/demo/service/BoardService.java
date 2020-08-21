package com.example.demo.service;

import com.example.demo.entity.overlap;
import com.example.demo.entity.signIn;

public interface BoardService {
    public boolean overlap(overlap info) throws  Exception;
    public boolean signIn(signIn info) throws Exception;
}
