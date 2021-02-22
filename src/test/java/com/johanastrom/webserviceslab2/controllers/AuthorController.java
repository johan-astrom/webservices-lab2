package com.johanastrom.webserviceslab2.controllers;

import com.johanastrom.webserviceslab2.dtos.AuthorRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthorController {


    @Test
    public void getAllAuthorsShouldReturnListOfAuthorRecordObjects(){
        AuthorController controller = new AuthorController();
        List<AuthorRecord> authorList = controller.getAllAuthors();
        assertThat(authorList.size()).isGreaterThan(0);

    }

}
