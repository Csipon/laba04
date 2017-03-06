package com.kurachenko.file.abstr;

import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public abstract class GenericFile<T extends Identified<PK>, PK extends Serializable> {
    protected Map<Integer, T> map = new HashMap<>();
    private Integer id = 0;

    public GenericFile() {
        setMap(getAll());
    }

    public abstract void writeInFile() throws IOException, PersistException;

    protected abstract T createNewObject() throws IOException;

    protected abstract String getNameFile();

    public void save() throws PersistException {
        try {
            writeInFile();
        } catch (IOException e) {
            throw new PersistException(e);
        }
    }

    public T create() throws PersistException {
        T object;
        try {
            object = createNewObject();
        } catch (IOException e) {
            throw new PersistException(e);
        }
        object.setId(newID());
        return object;
    }

    public abstract T persist(T object) throws PersistException, IOException;

    public T getByPK(Integer key) throws PersistException {
        if (map.isEmpty()) {
            setMap(getAll());
        }
        return map.get(key);
    }

    public void update(T object) throws PersistException {
        if (map.containsKey(object.getId())) {
            map.put((Integer) object.getId(), object);
        }
    }

    public void delete(T object) throws PersistException {
        if (map.containsKey(object.getId())) {
            map.remove(object.getId());
        }
    }

    public abstract List<T> getAll();


    public Integer setMap(List<T> list) {
        Integer lastId = 0;
        if (list != null && list.size() > 0) {
            for (T temp : list) {
                if (lastId < (Integer) temp.getId()) {
                    lastId = (Integer) temp.getId();
                }
                map.put((Integer) temp.getId(), temp);
            }
        }
        return ++lastId;
    }


    private Integer newID() {
        if (id == 0) {
            Set<Integer> set = map.keySet();
            for (Integer lastId : set) {
                if (id < lastId) {
                    id = lastId;
                }
            }
        }
        return ++id;
    }
}
