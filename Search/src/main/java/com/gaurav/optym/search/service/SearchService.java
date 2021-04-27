package com.gaurav.optym.search.service;

import com.gaurav.optym.search.domain.Joke;
import com.gaurav.optym.search.model.Terms;
import com.gaurav.optym.search.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SearchService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    TermRepository termRepository;

    public Iterable<Object> getAllJokes() {
        System.out.println(String.format("****** Fetching from table: %s ******", "Employees"));
        Iterable<Object> result = jdbcTemplate.query("select * from Joke",
                new RowMapper<Object>() {
                    @Override
                    public Object mapRow(ResultSet rs, int i) throws SQLException {
                        String res = String.format("id:%s,type:%s,setup:%s,punchline:%s",
                                rs.getString("id"),
                                rs.getString("type"),
                                rs.getString("setup"),
                                rs.getString("punchline"));
                        System.out.println(res);
                        return res;
                    }
                });
        return result;
    }

    public Iterable<Joke> getJokesByTerm(String term) {
        System.out.println(String.format("****** Fetching from table: %s ******", "Employees"));
        String sql = "select * from Joke where setup like '%" + term.toLowerCase() + "%'";
        saveTerms(term);
        Iterable<Joke> result = jdbcTemplate.query(sql,
                new RowMapper<Joke>() {
                    @Override
                    public Joke mapRow(ResultSet rs, int i) throws SQLException {
                        return getJokeFromResultSet(rs);
                    }
                });
        return result;
    }

    public List<Joke> getJokesById(String id) {
        System.out.println(String.format("****** Fetching from table: %s ******", "Jokes"));
        String sql = "select * from Joke where id = " + Integer.valueOf(id) + "";

        List<Joke> result = jdbcTemplate.query(sql,
                new RowMapper<Joke>() {
                    @Override
                    public Joke mapRow(ResultSet rs, int i) throws SQLException {
                        return getJokeFromResultSet(rs);
                    }
                });
        return result;
    }

    private Joke getJokeFromResultSet(ResultSet rs) throws SQLException {
        Long id = Long.parseLong(rs.getString("id"));
        String type = rs.getString("type");
        String setup = rs.getString("setup");
        String punchline = rs.getString("punchline");
        System.out.println(rs.getString("punchline"));
        return new Joke(id, type, setup, punchline);

    }

    public Iterable<String> getCategories() {
        System.out.println(String.format("****** Fetching from table: %s ******", "Jokes"));
        String sql = "select distinct type as type from Joke ";
        Iterable<String> result = jdbcTemplate.query(sql,
                new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int i) throws SQLException {
                        return rs.getString("type");
                    }
                });
        return result;
    }

    public Iterable<Terms> getRecentlySearchedTerms() {
        System.out.println(String.format("****** Fetching from table: %s ******", "Terms"));
        String sql = "select * from terms order by term_count desc";
        List<Terms> result = jdbcTemplate.query(sql, new RowMapper<Terms>() {
            @Override
            public Terms mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String term = rs.getString("term");
                int count = rs.getInt("term_count");
                return new Terms(id, term, count);
            }
        });
        return result;
    }

    public void saveTerms(String term) {
        String sql = "select * from terms where term='" + term + "'";
        Terms termObj = null;
        List<Terms> res = jdbcTemplate.query(sql, new RowMapper<Terms>() {
            @Override
            public Terms mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String term = rs.getString("term");
                int count = rs.getInt("term_count");
                return new Terms(id, term, count);
            }
        });
        if (res != null && res.size() > 0) {
            termObj = res.get(0);
            int count = termObj.getTermCount();
            termObj.setTermCount(++count);
        } else {
            termObj = new Terms();
            termObj.setTerm(term);
            termObj.setTermCount(1);
        }
        termRepository.save(termObj);
    }

    public Terms save(Terms term) {
        return termRepository.save(term);
    }
}
