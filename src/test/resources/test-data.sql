INSERT INTO public.users (
    id, address, email, first_name, status, last_name, password, phone_number, role
) VALUES
      (1, '123 Main St', 'john.doe@example.com', 'John', 1, 'Doe', '$2a$10$d0pOPqh3G6WQQZtKxIUeTeeI/hht25FPJX5wpF.PnE38Dt9enc.0a', '+3815551234', 2),
      (2, '456 Oak St', 'jane.smith@example.com', 'Jane', 1, 'Smith', '$2a$10$nDwh6./ScO6y3IquLk4ut.2Ci5PTAN3rTf/0yz1GyF62dfS45KjX2', '+3815555678', 2),
      (3, '789 Pine St', 'bob.johnson@example.com', 'Bob', 1, 'Johnson', '$2a$10$TZ5gfQSX6n8EiFgZVtdUe.CzLMPCQ6CCRt6ieKZTnFw00c0FYCTSK', '+3815559876', 2),
      (4, '321 Elm St', 'alice.williams@example.com', 'Alice', 1, 'Williams', '$2a$10$4nYTZ9xl8DnOebwZ4Op1/eqcKqmXsMHsi1HNHBG8P30nJhqApV0QO', '+3815554321', 2),
      (5, '654 Birch St', 'charlie.brown@example.com', 'Charlie', 1, 'Brown', '$2a$10$UKDUbeV3Pj132r.S1ZAuoec9vYpzwURvUXL1YPnjaGWBQeDbgQns6', '+3815558765', 1),
      (6, '987 Cedar St', 'eva.miller@example.com', 'Eva', 1, 'Miller', '$2a$10$Ax47ATx9iK3afXnhxvNJheD5nkl1jhMw7FL0CoBAq6bIonM/W6tHa', '+3815552109', 1 ),
      (7, '135 Maple St', 'david.davis@example.com', 'David', 1, 'Davis', '$2a$10$3umvo5Gd8.RPbUooFdGdx.4klOb9vCHj4pAP4t8.sXcOL6mbG3mAa', '+3815553456', 1),
      (8, '246 Pine St', 'grace.jones@example.com', 'Grace', 1, 'Jones', '$2a$10$Dnm26vmvm.IEdplKvEiVeORQEOHtbB2moQzXHvqTtyCwkqVmBfU6S', '+3815557890', 1),
      (9, '579 Oak St', 'frank.white@example.com', 'Frank', 1, 'White', '$2a$10$21i9qDk5BDGYMyTJSXsb0u2nvUTvM9K9LrXiYLyNiq4cpbDX6czMi', '+3815559034', 1),
      (10, '802 Walnut St', 'helen.martin@example.com', 'Helen', 1, 'Martin', '$2a$10$Kf.ME6kIpdbgWgUXeJCi5O9xE26BJ00m2Ukm9ZikN67UHudNGuEq2', '+3815551230', 3);

INSERT INTO public.hosts(id)
VALUES  (1),
        (2),
        (3),
        (4);

INSERT INTO public.guests (id) VALUES
                                   (5),
                                   (6),
                                   (7),
                                   (8),
                                   (9);

INSERT INTO public.admin (id) VALUES
    (10);

INSERT INTO public.amenities (id, name) VALUES
                                            (1, 'Wi-Fi'),
                                            (2, 'Swimming Pool'),
                                            (3, 'Gym'),
                                            (4, 'Parking'),
                                            (5, 'Air Conditioning'),
                                            (6, 'Pet Friendly'),
                                            (7, 'Kitchen'),
                                            (8, 'TV'),
                                            (9, 'Washer/Dryer'),
                                            (10, 'Balcony');

