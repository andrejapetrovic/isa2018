insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('1', true, 'Novi Sad', 'andreja2092@gmail.com', 'Petrovic', 'Andreja', '123', '123', 'RegisteredUser');
insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('2', true, 'Novi Sad', 'kovac@gmail.com', 'Kovac', 'Nikola', '123', '123', 'RegisteredUser');
insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('3', true, 'Novi Sad', 'radovanovic@gmail.com', 'Radovanovic', 'Aleksandar', '123', '123', 'RegisteredUser');

insert into friend_relation(friend_rel_id, user1_user_id, user2_user_id) values ('1', '1', '2');
insert into friend_relation(friend_rel_id, user1_user_id, user2_user_id) values ('2', '1', '3');

insert into airline(airline_id, name, address) values ('1', 'Air Serbia', 'Булевар уметности 16a, 11070 Нови Београд');
insert into airline(airline_id, name, address) values ('2', 'Aegan Airlines', 'Кнез Михаилова 30/IV, 11000 Београд');