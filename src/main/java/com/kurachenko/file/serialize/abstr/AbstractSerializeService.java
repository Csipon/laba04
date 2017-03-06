package com.kurachenko.file.serialize.abstr;

import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.abstr.GenericFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public abstract class AbstractSerializeService<T extends Identified<PK>, PK extends Integer> extends GenericFile<T, PK> {


    public AbstractSerializeService() throws PersistException {
    }

    @Override
    public T persist(T object) throws PersistException {
        if (!map.containsKey(object.getId())) {
            map.put(object.getId(), object);
            return object;
        }
        throw new PersistException("Object with this ID is exist");
    }


    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        try (FileInputStream fis = getReader()) {
            ObjectInputStream reader = new ObjectInputStream(fis);
            while (true) {
                try {
                    Object object = reader.readObject();
                    list.add((T) object);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(" this is IOExeption");
        } catch (ClassNotFoundException e) {
            System.out.println(" this is ClassNotFoundException");
        }
        return list;
    }


    public void writeInFile() throws PersistException {
        try (FileOutputStream fos = getWriter(false)) {
            ObjectOutputStream writer = new ObjectOutputStream(fos);
            for (Map.Entry<Integer, T> m : map.entrySet()) {
                writer.writeObject(m.getValue());
            }
        } catch (IOException e) {
            throw new PersistException(e);
        }
    }


    private FileOutputStream getWriter(boolean isAppend) throws IOException {
        return new FileOutputStream("binaryfiles/" + getNameFile(), isAppend);
    }


    private FileInputStream getReader() throws IOException {
        return new FileInputStream("binaryfiles/" + getNameFile());
    }
}
