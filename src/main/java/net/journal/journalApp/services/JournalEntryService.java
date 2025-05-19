package net.journal.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.journal.journalApp.entity.JournalEntity;
import net.journal.journalApp.repository.JournalEntryRepository;
import java.util.List;
import java.time.Instant;
import org.bson.types.ObjectId;

@Component
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntity createJournalEntry(JournalEntity journalEntry) {
        Instant now = Instant.now();
        journalEntry.setCreatedAt(now);
        journalEntry.setUpdatedAt(now);
        return journalEntryRepository.save(journalEntry);
    }

    public JournalEntity getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }
    
    public JournalEntity updateJournalEntry(ObjectId id, JournalEntity journalEntry) {
        if (journalEntryRepository.existsById(id)) {
            JournalEntity existingEntry = journalEntryRepository.findById(id).orElse(null);
            if (existingEntry != null) {
                journalEntry.setId(id);
                journalEntry.setCreatedAt(existingEntry.getCreatedAt());
                journalEntry.setUpdatedAt(Instant.now());
                return journalEntryRepository.save(journalEntry);
            }
        }
        return null;
    }

    public void deleteJournalEntry(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    public List<JournalEntity> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }
}
