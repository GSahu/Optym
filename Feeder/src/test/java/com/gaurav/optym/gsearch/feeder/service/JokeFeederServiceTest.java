package com.gaurav.optym.gsearch.feeder.service;

import com.gaurav.optym.gsearch.feeder.domain.Joke;
import com.gaurav.optym.gsearch.feeder.repository.JokeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.gaurav.optym.gsearch.feeder.*")

public class JokeFeederServiceTest {
    private static final Logger log = LoggerFactory.getLogger(JokeFeederServiceTest.class);
    @Before
    public void init() throws Exception {
        log.info("startup");
        JokeFeederService jokeFeederServiceMock=mock(JokeFeederService.class);
        JokeRepository jokeRepositoryMock = mock(JokeRepository.class);
        Joke jokeMock = mock(Joke.class);
        whenNew(JokeFeederService.class).withNoArguments().thenReturn(jokeFeederServiceMock);
        whenNew(Joke.class).withNoArguments().thenReturn(jokeMock);
        log.info("Before tasks completed \n ******* Starting tests now...**********");

    }

    @Test
    public void getFeedData() {
        JokeFeederService jokeFeederService= new JokeFeederService();
        List<Joke> jokes = new ArrayList<>();
        jokes.add(new Joke());
        jokes.add(new Joke());
        jokes.add(new Joke());
        when(jokeFeederService.list()).thenReturn(jokes);

        Iterable<Joke> joke = jokeFeederService.list();
        Assert.assertNotNull(joke);

    }

}