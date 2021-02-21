package com.johanastrom.webserviceslab2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public record AuthorRecord(@JsonProperty int id,
                           @JsonProperty String firstName,
                           @JsonProperty String lastName,
                           @JsonProperty Timestamp birthDate){

}
