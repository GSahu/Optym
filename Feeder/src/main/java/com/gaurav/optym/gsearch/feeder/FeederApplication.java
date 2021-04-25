package com.gaurav.optym.gsearch.feeder;

import com.gaurav.optym.gsearch.feeder.service.JokeFeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.h2.tools.Server;

import java.sql.SQLException;

@SpringBootApplication
@ComponentScan(basePackages = "com.gaurav.optym.gsearch.feeder.*")
@EnableAutoConfiguration
@EnableScheduling
public class FeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeederApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }
    @Autowired
    JokeFeederService jokeFeederService;

    @Scheduled(fixedDelay = 8000L)
    public void loadJokes() {
        jokeFeederService.getFeedData();
    }

}
