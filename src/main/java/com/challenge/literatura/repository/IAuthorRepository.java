package com.challenge.literatura.repository;

import com.challenge.literatura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE :year BETWEEN a.anioNacimiento AND a.anioMuerte")
    List<Author> findYear(int year);
}
