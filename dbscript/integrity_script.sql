ALTER TABLE objects ADD CONSTRAINT objects_pk PRIMARY KEY ( object_id );
ALTER TABLE params ADD CONSTRAINT params_pk PRIMARY KEY ( param_id );
ALTER TABLE params ADD CONSTRAINT params_objects_fk FOREIGN KEY ( object_id ) REFERENCES objects ( object_id ) NOT DEFERRABLE;

/

--CREATE SEQUENCE objects_object_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER objects_object_id_trg BEFORE
    INSERT ON objects
    FOR EACH ROW
    WHEN (
        new.object_id IS NULL
    )
BEGIN
    :new.object_id := objects_object_id_seq.nextval;
END;

/

--CREATE SEQUENCE params_param_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER params_param_id_trg BEFORE
    INSERT ON params
    FOR EACH ROW
    WHEN (
        new.param_id IS NULL
    )
BEGIN
    :new.param_id := params_param_id_seq.nextval;
END;

/
