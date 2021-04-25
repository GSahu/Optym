package com.gaurav.optym.gsearch.feeder.repository;

import com.gaurav.optym.gsearch.feeder.domain.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<Joke,Long> {
}
