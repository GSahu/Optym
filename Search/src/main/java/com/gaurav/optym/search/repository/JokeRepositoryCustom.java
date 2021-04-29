package com.gaurav.optym.search.repository;

import com.gaurav.optym.search.domain.Joke;

import java.util.List;

public interface JokeRepositoryCustom {
    List<Joke> getJokeByTermLike(String term);

    List<Joke> getJokeByTermAndType(String term, String type);

    List<String> getTypes();
}
