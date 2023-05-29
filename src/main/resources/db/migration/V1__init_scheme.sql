CREATE TABLE IF NOT EXISTS groups (
                        group_id SERIAL PRIMARY KEY,
                        group_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
                          student_id SERIAL PRIMARY KEY,
                          group_id INTEGER REFERENCES groups(group_id),
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
                         course_id SERIAL PRIMARY KEY,
                         course_name VARCHAR(50) NOT NULL,
                         course_description TEXT
);

CREATE TABLE IF NOT EXISTS student_courses (
                                 student_id INTEGER NOT NULL REFERENCES students(student_id),
                                 course_id INTEGER NOT NULL REFERENCES courses(course_id),
                                 PRIMARY KEY (student_id, course_id)
);

CREATE TABLE IF NOT EXISTS first_names (
    first_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS last_names (
    last_name VARCHAR(50)
);