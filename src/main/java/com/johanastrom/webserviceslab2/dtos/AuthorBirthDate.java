package com.johanastrom.webserviceslab2.dtos;

import lombok.Data;

import java.sql.Timestamp;

public record AuthorBirthDate(Timestamp birthDate) {
}
