package com.kurachenko.file.xml;

import com.kurachenko.entity.Customer;
import com.kurachenko.file.xml.abstr.AbstractXmlService;
import com.kurachenko.file.xml.abstr.EntityList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/20/2017
 */
public class CustomerXmlService extends AbstractXmlService<Customer, Integer> {
    @Override
    protected Customer createNewObject() throws IOException {
        return new Customer();
    }

    @Override
    protected String getNameFile() {
        return "customer.xml";
    }

    @Override
    protected EntityList<Customer> getEntityList() {
        return new CustomerList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return CustomerList.class;
    }

    @XmlRootElement(name = "customers")
     static class CustomerList extends EntityList<Customer> {

        @XmlElement(name = "customer")
        List<Customer> customer;


        public CustomerList() {
            customer = new ArrayList<>();
        }

        @Override
        public List<Customer> getList() {
            return customer;
        }
    }
}
