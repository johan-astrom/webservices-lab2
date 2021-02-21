package com.johanastrom.webserviceslab2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

public record AuthorBirthDate(@JsonProperty Timestamp birthDate) {
}
