package com.johanastrom.webserviceslab2.services;

import com.johanastrom.webserviceslab2.dtos.AuthorBirthDate;
import com.johanastrom.webserviceslab2.dtos.AuthorName;
import com.johanastrom.webserviceslab2.dtos.AuthorRecord;
import com.johanastrom.webserviceslab2.entities.Author;
import com.johanastrom.webserviceslab2.mappers.AuthorToDto;
import com.johanastrom.webserviceslab2.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorIntermediary implements IntermediaryService {

    private AuthorToDto authorToDto;
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorIntermediary(AuthorToDto authorToDto, AuthorRepository authorRepository) {
        this.authorToDto=authorToDto;
        this.authorRepository=authorRepository;

    }


    @Override
    public List<AuthorRecord> getAllAuthors(){
        return authorToDto.map(authorRepository.findAll());
    }

    @Override
    public Optional<AuthorRecord> getOneAuthor(int id){
        return authorToDto.map(authorRepository.findById(id));
    }

    @Override
    public Optional<AuthorRecord> createAuthor(AuthorRecord authorRecord){
        for (AuthorRecord author : getAllAuthors()){
            if (authorRecord.equals(author)){
                return Optional.empty();
            }
        }
        AuthorRecord createdAuthor = authorToDto.map(authorRepository.save(authorToDto.map(authorRecord)));
        return Optional.of(createdAuthor);

    }

    @Override
    public Optional<AuthorRecord> replace(int id, AuthorRecord authorRecord){
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()){
            Author updatedAuthor = author.get();
            updatedAuthor.setFirstName(authorRecord.firstName());
            updatedAuthor.setLastName(authorRecord.lastName());
            updatedAuthor.setBirthDate(authorRecord.birthDate());
            AuthorRecord updatedAuthorRecord = authorToDto.map(authorRepository.save(updatedAuthor));
            return Optional.of(updatedAuthorRecord);
        }
        return Optional.empty();
    }

    // TODO: 2021-02-21 Lägg till andra update
    @Override
    public Optional<AuthorRecord> update(int id, AuthorName authorName){
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()){
            Author updatedAuthor = author.get();
            if (authorName.firstName()!=null) {
                updatedAuthor.setFirstName(authorName.firstName());
            }
            if (authorName.lastName()!=null) {
                updatedAuthor.setLastName(authorName.lastName());
            }
            return Optional.of(authorToDto.map(authorRepository.save(updatedAuthor)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AuthorRecord> update(int id, AuthorBirthDate authorBirthDate){
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()){
            Author updatedAuthor = author.get();
            updatedAuthor.setBirthDate(authorBirthDate.birthDate());
            return Optional.of(authorToDto.map(authorRepository.save(updatedAuthor)));
        }
        //Gör koll och kasta exception i Controller class.
        return Optional.empty();
    }
}
