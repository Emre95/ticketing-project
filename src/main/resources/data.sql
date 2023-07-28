-- INSERT INTO roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, description)
-- VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Admin'),
--        ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Manager'),
--        ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Employee');
--
-- insert into users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, enabled,
--                   first_name, gender, last_name, user_name, role_id,password)
-- values ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, true, 'admin', 'MALE', 'admin', 'admin@admin.com',
--         1,'$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK');




INSERT INTO roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, description)
VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Admin'),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Manager'),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Employee');


INSERT INTO users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                  first_name, last_name, user_name, password, gender, phone, role_id)
VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'admin', 'admin', 'admin@admin.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK', 'MALE', '0000000000', 1),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Harold', 'Finch', 'harold@manager.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK', 'MALE', '0123456789', 2),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Samantha', 'Groves', 'samantha@manager.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK', 'MALE', '9876543210', 2),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'John', 'Reese', 'john@employee.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK', 'MALE', '7894561230', 3),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Sameen', 'Shaw', 'sameen@employee.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK', 'MALE', '0321654987', 3),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Grace', 'Hendricks', 'grace@employee.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK', 'MALE', '7410258963', 3),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Lionel', 'Fusco', 'lionel@employee.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK','MALE', '3698520147', 3);


INSERT INTO projects(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, project_code, project_name,
                     project_detail, project_status, start_date, end_date, manager_id)
VALUES ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'SP00', 'Spring Core', 'Spring Core Project', 'OPEN', '2022-01-05', '2022-06-12', 2),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'SP01', 'Spring Boot', 'Spring Boot Project', 'IN_PROGRESS', '2022-01-05', '2022-06-12', 2),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'SP02', 'Spring MVC', 'Spring MVC Project', 'IN_PROGRESS', '2022-01-05', '2022-06-12', 3),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'SP03', 'Spring Data', 'Spring Data Project', 'OPEN', '2022-01-05', '2022-06-12', 3);

INSERT INTO tasks(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, task_subject, task_detail, task_status, assigned_date, project_id, employee_id)
VALUES ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'Dependency Injection', 'Injecting dependencies', 'OPEN', '2022-01-05', 1, 4),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, '@SpringBootApplication', 'Adding @SpringBootApplication annotation', 'IN_PROGRESS', '2022-01-05', 1, 4),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'Controller', 'Creating controllers', 'COMPLETED', '2022-01-05', 1, 4),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'Entity', 'Creating entities', 'OPEN', '2022-01-05', 1, 4),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'Dependency Injection', 'Injecting dependencies', 'OPEN', '2022-01-05', 2, 5),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, '@SpringBootApplication', 'Adding @SpringBootApplication annotation', 'COMPLETED', '2022-01-05', 2, 5),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'Controller', 'Creating controllers', 'IN_PROGRESS', '2022-01-05', 2, 5),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'Entity', 'Creating entities', 'COMPLETED', '2022-01-05', 2, 5),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'Dependency Injection', 'Injecting dependencies', 'COMPLETED', '2022-01-05', 3, 6),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, '@SpringBootApplication', 'Adding @SpringBootApplication annotation', 'COMPLETED', '2022-01-05', 3, 6),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'Controller', 'Creating controllers', 'IN_PROGRESS', '2022-01-05', 3, 6),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'Entity', 'Creating entities', 'COMPLETED', '2022-01-05', 3, 6),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'Dependency Injection', 'Injecting dependencies', 'COMPLETED', '2022-01-05', 4, 7),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, '@SpringBootApplication', 'Adding @SpringBootApplication annotation', 'COMPLETED', '2022-01-05', 4, 7),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'Controller', 'Creating controllers', 'COMPLETED', '2022-01-05', 4, 7),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'Entity', 'Creating entities', 'COMPLETED', '2022-01-05', 4, 7);