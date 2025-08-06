-- First, insert product images
INSERT INTO product_image (id, name, type, image) VALUES
(-1, 'iphone15.jpg', 'image/jpeg', null),
(-2, 'galaxy_s24.jpg', 'image/jpeg', null),
(-3, 'pixel8.jpg', 'image/jpeg', null),
(-4, 'macbook_pro.jpg', 'image/jpeg', null),
(-5, 'thinkpad_x1.jpg', 'image/jpeg', null),
(-6, 'sony_wh1000xm5.jpg', 'image/jpeg', null),
(-7, 'airpods_pro2.jpg', 'image/jpeg', null),
(-8, 'switch_oled.jpg', 'image/jpeg', null),
(-9, 'ps5.jpg', 'image/jpeg', null),
(-10, 'kindle_paperwhite.jpg', 'image/jpeg', null);

-- Now insert products, linking each to its image
INSERT INTO product (name, brand, category, description, price, release_date, is_available, quantity, image_id) VALUES
('iPhone 15', 'Apple', 'Smartphones', 'The iPhone 15 features Apple''s latest A17 Bionic chip, an advanced camera system with improved low-light performance, Dynamic Island design, and extended battery life for all-day use. It runs iOS 17 and supports MagSafe accessories and fast wireless charging.', 999.99, '2024-09-01', TRUE, 50, -1),
('Galaxy S24', 'Samsung', 'Smartphones', 'Samsung Galaxy S24 delivers a high-end Android experience with a 120Hz AMOLED display, Snapdragon Gen 3 processor, triple-lens AI-enhanced camera system, and robust battery optimized for gaming and multitasking. Includes One UI 6 with extended security updates.', 899.99, '2024-08-20', TRUE, 40, -2),
('Pixel 8', 'Google', 'Smartphones', 'Google Pixel 8 offers a pure Android experience with Material You theming, Google Tensor chip, live translation, AI-based camera features, and regular OS updates directly from Google. It’s known for sharp photos and clean UI.', 799.00, '2023-10-15', TRUE, 35, -3),
('MacBook Pro 14"', 'Apple', 'Laptops', 'This M3-powered MacBook Pro 14" delivers exceptional performance for professional tasks like video editing, 3D rendering, and software development. It includes a Liquid Retina XDR display, long battery life, and a refined aluminum chassis.', 1999.00, '2023-11-05', TRUE, 15, -4),
('ThinkPad X1 Carbon', 'Lenovo', 'Laptops', 'The Lenovo ThinkPad X1 Carbon Gen 11 is a lightweight, business-class ultrabook with military-grade durability, a bright 2K display, Dolby Atmos speakers, and long battery life. Ideal for remote professionals and enterprise environments.', 1599.99, '2024-01-12', TRUE, 20, -5),
('Sony WH-1000XM5', 'Sony', 'Headphones', 'Sony WH-1000XM5 are over-ear wireless headphones featuring industry-leading active noise cancellation, adaptive sound control, 30-hour battery life, high-resolution audio with LDAC, and voice assistant integration.', 349.50, '2022-05-10', TRUE, 60, -6),
('AirPods Pro 2', 'Apple', 'Headphones', 'Apple AirPods Pro 2 offer rich sound, adaptive transparency, spatial audio, and personalized noise cancellation. The updated model includes improved battery life and a USB-C MagSafe case for convenience and wireless charging.', 249.00, '2023-09-15', TRUE, 45, -7),
('Nintendo Switch OLED', 'Nintendo', 'Gaming', 'The Nintendo Switch OLED edition features a vibrant 7-inch OLED display, enhanced audio, improved kickstand, and 64GB internal storage. It supports both handheld and docked modes, perfect for gaming at home or on the go.', 349.99, '2021-10-08', TRUE, 25, -8),
('PlayStation 5', 'Sony', 'Gaming', 'Sony PlayStation 5 delivers next-gen performance with a blazing-fast SSD, haptic feedback-enabled DualSense controller, ray-traced graphics, and 3D audio. Ideal for immersive single-player and competitive multiplayer gaming.', 499.99, '2020-11-12', TRUE, 10, -9),
('Kindle Paperwhite', 'Amazon', 'E-Readers', 'Amazon Kindle Paperwhite features a high-resolution glare-free screen, adjustable warm light, waterproof design (IPX8), and weeks-long battery life. Great for reading indoors or outdoors, even in direct sunlight.', 139.99, '2022-11-01', TRUE, 70, -10);

INSERT INTO users (id, username, password_hash, first_name, last_name)
VALUES (1, 'admin@example.com', '$2a$10$L1GkbuTNeeqBq2nB0FsJZe7sYc8ic.6Q/8Uwy5k3Sagb1EZqhvpeq', 'Admin', 'Admin');

INSERT INTO user_roles (user_id, role) VALUES (1, 'ROLE_ADMIN');