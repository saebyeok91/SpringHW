package com.example.demo.service;

import com.example.demo.entity.Archive;
import com.example.demo.repository.ArchiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    static final Logger log = LoggerFactory.getLogger(ArchiveServiceImpl.class);

    @Autowired
    private ArchiveRepository eventRepo;

    @Override
    public void register(Archive board) throws Exception {
        eventRepo.create(board);
    }
    @Override
    public Archive read(Long boardNo) throws Exception {
        log.info("VueBoardService read(boardNo): " + boardNo);
        return eventRepo.read(boardNo);
    }
    @Override
    public void modify(Archive board) throws Exception {
        log.info("VueBoardService modify(board): " + board);
        eventRepo.update(board);
    }
    @Override
    public void remove(Long boardNo) throws Exception {
        eventRepo.delete(boardNo);
    }
    @Override
    public List<Archive> list() throws Exception {
        List<Archive> list = eventRepo.list();
        System.out.println(list);
        return list;
    }
}
