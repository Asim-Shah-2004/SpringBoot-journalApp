package net.journal.journalApp.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import net.journal.journalApp.entities.Journal;


public interface JournalEntryRepository extends MongoRepository<Journal,String> {
	
}
