package net.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import net.journal.journalApp.entity.JournalEntity;
import net.journal.journalApp.services.JournalEntryService;
import java.util.List;
import org.bson.types.ObjectId;

@RequestMapping("journal")
public class JournalEntryController {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @RequestMapping("/create")
    public JournalEntity createJournalEntry(@RequestBody JournalEntity journalEntry) {
        return journalEntryService.createJournalEntry(journalEntry);
    }

    @RequestMapping("/get/{id}")
    public JournalEntity getJournalEntryById(@PathVariable ObjectId id) {
        return journalEntryService.getJournalEntryById(id);
    }

    @RequestMapping("/update/{id}")
    public JournalEntity updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntity journalEntry) {
        return journalEntryService.updateJournalEntry(id, journalEntry);
    }

    @RequestMapping("/delete/{id}")
    public void deleteJournalEntry(@PathVariable ObjectId id) {
        journalEntryService.deleteJournalEntry(id);
    }

    @RequestMapping("/all")
    public List<JournalEntity> getAllJournalEntries() {
        return journalEntryService.getAllJournalEntries();
    }
    
}