INSERT INTO public.available_period (id, price_per_night, start_date, end_date) VALUES
                                                                                    (1, 100, '2023-12-01', '2023-12-10'),
                                                                                    (2, 120, '2023-12-11', '2023-12-20'),
                                                                                    (3, 90, '2023-12-21', '2023-12-31'),
                                                                                    (4, 80, '2024-01-01', '2024-01-10'),
                                                                                    (5, 110, '2024-01-11', '2024-01-20'),
                                                                                    (6, 95, '2024-01-21', '2024-01-31'),
                                                                                    (7, 150, '2024-02-01', '2024-02-10'),
                                                                                    (8, 130, '2024-02-11', '2024-02-20'),
                                                                                    (9, 70, '2024-02-21', '2024-02-28'),
                                                                                    (10, 200, '2024-03-01', '2024-03-10'),
                                                                                    (11, 180, '2024-03-11', '2024-03-20'),
                                                                                    (12, 110, '2024-03-21', '2024-03-31'),
                                                                                    (13, 120, '2024-04-01', '2024-04-10'),
                                                                                    (14, 100, '2024-04-11', '2024-04-20'),
                                                                                    (15, 90, '2024-04-21', '2024-04-30'),
                                                                                    (16, 160, '2024-05-01', '2024-05-10'),
                                                                                    (17, 140, '2024-05-11', '2024-05-20'),
                                                                                    (18, 75, '2024-05-21', '2024-05-31'),
                                                                                    (19, 190, '2024-06-01', '2024-06-10'),
                                                                                    (20, 170, '2024-06-11', '2024-06-20'),
                                                                                    (21, 120, '2024-06-21', '2024-06-30'),
                                                                                    (22, 90, '2024-07-01', '2024-07-10'),
                                                                                    (23, 80, '2024-07-11', '2024-07-20'),
                                                                                    (24, 110, '2024-07-21', '2024-07-31'),
                                                                                    (25, 130, '2024-08-01', '2024-08-10'),
                                                                                    (26, 70, '2024-08-11', '2024-08-20'),
                                                                                    (27, 200, '2024-08-21', '2024-08-31'),
                                                                                    (28, 180, '2024-09-01', '2024-09-10'),
                                                                                    (29, 100, '2024-09-11', '2024-09-20'),
                                                                                    (30, 90, '2024-09-21', '2024-09-30'),
                                                                                    (31, 120, '2024-10-01', '2024-10-10'),
                                                                                    (32, 110, '2024-10-11', '2024-10-20'),
                                                                                    (33, 95, '2024-10-21', '2024-10-31'),
                                                                                    (34, 140, '2024-11-01', '2024-11-10'),
                                                                                    (35, 130, '2024-11-11', '2024-11-20'),
                                                                                    (36, 75, '2024-11-21', '2024-11-30'),
                                                                                    (37, 180, '2024-12-01', '2024-12-10'),
                                                                                    (38, 160, '2024-12-11', '2024-12-20'),
                                                                                    (39, 90, '2024-12-21', '2024-12-31'),
                                                                                    (40, 120, '2025-01-01', '2025-01-10'),
                                                                                    (41, 110, '2025-01-11', '2025-01-20'),
                                                                                    (42, 50, '2025-01-01', '2025-01-10'),
                                                                                    (43, 100, '2025-01-11', '2025-01-20'),
                                                                                    (44, 60, '2025-01-23', '2025-02-10'),
                                                                                    (45, 40, '2025-02-11', '2025-03-20');

