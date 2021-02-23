package com.johanastrom.webserviceslab2.controllers;

import com.johanastrom.webserviceslab2.dtos.AuthorPersonalData;
import com.johanastrom.webserviceslab2.dtos.AuthorRecord;
import com.johanastrom.webserviceslab2.services.AuthorIntermediary;
import com.johanastrom.webserviceslab2.services.IntermediaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private IntermediaryService intermediaryService;

    @Autowired
    public AuthorController(IntermediaryService intermediaryService) {
        this.intermediaryService = intermediaryService;
    }

    @GetMapping//("/authors")
    public List<AuthorRecord> getAllAuthors() {
        return intermediaryService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorRecord getOneAuthor(@PathVariable int id) {
        return intermediaryService.getOneAuthor(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id <" + id + "> not found."));
    }

    @PostMapping//("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorRecord createAuthor(@RequestBody AuthorRecord authorRecord) {
        return intermediaryService.createAuthor(authorRecord).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.CONFLICT, "Author already persisted to database."));
    }

    @PutMapping("/{id}")
    public AuthorRecord replaceAuthor(@PathVariable int id, @RequestBody AuthorRecord authorRecord) {
        return intermediaryService.replace(id, authorRecord).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id <" + id + "> not found."));
    }

    @PatchMapping("/{id}")
    public AuthorRecord updateAuthor(@PathVariable int id, @RequestBody AuthorPersonalData authorPersonalData) {
        return intermediaryService.update(id, authorPersonalData).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id <" + id + "> not found."));
    }

}
