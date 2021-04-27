package com.gaurav.optym.search.controller;

import com.gaurav.optym.search.domain.Joke;
import com.gaurav.optym.search.model.Terms;
import com.gaurav.optym.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/getAllJokes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Object> getAllJokes() {
        return searchService.getAllJokes();
    }

    /*@GetMapping(value = "/getJokesByTerm", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8082")
    public Iterable<Object> getJokesByTerm(@RequestParam(value = "term", defaultValue = "beach") String term) {
        return searchService.getJokesByTerm(term);
    }*/

    @GetMapping(value = "/getJokesById", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8082")
    public List<Joke> getJokesById(@RequestParam(value = "id", defaultValue = "0") String id) {
        return searchService.getJokesById(id);
    }

    @GetMapping(value = "/getJokesByTerm", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8082")
    public Iterable<Joke> getJokesByTerm(@RequestParam(value = "term", defaultValue = "the") String term) {
        return searchService.getJokesByTerm(term);
    }

    @GetMapping(value = "/getCategories", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8082")
    public Iterable<String> getCategories() {
        return searchService.getCategories();
    }

    @GetMapping(value = "/getRecentSearchedTerms", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8082")
    public Iterable<Terms> getRecentlySearchedTerms(){
        return searchService.getRecentlySearchedTerms();
    }
}
