package net.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import net.journal.journalApp.entity.JournalEntity;
import net.journal.journalApp.entity.UserEntity;
import net.journal.journalApp.services.JournalEntryService;
import net.journal.journalApp.services.UserService;

import java.util.List;
import org.bson.types.ObjectId;
import javax.validation.Valid;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create/{username}")
    public ResponseEntity<JournalEntity> createJournalEntry(@Valid @RequestBody JournalEntity journalEntry,@PathVariable String username) {
        JournalEntity created = journalEntryService.createJournalEntry(journalEntry,username);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable ObjectId id) {
        JournalEntity entry = journalEntryService.getJournalEntryById(id);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entry);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntity> updateJournalEntry(@PathVariable ObjectId id, @Valid @RequestBody JournalEntity journalEntry) {
        JournalEntity updated = journalEntryService.updateJournalEntry(id, journalEntry);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<Void> deleteJournalEntry(@PathVariable ObjectId id,@PathVariable String username) {
        journalEntryService.deleteJournalEntry(id,username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<JournalEntity>> getAllJournalEntries(@PathVariable String username) {
        List<JournalEntity> entries = journalEntryService.getAllJournalEntries(username);
        return ResponseEntity.ok(entries);
    }
}
