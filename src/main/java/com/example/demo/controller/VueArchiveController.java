package com.example.demo.controller;

import com.example.demo.entity.Archive;
import com.example.demo.entity.Member;
import com.example.demo.service.ArchiveService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDateTime;
import java.util.List;

@Log
@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class VueArchiveController {

    @Autowired
    private ArchiveService service;

    @GetMapping("/{boardNo}")
    public ResponseEntity<Archive> read(
            @PathVariable("boardNo") Long boardNo) throws Exception {
        log.info("read()");

        Archive board = service.read(boardNo);
        // System.out.println("VueBoardController: " + board);

        // return ResponseEntity.ok(board);
        // return ResponseEntity.status(HttpStatus.OK).body(board);
        return new ResponseEntity<Archive>(board, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Archive>> list() throws Exception {

        log.info("list()");

        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PostMapping("/genPost")
    public ResponseEntity<Archive> register(
            @Validated @RequestBody Archive board,
            UriComponentsBuilder uriBuilder) throws Exception {
        log.info("POST register()");

        service.register(board);

        log.info("register board.getBoardNo() = " + board.getBoardNo());

        /*
        URI resourceURI = uriBuilder.path("boards/{boardNo}")
                .buildAndExpand(board.getBoardNo())
                .encode()
                .toUri();
        return ResponseEntity.created(resourceURI).build();
         */

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> remove(
            @PathVariable("boardNo") Long boardNo) throws Exception {
        log.info("remove");

        service.remove(boardNo);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{boardNo}")
    public ResponseEntity<Archive> modify(
            @PathVariable("boardNo") Long boardNo,
            @Validated @RequestBody Archive board) throws Exception {
        log.info("Put - modify()");
        System.out.println(board);

        board.setBoardNo(boardNo);
        service.modify(board);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }
}
