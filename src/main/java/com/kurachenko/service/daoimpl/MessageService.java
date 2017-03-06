package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Message;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.MessageProxy;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoabstract.MessageAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 2/7/2017
 */
@Service
public class MessageService extends MessageAbstract{
    @Autowired
    public MessageService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Message create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Message createNewObject() {
        return new MessageProxy(this);
    }
}
