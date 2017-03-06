package com.kurachenko.file.xml;

import com.kurachenko.entity.Administrator;
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
public class AdminXmlService extends AbstractXmlService<Administrator, Integer> {
    @Override
    protected Administrator createNewObject() throws IOException {
        return new Administrator();
    }

    @Override
    protected String getNameFile() {
        return "admin.xml";
    }

    @Override
    protected EntityList<Administrator> getEntityList() {
        return new AdminList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return AdminList.class;
    }

    @XmlRootElement(name = "administrators")
    static class AdminList extends EntityList<Administrator> {

        @XmlElement(name = "administrator")
        List<Administrator> administrator;


        public AdminList() {
            administrator = new ArrayList<>();
        }

        @Override
        public List<Administrator> getList() {
            return administrator;
        }
    }
}
