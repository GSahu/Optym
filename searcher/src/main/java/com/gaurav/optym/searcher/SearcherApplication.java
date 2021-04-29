package com.gaurav.optym.searcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearcherApplication {

    private static final Logger log = LoggerFactory.getLogger(SearcherApplication.class);

    public static void main(String[] args) {
        log.info("Starting Searcher UI service");
        SpringApplication.run(SearcherApplication.class, args);
    }

}
