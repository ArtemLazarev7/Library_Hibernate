package org.example.lazarev.repositories;

import org.example.lazarev.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByTitleStartingWith(String title);




}
