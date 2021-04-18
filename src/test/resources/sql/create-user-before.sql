delete from event_recommendation_system_test.user_roles;
delete from event_recommendation_system_test.user_tags;
delete from event_recommendation_system_test.registration_verification_token;
delete from event_recommendation_system_test.event_participants;
delete from event_recommendation_system_test.user;

insert into event_recommendation_system_test.user (id, activation_code, active, avatar, email, enabled, first_login, first_name, last_name, password, username)
values (1, null, 1, null, 'root@root.root', 1, 0, 'root@root.root', 'root@root.root', '$2a$10$4k.NtX9a6V5nRAcj5aep1uoy0AkGMPJrVC2R1spxhaJYBKvm0DYP6', 'root@root.root'),
       (2, null, 1, null, 'user@user.user', 1, 0, 'user@user.user', 'user@user.user', '$2a$10$4k.NtX9a6V5nRAcj5aep1uoy0AkGMPJrVC2R1spxhaJYBKvm0DYP6', 'user@user.user');

insert into event_recommendation_system_test.user_roles(user_id, roles)
values (1, 'ADMIN'),
       (1, 'USER'),
       (2, 'USER');

