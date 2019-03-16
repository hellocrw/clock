package com.gduf.clock;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("deprecation")
@SpringBootApplication
@MapperScan(value="com.gduf.clock.dao")
public class ClockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClockApplication.class, args);
    }

}
