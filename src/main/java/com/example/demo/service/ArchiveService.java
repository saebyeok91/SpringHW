package com.example.demo.service;

import com.example.demo.entity.Archive;
import java.util.List;

public interface ArchiveService {
    public void register(Archive board) throws Exception;
    public Archive read(Long boardNo) throws Exception;
    public void modify(Archive board) throws Exception;
    public void remove(Long boardNo) throws Exception;
    public List<Archive> list() throws Exception;
}
