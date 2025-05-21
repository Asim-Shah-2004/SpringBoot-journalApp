package net.journal.journalApp.repository;

import net.journal.journalApp.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUsername(String username);  
    UserEntity deleteByUsername(String username);
} 
