package com.test.cnss.myProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.test.cnss.myProject")
@EnableJpaRepositories(basePackages = { "com.test.cnss.myProject.repository" })
public class MyProjectApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MyProjectApplication.class, args);
    }
    
}
