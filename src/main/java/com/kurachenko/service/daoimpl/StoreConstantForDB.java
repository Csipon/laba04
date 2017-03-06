package com.kurachenko.service.daoimpl;

/**
 * @author Pavel Kurachenko
 * @since 12/21/2016
 */
public interface StoreConstantForDB {

    String LAST_INSERT_ID = "SELECT max(object_id) FROM objects";
    String INSERT_OBJECT = "INSERT INTO objects (name, description) VALUES(?, ?)";
    String INSERT_PARAMS = "INSERT INTO params (name, value, object_id) VALUES(?, ?, ?)";
    String SELECT_PARAMS = "SELECT name, value FROM params WHERE object_id = ?";
    String SELECT_OBJECT_ID = "SELECT object_id FROM params WHERE name = ? and value = ?";
    String SELECT_PARAMS_VALUE = "SELECT value FROM params WHERE object_id = ? AND name = ?";
    String SELECT_OBJECT = "SELECT object_id, name, description FROM objects";
    String SELECT_OBJECT_BY_ID = "SELECT object_id, name, description FROM objects WHERE object_id = ?";
    String SELECT_LOGIN = "select value from params where value = '%s' and name = 'login'";
    String SELECT_LOGIN_ID = "select object_id from params where value = '%s' and name = 'login'";
    String SELECT_PASSWORD = "select value from params where value = '%s' and name = 'password' " +
            "AND object_id = (" + SELECT_LOGIN_ID + ")";
    String SELECT_PASSWORD_ID = "select object_id from params where value = '%s' and name = 'password' " +
            "AND object_id = (" + SELECT_LOGIN_ID + ")";
    String SELECT_ALL = "SELECT object_id, name as role, (" + SELECT_LOGIN + ") as login, (" + SELECT_PASSWORD + ") as password " +
            "FROM objects WHERE object_id = (" + SELECT_PASSWORD_ID + ")";
    String UPDATE_OBJECT = "UPDATE objects SET description = ? WHERE object_id = ?";
    String UPDATE_PARAMS = "UPDATE params SET value = ? WHERE object_id = ? AND name = ?";
    String ALL_LOGIN = "SELECT value FROM params WHERE name = 'login'";
    String DELETE_OBJECT = "DELETE objects WHERE object_id = ?";
    String DELETE_PARAMS = "DELETE params WHERE object_id = ?";
    String SELECT_BY_LOGIN = "SELECT id, name as role, (select value from params where value = '%s' and name = 'login') as login," +
            " (select value from params where name = 'password' AND object_id = (select object_id from params where value = '%s' and name = 'login')) as password \n" +
            "FROM objects \n" +
            "WHERE id = (select object_id from params where value = '%s' and name = 'login')";



}
