package com.kurachenko.service.daoabstract.interfaces;

import com.kurachenko.exception.PersistException;

/**
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */
public interface DaoFactory<Context> {

    public interface DaoCreator<Context> {
        public GenericDao create(Context context) throws PersistException;
    }

    /**
     * Return connection to Data Base
     * */
    public Context getContext() throws PersistException;

    /**
     * Return object for manage persistent state object
     */
    public GenericDao getDao(Context context, Class dtoClass) throws PersistException;
}
