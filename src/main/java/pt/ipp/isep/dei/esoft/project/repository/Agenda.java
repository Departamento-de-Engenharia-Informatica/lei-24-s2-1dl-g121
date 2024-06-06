package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Agenda implements Serializable {
    private final List<Entry> lstEntries;

    public Agenda() {
        lstEntries  = new ArrayList<>();
    }

    public Optional<Entry> add(Entry Entry) {
        Optional<Entry> newEntry = Optional.empty();
        boolean operationSuccess = false;
        if (validateEntry(Entry)) {
            newEntry = Optional.of(Entry.clone());
            operationSuccess = lstEntries.add(newEntry.get());
        }
        if (!operationSuccess) {
            newEntry = Optional.empty();
        }
        return newEntry;
    }

    private boolean validateEntry(Entry entry) {
        return !lstEntries.contains(entry);
    }

    public List<Entry> getEntries() {
        return List.copyOf(lstEntries);
    }

    public List<Entry> getEntriesByStatus(status status) {
        List<Entry> lstEntries = new ArrayList<>();
        for (Entry entry : this.lstEntries) {
            if (entry.getStatus().equals(status)) {
                lstEntries.add(entry);
            }
        }
        return lstEntries;
    }

    public List<Entry> getEntriesByTask(Task task) {
        List<Entry> lstEntries = new ArrayList<>();
        for (Entry entry : this.lstEntries) {
            if (entry.getTask().equals(task)) {
                lstEntries.add(entry);
            }
        }
        return lstEntries;
    }

    public List<String> getEntriesIDs() {
        List<String> lstIDs = new ArrayList<>();
        for (Entry entry : this.lstEntries) {
            lstIDs.add(entry.getID());
        }
        return lstIDs;
    }

    public List<String> getEntriesIdAndDate() {
        List<String> lstEntries = new ArrayList<>();
        for (Entry entry : this.lstEntries) {
            lstEntries.add(entry.getIdAndDate());
        }
        return lstEntries;
    }

    public boolean remove(Entry entry) {
        return lstEntries.remove(entry);
    }
}
