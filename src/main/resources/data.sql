INSERT INTO image (id_image, image_url) VALUES
                    (1, 'path/to/image1.jpg'),
                    (2, 'path/to/image2.jpg'),
                    (3, 'path/to/image3.jpg')
ON CONFLICT (id_image) DO NOTHING;

INSERT INTO category (id_category, category, description, id_image) VALUES
                    (1, 'Italian', 'Italian cuisine featuring pasta, pizza, and more', 1),
                    (2, 'Mexican', 'Traditional Mexican food with vibrant flavors', 2),
                    (3, 'Japanese', 'Authentic Japanese sushi, ramen, and more', 3)
ON CONFLICT (id_image) DO NOTHING;

INSERT INTO restaurant (id_restaurant, restaurant_name, address, phone_number, category_id) VALUES
                        (1, 'Marios Italian Bistro', '123 Pasta Lane', '555-1234', 1),
                        (2, 'Taco Fiesta', '456 Avocado Ave', '555-5678', 2),
                        (3, 'Sushi Paradise', '789 Sushi St', '555-9876', 3),
                        (4, 'Pasta Lovers', '321 Ravioli Rd', '555-4321', 1),
                        (5, 'Burrito Haven', '654 Lime Dr', '555-8765', 2)
ON CONFLICT (id_restaurant) DO NOTHING;
