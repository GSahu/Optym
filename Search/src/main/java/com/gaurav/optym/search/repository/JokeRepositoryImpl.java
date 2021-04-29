package com.gaurav.optym.search.repository;

import com.gaurav.optym.search.domain.Joke;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
@Transactional(readOnly = true)
public class JokeRepositoryImpl implements JokeRepositoryCustom{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Joke> getJokeByTermLike(String term) {
        String sql = "select * from Joke where lower(setup) LIKE ?";
        Query query=entityManager.createNativeQuery(sql,Joke.class);
        query.setParameter(1,"%"+term.toLowerCase()+"%");
        return query.getResultList();
    }

    @Override
    public List<Joke> getJokeByTermAndType(String term, String type) {
        String sql = "select * from Joke where lower(setup) LIKE ? and lower(type) LIKE ?";
        Query query=entityManager.createNativeQuery(sql);
        query.setParameter(1,"%"+term.toLowerCase()+"%");
        query.setParameter(2,"%"+type.toLowerCase()+"%");
        return query.getResultList();
    }

    @Override
    public List<String> getTypes() {
        String sql = "select distinct type from Joke";
        Query query=entityManager.createNativeQuery(sql);
        return query.getResultList();
    }
}
