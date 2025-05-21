package net.journal.journalApp.entity;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Document(collection = "journal")
@Data
public class JournalEntity {

    @Id
    private ObjectId id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    private Instant createdAt;
    private Instant updatedAt;

}
