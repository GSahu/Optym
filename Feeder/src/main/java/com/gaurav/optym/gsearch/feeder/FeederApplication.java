package com.gaurav.optym.gsearch.feeder;

import com.gaurav.optym.gsearch.feeder.service.JokeFeederService;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;

@SpringBootApplication
@ComponentScan(basePackages = "com.gaurav.optym.gsearch.feeder.*")
@EnableAutoConfiguration
@EnableScheduling
public class FeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeederApplication.class, args);
    }
    private static final Logger log = LoggerFactory.getLogger(FeederApplication.class);

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        log.info("Starting Feeder server Db instance");
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }
    @Autowired
    JokeFeederService jokeFeederService;

    @Scheduled(fixedDelay = 8000L)
    public void loadJokes() {
        log.info("Loading new joke");
        jokeFeederService.getFeedData();
    }

}
