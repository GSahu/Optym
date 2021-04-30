package com.gaurav.optym.search.service;

import com.gaurav.optym.search.domain.Joke;
import com.gaurav.optym.search.model.Terms;
import com.gaurav.optym.search.repository.JokeRepository;
import com.gaurav.optym.search.repository.TermRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    public ResponseEntity<Object> downloadJoke(String term) throws IOException {
        List<Joke> csvDataList= new ArrayList<>();
        csvDataList= (List<Joke>) getJokesByTerm(term);
        FileWriter filewriter = null;
        try {

            StringBuilder filecontent = new StringBuilder("ID,Type, Setup, Punchline\n");
            for (Joke joke : csvDataList) {
                filecontent.append(joke.getId()).append(",")
                        .append(joke.getType()).append(",")
                        .append(joke.getSetup())
                        .append(joke.getPunchline())
                        .append("\n");
            }

            String filename = "Search/src/main/resources/jokecsvdata.csv";

            filewriter = new FileWriter(filename);
            filewriter.write(filecontent.toString());
            filewriter.flush();

            File file = new File(filename);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (filewriter != null)
                filewriter.close();
        }
    }


}
