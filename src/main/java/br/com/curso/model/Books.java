package br.com.curso.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = true)
    private String author;

    @Column(name = "launch_date", nullable = false)
    private LocalDate launchDate;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "title", nullable = true)
    private String title;

    public Books() {
    }

    public Books(Long id, String author, LocalDate launchDate, Double price, String title) {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return getId().equals(books.getId()) && getLaunchDate().equals(books.getLaunchDate()) && getPrice().equals(books.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLaunchDate(), getPrice());
    }
}
