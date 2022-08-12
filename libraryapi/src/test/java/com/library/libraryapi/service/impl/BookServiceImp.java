package com.library.libraryapi.service.impl;

import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.model.repository.BookRepository;
import com.library.libraryapi.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImp implements BookService {

    public BookRepository repository;

    public BookServiceImp(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
