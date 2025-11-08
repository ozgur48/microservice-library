package com.turkcell.book_service.infrastructure.book;

import com.turkcell.book_service.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class BookEntityMapper {
    public JpaBookEntity toEntity(Book book){
        return new JpaBookEntity(
                book.bookId().value(),
                book.title().value(),
                book.year().value(),
                book.language().value(),
                book.totalCopies().copy(),
                book.isbn().value(),
                book.bookStatus(),
                book.availableCopies().copy(),
                book.authorId(),
                book.publisherId());


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
