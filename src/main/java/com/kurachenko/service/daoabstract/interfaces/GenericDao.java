package com.kurachenko.service.daoabstract.interfaces;

import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /**
     * Create new record and return new object
     * */
    public T create() throws PersistException;

    /**
     * Create new record, proper object object
     * */
    public T persist(T object)  throws PersistException;

    /**
     *  Return object by PK or null
     * */
    public T getByPK(PK key) throws PersistException;

    /**
     *  Persist condition of object in DB
     * */
    public void update(T object) throws PersistException;

    /**
     * Delete record about of object from DB
     * */
    public void delete(T object) throws PersistException;

    /**
     * Return object list
     * */
    public List<T> getAll() throws PersistException;
}
