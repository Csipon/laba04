package com.kurachenko.file.xml;

import com.kurachenko.entity.Project;
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
public class ProjectXmlService extends AbstractXmlService<Project, Integer> {
    @Override
    protected Project createNewObject() throws IOException {
        return new Project();
    }

    @Override
    protected String getNameFile() {
        return "project.xml";
    }

    @Override
    protected EntityList<Project> getEntityList() {
        return new ProjectList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return ProjectList.class;
    }


    @XmlRootElement(name = "projects")
    static class ProjectList extends EntityList<Project> {

        @XmlElement(name = "project")
        private List<Project> project;

        public ProjectList() {
            project = new ArrayList<>();
        }

        @Override
        public List<Project> getList() {
            return project;
        }
    }
}
