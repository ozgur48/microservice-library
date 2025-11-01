package com.turkcell.book_service.domain.repository;


import com.turkcell.book_service.domain.model.Book;
import com.turkcell.book_service.domain.model.BookId;

import java.util.List;
import java.util.Optional;


public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(BookId bookId);
    List<Book> findAll();
    List<Book> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(BookId bookId);
}
