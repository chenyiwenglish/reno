package com.chenyiwenglish.reno;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenyiwenglish.reno.dal.dao")
public class RenoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenoApplication.class, args);
    }

}
