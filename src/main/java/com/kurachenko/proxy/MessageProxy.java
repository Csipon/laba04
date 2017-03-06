package com.kurachenko.proxy;

import com.kurachenko.entity.Message;
import com.kurachenko.service.daoimpl.MessageService;

/**
 * @author Pavel Kurachenko
 * @since 2/7/2017
 */
public class MessageProxy extends Message {

    private MessageService service;

    public MessageProxy(MessageService service) {
        this.service = service;
    }
}
