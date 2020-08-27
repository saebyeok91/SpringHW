package com.example.demo.security;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository repository;

    @Override
    // 요청받은 id와 pw가 존재하는지 검사
    public UserDetails loadUserByUsername(String username)
                                throws UsernameNotFoundException {
        log.info("userName: " + username);

        Member member = repository.findByUserId(username).get(0);

        log.info("member: " + member);

        // custom user로 리턴
        return member == null ? null : new CustomUser(member);
    }
}
