package com.gaurav.optym.search.repository;

import com.gaurav.optym.search.model.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Terms,Integer> {
}
