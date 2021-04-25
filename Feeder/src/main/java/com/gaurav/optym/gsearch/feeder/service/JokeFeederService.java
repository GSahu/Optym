package com.gaurav.optym.gsearch.feeder.service;

import com.gaurav.optym.gsearch.feeder.domain.Joke;
import com.gaurav.optym.gsearch.feeder.repository.JokeRepository;
import com.gaurav.optym.gsearch.feeder.source.OfficialJokeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeFeederService {

    @Autowired
    private OfficialJokeProvider jokeProvider;

    @Autowired
    private JokeRepository jokeRepository;

    public void getFeedData() {
        Joke joke = jokeProvider.getData();
        save(joke);
    }

    public Iterable<Joke> list() {
        return jokeRepository.findAll();
    }

    public Joke save(Joke joke) {
        return jokeRepository.save(joke);
    }
}
