package com.chaoweifish.companyweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chaoweifish.companyweb.mapper")
public class CompanywebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanywebApplication.class, args);
    }

}
