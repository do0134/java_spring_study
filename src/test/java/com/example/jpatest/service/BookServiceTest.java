package com.example.jpatest.service;

import com.example.jpatest.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class BookServiceTest {

    @MockBean
    private BookService bookService;


    @Test
    public void 더티체킹_테스트() {
        String origin_title = "1번책";
        String origin_content = "1번책 내용";
        String update_title = "1번이 아니었던 책";
        String update_content = "비어있음";
        Book book = bookService.createBook(origin_title, origin_content);
        bookService.updateBook(book.getId(), update_title, update_content);
        Assertions.assertEquals(book.getName(), update_title);
        Assertions.assertEquals(book.getContent(), update_content);
    }
}
