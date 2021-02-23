package com.johanastrom.webserviceslab2.repositories;

import com.johanastrom.webserviceslab2.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
