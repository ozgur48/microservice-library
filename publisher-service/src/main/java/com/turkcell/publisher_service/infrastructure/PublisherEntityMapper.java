package com.turkcell.publisher_service.infrastructure;

import com.turkcell.publisher_service.domain.model.Address;
import com.turkcell.publisher_service.domain.model.Name;
import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.model.PublisherId;
import org.springframework.stereotype.Component;

@Component
public class PublisherEntityMapper{
    public JpaPublisherEntity toEntity(Publisher publisher){
        return new JpaPublisherEntity(
                publisher.id().value(),
                publisher.name().value(),
                publisher.address().value()
        );

    }
    public Publisher toDomain(JpaPublisherEntity jpaPublisherEntity){
        return Publisher.rehdyrate(
                new PublisherId(jpaPublisherEntity.id()),
                new Name(jpaPublisherEntity.name()),
                new Address(jpaPublisherEntity.address())
        );
    }
}