INSERT INTO public.accommodations (
    id, cancellation_deadline, description, is_automatically_accepted, address, city, country,
    max_guests, min_guests, name, is_pricing_per_person, type, host_id
) VALUES
      (1,  7, 'Beautiful Hotel near the beach', true, '123 Beachfront Blvd', 'Seaside City', 'Oceanland', 100, 1, 'Oceanfront Paradise Hotel', true, 1, 1),
      (2,  3, 'Cozy Hostel in the city center', false, '456 Downtown Street', 'Urbanville', 'Countryland', 50, 1, 'Downtown Hostel', true, 2, 2),
      (3,  3, 'Charming Bed and Breakfast in the countryside', true, '789 Countryside Lane', 'Rural Town', 'Countryland', 20, 1, 'Countryside B&B', false, 3, 3),
      (4,  3, 'Luxurious Resort with spa and golf course', false, '321 Luxury Resort Dr', 'Luxury City', 'Wealthyland', 200, 2, 'Grand Luxury Resort', true, 4, 4),
      (5,  3, 'Convenient Motel on the highway', true, '654 Highway Road', 'Roadside Town', 'Countryland', 30, 1, 'Highway Motel', true, 5, 1),
      (6,  3, 'Spacious Apartment in the city', true, '987 Downtown Avenue', 'Urbanville', 'Countryland', 80, 1, 'City View Apartment', true, 6, 2),
      (7,  3, 'Cozy House with garden', true, '135 Greenery Lane', 'Suburbia', 'Countryland', 150, 1, 'Green Oasis House', true, 7, 3),
      (8,  3, 'Private Room with a view', false, '246 Hilltop Street', 'Scenic Hills', 'Countryland', 2, 1, 'Scenic View Room', false, 8, 4),
      (9,  3, 'Rustic Tent in the woods', true, '579 Forest Trail', 'Natureville', 'Greenland', 4, 1, 'Woodland Retreat Tent', false, 6, 1),
      (10, 3,  'Modern Hotel with rooftop pool', true, '802 Skyline Avenue', 'Metropolis', 'Countryland', 120, 1, 'Skyline View Hotel', true, 1, 2),
      (11, 3,  'Historic Hostel in the old town', false, '153 Heritage Lane', 'Oldtown', 'Countryland', 60, 1, 'Heritage Hostel', true, 1, 3),
      (12, 3,  'Quaint Bed and Breakfast near the river', true, '467 Riverside Road', 'Rivertown', 'Countryland', 18, 1, 'Riverside B&B', false, 2, 4),
      (13, 3,  'Exclusive Resort with beachfront villas', false, '631 Beach Resort Drive', 'Exotic Beach', 'Tropicaland', 250, 2, 'Beachfront Paradise Resort', true, 3, 1),
      (14, 3,  'Classic Motel with retro vibes', true, '874 Retro Road', 'Nostalgia Town', 'Countryland', 25, 1, 'Retro Motel', true, 4, 2),
      (15, 3,  'Downtown Apartment with city views', true, '209 Urban Heights', 'Cityscape', 'Countryland', 90, 1, 'Urban Heights Apartment', true, 5, 3),
      (16, 3,  'Country Cottage with a serene garden', true, '362 Garden Lane', 'Tranquil Village', 'Countryland', 120, 1, 'Tranquil Garden Cottage', true, 1, 4),
      (17, 3,  'Cozy Room with fireplace', false, '513 Hearthstone Street', 'Fireside Village', 'Countryland', 2, 1, 'Fireside Retreat Room', false, 1, 1),
      (18, 3,  'Mountain Retreat Tent with panoramic views', true, '756 Mountain Trail', 'Mountain Haven', 'AlpineLand', 3, 1, 'Mountain Vista Tent', false, 8, 2),
      (19, 3,  'Luxury Hotel with spa and fine dining', true, '999 Opulence Avenue', 'Grand City', 'Wealthyland', 150, 1, 'Opulent Grand Hotel', true, 5, 3),
      (20, 3,  'Eco-friendly Hostel in a green neighborhood', false, '244 Green Haven', 'Sustainable City', 'Greenland', 40, 1, 'Green Haven Hostel', true, 2, 4);

INSERT INTO public.accommodations_amenities (accommodation_id, amenities_id) VALUES
                                                                                 (1, 1), (1, 2), (1, 3),
                                                                                 (2, 4), (2, 5), (2, 6),
                                                                                 (3, 7), (3, 8), (3, 9),
                                                                                 (4, 10), (4, 1), (4, 2),                                                                                    (5, 3), (5, 4), (5, 5),                                                                                    (6, 6), (6, 7), (6, 8),                                                                                    (7, 9), (7, 10), (7, 1),                                                                                   (8, 2), (8, 3), (8, 4),                                                                                    (9, 5), (9, 6), (9, 7),                                                                                    (10, 8), (10, 9), (10, 10),
                                                                                 (11, 1), (11, 2), (11, 3),
                                                                                 (12, 4), (12, 5), (12, 6),
                                                                                 (13, 7), (13, 8), (13, 9),
                                                                                 (14, 10), (14, 1), (14, 2),
                                                                                 (15, 3), (15, 4), (15, 5),
                                                                                 (16, 6), (16, 7), (16, 8),
                                                                                 (17, 9), (17, 10), (17, 1),
                                                                                 (18, 2), (18, 3), (18, 4),
                                                                                 (19, 5), (19, 6), (19, 7),
                                                                                 (20, 8), (20, 9), (20, 10);

