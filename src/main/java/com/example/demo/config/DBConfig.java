package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

// Configuration은 설정파일임을 명시하는 부분
// 몇몇 특정된 키워드들을 제외한 사용자 커스텀 클래스의 경우 설정파일임을 명시해줘야한다.
@Configuration
public class DBConfig {
    // Spring 프레임워크에서 관리하는 객체를 Bean이라고 한다.
    @Bean
    public DataSource dataSource() {
        // JPA를 사용하기 위한 목적으로 설정하는 것들
        // MySQL의 사용자, 비번, 구동 드라이버 위치 등을 지정하면 됨.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/private?serverTimezone=Asia/Seoul&useSSL=false"
        );
        dataSource.setUsername("bitai");
        dataSource.setPassword("456123");
        return dataSource;
    }
}
