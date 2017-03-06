package com.kurachenko.file.json;

import com.kurachenko.entity.Journal;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 2/4/2017
 */
public class JournalFileService extends AbstractFileService<Journal, Integer> {
    public JournalFileService() throws PersistException {
    }

    @Override
    protected Journal createNewObject() throws IOException {
        return new Journal();
    }

    @Override
    protected String getNameFile() {
        return "journal.json";
    }
}
