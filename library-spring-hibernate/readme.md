# Library


A simple web project is implemented using Hibernate and Spring Data, which allows librarians
to register readers, give them
books and release books (after the reader returns
the book back to the library) (CRUD).Also added pagination and sorting, a search page, and checking for overdue books.

## Usage example:
1. Request for http://localhost:8080/books - displays the entire list of books
2. Request for http://localhost:8080/books?page=1&books_per_page=3 - displays a list of books with pagination of 3 books 2 pages.
3. Request for http://localhost:8080/books?sort_by_year=true adding sorting by year.
4. Request for http://localhost:8080/books/search the book search page by title.
5. If the book is overdue (10 days), it is highlighted in red.
