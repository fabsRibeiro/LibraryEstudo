package com.library.libraryapi.api.resource;

import com.library.libraryapi.api.dto.BookDTO;
import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO dto){
        Book entity =
                Book.builder()
                        .author(dto.getAuthor())
                        .title(dto.getTitle())
                        .isbn(dto.getIsbn())
                        .build();

        entity = service.save(entity);

        return BookDTO.builder()
                .id(entity.getId())
                .author(entity.getAuthor())
                .title(entity.getTitle())
                .isbn(entity.getIsbn())
                .build();
    }

}
