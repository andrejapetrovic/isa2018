insert into user (user_id, activated, city, email, last_name, name, password, phone) values ('1', true, 'Novi Sad', 'andreja2092@gmail.com', 'Petrovic', 'Andreja', '123', '123');
insert into user (user_id, activated, city, email, last_name, name, password, phone) values ('2', true, 'Novi Sad', 'kovac@gmail.com', 'Kovac', 'Nikola', '123', '123');
insert into user (user_id, activated, city, email, last_name, name, password, phone) values ('3', true, 'Novi Sad', 'radovanovic@gmail.com', 'Radovanovic', 'Aleksandar', '123', '123');
insert into user_roles (user_id, role) values (1, 'AirlineAdmin');
insert into user_roles (user_id, role) values (1, 'RegisteredUser');
insert into user_roles (user_id, role) values (2, 'RegisteredUser');
insert into user_roles (user_id, role) values (2, 'HotelAdmin');
insert into user_roles (user_id, role) values (3, 'RegisteredUser');
insert into user_roles (user_id, role) values (3, 'RentACarAdmin');

insert into friend_relation(user_id, friend_id) values ('1', '2');
insert into friend_relation(user_id, friend_id) values ('2', '1');
insert into friend_relation(user_id, friend_id) values ('1', '3');
insert into friend_relation(user_id, friend_id) values ('3', '1');

insert into airline(airline_id, name, address) values ('1', 'Air Serbia', 'Булевар уметности 16a, 11070 Нови Београд');
insert into airline(airline_id, name, address) values ('2', 'Aegan Airlines', 'Кнез Михаилова 30/IV, 11000 Београд');

insert into aircraft(aircraft_id, model_name, model_number, owner_airline_id, type) values('1', 'Boeing 747', '123', '1', 'Wide_body_jet');
insert into aircraft(aircraft_id, model_name, model_number, owner_airline_id, type) values('2', 'Boeing 747', '111', '2', 'Wide_body_jet');
insert into aircraft(aircraft_id, model_name, model_number, owner_airline_id, type) values('3', 'Boeing 747', '1234', '1', 'Wide_body_jet');

insert into destination(dest_id, airport_code, city, country, airport) values ('1', 'BEG', 'Belgrade', 'Serbia', 'Nikola Tesla');
insert into destination(dest_id, airport_code, city, country, airport) values ('2', 'VIE', 'Vienna', 'Austria', 'Vienna International');       
insert into destination(dest_id, airport_code, city, country, airport) values ('3', 'BCN', 'Barcelona', 'Spain', 'Barcelona-El Prat');
insert into destination(dest_id, airport_code, city, country, airport) values ('4', 'VFA', 'Victoria Falls', 'Zimbabwe', 'Victoria Falls');
insert into destination(dest_id, airport_code, city, country, airport) values ('5', 'FNJ', 'Pyongyang', 'North Korea', 'Pyongyang Kim Jong-Il International');
insert into destination(dest_id, airport_code, city, country, airport) values ('6', 'KIN', 'Kingston', 'Jamaica', 'Norman Manley');

insert into airline_destinations(airline_id, dest_id) values ('1', '1');
insert into airline_destinations(airline_id, dest_id) values ('1', '2');
insert into airline_destinations(airline_id, dest_id) values ('1', '3');

insert into flight(flight_id, from_id, to_id, departure_date, landing_date, airline_id, aircraft_id, stop_count, one_way_price, return_price) values ('1', '1', '2', '2019-01-10 8:00','2019-01-10 13:00', 1, 1, 2, 400.00, 800.00);
insert into flight(flight_id, from_id, to_id, departure_date, landing_date, airline_id, aircraft_id, stop_count, one_way_price, return_price) values ('2', '1', '2', '2019-01-10 12:00','2018-12-29 13:30', 2, 2, 0, 600.00, 900.00);
insert into flight(flight_id, from_id, to_id, departure_date, landing_date, airline_id, aircraft_id, stop_count, one_way_price, return_price) values ('3', '2', '1', '2019-01-11 23:30','2018-12-31 00:45', 1, 1, 0, 400.00, 800.00);
insert into flight(flight_id, from_id, to_id, departure_date, landing_date, airline_id, aircraft_id, stop_count, one_way_price, return_price) values ('4', '2', '1', '2019-01-11 17:50','2018-12-30 19:55', 1, 1, 0, 400.00, 800.00);

insert into flight_stops values(1, 3);
insert into flight_stops values(1, 4);	
/*insert into cabin(cabin_id, flight_class, aircraft_id) values (1, 'Economy', 1);
insert into cabin(cabin_id, flight_class, aircraft_id) values (2, 'Premium_economy)', 1);
insert into cabin(cabin_id, flight_class, aircraft_id) values (3, 'Bussines', 1);
insert into cabin(cabin_id, flight_class, aircraft_id) values (4, 'First', 1);
insert into cabin(cabin_id, flight_class, aircraft_id) values (5, 'Economy', 2);
insert into seat(seat_id, cabin_id, x, y) values (1, 1, 0, 0);
insert into seat(seat_id, cabin_id, x, y) values (2, 1, 0, 0);
insert into seat(seat_id, cabin_id, x, y) values (3, 1, 0, 0);
insert into seat(seat_id, cabin_id, x, y) values (4, 1, 0, 0);
insert into seat(seat_id, cabin_id, x, y) values (5, 3, 0, 0);
insert into flight_seat(id, flight_id, seat_id, reserved) values (1, 1, 1, true);
insert into flight_seat(id, flight_id, seat_id, reserved) values (2, 1, 2, false);
insert into flight_seat(id, flight_id, seat_id, reserved) values (3, 1, 3, false);
insert into flight_seat(id, flight_id, seat_id, reserved) values (4, 1, 4, false);
insert into flight_seat(id, flight_id, seat_id, reserved) values (5, 2, 5, false);*/