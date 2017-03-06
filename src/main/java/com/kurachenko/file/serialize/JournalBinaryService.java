package com.kurachenko.file.serialize;

import com.kurachenko.entity.Journal;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 2/4/2017
 */
public class JournalBinaryService extends AbstractSerializeService<Journal, Integer> {
    public JournalBinaryService() throws PersistException {
    }

    @Override
    protected Journal createNewObject() throws IOException {
        return new Journal();
    }

    @Override
    protected String getNameFile() {
        return "journal.txt";
    }
}
