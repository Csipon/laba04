package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Pavel Kurachenko
 * @since 12/30/2016
 */
public abstract class FieldHelper {


    public static List<Field> removeFieldsRecursion(Class clazz, boolean read) {
        List<Field> fields = new ArrayList<>();
        if (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            if (read) {
                removeFieldsForRead(fields);
            }else {
                removeFieldsForChange(fields);
            }
            fields.addAll(removeFieldsRecursion(clazz.getSuperclass(), read));
        }
        return fields;
    }

    public static void removeFieldsForRead(List<Field> fields){
        for (int i = fields.size() - 1; i >= 0; i--) {
            fields.get(i).setAccessible(true);
            String fieldName = fields.get(i).getName();
            if (fieldName.equals("description") || fieldName.equals("id") || fieldName.equals("serialVersionUID")
                    || Identified.class.isAssignableFrom(fields.get(i).getType())
                    || AbstractJDBCDao.class.isAssignableFrom(fields.get(i).getType())
                    || fields.get(i).getType().isArray()
                    || Map.class.isAssignableFrom(fields.get(i).getType())) {
                fields.remove(i);
            }
        }
    }

    public static void removeFieldsForChange(List<Field> fields){
        for (int i = fields.size() - 1; i >= 0; i--) {
            fields.get(i).setAccessible(true);
            String fieldName = fields.get(i).getName();
            if (fieldName.equals("description") || fieldName.equals("id") || fieldName.equals("serialVersionUID")
                    || AbstractJDBCDao.class.isAssignableFrom(fields.get(i).getType())) {
                fields.remove(i);
            }
        }
    }

    public static Map<String, Class> getMapFieldsType(Class clazz, boolean read) {
        List<Field> fields = removeFieldsRecursion(clazz, read);
        Map<String, Class> map = new HashMap<>();
        for (Field field : fields) {
            map.put(field.getName(), field.getType());
        }
        return map;
    }
}
