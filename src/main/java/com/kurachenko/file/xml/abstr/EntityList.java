package com.kurachenko.file.xml.abstr;

import java.util.Collection;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/20/2017
 */

public abstract class EntityList<T> {

    public void add(T object){
        this.getList().add(object);
    }

    public T get(int i){
        return getList().get(i);
    }

    public void remove(T object){
        this.getList().remove(object);
    }

    public void remove(int i){
        getList().remove(i);
    }

    public void addAll(Collection collection){
        getList().addAll(collection);
    }

    public abstract List<T> getList();
}
