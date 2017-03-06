package com.kurachenko.file.json;

import com.kurachenko.entity.Administrator;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 2/4/2017
 */
public class AdminFileService extends AbstractFileService<Administrator, Integer> {
    public AdminFileService() throws PersistException {
    }

    @Override
    protected Administrator createNewObject() throws IOException {
        return new Administrator();
    }

    @Override
    protected String getNameFile() {
        return "admin.json";
    }
}
