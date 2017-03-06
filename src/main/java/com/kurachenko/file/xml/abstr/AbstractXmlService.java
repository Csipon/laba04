package com.kurachenko.file.xml.abstr;

import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.abstr.GenericFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/20/2017
 */
public abstract class AbstractXmlService<T extends Identified<PK>, PK extends Integer> extends GenericFile<T, PK> {


    public AbstractXmlService() {
    }

    @Override
    public T persist(T object) throws PersistException {
        if (map.containsKey(object.getId())) {
            throw new PersistException("Object with this ID already exist");
        }
        map.put(object.getId(), object);
        return object;
    }


    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        try (FileReader reader = getReader()) {
            Unmarshaller um = JAXBContext.newInstance(getListClass()).createUnmarshaller();
            list.addAll(((EntityList<T>) um.unmarshal(reader)).getList());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeInFile() throws IOException {
        try(BufferedWriter writer = new BufferedWriter(getWriter(false))) {
            EntityList<T> list = getEntityList();
            list.addAll(map.values());
            JAXBContext context = JAXBContext.newInstance(getListClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(list, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }



    protected FileWriter getWriter(boolean isAppend) throws IOException {
        return new FileWriter("xml/" + getNameFile(), isAppend);
    }


    protected FileReader getReader() throws IOException {
        return new FileReader("xml/" + getNameFile());
    }

    protected abstract EntityList<T> getEntityList();

    protected abstract Class<? extends EntityList> getListClass();
}
