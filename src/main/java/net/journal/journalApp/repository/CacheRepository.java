package net.journal.journalApp.repository;
import net.journal.journalApp.entity.CacheEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CacheRepository extends MongoRepository<CacheEntity, ObjectId> {
    
}
