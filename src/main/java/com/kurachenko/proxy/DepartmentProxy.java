package com.kurachenko.proxy;

import com.kurachenko.entity.Department;
import com.kurachenko.service.daoimpl.DepartmentService;

/**
 * @author Pavel Kurachenko
 * @since 1/4/2017
 */
public class DepartmentProxy extends Department{
    private DepartmentService service;

    public DepartmentProxy(DepartmentService service) {
        this.service = service;
    }
}

