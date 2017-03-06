package com.kurachenko.entity.enums;

import java.io.Serializable;

/**
 * this enum created as entity and he role is information by level qualification,
 * each level qualification have a value, than bigger value there level qualification better,
 * equal level qualification by value variable {@code qualificationLevel}
 * @author Pavel Kurachenko
 * @since 1/21/2017
 */
public enum Qualification implements Serializable{
    JUNIOR(1),
    MIDDLE(2),
    SENIOR(3),
    TECHLEAD(4);


    private int qualificationLevel;
    Qualification(int qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public int getQualificationLevel() {
        return qualificationLevel;
    }
}
