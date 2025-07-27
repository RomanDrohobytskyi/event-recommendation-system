ALTER TABLE event_recommendation_system.user_roles ADD UNIQUE (user_id, roles);

CREATE UNIQUE INDEX IF NOT EXISTS user_roles_user_id_roles_unique
ON user_roles (user_id, roles);


/*
    Roles for default users
 */
INSERT IGNORE INTO event_recommendation_system.user_roles(user_id, roles)
VALUES (1, 'ADMIN'),
       (1, 'USER'),
       (2, 'ADMIN'),
       (2, 'USER'),
       (3, 'USER');

-- Tags: SPORT
INSERT INTO tag (id, event_type, description, name)
VALUES
    (1, 'SPORT', 'Sport', 'sport'),
    (2, 'SPORT', 'Football', 'football'),
    (3, 'SPORT', 'Basketball', 'basketball'),
    (4, 'SPORT', 'Bodybuilding', 'bodybuilding'),
    (5, 'SPORT', 'Gym', 'gym'),
    (6, 'SPORT', 'Workout', 'workout'),
    (7, 'SPORT', 'Running', 'running'),
    (8, 'SPORT', 'Crossfit', 'crossfit'),
    (9, 'SPORT', 'Bicycle', 'bicycle')
ON CONFLICT DO NOTHING;

-- Tags: EDUCATION
INSERT INTO tag (id, event_type, description, name)
VALUES
    (10, 'EDUCATION', 'Computer Science', 'computerScience'),
    (11, 'EDUCATION', 'IT', 'it'),
    (12, 'EDUCATION', 'Popular Science', 'popularScience'),
    (13, 'EDUCATION', 'Space Science', 'space'),
    (14, 'EDUCATION', 'Evolution', 'evolution'),
    (15, 'EDUCATION', 'Astronomy', 'astronomy'),
    (16, 'EDUCATION', 'Physics', 'physics'),
    (17, 'EDUCATION', 'Chemistry ', 'chemistry'),
    (18, 'EDUCATION', 'Biology  ', 'biology')
ON CONFLICT DO NOTHING;

-- Tags: ART
INSERT INTO tag (id, event_type, description, name)
VALUES
    (19, 'ART', 'Design', 'design'),
    (20, 'ART', 'Music', 'music'),
    (21, 'ART', 'Painting', 'painting'),
    (22, 'ART', 'Drawing ', 'drawing'),
    (23, 'ART', 'Photography', 'photography'),
    (24, 'ART', 'Evolution', 'photography'),
    (25, 'ART', 'Art', 'art')
ON CONFLICT DO NOTHING;

-- Subscriptions
INSERT INTO subscription (id, description, type)
VALUES
    (1, 'None', 'NONE'),
    (2, 'Event creation subscription', 'CREATION'),
    (3, 'Event modification subscription', 'MODIFICATION'),
    (4, 'Event registration subscription', 'REGISTRATION'),
    (5, 'Event rating subscription', 'RATING')
ON CONFLICT DO NOTHING;