package com.library.libraryapi.mode.repository;


import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.model.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir o livro na base com o isbn informado")
    public void returnTrueWhenIsbnExists(){
        //cenário
        String isbn = "123";
        Book book = Book.builder().title("As aventuras").author("Fulano").isbn(isbn).build();
        entityManager.persist(book);

        //execução
        boolean exists = bookRepository.existsByIsbn(isbn);

        //verificacao
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir o livro na base com o isbn informado")
    public void returnFalseWhenIsbnDoesntExists(){
        //cenário
        String isbn = "123";

        //execução
        boolean exists = bookRepository.existsByIsbn(isbn);

        //verificacao
        assertThat(exists).isFalse();
    }
}
