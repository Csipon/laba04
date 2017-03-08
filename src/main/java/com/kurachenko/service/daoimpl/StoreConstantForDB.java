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
    String SELECT_OBJECT_ROLE = "SELECT name as role FROM objects WHERE object_id = ?";
    String SELECT_OBJECT_BY_ID = "SELECT object_id, name, description FROM objects WHERE object_id = ?";
    String UPDATE_OBJECT = "UPDATE objects SET description = ? WHERE object_id = ?";
    String UPDATE_PARAMS = "UPDATE params SET value = ? WHERE object_id = ? AND name = ?";
    String ALL_LOGIN = "SELECT object_id, value FROM params WHERE name = 'login'";
    String ALL_PASSWORDS = "SELECT object_id, value FROM params WHERE name = 'password'";
    String DELETE_OBJECT = "DELETE objects WHERE object_id = ?";
    String DELETE_PARAMS = "DELETE params WHERE object_id = ?";
    String SELECT_LOGIN_PASSWORD = "SELECT p1.object_id, p1.value as login, p2.value as password, 1 as enabled " +
            "FROM params p1 inner JOIN params p2 on p2.OBJECT_ID = p1.OBJECT_ID and p2.name = 'password' " +
            "where p1.value = ?";


}
