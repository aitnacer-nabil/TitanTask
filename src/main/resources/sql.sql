drop database gestion;
create database if not exists gestion;

use gestion;
create table if not exists user
(
    id            int primary key AUTO_INCREMENT,
    user_id       varchar(55),
    user_name     varchar(55),
    user_email    varchar(55),
    user_role     ENUM ('ADMIN','USER'),
    user_password varchar(255)
);
create table if not exists category
(
    ref           int primary key auto_increment,
    name_category varchar(100)
);
create table if not exists task
(
    id            int primary key AUTO_INCREMENT,
    task_id       varchar(55),
    name          varchar(55),
    description   TEXT,
    date_creation TIMESTAMP,
    priority      ENUM ('haute','basse','moyenne'),
    ref_category  varchar(55) references category (ref),
    ref_user      varchar(55) references user (user_id)
);

create table if not exists task_action_history
(
    id                int primary key AUTO_INCREMENT,
    task_id           int,
    action_change     enum ('CREATE','UPDATE','DELETE'),
    time_modification TIMESTAMP,
    user_name         varchar(250)

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
INSERT INTO category (name_category)
VALUES ('Work'),
       ('Personal'),
       ('Shopping'),
       ('Health'),
       ('Education'),
       ('Entertainment');
INSERT INTO task (task_id, name, description, date_creation, priority, ref_category, ref_user)
VALUES ('task1', 'Complete Project Report', 'Finish the report for the upcoming meeting', CURRENT_TIMESTAMP, 'haute',
        'Work', 'user1'),
       ('task2', 'Gym Session', 'Hit the gym for a workout', CURRENT_TIMESTAMP, 'moyenne', 'Health', 'user2'),
       ('task3', 'Buy Groceries', 'Purchase items for the week', CURRENT_TIMESTAMP, 'basse', 'Shopping', 'user3'),
       ('task4', 'Study Java Stream API', 'Learn and practice Java Stream API concepts', CURRENT_TIMESTAMP, 'moyenne',
        'Education', 'user4'),
       ('task5', 'Movie Night', 'Watch a movie with friends', CURRENT_TIMESTAMP, 'basse', 'Entertainment', 'user5'),
       ('task6', 'Prepare Presentation', 'Get ready for the upcoming client presentation', CURRENT_TIMESTAMP, 'haute',
        'Work', 'user6'),
       ('task7', 'Read a Book', 'Spend some time reading a good book', CURRENT_TIMESTAMP, 'basse', 'Personal', 'user7'),
       ('task8', 'Code Review', 'Review and provide feedback on team members\' code', CURRENT_TIMESTAMP, 'moyenne',
        'Work', 'user1'),
       ('task9', 'Cook Dinner', 'Prepare a delicious dinner for the family', CURRENT_TIMESTAMP, 'basse', 'Personal',
        'user2'),
       ('task10', 'Yoga Session', 'Practice yoga for relaxation', CURRENT_TIMESTAMP, 'moyenne', 'Health', 'user3'),
       ('task11', 'Online Course', 'Enroll in an online course to enhance skills', CURRENT_TIMESTAMP, 'moyenne',
        'Education', 'user4'),
       ('task12', 'Plan Weekend Trip', 'Organize a short weekend getaway with friends', CURRENT_TIMESTAMP, 'haute',
        'Personal', 'user5'),
       ('task13', 'Write Blog Post', 'Create a blog post on a relevant topic', CURRENT_TIMESTAMP, 'moyenne', 'Work',
        'user6'),
       ('task14', 'Visit Doctor', 'Schedule and visit the doctor for a health checkup', CURRENT_TIMESTAMP, 'haute',
        'Health', 'user7'),
       ('task15', 'Movie Marathon', 'Have a movie marathon at home', CURRENT_TIMESTAMP, 'basse', 'Entertainment',
        'user1');

