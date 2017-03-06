package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurachenko.proxy.MessageProxy;
import com.kurachenko.service.daoimpl.MessageService;
import org.junit.Test;

/**
 * @author Pavel Kurachenko
 * @since 2/10/2017
 */
public class GsonTest {
    MessageService service;

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(new MessageProxy(service)));
    }
}
