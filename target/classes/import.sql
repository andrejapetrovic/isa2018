insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('1', true, 'Novi Sad', 'andreja2092@gmail.com', 'Petrovic', 'Andreja', '123', '123', 'RegisteredUser');
insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('2', true, 'Novi Sad', 'kovac@gmail.com', 'Kovac', 'Nikola', '123', '123', 'RegisteredUser');
insert into user (user_id, activated, city, email, last_name, name, password, phone, role) values ('3', true, 'Novi Sad', 'radovanovic@gmail.com', 'Radovanovic', 'Aleksandar', '123', '123', 'RegisteredUser');

insert into friend_relation(user_id, friend_id) values ('1', '2');
insert into friend_relation(user_id, friend_id) values ('2', '1');
insert into friend_relation(user_id, friend_id) values ('1', '3');
insert into friend_relation(user_id, friend_id) values ('3', '1');

insert into airline(airline_id, name, address) values ('1', 'Air Serbia', 'Булевар уметности 16a, 11070 Нови Београд');
insert into airline(airline_id, name, address) values ('2', 'Aegan Airlines', 'Кнез Михаилова 30/IV, 11000 Београд');

insert into airplane(airplane_id, model_name, model_number, owner_airline_id) values('1', 'Boeing 747', '123', '1');

insert into segment(segment_id, cols, rows, travel_class) values ('1', 5, 30, 'Economy');
insert into segment(segment_id, cols, rows, travel_class) values ('2', 5, 30, 'Economy');
insert into segment(segment_id, cols, rows, travel_class) values ('3', 2, 25, 'Business');
insert into segment(segment_id, cols, rows, travel_class) values ('4', 2, 25, 'Business');
insert into airplane_segments(airplane_id, segment_id) values ('1', '1');
insert into airplane_segments(airplane_id, segment_id) values ('1', '2');
insert into airplane_segments(airplane_id, segment_id) values ('1', '3');
insert into airplane_segments(airplane_id, segment_id) values ('1', '4');

insert into destination(dest_id, airport_code, city, country, airport) values ('1', 'BEG', 'Belgrade', 'Serbia', 'Nikola Tesla');
insert into destination(dest_id, airport_code, city, country, airport) values ('2', 'VIE', 'Vienna', 'Austria', 'Vienna International');       
insert into destination(dest_id, airport_code, city, country, airport) values ('3', 'BCN', 'Barcelona', 'Spain', 'Barcelona-El Prat');
insert into destination(dest_id, airport_code, city, country, airport) values ('4', 'VFA', 'Victoria Falls', 'Zimbabwe', 'Victoria Falls');
insert into destination(dest_id, airport_code, city, country, airport) values ('5', 'FNJ', 'Pyongyang', 'North Korea', 'Pyongyang Kim Jong-Il International');
insert into destination(dest_id, airport_code, city, country, airport) values ('6', 'KIN', 'Kingston', 'Jamaica', 'Norman Manley');

insert into airline_destinations(airline_id, dest_id) values ('1', '1');
insert into airline_destinations(airline_id, dest_id) values ('1', '2');
insert into airline_destinations(airline_id, dest_id) values ('1', '3');

insert into flight_class(class_id, name) values ('1', 'Economy');
insert into flight_class(class_id, name) values ('2', 'Premium Economy');
insert into flight_class(class_id, name) values ('3', 'Bussines');
insert into flight_class(class_id, name) values ('4', 'First');

insert into flight_type(flight_type_id, name) values ('1', 'Round-trip');
insert into flight_type(flight_type_id, name) values ('2', 'One-way');

insert into passenger(passenger_id, type) values ('1', 'Adults');
insert into passenger(passenger_id, type) values ('2', 'Children');
insert into passenger(passenger_id, type) values ('3', 'Infants');

insert into flight(flight_id, from_id, to_id, depart_date, return_date, airline_id, airplane_id, stop_count, remaining_seats) values ('1', '1', '2', '2018-12-29', '2018-12-30', 1, 1, 0, 3);
insert into flight(flight_id, from_id, to_id, depart_date, return_date, airline_id, airplane_id, stop_count, remaining_seats) values ('2', '1', '2', '2018-12-29', '2018-12-30', 2, 2, 0, 1);
insert into flights_classes(flight_id, class_id) values ('1', '1');	
insert into flights_classes(flight_id, class_id) values ('1', '2');
insert into flights_classes(flight_id, class_id) values ('1', '3');
insert into flights_types(flight_id, flight_type_id) values ('1', '1');					