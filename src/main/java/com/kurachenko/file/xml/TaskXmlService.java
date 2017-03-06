package com.kurachenko.file.xml;

import com.kurachenko.entity.Task;
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
public class TaskXmlService extends AbstractXmlService<Task, Integer> {
    @Override
    protected Task createNewObject() throws IOException {
        return new Task();
    }

    @Override
    protected String getNameFile() {
        return "task.xml";
    }

    @Override
    protected EntityList<Task> getEntityList() {
        return new TaskList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return TaskList.class;
    }

    @XmlRootElement(name = "tasks")
    static class TaskList extends EntityList<Task> {

        @XmlElement(name = "task")
        private List<Task> task;

        public TaskList() {
            task = new ArrayList<>();
        }

        @Override
        public List<Task> getList() {
            return task;
        }
    }
}
