package com.kurachenko.file.serialize;

import com.kurachenko.entity.Administrator;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 2/4/2017
 */
public class AdminBinaryService extends AbstractSerializeService<Administrator, Integer> {
    public AdminBinaryService() throws PersistException {
    }

    @Override
    protected Administrator createNewObject() throws IOException {
        return new Administrator();
    }

    @Override
    protected String getNameFile() {
        return "admin.txt";
    }
}
