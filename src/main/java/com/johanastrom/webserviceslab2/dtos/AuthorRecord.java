package com.johanastrom.webserviceslab2.dtos;

import java.sql.Timestamp;

public record AuthorRecord(int id, String firstName, String lastName, Timestamp birthDate){

}
