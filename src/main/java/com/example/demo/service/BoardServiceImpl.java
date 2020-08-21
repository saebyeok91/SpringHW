package com.example.demo.service;

import com.example.demo.entity.overlap;
import com.example.demo.entity.signIn;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository repo;

    @Override
    public boolean overlap(overlap info) throws Exception {
        repo.create(info);
        return false;
    }

    @Override
    public boolean signIn(signIn info) throws Exception {
        return repo.signIn(info);
    }

}
