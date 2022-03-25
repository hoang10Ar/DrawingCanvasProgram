package com.hoang.command;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.util.Date;

@Getter
@Setter
@Component
@Scope("prototype")
@Document("commands")
public class Command {
    @Id
    private String id;
    private String content;
    private Date dateCreated;
}
