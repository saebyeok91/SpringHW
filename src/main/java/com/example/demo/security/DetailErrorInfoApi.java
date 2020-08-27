package com.example.demo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DetailErrorInfoApi {
    private String target;
    private String message;
}
// Lombok과 생성자에 의해 동작하는 부분