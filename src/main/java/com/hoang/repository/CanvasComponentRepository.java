package com.hoang.repository;

import com.hoang.component_classes.CanvasComponent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CanvasComponentRepository extends MongoRepository<CanvasComponent, String> {
    void deleteCanvasComponentByDateCreatedAfter(Date date);
}
