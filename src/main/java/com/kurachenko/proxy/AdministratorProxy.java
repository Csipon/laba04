package com.kurachenko.proxy;

import com.kurachenko.entity.Administrator;
import com.kurachenko.service.daoimpl.AdministratorService;

/**
 * @author Pavel Kurachenko
 * @since 1/19/2017
 */
public class AdministratorProxy extends Administrator {
    private AdministratorService service;

    public AdministratorProxy(AdministratorService service) {
        this.service = service;
    }
}
