package com.kurachenko.file.xml;

import com.kurachenko.entity.ProjectManager;
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
public class ProjectManagerXmlService extends AbstractXmlService<ProjectManager, Integer> {
    @Override
    protected ProjectManager createNewObject() throws IOException {
        return new ProjectManager();
    }

    @Override
    protected String getNameFile() {
        return "manager.xml";
    }

    @Override
    protected EntityList<ProjectManager> getEntityList() {
        return new ProjectManagerList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return ProjectManagerList.class;
    }

    @XmlRootElement(name = "managers")
    static class ProjectManagerList extends EntityList<ProjectManager> {

        @XmlElement(name = "manager")
        private List<ProjectManager> manager;

        public ProjectManagerList() {
            manager = new ArrayList<>();
        }

        @Override
        public List<ProjectManager> getList() {
            return manager;
        }
    }
}
