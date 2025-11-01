package com.turkcell.book_service.infrastructure.book;

import com.turkcell.book_service.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class BookEntityMapper {
    public JpaBookEntity toEntity(Book book){
        JpaBookEntity jpaBookEntity = new JpaBookEntity();
        jpaBookEntity.setId(book.bookId().value());
        jpaBookEntity.setTitle(book.title().value());
        jpaBookEntity.setLanguage(book.language().value());
        jpaBookEntity.setTotalCopies(book.totalCopies().copy());
        jpaBookEntity.setAvailableCopies(book.availableCopies().copy());
        jpaBookEntity.setYear(book.year().value());
        jpaBookEntity.setIsbn(book.isbn().value());
        jpaBookEntity.setBookStatus(book.bookStatus());

        return jpaBookEntity;
    }
    public Book toDomain(JpaBookEntity jpaBookEntity){

        return Book.rehydrate(
                new BookId(jpaBookEntity.id()),
                new Title(jpaBookEntity.title()),
                new Language(jpaBookEntity.language()),
                jpaBookEntity.bookStatus(),
                new Year(jpaBookEntity.year()),
                new TotalCopies(jpaBookEntity.totalCopies()),
                new AvailableCopies(jpaBookEntity.availableCopies()),
                new Isbn(jpaBookEntity.isbn()),
                jpaBookEntity.authorId(),
                jpaBookEntity.publisherId()
        );
    }
}
