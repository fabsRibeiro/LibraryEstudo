package com.library.libraryapi.model.repository;

import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.model.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query(value = " SELECT CASE WHEN ( count (l.id) > 0 ) THEN true ELSE false END " +
            " FROM Loan l WHERE l.book = :book " +
            " AND (l.returned is null OR l.returned is false)")
    boolean existsByBookAndNotReturned(@Param("book") Book book);

    @Query(value = " SELECT 1 FROM Loan l JOIN l.book AS b WHERE b.isbn = :isbn" +
            " OR l.customer = :customer ")
    Page<Loan> findByBookIsbnOrCustomer(
            @Param("isbn") String isbn,
            @Param("customer") String customer,
            Pageable pageRequest);

    Page<Loan> findByBook(Book book, Pageable pageable);


    @Query(" SELECT l FROM Loan l WHERE l.loanDate <= :threeDaysAgo " +
            " AND (l.returned is null OR l.returned is false)")
    List<Loan> findByLoanDateLessThenAndNotReturned(@Param("threeDaysAgo") LocalDate threeDaysAgo);
}
