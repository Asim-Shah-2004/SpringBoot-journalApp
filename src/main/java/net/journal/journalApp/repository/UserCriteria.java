package net.journal.journalApp.repository;
import net.journal.journalApp.entity.UserEntity;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UserCriteria {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntity> getUsersForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("Email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("sentiment").is(true));
        return mongoTemplate.find(query, UserEntity.class);
    }  
}
