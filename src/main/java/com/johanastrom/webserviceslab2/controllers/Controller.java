package com.johanastrom.webserviceslab2.controllers;

import com.johanastrom.webserviceslab2.dtos.AuthorRecord;
import com.johanastrom.webserviceslab2.entities.Author;
import com.johanastrom.webserviceslab2.repositories.AuthorRepository;
import com.johanastrom.webserviceslab2.services.AuthorIntermediary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Controller {

    private AuthorIntermediary authorIntermediary;

    @Autowired
    public Controller(AuthorIntermediary authorIntermediary) {
        this.authorIntermediary = authorIntermediary;
    }

    @GetMapping("/authors")
    public List<AuthorRecord> getAllAuthors(){
        return authorIntermediary.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public AuthorRecord getOneAuthor(@PathVariable int id){
        return authorIntermediary.getOneAuthor(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Author with id <" + id + "> not found."));
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorRecord createAuthor(@RequestBody AuthorRecord authorRecord){
        for (AuthorRecord persistedAuthor : authorIntermediary.getAllAuthors()) {
            if (persistedAuthor.equals(authorRecord)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Author already persisted to database.");
            }
        }
        return authorIntermediary.createAuthor(authorRecord);
    }

}
