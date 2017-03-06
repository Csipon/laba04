
drop table params;
drop table objects;



CREATE TABLE objects (
    object_id     NUMBER(15) NOT NULL,
    name          VARCHAR2(255),
    description   VARCHAR2(4000 BYTE)
)

/

CREATE TABLE params (
    param_id    NUMBER NOT NULL,
    name        VARCHAR2(50),
    value       VARCHAR2(255),
    object_id   NUMBER(15) NOT NULL
)

/
