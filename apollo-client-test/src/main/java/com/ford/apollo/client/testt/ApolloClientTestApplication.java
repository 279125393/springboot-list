package com.ford.apollo.client.testt;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class ApolloClientTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloClientTestApplication.class, args);
    }

}
