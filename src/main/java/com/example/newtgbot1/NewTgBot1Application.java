package com.example.newtgbot1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class NewTgBot1Application {
    public static void main(String[] args) {
        SpringApplication.run(NewTgBot1Application.class, args);
        try{
            System.out.println();
            System.out.println();
        }catch (Exception e){
            log.error("Error occurred: " + e.getMessage());
        }
    }



}
