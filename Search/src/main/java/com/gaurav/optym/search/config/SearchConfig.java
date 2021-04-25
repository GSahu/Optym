package com.gaurav.optym.search.config;

import com.gaurav.optym.search.service.SearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfig {

    @Bean
    SearchService searchService() {
        return new SearchService();
    }
}
