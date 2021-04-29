package com.gaurav.optym.search.repository;

import com.gaurav.optym.search.domain.Joke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JokeRepository extends JpaRepository<Joke,Integer>,JokeRepositoryCustom {
}
