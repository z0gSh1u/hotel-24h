package tech.zxuuu.hotel24h.util;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"tech.zxuuu.hotel24h.mapper"})
public class mybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(mybatisApplication.class, args);
    }
}
