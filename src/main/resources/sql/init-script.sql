insert IGNORE into event_recommendation_system.user (id, activation_code, active, avatar, email, enabled, first_login, first_name, last_name, password, username)
values (1, null, 1, null, 'roman.drohobytskyi@gmail.com', 1, 0, 'romanDrohobytskyi98', 'romanDrohobytskyi98', '$2a$10$4k.NtX9a6V5nRAcj5aep1uoy0AkGMPJrVC2R1spxhaJYBKvm0DYP6', 'romanDrohobytskyi98'),
       (2, null, 1, null, 'root@root.root', 1, 1, 'root@root.root', 'root@root.root', '$2a$10$4k.NtX9a6V5nRAcj5aep1uoy0AkGMPJrVC2R1spxhaJYBKvm0DYP6', 'root@root.root'),
       (3, null, 1, null, 'user@user.user', 1, 1, 'user@user.user', 'user@user.user', '$2a$10$4k.NtX9a6V5nRAcj5aep1uoy0AkGMPJrVC2R1spxhaJYBKvm0DYP6', 'user@user.user');

insert IGNORE into event_recommendation_system.user_roles(user_id, roles)
values (1, 'ADMIN'),
       (1, 'USER'),
       (2, 'ADMIN'),
       (2, 'USER'),
       (3, 'USER');
/*
    TODO: -SOCIAL-    -CORPORATE-    -FESTIVAL-
*/
/*Sport event hashtags*/
insert IGNORE into event_recommendation_system.tag (id, event_type, description, name)
values (1, 'SPORT', 'Sport', 'sport'),
        (2, 'SPORT', 'Football', 'football'),
        (3, 'SPORT', 'Basketball', 'basketball'),
        (4, 'SPORT', 'Bodybuilding', 'bodybuilding'),
        (5, 'SPORT', 'Gym', 'gym'),
        (6, 'SPORT', 'Workout', 'workout'),
        (7, 'SPORT', 'Running', 'running'),
        (8, 'SPORT', 'Crossfit', 'crossfit'),
        ( 9,'SPORT', 'Bicycle', 'bicycle');

/*Education event hashtags*/
insert IGNORE into event_recommendation_system.tag (id, event_type, description, name)
values (10, 'EDUCATION', 'Computer Science', 'computerScience'),
        (11, 'EDUCATION', 'IT', 'it'),
        (12, 'EDUCATION', 'Popular Science', 'popularScience'),
        (13, 'EDUCATION', 'Space Science', 'space'),
        (14, 'EDUCATION', 'Evolution', 'evolution'),
        (15, 'EDUCATION', 'Astronomy', 'astronomy'),
        (16, 'EDUCATION', 'Physics', 'physics'),
        (17, 'EDUCATION', 'Chemistry ', 'chemistry'),
        (18, 'EDUCATION', 'Biology  ', 'biology');

/*Art event hashtags*/
insert IGNORE into event_recommendation_system.tag (id, event_type, description, name)
values (19, 'ART', 'Design', 'design'),
       (20, 'ART', 'Music', 'music'),
       (21, 'ART', 'Painting', 'painting'),
       (22, 'ART', 'Drawing ', 'drawing'),
       (23, 'ART', 'Photography', 'photography'),
       (24, 'ART', 'Evolution', 'photography'),
       (25, 'ART', 'Art', 'art');