package com.example.jpatest.controller;

import com.example.jpatest.model.Book;
import com.example.jpatest.model.BookRequestDto;
import com.example.jpatest.model.SaleBooks;
import com.example.jpatest.repository.BookRepository;
import com.example.jpatest.service.BookService;
import com.example.jpatest.service.SaleBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final SaleBookService saleBookService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody BookRequestDto bookRequestDto) {
        return new ResponseEntity<>(bookService.createBook(bookRequestDto.getName(),bookRequestDto.getContent()), HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody BookRequestDto bookRequestDto, @PathVariable("bookId") Long bookId) {
        bookService.updateBook(bookId,bookRequestDto.getName(),bookRequestDto.getContent());
        return new ResponseEntity<>(bookRepository.findById(bookId).orElseThrow(RuntimeException::new), HttpStatus.OK);
    }

    @PutMapping("/fake/{bookId}")
    public ResponseEntity<Book> fakeBook(@RequestBody BookRequestDto bookRequestDto, @PathVariable("bookId") Long bookId) {
        bookService.fake(bookId,bookRequestDto.getName(),bookRequestDto.getContent());
        return new ResponseEntity<>(bookRepository.findById(bookId).orElseThrow(RuntimeException::new), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable("bookId") Long bookId) {
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @PostMapping("/saleBook/{bookId}")
    public ResponseEntity<SaleBooks> createSaleBook(@PathVariable("bookId") Long bookId) {
        return new ResponseEntity<>(saleBookService.createSaleBook(bookId), HttpStatus.OK);
    }

    @GetMapping("/saleBook/{saleBookId}")
    public ResponseEntity<SaleBooks> getSaleBook(@PathVariable("saleBookId") Long saleBookId) {
        return new ResponseEntity<>(saleBookService.getSaleBooks(saleBookId), HttpStatus.OK);
    }

    @GetMapping("/saleBook")
    public ResponseEntity<List<SaleBooks>> getAllSaleBooks() {
        return new ResponseEntity<>(saleBookService.getAllSaleBooks(), HttpStatus.OK);
    }
}
