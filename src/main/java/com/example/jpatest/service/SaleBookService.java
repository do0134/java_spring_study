package com.example.jpatest.service;

import com.example.jpatest.model.Book;
import com.example.jpatest.model.SaleBooks;
import com.example.jpatest.repository.SaleBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleBookService {
    private final SaleBookRepository saleBookRepository;
    private final BookService bookService;

    @Transactional
    public SaleBooks createSaleBook(Long bookId) {
        Book book = bookService.getBook(bookId);
        SaleBooks saleBooks = SaleBooks.of(book);
        saleBookRepository.save(saleBooks);
        return saleBooks;
    }

    public SaleBooks getSaleBooks(Long saleBookId) {
        SaleBooks saleBooks = saleBookRepository.findById(saleBookId).orElseThrow(RuntimeException::new);
        System.out.println(saleBooks);
        Book book = saleBooks.getBook();
        return SaleBooks.of(book);
    }

    public List<SaleBooks> getAllSaleBooks() {
        return saleBookRepository.findAll();
    }
}
