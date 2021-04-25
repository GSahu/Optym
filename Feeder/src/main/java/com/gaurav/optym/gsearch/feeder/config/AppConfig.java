package com.gaurav.optym.gsearch.feeder.config;

import com.gaurav.optym.gsearch.feeder.source.OfficialJokeProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Bean definitions
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public OfficialJokeProvider officialJokeProvider() {
        return new OfficialJokeProvider();
    }


}
