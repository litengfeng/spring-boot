package com.fishfree;

import com.fishfree.jpa.config.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;


//@RestController
//@ComponentScan(basePackages = {"com.fishfree.jpa.controller"})
@SpringBootApplication(scanBasePackageClasses = {JpaConfiguration.class})
//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages="com.fishfree.jpa.repository")
//@EnableTransactionManagement
//@EntityScan(basePackages="com.fishfree.jpa.entity")
public class SpringBootDemoApplication {

    @RequestMapping("/")
    public String home() {
        System.out.println("home method");
        return "spring boot demo";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
