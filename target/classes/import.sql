insert into user (user_id, activated, city, email, last_name, name, password, phone) values ('1', true, 'Novi Sad', 'aki@gmail.com', 'Petrovic', 'Andreja', '123', '123');
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


INSERT INTO rentacar(id,address,description,name_Of_RentACar,rating) VALUES('1','1Mileve Maric','dsafs','1NS Rent','2');
INSERT INTO rentacar(id,address,description,name_Of_RentACar,rating) VALUES('2','2Jovana Ducica','dsafs','2NS Rent','2');
INSERT INTO rentacar(id,address,description,name_Of_RentACar,rating) VALUES('3','3Mileve Maric','dsafs','3NS Rent','2');

INSERT INTO branch_office(id,address,city,servis_id) VALUES('1','1Mileve Maric','1Novi Sad','1');
INSERT INTO branch_office(id,address,city,servis_id) VALUES('2','2Mileve Maric','2Novi Sad','2');
INSERT INTO branch_office(id,address,city,servis_id) VALUES('3','3Mileve Maric','3Novi Sad','3');

INSERT INTO car(car_type, id, description, model_name,model_number,number_of_cases,number_of_doors,number_of_seats,price_per_day,branch_office_id,reserved) values('Prestige_Cars','1','','Porsche911','1','1','2','2','80','1',false);
INSERT INTO car(car_type,id,description,model_name,model_number,number_of_cases,number_of_doors,number_of_seats,price_per_day,branch_office_id,reserved) values('Full_Size','2','','Ford Taurus','4','4','2','2','30','2',true);
INSERT INTO car(car_type,id,description,model_name,model_number,number_of_cases,number_of_doors,number_of_seats,price_per_day,branch_office_id,reserved) values('SUV_Convertible','3','','Audi q7','3','4','2','2','50','3',false);
INSERT INTO reservation_rentacar(reservation_id, car_type, drop_of_date, drop_of_loc, passengers, pick_up_date, pick_up_loc, car_id, servis_id) VALUES('1','Prestige_Cars','2019-02-12','1Mileve Maric','1','2019-02-08','1Mileve Maric','1','1');
