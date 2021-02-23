package com.johanastrom.webserviceslab2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public record AuthorPersonalData(@JsonProperty("firstName") String firstName,
                                 @JsonProperty("lastName") String lastName,
                                 @JsonProperty("birthDate")Timestamp birthDate) {
}
