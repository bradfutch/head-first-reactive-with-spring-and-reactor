package io.spring.workshop.stockquotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
@EnableDiscoveryClient
public class StockQuotesApplication {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().permitAll();
        return http.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(StockQuotesApplication.class, args);
    }
}
