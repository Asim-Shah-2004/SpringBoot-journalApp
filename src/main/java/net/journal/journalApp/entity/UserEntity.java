package net.journal.journalApp.entity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;

@Document(collection = "user")
@Data
public class UserEntity {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String username;

    @NotBlank(message = "password required")
    private String password;

    @DBRef
    private List<JournalEntity> journals = new ArrayList<>();
    
    private List<String> roles = new ArrayList<>();

    private Instant createdAt;
    private Instant updatedAt;
    
}
