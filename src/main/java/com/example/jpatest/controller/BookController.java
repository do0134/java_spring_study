package com.example.jpatest.controller;

import com.example.jpatest.model.Book;
import com.example.jpatest.model.BookRequestDto;
import com.example.jpatest.repository.BookRepository;
import com.example.jpatest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody BookRequestDto bookRequestDto) {
        return new ResponseEntity<>(bookService.createBook(bookRequestDto.getName(),bookRequestDto.getContent()), HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody BookRequestDto bookRequestDto, @PathVariable("bookId") Long bookId) {
        bookService.updateBook(bookId,bookRequestDto.getName(),bookRequestDto.getContent());
        return new ResponseEntity<>(bookRepository.findById(bookId).orElseThrow(RuntimeException::new), HttpStatus.OK);
    }
}
