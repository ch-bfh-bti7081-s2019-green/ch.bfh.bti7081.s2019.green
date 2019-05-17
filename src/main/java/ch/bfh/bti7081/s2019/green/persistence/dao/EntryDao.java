package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.diary.Entry;


public class EntryDao extends AbstractDao<Entry> {
    public EntryDao() {
        super(Entry.class);
    }
}