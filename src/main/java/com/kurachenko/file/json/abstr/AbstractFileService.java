package com.kurachenko.file.json.abstr;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public abstract class AbstractFileService<T extends Identified<PK>, PK extends Integer> extends GenericFile<T, PK> {
    private ObjectMapper mapper = new ObjectMapper();

    public AbstractFileService() throws PersistException {
    }

    @Override
    public T persist(T object) throws PersistException {
        try(BufferedWriter writer = new BufferedWriter(getWriter(true))) {
            if (map.containsKey(object.getId())) {
                throw new PersistException("Object with this ID already exist");
            }
            map.put(object.getId(), object);

            String objectLine;
            objectLine = mapper.writeValueAsString(object);
            writer.write(objectLine);
            writer.newLine();
        } catch (IOException e) {
            throw new PersistException(e);
        }
        return object;
    }


    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(getReader())) {
            while (reader.ready()) {
                T object = (T) mapper.readValue(reader.readLine(), createNewObject().getClass());
                list.add(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeInFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(getWriter(false));
        for (Map.Entry<Integer, T> m : map.entrySet()) {
            writer.write(mapper.writeValueAsString(m.getValue()));
            writer.newLine();
        }
        writer.close();
    }



    protected FileWriter getWriter(boolean isAppend) throws IOException {
        return new FileWriter("jsondatabase/" + getNameFile(), isAppend);
    }


    protected FileReader getReader() throws IOException {
        return new FileReader("jsondatabase/" + getNameFile());
    }
}
