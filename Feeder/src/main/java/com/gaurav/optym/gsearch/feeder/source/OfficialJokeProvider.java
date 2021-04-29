package com.gaurav.optym.gsearch.feeder.source;

import com.gaurav.optym.gsearch.feeder.domain.Joke;
import com.gaurav.optym.gsearch.feeder.exception.DataProviderCommunicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class OfficialJokeProvider implements FeedProvider {
    private static final Logger log = LoggerFactory.getLogger(OfficialJokeProvider.class);

    @Value("${feed.jokes.url}")
    private String endpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Joke getData() {
        log.debug("Calling remote endpoint=" + endpoint);
        Joke fetchedData = null;
        try {

            fetchedData = restTemplate.getForObject(URI.create(endpoint), Joke.class);
            log.info(fetchedData.toString());

        } catch (RestClientException e) {
            log.error("Error while fetching data from " + endpoint + "\n" + e.getMessage());
            throw new DataProviderCommunicationException(e.getMessage(), e, "random joke provider", endpoint);
        }

        return fetchedData;
    }
}
