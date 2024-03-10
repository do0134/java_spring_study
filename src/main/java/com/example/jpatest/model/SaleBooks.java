package com.example.jpatest.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class SaleBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "price")
    private Long price = 1000L;

    @Column(name = "stock")
    private Long stock = 1000L;

    public static SaleBooks of(Book book) {
        SaleBooks saleBooks = new SaleBooks();
        saleBooks.setBook(book);
        return saleBooks;
    }
}
