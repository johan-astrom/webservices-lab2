package com.johanastrom.webserviceslab2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorName(@JsonProperty String firstName,
                         @JsonProperty String lastName) {
}
