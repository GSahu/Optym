package com.gaurav.optym.gsearch.feeder.service;

import com.gaurav.optym.gsearch.feeder.domain.Joke;
import com.gaurav.optym.gsearch.feeder.repository.JokeRepository;
import com.gaurav.optym.gsearch.feeder.source.OfficialJokeProvider;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class JokeFeederServiceTest {
    //@Autowired
    @Mock
    private JokeRepository jokeRepository;
    @Autowired
    private JokeFeederService jokeFeederService;

    @Test
    public void getFeedData() {
        OfficialJokeProvider jokeProvider = new OfficialJokeProvider();

        Joke joke = jokeProvider.getData();
        Assert.assertNotNull(joke);

    }

    @Test
    public void save() {
        OfficialJokeProvider jokeProvider = new OfficialJokeProvider();

        Joke joke = jokeProvider.getData();
        Assert.assertNotNull(joke);
        Mockito.when(jokeRepository.save(joke)).thenReturn(joke);
        Assert.assertEquals(joke, jokeFeederService.save(joke));
    }
}