INSERT INTO public.accommodations_available_periods (accommodation_id, available_periods_id) VALUES
                                                                                                 (1, 1),
                                                                                                 (1, 4),
                                                                                                 (1, 42),
                                                                                                 (1, 43),
                                                                                                 (1, 44),
                                                                                                 (2, 2),
                                                                                                 (2, 5),
                                                                                                 (3, 3),
                                                                                                 (3, 6),
                                                                                                 (4, 7),
                                                                                                 (4, 10),
                                                                                                 (5, 8),
                                                                                                 (5, 11),
                                                                                                 (6, 9),
                                                                                                 (6, 12),
                                                                                                 (7, 13),
                                                                                                 (7, 16),
                                                                                                 (8, 14),
                                                                                                 (8, 17),
                                                                                                 (9, 15),
                                                                                                 (9, 18),
                                                                                                 (10, 19),
                                                                                                 (10, 21),
                                                                                                 (11, 20),
                                                                                                 (11, 22),
                                                                                                 (12, 23),
                                                                                                 (12, 26),
                                                                                                 (13, 24),
                                                                                                 (13, 27),
                                                                                                 (14, 25),
                                                                                                 (14, 28),
                                                                                                 (15, 29),
                                                                                                 (15, 31),
                                                                                                 (16, 30),
                                                                                                 (16, 32),
                                                                                                 (17, 33),
                                                                                                 (17, 36),
                                                                                                 (18, 34),
                                                                                                 (18, 37),
                                                                                                 (19, 35),
                                                                                                 (19, 38),
                                                                                                 (20, 39),
                                                                                                 (20, 41);


INSERT INTO public.reservations (id, price, status, start_date, end_date, accommodation_id, guest_id) VALUES
                                                                                                          (1, 500, 2, '2023-12-05', '2023-12-10', 1, 5),
                                                                                                          (2, 600, 1, '2023-12-15', '2023-12-20', 2, 6),
                                                                                                          (3, 450, 2, '2024-01-05', '2024-01-10', 3, 7),
                                                                                                          (4, 400, 4, '2024-01-15', '2024-01-20', 4, 8),
                                                                                                          (5, 550, 2, '2024-02-05', '2024-02-10', 5, 9),
                                                                                                          (6, 470, 1, '2024-02-15', '2024-02-20', 6, 5),
                                                                                                          (7, 700, 2, '2024-03-05', '2024-03-10', 7, 6),
                                                                                                          (8, 650, 3, '2024-03-15', '2024-03-20', 8, 7),
                                                                                                          (9, 420, 2, '2024-04-05', '2024-04-10', 9, 8),
                                                                                                          (10, 800, 2, '2024-04-15', '2024-04-20', 10, 9),
                                                                                                          (11, 250, 4, '2023-01-10', '2023-01-15', 1, 5),
                                                                                                          (12, 700, 4, '2023-01-17', '2023-01-20', 1, 5),
                                                                                                          (13, 300, 4, '2023-01-25', '2023-01-29', 1, 5),
                                                                                                          (14, 4000, 4, '2023-02-05', '2023-02-15', 1, 5),
                                                                                                          (15, 1200, 4, '2023-02-20', '2023-02-24', 1, 5),
                                                                                                          (16, 720, 4, '2023-05-10', '2023-05-15', 1, 5),
                                                                                                          (17, 450, 4, '2023-06-03', '2023-06-04', 1, 5),
                                                                                                          (18, 500, 4, '2023-06-08', '2023-06-12', 1, 5),
                                                                                                          (19, 800, 4, '2023-08-10', '2023-08-15', 1, 5),
                                                                                                          (20, 900, 4, '2023-09-02', '2023-09-08', 1, 5),
                                                                                                          (21, 1500, 4, '2023-12-17', '2023-12-20', 1, 6),
                                                                                                          (22, 650, 4, '2023-12-22', '2023-12-30', 1, 6),
                                                                                                          (23, 1500, 4, '2024-02-21', '2024-02-28', 11, 5),
                                                                                                          (24, 650, 4, '2024-02-21', '2024-02-28', 12, 5),
                                                                                                          (25, 650, 4, '2024-02-25', '2024-02-28', 12, 5),
                                                                                                          (26, 650, 0, '2024-07-13', '2024-07-17', 12, 5),
                                                                                                          (27, 650, 0, '2024-07-14', '2024-07-18', 12, 5);


