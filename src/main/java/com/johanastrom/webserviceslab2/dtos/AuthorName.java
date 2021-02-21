package com.johanastrom.webserviceslab2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorName(@JsonProperty("firstName") String firstName,
                         @JsonProperty("lastName") String lastName) {
}
