CREATE TABLE IF NOT EXISTS course(
     id              SERIAL PRIMARY KEY,
     course_name     VARCHAR (255) NOT NULL UNIQUE ,
     unit            INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS employee(
     id              SERIAL PRIMARY KEY,
     user_name       VARCHAR (50) UNIQUE ,
     password        VARCHAR (50) ,
     first_name      VARCHAR (100) ,
     last_name       VARCHAR (100) ,
     national_code    VARCHAR (10) NOT NULL UNIQUE ,
     phone_number    VARCHAR (11) ,
     salary          DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS professor(
    id              SERIAL PRIMARY KEY,
    user_name       VARCHAR (50) UNIQUE ,
    password        VARCHAR (50) ,
    first_name      VARCHAR (100) ,
    last_name       VARCHAR (100) ,
    national_code    VARCHAR (10) NOT NULL UNIQUE ,
    phone_number    VARCHAR (11) ,
    salary          DOUBLE PRECISION ,
    course_id       INTEGER ,
    exam_id         INTEGER ,
    CONSTRAINT professor_course_id_fk FOREIGN KEY (course_id) REFERENCES course(id)
);

CREATE TABLE IF NOT EXISTS student(
    id              SERIAL PRIMARY KEY,
    user_name       VARCHAR (50) UNIQUE ,
    password        VARCHAR (50) ,
    first_name      VARCHAR (100) ,
    last_name       VARCHAR (100) ,
    national_code    VARCHAR (10) NOT NULL UNIQUE ,
    phone_number    VARCHAR (11)
    );

CREATE TABLE IF NOT EXISTS student_to_course(
    id              SERIAL PRIMARY KEY,
    student_id      INTEGER ,
    course_id      INTEGER ,
    grade          DOUBLE PRECISION,
    CONSTRAINT student_to_course_student_id_fk FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT student_to_course_course_id_fk FOREIGN KEY (course_id) REFERENCES course(id)
);