INSERT INTO public.reviews (
    id, comment, created_on, rating, status, reviewer_id
) VALUES
      (1, 'Great host!', '2023-11-28', 4, 0, 5),
      (2, 'Excellent experience!', '2023-11-29', 5, 0, 6),
      (3, 'Could be better.', '2023-11-30', 3, 0, 7),
      (4, 'Highly recommended!', '2023-12-01', 5, 0, 8),
      (5, 'Enjoyed my stay.', '2023-12-02', 4, 0, 9),
      (6, 'A wonderful stay! The staff was very friendly.', NOW(), 4.5, 0, 5),
      (7, 'The room was clean and comfortable. Great experience.', NOW(), 3.0, 0, 6),
      (8, 'Outstanding service and amenities. Highly recommend!', NOW(), 5.0, 0, 7),
      (9, 'Good value for the money. Enjoyed my time here.', NOW(), 4.0, 0, 8),
      (10, 'Disappointing experience. Room was not as expected.', NOW(), 2.5, 0, 9),
      (11, 'The accommodation exceeded my expectations. Amazing!', NOW(), 4.2, 0, 5),
      (12, 'Average stay. Nothing exceptional.', NOW(), 3.8, 0, 6),
      (13, 'Absolutely fantastic! Would definitely come back.', NOW(), 4.7, 0, 7),
      (14, 'Friendly staff and great location. Enjoyed my stay.', NOW(), 3.5, 0, 8),
      (15, 'Terrible experience. Would not recommend.', NOW(), 2.0, 0, 9);

INSERT INTO public.host_reviews (
    id, reviewed_host_id
) VALUES
      (1, 1),
      (2, 2),
      (3, 3),
      (4, 4),
      (5, 1);

INSERT INTO public.accommodation_reviews (
    id, reviewed_accommodation_id)
VALUES
    (6,1),
    (7, 2),
    (8, 3),
    (9, 4),
    (10, 5),
    (11, 6),
    (12, 7),
    (13, 6),
    (14, 10),
    (15, 10);


INSERT INTO public.reports (
    id, created_on, message, status, reportee_id
) VALUES
      (1, '2023-12-15', 'Inappropriate behavior', 0, 5),
      (2, '2023-12-16', 'Violated house rules', 0, 5),
      (3, '2023-12-17', 'Unresponsive host', 0, 6),
      (4, '2023-12-18', 'Misrepresentation of property', 0, 7),
      (5, '2023-12-19', 'Suspicious activity', 0, 8),
      (6, NOW(), 'Inappropriate behavior', 0, 1),
      (7, NOW(), 'Noise disturbance', 0, 2),
      (8, NOW(), 'Unpleasant experience', 0, 3),
      (9, NOW(), 'Disrespectful behavior', 0, 4),
      (10, NOW(), 'Repeated violation of policies', 0, 1),
      (11, NOW(), 'Disrespectful', 0, 1),
      (12, NOW(), 'Rude', 0, 2);

INSERT INTO public.host_reports (
    id, reported_host_id
) VALUES
      (1, 3),
      (2, 2),
      (3, 4),
      (4, 1),
      (5, 1);


INSERT INTO public.guest_reports (
    id, reported_guest_id
) VALUES
      (6, 5),
      (7, 6),
      (8, 7),
      (9, 8),
      (10, 9);

