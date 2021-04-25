package com.gaurav.optym.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class SearchService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
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
                        return  res;
                    }
                });
        return result;
    }

    public Iterable<Object> getJokesByTerm(String term) {
        System.out.println(String.format("****** Fetching from table: %s ******", "Employees"));
        String sql = "select * from Joke where setup like '%"+term.toLowerCase()+"%'";
        /*MapSqlParameterSource namedParams= new MapSqlParameterSource();
        namedParams.addValue("term", term);*/

        Iterable<Object> result = jdbcTemplate.query(sql,
                new RowMapper<Object>() {
                    @Override
                    public Object mapRow(ResultSet rs, int i) throws SQLException {
                        String res = String.format("id:%s,type:%s,setup:%s,punchline:%s",
                                rs.getString("id"),
                                rs.getString("type"),
                                rs.getString("setup"),
                                rs.getString("punchline"));
                        System.out.println(res);
                        return  res;
                    }
                });
        return result;
    }
}
