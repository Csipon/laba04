package com.kurachenko.file.xml;

import com.kurachenko.entity.Journal;
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
public class JournalXmlService extends AbstractXmlService<Journal, Integer> {
    @Override
    protected Journal createNewObject() throws IOException {
        return new Journal();
    }

    @Override
    protected String getNameFile() {
        return "journal.xml";
    }

    @Override
    protected EntityList<Journal> getEntityList() {
        return new JournalList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return JournalList.class;
    }

    @XmlRootElement(name = "journals")
    static class JournalList extends EntityList<Journal> {

        @XmlElement(name = "journal")
        private List<Journal> journal;

        public JournalList() {
            journal = new ArrayList<>();
        }

        @Override
        public List<Journal> getList() {
            return journal;
        }
    }
}
