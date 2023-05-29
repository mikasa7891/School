
INSERT INTO groups (group_id, group_name) VALUES
                                                   (1, 'Group A'),
                                                   (2, 'Group B'),
                                                   (3, 'Group C');

INSERT INTO students (student_id, group_id, first_name, last_name) VALUES
                                                                            (1, 1, 'John', 'Doe'),
                                                                            (2, 1, 'Jane', 'Smith'),
                                                                            (3, 2, 'Michael', 'Johnson'),
                                                                            (4, 3, 'Emily', 'Williams');

INSERT INTO courses (course_id, course_name, course_description) VALUES
                                                                          (1, 'Mathematics', 'Introduction to Mathematics'),
                                                                          (2, 'History', 'World History'),
                                                                          (3, 'Science', 'Physics and Chemistry');

INSERT INTO student_courses (student_id, course_id) VALUES
                                                             (1, 1),
                                                             (1, 2),
                                                             (2, 1),
                                                             (3, 3),
                                                             (4, 2);
