package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;

import java.util.List;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 12/30/2016
 */
public abstract class CheckHelper {
    public static void checkOneUpdate(int i) throws PersistException {
        if (i != 1) {
            throw new PersistException("On persist modify more then 1 record: " + i);
        }
    }

    public static void checkIdObject(Identified object) throws PersistException {
        if (object.getId() != null) {
            throw new PersistException("Object is already persist.");
        }
    }

    public static boolean checkLogin(List<String> list, String login) {
        for (String temp : list) {
            if (temp.equalsIgnoreCase(login)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkAccept(Map<Integer, Boolean> map){
        for (Map.Entry<Integer, Boolean> m : map.entrySet()){
            if (!m.getValue()){
                return false;
            }
        }
        return true;
    }


    public static String passwordCode(String password) {
        String result = "";
        for (int i = 0; i < password.length(); i++) {
            char temp = password.charAt(i);
            if (i % 2 == 0) {
                temp += 25;
            } else {
                temp += 32;
            }
            result += temp;
        }
        return result;
    }

    public static String passwordDecode(String password) {
        String result = "";
        for (int i = 0; i < password.length(); i++) {
            char temp = password.charAt(i);
            if (i % 2 == 0) {
                temp -= 25;
            } else {
                temp -= 32;
            }
            result += temp;
        }
        return result;
    }

}
