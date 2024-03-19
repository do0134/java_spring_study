package com.example.jpatest.service;

import com.example.jpatest.model.Book;
import com.example.jpatest.model.SaleBooks;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class SaleBooksServiceTest {
    @MockBean
    private BookService bookService;

    @MockBean
    private SaleBookService saleBookService;

    @Test
    public void checkAllSalesBook() {
        bookService.createBook("1번책", "1번내용");

        saleBookService.createSaleBook(1L);
        saleBookService.createSaleBook(1L);

        List<SaleBooks> saleBooks = saleBookService.getAllSaleBooks();

        Assertions.assertThat(saleBooks.size()).isEqualTo(2);
    }
}
