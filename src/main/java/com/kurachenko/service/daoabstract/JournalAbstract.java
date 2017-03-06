package com.kurachenko.service.daoabstract;

import com.kurachenko.entity.Journal;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoimpl.JournalService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 2/1/2017
 */
public abstract class JournalAbstract extends AbstractJDBCDao<Journal, Integer> {
    public JournalAbstract(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    public List<Journal> getJournalsByNameParam(String nameParam, Integer id) throws PersistException {
        List<Journal> journals = new ArrayList<>();
        JournalService service = (JournalService) getService(Journal.class);
        for (Integer i : getListId(nameParam, id)) {
            journals.add(service.getByPK(i));
        }
        return journals;
    }

    public Map<Integer, Boolean> makeMap(List<Integer> list) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (Integer idEmp : list) {
            map.put(idEmp, false);
        }
        return map;
    }

    public List<Journal> getListEmployee(List<Journal> list, Integer idEmployee){
        List<Journal> result = new ArrayList<>();
        for (Journal j : list){
            if (j.getMapEmployee().containsKey(idEmployee)){
                result.add(j);
            }
        }
        return result;
    }
}
