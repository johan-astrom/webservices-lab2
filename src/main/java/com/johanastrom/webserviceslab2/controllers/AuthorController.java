package com.johanastrom.webserviceslab2.controllers;

import com.johanastrom.webserviceslab2.dtos.AuthorName;
import com.johanastrom.webserviceslab2.dtos.AuthorRecord;
import com.johanastrom.webserviceslab2.services.AuthorIntermediary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private AuthorIntermediary authorIntermediary;

    @Autowired
    public AuthorController(AuthorIntermediary authorIntermediary) {
        this.authorIntermediary = authorIntermediary;
    }

    @GetMapping("/authors")
    public List<AuthorRecord> getAllAuthors() {
        return authorIntermediary.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public AuthorRecord getOneAuthor(@PathVariable int id) {
        return authorIntermediary.getOneAuthor(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id <" + id + "> not found."));
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorRecord createAuthor(@RequestBody AuthorRecord authorRecord) {
        return authorIntermediary.createAuthor(authorRecord).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.CONFLICT, "Author already persisted to database."));
    }

    @PutMapping("/authors/{id}")
    public AuthorRecord replaceAuthor(@PathVariable int id, @RequestBody AuthorRecord authorRecord) {
        return authorIntermediary.replace(id, authorRecord).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id <" + id + "> not found."));
    }

    @PatchMapping("/authors/{id}")
    public AuthorRecord updateAuthor(@PathVariable int id, @RequestBody AuthorName authorName) {
        return authorIntermediary.update(id, authorName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id <" + id + "> not found."));
    }

    /*@PatchMapping("/authors/{id}")
    public AuthorRecord replaceAuthor(@PathVariable int id, @RequestBody AuthorBirthDate authorBirthDate){
        return authorIntermediary.update(id, authorBirthDate).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id <" + id + "> not found."));
    }*/

}
