package com.kurachenko.file.json;

import com.kurachenko.entity.Customer;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class CustomerFileService extends AbstractFileService<Customer, Integer> {
    public CustomerFileService() throws PersistException {
    }

    @Override
    protected Customer createNewObject() throws IOException {
        return new Customer();
    }

    @Override
    protected String getNameFile() {
        return "customer.json";
    }
}
