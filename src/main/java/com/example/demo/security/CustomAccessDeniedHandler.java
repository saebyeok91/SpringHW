package com.example.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req,
                       HttpServletResponse res,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        /* Error Handling & info */
        com.example.demo.security.ErrorInfoApi errorinfoApi = new com.example.demo.security.ErrorInfoApi();
        errorinfoApi.setMessage("Access Denied");

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(errorinfoApi);

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write(jsonString);
    }
}
