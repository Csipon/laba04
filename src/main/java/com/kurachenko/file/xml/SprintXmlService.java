package com.kurachenko.file.xml;

import com.kurachenko.entity.Sprint;
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
public class SprintXmlService extends AbstractXmlService<Sprint, Integer>{
    @Override
    protected Sprint createNewObject() throws IOException {
        return new Sprint();
    }

    @Override
    protected String getNameFile() {
        return "sprint.xml";
    }

    @Override
    protected EntityList<Sprint> getEntityList() {
        return new SprintList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return SprintList.class;
    }

    @XmlRootElement(name = "sprints")
    static class SprintList extends EntityList<Sprint> {

        @XmlElement(name = "sprint")
        private List<Sprint> sprint;

        public SprintList() {
            sprint = new ArrayList<>();
        }

        @Override
        public List<Sprint> getList() {
            return sprint;
        }
    }
}
