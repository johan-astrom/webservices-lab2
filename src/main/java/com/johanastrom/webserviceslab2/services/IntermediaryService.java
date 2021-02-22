package com.johanastrom.webserviceslab2.services;

import com.johanastrom.webserviceslab2.dtos.AuthorPersonalData;
import com.johanastrom.webserviceslab2.dtos.AuthorRecord;

import java.util.List;
import java.util.Optional;

public interface IntermediaryService {
    List<AuthorRecord> getAllAuthors();

    Optional<AuthorRecord> getOneAuthor(int id);

    Optional<AuthorRecord> createAuthor(AuthorRecord authorRecord);

    Optional<AuthorRecord> replace(int id, AuthorRecord authorRecord);

    Optional<AuthorRecord> update(int id, AuthorPersonalData authorPersonalData);

}
