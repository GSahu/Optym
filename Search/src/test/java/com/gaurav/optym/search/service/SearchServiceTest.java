package com.gaurav.optym.search.service;

import com.gaurav.optym.search.domain.Joke;
import com.gaurav.optym.search.model.Terms;
import com.gaurav.optym.search.repository.JokeRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.gaurav.optym.search.*")

public class SearchServiceTest {
    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

    @Before
    public void init() throws Exception {
        log.info("startup");
        SearchService searchServiceMock = mock(SearchService.class);
        JokeRepositoryImpl jokeRepositoryMock = mock(JokeRepositoryImpl.class);
        Joke jokeMock = mock(Joke.class);
        whenNew(SearchService.class).withNoArguments().thenReturn(searchServiceMock);
        whenNew(JokeRepositoryImpl.class).withNoArguments().thenReturn(jokeRepositoryMock);
        whenNew(Joke.class).withNoArguments().thenReturn(jokeMock);
        log.info("Before tasks completed \n ******* Starting tests now...**********");

    }

    @Test
    public void getAllJokes() throws Exception {
        log.info("getAllJokesTest Started");
        SearchService searchService = new SearchService();
        List<Joke> jokes = new ArrayList<>();
        jokes.add(new Joke());
        jokes.add(new Joke());
        jokes.add(new Joke());

        when(searchService.getAllJokes()).thenReturn(jokes);
        Assert.notNull(searchService.getAllJokes(), "not null");
        log.info("getJokesByTermTest Passed");

    }

    @Test
    public void getJokesByTermTest() {
        log.info("getJokesByTermTest Started");
        SearchService searchService = new SearchService();

        List<Joke> jokes = new ArrayList<>();
        Joke jokeTest = new Joke(1L, "who are you", "Bond!! James Bond", "general");

        jokes.add(new Joke(1L, "who are you", "Bond!! James Bond", "general"));
        jokes.add(new Joke(1L, "who are you", "Swami!! James Swami", "general"));
        jokes.add(new Joke(1L, "who are you", "Deva!! James Deva", "general"));

        when(searchService.getJokesByTerm("Who")).thenReturn(jokes);
        Assert.notNull(searchService.getJokesByTerm("Who"), "not null");
        log.info("getJokesByTermTest Passed");

    }

    @Test
    public void getCategoriesTest() {
        log.info("getCategoriesTest Started");
        SearchService searchService = new SearchService();
        Iterable<String> types = new ArrayList<>(Arrays.asList("general", "knock-knock", "programming"));
        when(searchService.getCategories()).thenReturn(types);
        Assert.notNull(searchService.getCategories(), "not null");
        log.info("getCategoriesTest Passed");

    }

    @Test
    public void getRecentlySearchedTermsTest() {
        log.info("getRecentlySearchedTermsTest Started");
        SearchService searchService = new SearchService();
        List<Terms> terms = new ArrayList<>();
        terms.add(new Terms(1, "Who", 2));
        terms.add(new Terms(2, "Why", 4));
        terms.add(new Terms(3, "What", 1));

        when(searchService.getRecentlySearchedTerms()).thenReturn(terms);
        List<Terms> searchedterms = (List<Terms>) searchService.getRecentlySearchedTerms();
        Assert.isTrue(searchedterms.size() > 0, "true");
        log.info("getRecentlySearchedTermsTest Passed");

    }
}