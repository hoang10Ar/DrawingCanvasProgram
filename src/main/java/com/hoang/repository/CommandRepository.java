package com.hoang.repository;

import com.hoang.command.Command;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CommandRepository extends MongoRepository<Command, String> {
    void deleteCommandByDateCreatedAfter(Date date);
}
