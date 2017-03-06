package com.kurachenko.service.daoabstract;

import com.kurachenko.entity.*;
import com.kurachenko.entity.enums.Qualification;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.CheckHelper;
import com.kurachenko.service.daoabstract.daohelpers.DaoHelper;
import com.kurachenko.service.daoabstract.daohelpers.FieldHelper;
import com.kurachenko.service.daoabstract.daohelpers.FormatHelper;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoabstract.interfaces.GenericDao;
import com.kurachenko.service.daoimpl.StoreConstantForDB;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.kurachenko.service.daoabstract.daohelpers.CheckHelper.checkIdObject;
import static com.kurachenko.service.daoabstract.daohelpers.CheckHelper.checkOneUpdate;

/**
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */
public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {
    private DaoFactory<Connection> parentFactory;
    private Connection connection;

    public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");

    public abstract T createNewObject();

    public AbstractJDBCDao(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        this.parentFactory = parentFactory;
        this.connection = connection;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for get one object by ID
     */
    @Override
    public T getByPK(Integer key) throws PersistException {
        List<T> list;
        String sql = StoreConstantForDB.SELECT_OBJECT + " WHERE object_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }

    /**
     * Method for get all objects
     */
    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = StoreConstantForDB.SELECT_OBJECT
                + " WHERE name = '" + createNewObject().getClass().getSuperclass().getSimpleName() + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }

    /**
     * Method for persist object
     */
    @Override
    public synchronized T persist(T object) throws PersistException {
        checkIdObject(object);
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.INSERT_OBJECT)) {
            DaoHelper.prepareStatementForInsertObject(statement, object);
            checkOneUpdate(statement.executeUpdate());
        } catch (Exception e) {
            throw new PersistException(e);
        }
        object.setId(getLastId());
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.INSERT_PARAMS)) {
            insertParams(statement, object);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return getByPK(getLastId());
    }

    /**
     * Method for update object
     */
    @Override
    public void update(T object) throws PersistException {
        // Сохраняем зависимости
        String sql = StoreConstantForDB.UPDATE_OBJECT;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            updateObject(statement, object);
            checkOneUpdate(statement.executeUpdate());
        } catch (Exception e) {
            throw new PersistException(e);
        }
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.UPDATE_PARAMS)) {
            updateParam(statement, object);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    /**
     * Method for delete object
     */
    @Override
    public void delete(T object) throws PersistException {
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.DELETE_PARAMS)) {
            statement.setInt(1, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.DELETE_OBJECT)) {
            statement.setObject(1, object.getId());
            checkOneUpdate(statement.executeUpdate());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    /**
     * Method for parse ResultSet
     */
    private List<T> parseResultSet(ResultSet rs) throws PersistException {
        List<T> list = new ArrayList<>();
        parseObjects(rs, list);
        parseResultSetParams(list);
        return list;
    }

    private void parseObjects(ResultSet rs, List<T> list) throws PersistException {
        try {
            while (rs.next()) {
                T object = createNewObject();
                object.setId(rs.getInt("object_id"));
                object.setDescription(rs.getString("description"));
                list.add(object);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    /**
     * Method for parse all object parameters and set them in object
     */
    private void parseResultSetParams(List<T> list) throws PersistException {
        String sql = StoreConstantForDB.SELECT_PARAMS;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (T object : list) {
                statement.setInt(1, object.getId());
                ResultSet rs = statement.executeQuery();
                setParam(rs, object);
            }
        } catch (Exception e) {
            throw new PersistException("parseResultSetParams", e);
        }
    }

    public List<Project> parseRsProject(ResultSet rs) throws SQLException, PersistException, ParseException {
        List<Project> list = new ArrayList<>();
        while (rs.next()) {
            String value = rs.getString("value");
            Object param = parseStringParam(value, Project.class);
            if (param == null){
                continue;
            }
            list.add((Project) param);
        }
        return list;
    }

    public List<Employee> parseRsEmployee(ResultSet rs) throws SQLException, PersistException, ParseException {
        List<Employee> list = new ArrayList<>();
        while (rs.next()) {
            String value = rs.getString("value");
            Object param = parseStringParam(value, Employee.class);
            if (param == null){
                continue;
            }
            list.add((Employee) param);
        }
        return list;
    }

    public List<Sprint> parseRsSprint(ResultSet rs) throws SQLException, PersistException, ParseException {
        List<Sprint> list = new ArrayList<>();
        while (rs.next()) {
            String value = rs.getString("value");
            Object param = parseStringParam(value, Sprint.class);
            if (param == null){
                continue;
            }
            list.add((Sprint) param);
        }
        return list;
    }

    public List<Task> parseRsTask(ResultSet rs) throws SQLException, PersistException, ParseException {
        List<Task> list = new ArrayList<>();
        while (rs.next()) {
            String value = rs.getString("value");
            Object param = parseStringParam(value, Task.class);
            if (param == null){
                continue;
            }
            list.add((Task) param);
        }
        return list;
    }

    public void insertParams(PreparedStatement statement, T object) throws IllegalAccessException, SQLException, PersistException {
        List<Field> fields = FieldHelper.removeFieldsRecursion(object.getClass(), false);
        for (Field field : fields) {
            if (field.getType().isArray() && field.get(object) != null) {
                insertArray((Identified[]) field.get(object), field.getName(), object.getId());
            }else if(Map.class.isAssignableFrom(field.getType()) && field.get(object) != null){
                insertMap((Map<Integer, Boolean>) field.get(object), field.getName(), object.getId());
            }else {
                DaoHelper.insertParam(statement, object, field);
            }
        }
    }

    public void insertMap(Map<Integer, Boolean> map, String paramName, Integer id) throws PersistException {
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.INSERT_PARAMS)) {
            for (Map.Entry<Integer, Boolean> m : map.entrySet()) {
                statement.setString(1, paramName);
                statement.setString(2, m.getKey() + ":" + m.getValue());
                statement.setInt(3, id);
                checkOneUpdate(statement.executeUpdate());
            }
        } catch (SQLException | PersistException e) {
            throw new PersistException(e);
        }
    }

    public void insertArray(Identified[] objects, String paramName, Integer id) throws PersistException {
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.INSERT_PARAMS)) {
            for (Identified identified : objects) {
                statement.setString(1, paramName);
                statement.setString(2, String.valueOf(identified.getId()));
                statement.setInt(3, id);
                checkOneUpdate(statement.executeUpdate());
            }
        } catch (SQLException | PersistException e) {
            throw new PersistException(e);
        }
    }

    /**
     * Method for prepare statement for 'objects' table
     */
    public void updateObject(PreparedStatement statement, T object) throws PersistException {
        try {
            statement.setString(1, object.getDescription());
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            throw new PersistException("prepareStatementForUpdateObject", e);
        }
    }

    public void updateParam(PreparedStatement statement, T object) throws IllegalAccessException, SQLException, PersistException {
        List<Field> fields = FieldHelper.removeFieldsRecursion(object.getClass(), false);
        for (Field field : fields) {
            if (field.get(object) != null || field.getType() == Sprint.class) {
                if (field.getType().isArray()) {
                    deleteParam(object, field.getName());
                    insertArray((Identified[]) field.get(object), field.getName(), object.getId());
                }else if(Map.class.isAssignableFrom(field.getType())){
                    deleteParam(object, field.getName());
                    insertMap((Map<Integer, Boolean>) field.get(object), field.getName(), object.getId());
                } else {
                    DaoHelper.setParamForUpdate(statement, object, field);
                }
            }
        }
    }

    public void deleteParam(T object, String paramName) {
        try (PreparedStatement statement
                     = connection.prepareStatement(
                StoreConstantForDB.DELETE_PARAMS + " AND name = '" + paramName + "'")) {
            statement.setInt(1, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map getMap(T object, String nameParam){
        Map<Integer, Boolean> map = new HashMap<>();
        try(PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.SELECT_PARAMS_VALUE)){
            statement.setInt(1, object.getId());
            statement.setString(2, nameParam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String value = rs.getString("value");
                if (!value.equals("null")){
                    String[] temp = value.split(":");
                    Integer idEmployee = Integer.parseInt(temp[0]);
                    Boolean accept = Boolean.parseBoolean(temp[1]);
                    map.put(idEmployee, accept);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Object[] getArrays(T object, String nameParam) throws PersistException {
        List list;
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.SELECT_PARAMS_VALUE)) {
            statement.setInt(1, object.getId());
            statement.setString(2, nameParam);
            ResultSet rs = statement.executeQuery();
            if (nameParam.equals("managedProjects") || nameParam.equals("projects")) {
                list = parseRsProject(rs);
                return list.toArray(new Project[list.size()]);
            } else if (nameParam.equals("subordinates")) {
                list = parseRsEmployee(rs);
                return list.toArray(new Employee[list.size()]);
            }else if(nameParam.equals("tasks")){
                list = parseRsTask(rs);
                return list.toArray(new Task[list.size()]);
            }else if(nameParam.equals("sprints")){
                list = parseRsSprint(rs);
                return list.toArray(new Sprint[list.size()]);
            }
        } catch (SQLException | ParseException | PersistException e) {
            throw new PersistException(e);
        }
        return null;
    }

    public Object getObject(T object, Class typeObject) throws PersistException {
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.SELECT_PARAMS_VALUE)) {
            statement.setInt(1, object.getId());
            statement.setString(2, FormatHelper.getFirstCharLower(typeObject.getSimpleName()));
            ResultSet rs = statement.executeQuery();
            rs.next();
            String value = rs.getString("value");
            return parseStringParam(value, typeObject);
        } catch (SQLException | ParseException e) {
            throw new PersistException(e);
        }
    }

    /**
     * Method for return last insert object
     */
    public Integer getLastId() throws PersistException {
        Integer id;
        try (PreparedStatement statement = connection.prepareStatement(FormatHelper.getLastInsert())) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            id = rs.getInt("object_id");
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new PersistException();
    }

    public boolean validLogin(String login){
        List<String> logins = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.ALL_LOGIN)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                logins.add(rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CheckHelper.checkLogin(logins, login);
    }

    public List<Integer> getListId(String nameParam, Integer id){
        try(PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.SELECT_OBJECT_ID)) {
            statement.setString(1,nameParam);
            statement.setString(2, String.valueOf(id));
            List<Integer> list = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Integer idJournal = rs.getInt("object_id");
                list.add(idJournal);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public GenericDao getService(Class clazz) throws PersistException {
        return parentFactory.getDao(connection, clazz);
    }

    public Object parseStringParam(String value, Class type) throws PersistException, ParseException {
        if (value.equals("null") || value.length() < 1) {
            return null;
        }
        if (type == Date.class) {
            return formatter.parse(value);
        }
        if (type == String.class) {
            return value;
        } else if (Number.class.isAssignableFrom(type)) {
            return FormatHelper.getNumber(value, type);
        } else if (type == Character.class) {
            return value.charAt(0);
        } else if (type == Boolean.class) {
            return Boolean.parseBoolean(value);
        }else if (type == Qualification.class) {
            return Qualification.valueOf(value);
        } else {
            return getService(type).getByPK(Integer.parseInt(value));
        }
    }

    public void setParam(ResultSet rs, Object object) throws PersistException {
        try {
            Map<String, Class> mapType = FieldHelper.getMapFieldsType(object.getClass(), true);
            while (rs.next()) {
                String fieldName = rs.getString("name");
                String value = rs.getString("value");
                if (mapType.get(fieldName) == null){
                    continue;
                }
                Object param = parseStringParam(value, mapType.get(fieldName));
                object.getClass().getMethod(FormatHelper.getNameMethod(fieldName), mapType.get(fieldName))
                        .invoke(object, param);
            }
        } catch (Exception e) {
            throw new PersistException("setParam",e);
        }
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    protected Connection getConnection() {
        return connection;
    }
}