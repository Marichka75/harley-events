INSERT INTO events (name, description, date_time, location) VALUES
('Madrid Harley Rally', 'A two-day rally through the scenic routes around Madrid. Enjoy live music and food trucks.', '2025-05-15 10:00:00', 'Madrid'),
('Barcelona Biker Fest', 'Annual festival celebrating the biker lifestyle in the heart of Barcelona. Featuring custom bike shows and parades.', '2025-06-20 12:00:00', 'Barcelona'),
('Valencia Coastal Ride', 'A stunning group ride along the coast of Valencia, ending with a paella feast.', '2025-07-10 09:30:00', 'Valencia'),
('Seville Hot Road Cruise', 'A summer evening cruise through the historic streets of Seville. Beat the heat with cool drinks and good company.', '2025-08-05 19:00:00', 'Seville'),
('Bilbao Iron Horse Meetup', 'A gathering of Harley riders in the Basque Country, exploring the modern and historic parts of Bilbao.', '2025-09-01 11:00:00', 'Bilbao'),
('Granada Alpujarra Tour', 'An adventurous ride through the mountainous terrain of the Alpujarras, near Granada.', '2025-10-12 08:00:00', 'Granada'),
('Mallorca Island Ride', 'A scenic tour of the beautiful island of Mallorca. Enjoy coastal views and winding roads.', '2025-11-03 10:00:00', 'Palma de Mallorca'),
('Canary Islands Volcano Ride', 'A thrilling ride around the volcanoes of Tenerife, followed by a local barbecue.', '2025-12-08 09:00:00', 'Santa Cruz de Tenerife');

INSERT INTO users (username, email, password, role) VALUES
('harley_rider_1', 'rider1@bikertribe.com', '{bcrypt}$2a$10$y6m41o7cT6o8Z6mPjM5xmu4Z.q5P2Y6jQ.mKzR5M7jN2u2L4Q5z1K', 'RIDER'),
('admin_user', 'admin@bikertribe.com', '{bcrypt}$2a$10$y6m41o7cT6o8Z6mPjM5xmu4Z.q5P2Y6jQ.mKzR5M7jN2u2L4Q5z1K', 'ADMIN'),
('biker_dave', 'dave@bikertribe.com', '{bcrypt}$2a$10$y6m41o7cT6o8Z6mPjM5xmu4Z.q5P2Y6jQ.mKzR5M7jN2u2L4Q5z1K', 'RIDER'),
('spain_biker', 'spainbiker@bikertribe.com', '{bcrypt}$2a$10$y6m41o7cT6o8Z6mPjM5xmu4Z.q5P2Y6jQ.mKzR5M7jN2u2L4Q5z1K', 'RIDER');

INSERT INTO user_events (user_id, event_id) VALUES
(1, 1),
(1, 3),
(3, 2),
(3, 4),
(4, 5),
(4, 6),
(4, 7);