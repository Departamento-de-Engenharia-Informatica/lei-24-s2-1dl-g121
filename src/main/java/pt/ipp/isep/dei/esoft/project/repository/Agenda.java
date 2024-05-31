package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.status;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Entry> lstEntries = new ArrayList<>();

    public Entry newEntry(Task task, int duration, status status) {
        return new Entry(task, duration, status);
    }

    public boolean registerEntry(Entry entry) {
        if (entry != null) {
            return this.lstEntries.add(entry);
        }
        return false;
    }

    public boolean removeEntry(Entry entry) {
        return this.lstEntries.remove(entry);
    }

    public List<Entry> getEntries() {
        return this.lstEntries;
    }

    public Entry getEntryByTask(Task task) {
        for (Entry entry : this.lstEntries) {
            if (entry.getTask().equals(task)) {
                return entry;
            }
        }
        return null;
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

    public List<Entry> getEntriesByDuration(int duration) {
        List<Entry> lstEntries = new ArrayList<>();
        for (Entry entry : this.lstEntries) {
            if (entry.getDuration() == duration) {
                lstEntries.add(entry);
            }
        }
        return lstEntries;
    }

    public List<Entry> getEntriesByTask(Task task, status status) {
        List<Entry> lstEntries = new ArrayList<>();
        for (Entry entry : this.lstEntries) {
            if (entry.getTask().equals(task) && entry.getStatus().equals(status)) {
                lstEntries.add(entry);
            }
        }
        return lstEntries;
    }

    public List<Entry> getEntriesByTask(Task task, int duration) {
        List<Entry> lstEntries = new ArrayList<>();
        for (Entry entry : this.lstEntries) {
            if (entry.getTask().equals(task) && entry.getDuration() == duration) {
                lstEntries.add(entry);
            }
        }
        return lstEntries;
    }
}
