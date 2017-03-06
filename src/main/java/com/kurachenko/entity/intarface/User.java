package com.kurachenko.entity.intarface;

import java.security.Principal;

/**
 * @author Pavel Kurachenko
 * @since 3/4/2017
 */
public interface User extends Identified<Integer>, Principal {

    String getName();
    String getSurname();
    String getLogin();
    String getPassword();

}
