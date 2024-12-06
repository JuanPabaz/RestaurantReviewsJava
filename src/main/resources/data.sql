INSERT INTO image (image_url) VALUES
                    ('path/to/image1.jpg'),
                    ( 'path/to/image2.jpg'),
                    ( 'path/to/image3.jpg')
ON CONFLICT (id_image) DO NOTHING;

INSERT INTO category (category, description, id_image) VALUES
                    ('Italian', 'Italian cuisine featuring pasta, pizza, and more', 1),
                    ( 'Mexican', 'Traditional Mexican food with vibrant flavors', 2),
                    ( 'Japanese', 'Authentic Japanese sushi, ramen, and more', 3)
ON CONFLICT (id_category) DO NOTHING;

INSERT INTO restaurant (restaurant_name, address, phone_number, category_id) VALUES
                        ( 'Marios Italian Bistro', '123 Pasta Lane', '555-1234', 1),
                        ( 'Taco Fiesta', '456 Avocado Ave', '555-5678', 2),
                        ( 'Sushi Paradise', '789 Sushi St', '555-9876', 3),
                        ( 'Pasta Lovers', '321 Ravioli Rd', '555-4321', 1),
                        ( 'Burrito Haven', '654 Lime Dr', '555-8765', 2)
ON CONFLICT (id_restaurant) DO NOTHING;
