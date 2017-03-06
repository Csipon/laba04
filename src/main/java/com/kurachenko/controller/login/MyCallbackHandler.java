package com.kurachenko.controller.login;

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 3/3/2017
 */
public class MyCallbackHandler implements CallbackHandler {
    private String login;
    private String password;

    public MyCallbackHandler(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        NameCallback nameCallback = null;
        PasswordCallback passwordCallback = null;

        int counter = 0;
        while (counter < callbacks.length){
            if (callbacks[counter] instanceof NameCallback){
                nameCallback = (NameCallback) callbacks[counter++];
                nameCallback.setName(login);
            }else if (callbacks[counter] instanceof PasswordCallback){
                passwordCallback = (PasswordCallback) callbacks[counter++];
                passwordCallback.setPassword(password.toCharArray());
            }
        }
    }
}
