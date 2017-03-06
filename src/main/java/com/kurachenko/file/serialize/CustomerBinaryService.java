package com.kurachenko.file.serialize;

import com.kurachenko.entity.Customer;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class CustomerBinaryService extends AbstractSerializeService<Customer, Integer> {
    public CustomerBinaryService() throws PersistException {
    }

    @Override
    protected Customer createNewObject() throws IOException {
        return new Customer();
    }

    @Override
    protected String getNameFile() {
        return "customer.txt";
    }


}
