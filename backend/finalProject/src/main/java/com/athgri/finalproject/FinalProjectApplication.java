package com.athgri.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.athgri.finalproject.utilities.Log.*;

@SpringBootApplication
@PropertySource("classpath:custom.properties")
public class FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);

        logInfo("Main App", "Service started");
    }
}
