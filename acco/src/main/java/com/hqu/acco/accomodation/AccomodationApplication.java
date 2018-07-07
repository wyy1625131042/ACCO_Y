package com.hqu.acco.accomodation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ComponentScan
public class AccomodationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccomodationApplication.class, args);
    }

}
