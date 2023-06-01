package org.example.lazarev.service;

import org.example.lazarev.models.Book;
import org.example.lazarev.models.Person;
import org.example.lazarev.repositories.BookRepository;
import org.example.lazarev.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    private final BookRepository bookRepository;


    public PeopleService(PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleRepository = peopleRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Person> index() {
        return peopleRepository.findAll();
    }

    @Transactional
    public Person show(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);

    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {

        return peopleRepository.findByFullName(fullName);

    }

    public List<Book> getBooksByPersonId(int id) {

        Optional<Person> person=peopleRepository.findById(id);
        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().forEach(book->{
                long diffInMillies=Math.abs(book.getTakenAt().getTime() - new Date().getTime());

                if(diffInMillies>86400000)
                    book.setExpired(true);
            });
            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }

//        return bookRepository.findAllByPersonId(id);

    }
}
