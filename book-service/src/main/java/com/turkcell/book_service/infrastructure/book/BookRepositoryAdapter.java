package com.turkcell.book_service.infrastructure.book;


import com.turkcell.book_service.domain.model.Book;
import com.turkcell.book_service.domain.model.BookId;
import com.turkcell.book_service.domain.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryAdapter implements BookRepository {

    private final JpaBookRepository jpaBookRepository;
    private final BookEntityMapper bookEntityMapper;

    public BookRepositoryAdapter(JpaBookRepository jpaBookRepository, BookEntityMapper bookEntityMapper) {
        this.jpaBookRepository = jpaBookRepository;
        this.bookEntityMapper = bookEntityMapper;
    }


    @Override
    public Book save(Book book) {
        // Book -jpaBookEntity mapping
        JpaBookEntity entity = bookEntityMapper.toEntity(book);

        // db ye kaydet
        entity = jpaBookRepository.save(entity);
        // kaydedilen yapıyı book olarak geri maple
        return bookEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Book> findById(BookId bookId) {
        return jpaBookRepository.findById(bookId.value()).map(bookEntityMapper::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll()
                .stream()
                .map(bookEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Book> findAllPaged(Integer pageIndex, Integer pageSize) {
        return jpaBookRepository.findAll(PageRequest.of(pageIndex, pageSize))
                .stream()
                .map(bookEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(BookId bookId) {
        jpaBookRepository.deleteById(bookId.value());
    }


}
