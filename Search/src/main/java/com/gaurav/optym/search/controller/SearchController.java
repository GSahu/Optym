package com.gaurav.optym.search.controller;

import com.gaurav.optym.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/getAllJokes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Object> getAllJokes() {
        return searchService.getAllJokes();
    }
    @GetMapping(value = "/getJokesByTerm", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Object> getJokesByTerm(@RequestParam(value = "term", defaultValue = "beach") String term) {
        return searchService.getJokesByTerm(term);
    }
}
