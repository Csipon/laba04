package com.kurachenko.file.xml;

import com.kurachenko.entity.Message;
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
public class MessageXmlService extends AbstractXmlService<Message, Integer> {
    @Override
    protected Message createNewObject() throws IOException {
        return new Message();
    }

    @Override
    protected String getNameFile() {
        return "message.xml";
    }

    @Override
    protected EntityList<Message> getEntityList() {
        return new MessageList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return MessageList.class;
    }

    @XmlRootElement(name = "messages")
    static class MessageList extends EntityList<Message> {

        @XmlElement(name = "message")
        private List<Message> message;

        public MessageList() {
            message = new ArrayList<>();
        }

        @Override
        public List<Message> getList() {
            return message;
        }
    }
}
