package com.example.jpatest.service;

import com.example.jpatest.model.Book;
import com.example.jpatest.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book createBook(String name, String content) {
        Book book = new Book();
        book.setName(name);
        book.setContent(content);

        bookRepository.save(book);

        Book findBook = bookRepository.findById(book.getId()).orElseThrow(RuntimeException::new);
        return findBook;
    }

    @Transactional
    public Book updateBook(Long id, String name, String content) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);

        if (!name.isEmpty()) {
            book.setName(name);
        }
        if (!content.isEmpty()) {
            book.setContent(content);
        }
        return book;
    }

    @Transactional
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void fake(Long id, String name, String content) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        fakeUpdate(book, name, content);
    }

    @Transactional
    public void fakeUpdate(Book book, String name, String content) {
        if (!name.isEmpty()) {
            book.setName(name);
        }
        if (!content.isEmpty()) {
            book.setContent(content);
        }
    }
}
