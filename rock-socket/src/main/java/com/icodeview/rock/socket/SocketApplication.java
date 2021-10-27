package com.icodeview.rock.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocketApplication.class,args);
    }
}
