package com.example.bookapi;

import com.example.bookapi.controller.BookController;
import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookApiApplicationTests {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book bookSample;
    private List<Book> booksList = new ArrayList<>();


    @BeforeEach
    private void setup(){
        MockitoAnnotations.openMocks(this);
        bookSample = new Book(1L, "Java 17 Features", "John Doe", 29.9);
        booksList.add(bookSample);
    }

    @Test
    void testGetAllBooks(){
        when(bookService.getAllBooks()).thenReturn(List.of(bookSample));

        List<Book> bookList = bookController.getAllBooks();
        assertThat(bookList).isNotEmpty();
        assertThat(bookList.size()).isEqualTo(1);
        assertThat(bookList.get(0).getTitle()).isEqualTo("Java 17 Features");

    }

    @Test
    void testGetBookByIdFound(){
        when(bookService.getBookById(1L)).thenReturn(Optional.of(bookSample));

        ResponseEntity<Book> bookResponseEntity = bookController.getBookById(1L);
        assertThat(bookResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(bookResponseEntity).isNotNull();
        assertThat(bookResponseEntity.getBody().getTitle()).isEqualTo("Java 17 Features");
    }

}
