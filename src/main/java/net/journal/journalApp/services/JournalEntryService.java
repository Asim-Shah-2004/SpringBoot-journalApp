package net.journal.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.journal.journalApp.repository.JournalEntryRepository;
import net.journal.journalApp.entities.Journal;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveJournalEntry(Journal journal) {
        journalEntryRepository.save(journal);
    }

    public Iterable<Journal> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public Journal getJournalEntryById(String id) {
        return journalEntryRepository.findById(id).get();
    }

    public void deleteJournalEntry(String id) {
        journalEntryRepository.deleteById(id);
    }

    public void editJournalEntry(String id, Journal journal) {
        Journal newJournal = journalEntryRepository.findById(id).get();
        newJournal.setTitle(journal.getTitle());
        newJournal.setContent(journal.getContent());
        journalEntryRepository.save(newJournal);
    }

}
