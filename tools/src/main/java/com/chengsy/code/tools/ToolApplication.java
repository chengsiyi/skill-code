package com.chengsy.code.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author chengsiyi
 * @date 2022/10/2 15:52
 */
@SpringBootApplication(scanBasePackages = {"com.chengsy"})
public class ToolApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ToolApplication.class, args);
    }
}
