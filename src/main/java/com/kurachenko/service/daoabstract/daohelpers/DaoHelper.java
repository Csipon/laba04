package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.kurachenko.service.daoabstract.daohelpers.CheckHelper.checkOneUpdate;

/**
 * @author Pavel Kurachenko
 * @since 12/22/2016
 */
public abstract class DaoHelper extends AbstractJDBCDao {

    private DaoHelper(DaoFactory parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    public static void prepareStatementForInsertObject(PreparedStatement statement, Object object) throws PersistException {
        try {
            statement.setString(1, object.getClass().getSuperclass().getSimpleName());
            statement.setString(2, ((Identified) object).getDescription());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    public static void setParamForUpdate(PreparedStatement statement, Object object, Field field) throws SQLException, IllegalAccessException, PersistException {
        statement.setString(1, FormatHelper.getDBFormat(field.get(object)));
        statement.setInt(2, (Integer) ((Identified) object).getId());
        statement.setString(3, field.getName());
        checkOneUpdate(statement.executeUpdate());
    }

    public static void insertParam(PreparedStatement statement, Object object, Field field) throws SQLException, IllegalAccessException, PersistException {
        statement.setString(1, field.getName());
        statement.setString(2, FormatHelper.paramToString(object, field));
        statement.setInt(3, (Integer) ((Identified) object).getId());
        checkOneUpdate(statement.executeUpdate());
    }
}
