package com.kurachenko.entity.intarface;

import java.io.Serializable;

/**
 * common interface for all entity which have id and description
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */
public interface Identified<PK extends Serializable> {

    /**
     * return identical of object
     * */
    PK getId();

    /**
     * set identical of object
     * */
    void setId(Integer i);

    /**
     * return description of object
     * */
    String getDescription();

    /**
     * set description of object
     * */
    void setDescription(String description);
}