INSERT INTO public.review_reports (
    id, reported_review_id
) VALUES
      (11, 10),
      (12, 15);


INSERT INTO public.images(
    id, file_name, path)
VALUES (1, 'test8.jpg', 'accommodations\1'),
       (2, 'test3.jpg', 'accommodations\1' ),
       (3, 'test4.jpg', 'accommodations\1'),
       (4, 'test5.jpg', 'accommodations\1'),
       (5, 'test2.jpg', 'accommodations\2'),
       (6, 'test3.jpg', 'accommodations\3'),
       (7, 'test4.jpg', 'accommodations\4'),
       (8, 'test5.jpg', 'accommodations\5'),
       (9, 'test6.jpg', 'accommodations\6'),
       (10, 'test7.jpg', 'accommodations\7'),
       (11, 'test8.jpg', 'accommodations\8'),
       (12, 'test9.jpg', 'accommodations\9'),
       (13, 'test10.jpg', 'accommodations\10'),
       (14, 'test11.jpg', 'accommodations\11'),
       (15, 'test12.jpg', 'accommodations\12'),
       (16, 'test13.jpg', 'accommodations\13'),
       (17, 'test14.jpg', 'accommodations\14'),
       (18, 'test15.jpg', 'accommodations\15'),
       (19, 'test16.jpg', 'accommodations\16'),
       (20, 'test17.jpg', 'accommodations\17'),
       (21, 'test18.jpg', 'accommodations\18'),
       (22, 'test19.jpg', 'accommodations\19'),
       (23, 'test20.jpg', 'accommodations\20');

INSERT INTO public.accommodations_images(
    accommodation_id, images_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 5),
       (3, 6),
       (4, 7),
       (5, 8),
       (6, 9),
       (7, 10),
       (8, 11),
       (9, 12),
       (10, 13),
       (11, 14),
       (12, 15),
       (13, 16),
       (14, 17),
       (15, 18),
       (16, 19),
       (17, 20),
       (18, 21),
       (19, 22),
       (20, 23);



INSERT INTO public.accommodation_modifications(
    id, cancellation_deadline, description, is_automatically_accepted, address, city, country, max_guests, min_guests, name, pricing_per_person, request_date, request_type, status, type, accommodation_id, host_id)
VALUES (1, 7, 'Beautiful Hotel near the beach', true, '123 Beachfront Blvd', 'Seaside City', 'Oceanland', 100, 1, 'Oceanfront Paradise Hotel', true,NOW(), 0, 0, 1, 1, 1),
       (2, 3, 'Cozy Hostel in the city center', false, '456 Downtown Street', 'Urbanville', 'Countryland', 50, 1, 'Downtown Hostel', true,NOW(), 0, 0, 2, 2, 2),
       (3, 3, 'Charming Bed and Breakfast in the countryside', true, '789 Countryside Lane', 'Rural Town', 'Countryland', 20, 1, 'Countryside B&B', false, NOW(), 0, 0, 3, 3, 3),
       (4, 3, 'Luxurious Resort with spa and golf course', false, '321 Luxury Resort Dr', 'Luxury City', 'Wealthyland', 200, 2, 'Grand Luxury Resort', true, NOW(), 0, 0, 4, 4, 4),
       (5, 3, 'Convenient Motel on the highway', true, '654 Highway Road', 'Roadside Town', 'Countryland', 30, 1, 'Highway Motel', true, NOW(), 0, 0, 1, 5, 1);

INSERT INTO public.accommodation_modifications_amenities (accommodation_modification_id, amenities_id) VALUES
                                                                                                           (1, 1), (1, 2), (1, 3),
                                                                                                           (2, 4), (2, 5), (2, 6),
                                                                                                           (3, 7), (3, 8), (3, 9),
                                                                                                           (4, 10), (4, 1), (4, 2),
                                                                                                           (5, 3), (5, 4), (5, 5);


INSERT INTO public.accommodation_modifications_images(
    accommodation_modification_id, images_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);
