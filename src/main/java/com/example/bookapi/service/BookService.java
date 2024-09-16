package com.example.bookapi.service;

import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final List<Book> books = new ArrayList<>();
    private long nextId;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
//        return books;
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
//        return books.stream().filter(book -> book.id().equals(id)).findFirst();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
//        Book newBook = new Book(nextId++, book.title(), book.author(), book.price());
//        books.add(newBook);
//        return newBook;
    }

    public Optional<Book> updateBook(Long id, Book updatedBook){

        return bookRepository.findById(id).map(book -> {
           Book bookToSave = new Book(id, updatedBook.getTitle(),updatedBook.getAuthor(), updatedBook.getPrice());
           return bookRepository.save(bookToSave);
        });

//        return getBookById(id).map(book -> {
//            books.remove(book);
//            Book newBook = new Book(id, updatedBook.title(), updatedBook.author(), updatedBook.price());
//            books.add(newBook);
//            return newBook;
//        });
    }

    public boolean deleteBook(Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
//        return books.removeIf(book->book.id().equals(id));
    }



}
