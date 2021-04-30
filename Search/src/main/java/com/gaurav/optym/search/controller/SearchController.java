package com.gaurav.optym.search.controller;

import com.gaurav.optym.search.domain.Joke;
import com.gaurav.optym.search.model.Terms;
import com.gaurav.optym.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/getAllJokes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Joke> getAllJokes() {
        return searchService.getAllJokes();
    }

    @GetMapping(value = "/getJokesByTerm", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Joke> getJokesByTerm(@RequestParam(value = "term", defaultValue = "the") String term) {
        return searchService.getJokesByTerm(term);
    }

    @GetMapping(value = "/getCategories", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<String> getCategories() {
        return searchService.getCategories();
    }

    @GetMapping(value = "/getRecentSearchedTerms", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Terms> getRecentlySearchedTerms() {
        return searchService.getRecentlySearchedTerms();
    }

    @GetMapping(value = "/downloadJoke", produces = MediaType.ALL_VALUE)
    public ResponseEntity<Object> downloadJoke(@RequestParam(value = "term", defaultValue = "the") String term) throws IOException {
        return searchService.downloadJoke(term);
    }
}
