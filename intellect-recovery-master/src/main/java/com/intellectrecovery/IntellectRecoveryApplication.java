package com.intellectrecovery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/intellectrecovery/mapper")
public class IntellectRecoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntellectRecoveryApplication.class, args);
    }

}
