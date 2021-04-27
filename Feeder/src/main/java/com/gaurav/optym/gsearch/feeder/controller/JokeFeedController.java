package com.gaurav.optym.gsearch.feeder.controller;

import com.gaurav.optym.gsearch.feeder.domain.Joke;
import com.gaurav.optym.gsearch.feeder.service.JokeFeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jokes")
public class JokeFeedController {

    @Autowired
    private JokeFeederService jokeFeederService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Joke> list() {
        return jokeFeederService.list();
    }
}
