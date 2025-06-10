package net.journal.journalApp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="cache")
@Data
public class CacheEntity {
    private String key;
    private String value;
}
