/*
1. 회원가입
2. 로그인
3. 페이지네이션
4. DB 검색
5. 기타기능1(필수)
6. 기타기능2(선택) */

package com.example.demo.controller;

import com.example.demo.entity.overlap;
import com.example.demo.entity.signIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ItemManiaController {
    private static final Logger log = LoggerFactory.getLogger(ItemManiaController.class);

    @Autowired
    private BoardService service;

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody String json) throws Exception {
        log.info("signIn");
        ResponseEntity<String> result;

        ObjectMapper jsonMapper = new ObjectMapper();
        signIn data = jsonMapper.readValue(json, signIn.class);

        boolean signIn = service.signIn(data);

        if (signIn) {
            result = ResponseEntity.ok("ok");
        } else {
            result = ResponseEntity.ok("fail");
        }
        return result;
    }

    @GetMapping("/overlap")
    public ResponseEntity<String> overlap(@RequestBody String json) throws Exception {
        log.info("overlap");

        ObjectMapper jsonMapper = new ObjectMapper();
        overlap data = jsonMapper.readValue(json, overlap.class);

        boolean overlap = service.overlap(data);
        String restr;

        if (overlap) {
            restr = "사용 가능한 id입니다";
        } else {
            restr = "중복된 id입니다";
        }
        return new ResponseEntity<String>(restr, HttpStatus.OK);
        // return ResponseEntity.ok(restr);
    }
}

    /* post가 담기는 곳이 body로 따로 있음
    @PostMapping("/postRegister")
    public String postRegister(UserInfo info, Model model)
    // register.html에서  th:object="${info}"가 id, pw, nickname 등을 가지고 있음
    // 그 info를 받는 게 UserInfo info
            throws Exception {

        log.info("postRegister");
        // 동작이 됐는지 확인하기 위해서 콘솔에 한번 찍어줌

        service.register(info);
        // service에 있는 register를 호출해서 info에 넣어줌
        // repository가 db에 정보를 입력하는데 service가 repository를 호출하니까
        // service에 값을 보내줌
        return "success";
    }

    @PostMapping("/register")
    // index에 있는 th:action="@{register}"과 맞아야함.
    // method가 post면 GetMapping으로는 요청을 처리할 수 없음.
    public String register(Model model)
            throws Exception {
        log.info("register");

        model.addAttribute("info", new UserInfo());
        // object와 attributeName이 같아야함(object에 매핑을 하니까)
        // entity 객체를 사용

        return "register";
    }

    @GetMapping("/index")
    public String index(UserInfo info, Model model)
            throws Exception {
        log.info("index");

        model.addAttribute("msg", "index");

        return "index";
    }
} */
