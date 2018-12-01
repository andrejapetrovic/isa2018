insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('1', true, 'Novi Sad', 'andreja2092@gmail.com', 'Petrovic', 'Andreja', '123', '123', 'RegisteredUser');
insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('2', true, 'Novi Sad', 'kovac@gmail.com', 'Kovac', 'Nikola', '123', '123', 'RegisteredUser');
insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('3', true, 'Novi Sad', 'radovanovic@gmail.com', 'Radovanovic', 'Aleksandar', '123', '123', 'RegisteredUser');

insert into friend_relation(user_id, friend_id) values ('1', '2');
insert into friend_relation(user_id, friend_id) values ('2', '1');
insert into friend_relation(user_id, friend_id) values ('1', '3');
insert into friend_relation(user_id, friend_id) values ('3', '1');

insert into airline(airline_id, name, address) values ('1', 'Air Serbia', 'Булевар уметности 16a, 11070 Нови Београд');
insert into airline(airline_id, name, address) values ('2', 'Aegan Airlines', 'Кнез Михаилова 30/IV, 11000 Београд');

insert into airplane(airplane_id, model_name, owner_airline_id) values('1', 'Boeing 747', '1');
insert into airline_planes(airline_id, airplane_id) values('1', '1');

insert into segment(segment_id, col_count, row_count, travel_class) values ('1', 5, 30, 'Economy');
insert into segment(segment_id, col_count, row_count, travel_class) values ('2', 5, 30, 'Economy');
insert into segment(segment_id, col_count, row_count, travel_class) values ('3', 2, 25, 'Business');
insert into segment(segment_id, col_count, row_count, travel_class) values ('4', 2, 25, 'Business');
insert into airplane_segments(airplane_id, segment_id) values ('1', '1');
insert into airplane_segments(airplane_id, segment_id) values ('1', '2');
insert into airplane_segments(airplane_id, segment_id) values ('1', '3');
insert into airplane_segments(airplane_id, segment_id) values ('1', '4');
insert into airplane_segments(airplane_id, segment_id) values ('1', '5');
insert into airplane_segments(airplane_id, segment_id) values ('1', '6');

insert into destination(dest_id, airport_code, city, country, airport) values ('1', 'BEG', 'Belgrade', 'Serbia', 'Nikola Tesla');
insert into destination(dest_id, airport_code, city, country, airport) values ('2', 'VIE', 'Vienna', 'Austria', 'Vienna International');       
insert into destination(dest_id, airport_code, city, country, airport) values ('3', 'BCN', 'Barcelona', 'Spain', 'Barcelona-El Prat');
insert into destination(dest_id, airport_code, city, country, airport) values ('4', 'VFA', 'Victoria Falls', 'Zimbabwe', 'Victoria Falls');
insert into destination(dest_id, airport_code, city, country, airport) values ('5', 'FNJ', 'Pyongyang', 'North Korea', 'Pyongyang Kim Jong-Il International');
insert into destination(dest_id, airport_code, city, country, airport) values ('6', 'KIN', 'Kingston', 'Jamaica', 'Norman Manley');

insert into airline_destinations(airline_id, dest_id) values ('1', '1');
insert into airline_destinations(airline_id, dest_id) values ('1', '2');
insert into airline_destinations(airline_id, dest_id) values ('1', '3');