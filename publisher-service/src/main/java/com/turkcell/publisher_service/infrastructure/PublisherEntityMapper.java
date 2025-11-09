package com.turkcell.publisher_service.infrastructure;

import com.turkcell.publisher_service.domain.model.Address;
import com.turkcell.publisher_service.domain.model.Name;
import com.turkcell.publisher_service.domain.model.Publisher;
import com.turkcell.publisher_service.domain.model.PublisherId;
import org.springframework.stereotype.Component;

@Component
public class PublisherEntityMapper{
    public JpaPublisherEntity toEntity(Publisher publisher){
        JpaPublisherEntity jpaPublisherEntity = new JpaPublisherEntity();
        jpaPublisherEntity.setId(publisher.id().value());
        jpaPublisherEntity.setName(publisher.name().value());
        jpaPublisherEntity.setAddress(publisher.address().value());
        return jpaPublisherEntity;
    }
    public Publisher toDomain(JpaPublisherEntity jpaPublisherEntity){
        return Publisher.rehdyrate(
                new PublisherId(jpaPublisherEntity.id()),
                new Name(jpaPublisherEntity.name()),
                new Address(jpaPublisherEntity.address())
        );
    }
}
