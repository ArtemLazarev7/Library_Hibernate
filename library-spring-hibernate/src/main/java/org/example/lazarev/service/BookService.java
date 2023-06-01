package org.example.lazarev.service;

import org.example.lazarev.models.Book;
import org.example.lazarev.models.Person;
import org.example.lazarev.repositories.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> showAll(boolean sortByYear) {
        if (sortByYear)
            return bookRepository.findAll(Sort.by("year"));
        else
            return bookRepository.findAll();
    }

    public List<Book> showAllPagination(int page, int booksPerPage, boolean sortByYear) {

        if (sortByYear)
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else
            return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }


    public Book showOne(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findByTittle(String query) {
        return bookRepository.findByTitleStartingWith(query);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Optional<Book> book = bookRepository.findById(id);

        updatedBook.setId(id);
        updatedBook.setPerson(book.get().getPerson());
        bookRepository.save(updatedBook);

    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {

        Optional<Book> book = bookRepository.findById(id);
        return Optional.ofNullable(book.get().getPerson());


    }

    @Transactional
    public void release(int id) {
        Optional<Book> book = bookRepository.findById(id);
        book.get().setPerson(null);
        book.get().setTakenAt(null);

    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        Optional<Book> book = bookRepository.findById(id);
        book.get().setPerson(selectedPerson);
        book.get().setTakenAt(new Date());

    }
}
