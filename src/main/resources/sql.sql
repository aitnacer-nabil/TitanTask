drop database gestion;
create database if not exists gestion;

use gestion;
create table if not exists user
(
    id            int primary key AUTO_INCREMENT,
    user_id       varchar(55) not null unique ,
    user_name     varchar(55) not null,
    user_email    varchar(55) not null,
    user_role     ENUM ('ADMIN','USER'),
    user_password varchar(255) not null
);
create table if not exists category
(
    ref           int primary key auto_increment,
    id_category varchar(100) not null UNIQUE ,
    name_category varchar(100) not null UNIQUE
);
create table if not exists task
(
    id            int primary key AUTO_INCREMENT,
    task_id       varchar(55) not null unique ,
    name          varchar(55) not null,
    description   TEXT not null,
    date_creation TIMESTAMP not null,
    priority      ENUM ('haute','basse','moyenne'),
    ref_category  varchar(55) references category (id_category) ,
    ref_user      varchar(55) references user (user_id)
);

create table if not exists task_action_history
(
    id                int primary key AUTO_INCREMENT,
    history_id varchar(55) not null unique ,
    task_id           varchar(55) not null unique ,
    action_change     enum ('CREATE','UPDATE','DELETE'),
    time_modification TIMESTAMP not null ,
    user_id         varchar(55) references user (user_id)

);

INSERT INTO user (user_id, user_name, user_email, user_role, user_password)
VALUES ('user1', 'Ahmed', 'ahmed@email.com', 'USER', 'password1'),
       ('user2', 'Fatima', 'fatima@email.com', 'USER', 'password2'),
       ('user3', 'Mehdi', 'mehdi@email.com', 'USER', 'password3'),
       ('user4', 'Amina', 'amina@email.com', 'USER', 'password4'),
       ('user5', 'Karim', 'karim@email.com', 'USER', 'password5'),
       ('user6', 'Nadia', 'nadia@email.com', 'USER', 'password6'),
       ('user7', 'Hassan', 'hassan@email.com', 'USER', 'password7'),
       ('admin1', 'Sara', 'sara@email.com', 'ADMIN', 'admin_password');
INSERT INTO category (id_category, name_category)
VALUES ('0c5a7','Work'),
       ('86411','Personal'),
       ('40ef8','Shopping'),
       ('eceb1','Health'),
       ('cdeaf','Education'),
       ('02e45','Entertainment');

INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (1, 'task1', 'Complete Project Report', 'Finish the report for the upcoming meeting', '2023-12-13 21:44:46', 'haute', '0c5a7', 'user1');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (6, 'task6', 'Prepare Presentation', 'Get ready for the upcoming client presentation', '2023-12-13 21:44:46', 'haute', '0c5a7', 'user6');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (8, 'task8', 'Code Review', 'Review and provide feedback on team members\' code', '2023-12-13 21:44:46', 'moyenne', '0c5a7', 'user1');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (13, 'task13', 'Write Blog Post', 'Create a blog post on a relevant topic', '2023-12-13 21:44:46', 'moyenne', '0c5a7', 'user6');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (3, 'task3', 'Buy Groceries', 'Purchase items for the week', '2023-12-13 21:44:46', 'basse', '40ef8', 'user3');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (5, 'task5', 'Movie Night', 'Watch a movie with friends', '2023-12-13 21:44:46', 'basse', '02e45', 'user5');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (15, 'task15', 'Movie Marathon', 'Have a movie marathon at home', '2023-12-13 21:44:46', 'basse', '02e45', 'user1');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (7, 'task7', 'Read a Book', 'Spend some time reading a good book', '2023-12-13 21:44:46', 'basse', '86411', 'user7');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (9, 'task9', 'Cook Dinner', 'Prepare a delicious dinner for the family', '2023-12-13 21:44:46', 'basse', '86411', 'user2');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (12, 'task12', 'Plan Weekend Trip', 'Organize a short weekend getaway with friends', '2023-12-13 21:44:46', 'haute', '86411', 'user5');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (4, 'task4', 'Study Java Stream API', 'Learn and practice Java Stream API concepts', '2023-12-13 21:44:46', 'moyenne', 'cdeaf', 'user4');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (11, 'task11', 'Online Course', 'Enroll in an online course to enhance skills', '2023-12-13 21:44:46', 'moyenne', 'cdeaf', 'user4');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (2, 'task2', 'Gym Session', 'Hit the gym for a workout', '2023-12-13 21:44:46', 'moyenne', 'eceb1', 'user2');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (10, 'task10', 'Yoga Session', 'Practice yoga for relaxation', '2023-12-13 21:44:46', 'moyenne', 'eceb1', 'user3');
INSERT INTO `task` (`id`, `task_id`, `name`, `description`, `date_creation`, `priority`, `ref_category`, `ref_user`) VALUES (14, 'task14', 'Visit Doctor', 'Schedule and visit the doctor for a health checkup', '2023-12-13 21:44:46', 'haute', 'eceb1', 'user7');
SELECT * FROM task LEFT JOIN category ON task.ref_category = category.id_category;
UPDATE category SET name_category='HEllo' WHERE id_category='ae18c';
INSERT INTO user (user_id, user_name, user_email, user_role, user_password) VALUES ('cad37', 'Ahmed', 'ahmed@example.com', 'ADMIN', 'password1')
SELECT * from task LEFT JOIN category ON task.ref_category = category.id_category where task_id ='0b925'