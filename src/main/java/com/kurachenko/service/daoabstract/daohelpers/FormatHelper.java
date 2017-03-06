package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoimpl.StoreConstantForDB;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;


/**
 * @author Pavel Kurachenko
 * @since 12/30/2016
 */
public abstract class FormatHelper extends AbstractJDBCDao{

    private FormatHelper(DaoFactory parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

//    public static Object parseStringParam(String value, Class type) throws PersistException, ParseException {
//        if (value.equals("null") || value.length() < 1.0) {
//            return null;
//        }
//        if (type == Date.class) {
//            return formatter.parse(value);
//        }
//        if (type == String.class) {
//            return value;
//        } else if (Number.class.isAssignableFrom(type)) {
//            return getNumber(value, type);
//        } else if (type == Character.class) {
//            return value.charAt(0);
//        } else if (type == Boolean.class) {
//            return Boolean.parseBoolean(value);
//        }else if (type == Qualification.class) {
//            return Qualification.valueOf(value);
//        } else {
//            return getService(type).getByPK(Integer.parseInt(value));
//        }
//    }

    public static Number getNumber(String value, Class type) {
        if (type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == Long.class) {
            return Long.parseLong(value);
        } else if (type == Float.class) {
            return Float.parseFloat(value);
        } else if (type == Byte.class) {
            return Byte.parseByte(value);
        } else if (type == Short.class) {
            return Short.parseShort(value);
        }
        throw new ClassCastException();
    }

    public static String getDBFormat(Object object) {
        if (object instanceof Identified) {
            return String.valueOf(((Identified) object).getId());
        } else if (object instanceof Date) {
            return formatter.format(object);
        } else {
            return String.valueOf(object);
        }
    }

    public static String paramToString(Object object, Field field) throws IllegalAccessException, SQLException {
        if (field.get(object) != null && field.get(object) instanceof Identified) {
            return String.valueOf(((Identified) field.get(object)).getId());
        }
        if (field.get(object) != null && field.getType() == Date.class) {
            return formatter.format(field.get(object));
        }
        return String.valueOf(field.get(object));
    }

    public static String getNameMethod(String s) {
        if (s.startsWith("is")){
            return "set" + s.substring(2);
        }
        return "set" + s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String getFirstCharLower(String s) {
        if (s.equals(Sprint.class.getSimpleName())){
            return "previousSprint";
        }
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static String getLastInsert() {
        return StoreConstantForDB.SELECT_OBJECT + " WHERE object_id = (" + StoreConstantForDB.LAST_INSERT_ID + ")";
    }
}
