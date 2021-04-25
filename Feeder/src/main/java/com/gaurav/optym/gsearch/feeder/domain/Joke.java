package com.gaurav.optym.gsearch.feeder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Joke {
    @Id
    private Long id;
    private String type;
    private String setup;
    private String punchline;
}
