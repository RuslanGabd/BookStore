package com.ruslan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication(scanBasePackages = {"com.ruslan.services", "com.ruslan.database.DAO"})
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
       var context = SpringApplication.run(ApplicationRunner.class, args);

    }
}
