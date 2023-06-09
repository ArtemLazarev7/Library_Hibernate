package org.example.lazarev.models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть длиной от 2 до 100 символов")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть длиной от 2 до 100 символов")
    @Column(name = "author")
    private String author;
    @Size(min = 1800,max=2023, message = "Год должен быть больше чем 1800 и меньше чем 2023")
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

    public Book() {

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getTakenAt() {
        return takenAt;
    }
    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
