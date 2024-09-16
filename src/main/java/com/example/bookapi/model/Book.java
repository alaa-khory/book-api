package com.example.bookapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "book_table")
//public record Book(
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        Long id,
//        String title,
//        String author,
//        Double price
//) {}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String author;
        private Double price;
}