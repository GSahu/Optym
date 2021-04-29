package com.gaurav.optym.search.service;

import com.gaurav.optym.search.domain.Joke;
import com.gaurav.optym.search.model.Terms;
import com.gaurav.optym.search.repository.JokeRepository;
import com.gaurav.optym.search.repository.TermRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchService {
    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private JokeRepository jokeRepository;
    @Autowired
    TermRepository termRepository;

    public Iterable<Joke> getAllJokes() {
        log.info(String.format("****** Fetching from table: %s ******", "Joke"));
        Iterable<Joke> result = jokeRepository.findAll();
        return result;
    }

    public Iterable<Joke> getJokesByTerm(String term) {
        log.info(String.format("****** Fetching Jokes by terms from table: %s ******", "Joke"));
        saveTerms(term);
        return jokeRepository.getJokeByTermLike(term);

    }

    public Iterable<String> getCategories() {
        log.info(String.format("****** Fetching categories from table: %s ******", "Jokes"));
        return jokeRepository.getTypes();
    }

    public Iterable<Terms> getRecentlySearchedTerms() {
        log.info(String.format("****** Fetching recently searched terms from table: %s ******", "Terms"));
        return termRepository.findAll();
    }

    public void saveTerms(String term) {
        List<Terms> terms = termRepository.findAll();
        Terms termObj = terms.stream().
                filter(t -> t.getTerm().equalsIgnoreCase(term))
                .findFirst().orElse(null);
        if (termObj == null) {
            termObj = new Terms();
            termObj.setTerm(term);
            termObj.setTermCount(1);
        } else {
            termObj.setTermCount(termObj.getTermCount() + 1);
        }
        log.info("Adding/Updating the term: "+term+" and count: "+ termObj.getTermCount()+" to repo");
        termRepository.save(termObj);
    }

}
