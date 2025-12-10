-- ===============================
-- FINAL data.sql – PROFESSIONAL VERSION (2025-12-10)
-- ===============================

-- Disable foreign key constraints temporarily
ALTER TABLE payments DROP CONSTRAINT IF EXISTS fk_order_id;
ALTER TABLE order_items DROP CONSTRAINT IF EXISTS fk_order_id;
ALTER TABLE order_items DROP CONSTRAINT IF EXISTS fk_product_id;
ALTER TABLE orders DROP CONSTRAINT IF EXISTS fk_client_id;
ALTER TABLE clients DROP CONSTRAINT IF EXISTS fk_user_id;

-- Clean tables + reset sequences
TRUNCATE TABLE payments RESTART IDENTITY CASCADE;
TRUNCATE TABLE order_items RESTART IDENTITY CASCADE;
TRUNCATE TABLE orders RESTART IDENTITY CASCADE;
TRUNCATE TABLE clients RESTART IDENTITY CASCADE;
TRUNCATE TABLE users RESTART IDENTITY CASCADE;
TRUNCATE TABLE promo_codes RESTART IDENTITY CASCADE;
TRUNCATE TABLE products RESTART IDENTITY CASCADE;


-- ===============================
-- 1. USERS
-- ===============================
INSERT INTO users (id, username, password, role, created_at) VALUES
    (1, 'admin_master',     'Adm!n#2025$',     'ADMIN',  NOW()),
    (2, 'alpha_corporate',  'Client#A45!x',    'CLIENT', NOW()),
    (3, 'nova_enterprises', 'Client#B78@q',    'CLIENT', NOW()),
    (4, 'orion_techlab',    'Client#C91$v',    'CLIENT', NOW()),
    (5, 'stellar_group',    'Client#D15*l',    'CLIENT', NOW()),
    (6, 'horizon_trade',    'Client#E62&w',    'CLIENT', NOW());


-- ===============================
-- 2. CLIENTS
-- ===============================
INSERT INTO clients (id, user_id, name, email, phone, address, tier, total_orders, total_spent, created_at, updated_at) VALUES
    (1, 2, 'Alpha Corporate SARL',       'contact@alpha-corp.ma',     '0611122233',  'Casablanca, Finance City',    'BASIC',     0,     0.00, NOW(), NOW()),
    (2, 3, 'Nova Enterprises',           'support@nova.ma',            '0622233344',  'Rabat, Hay Riad',             'SILVER',    3,  1200.00, NOW(), NOW()),
    (3, 4, 'Orion TechLab',              'info@oriontechlab.ma',       '0633344455',  'Marrakech, Gueliz Tech Park','GOLD',     12,  6500.00, NOW(), NOW()),
    (4, 5, 'Stellar Digital Group',      'admin@stellargroup.ma',      '0644455566',  'Tanger, Business District',   'PLATINUM', 25, 18000.00, NOW(), NOW()),
    (5, 6, 'Horizon Trade Solutions',    'contact@horizontrade.ma',    '0655566677',  'Fes, Ville Nouvelle',        'BASIC',     1,   450.00, NOW(), NOW());


-- ===============================
-- 3. PROMO CODES
-- ===============================
INSERT INTO promo_codes (id, code, used, used_at, order_id) VALUES
    (1, 'NEWCLIENT-10', FALSE, NULL, NULL),
    (2, 'BLACKFRIDAY25', TRUE,  NULL, NULL),
    (3, 'WELCOME-2025',  FALSE, NULL, NULL);


-- ===============================
-- 4. PRODUCTS
-- ===============================
INSERT INTO products (id, name, description, unit_price, stock, deleted, created_at, updated_at) VALUES
    (1,  'HP ProBook 470 G10',         'Intel i7, 16GB RAM, 512GB SSD',          9200.00, 20, FALSE, NOW(), NOW()),
    (2,  'Dell UltraSharp 27"',        'QHD, IPS, USB-C Hub',                    3300.00, 40, FALSE, NOW(), NOW()),
    (3,  'Logitech MX Master 3S',      'Wireless, ergonomic, silent clicks',      990.00, 80, FALSE, NOW(), NOW()),
    (4,  'Canon Laser Printer LBP236', 'Mono laser, WiFi, 38 ppm',               2600.00, 15, FALSE, NOW(), NOW()),
    (5,  'Anker PowerExpand 8-in-1',   'USB-C Hub, 4K HDMI, USB 3.1',             520.00, 70, FALSE, NOW(), NOW()),
    (6,  'Keychron K6 RGB Wireless',   'Hot-swappable, mechanical',              1050.00, 35, FALSE, NOW(), NOW()),
    (7,  'Seagate Backup Plus 2TB',    'External HDD USB 3.0',                    690.00, 50, FALSE, NOW(), NOW()),
    (8,  'Logitech StreamCam',         '1080p60, autofocus',                     1150.00, 25, FALSE, NOW(), NOW()),
    (9,  'TP-Link Archer AX73',        'WiFi 6 router, Gigabit',                 1300.00, 20, FALSE, NOW(), NOW()),
    (10, 'Aluminum Laptop Stand',      'Adjustable, heat dissipation',            290.00, 70, FALSE, NOW(), NOW()),
    (11, 'Epson GT-1500 Scanner',      'Discontinued model',                     1400.00, 0, TRUE,  '2024-01-10 10:00:00', NOW());


-- ===============================
-- 5. ORDERS
-- ===============================
INSERT INTO orders (id, client_id, status, promo_code, subtotal_ht, loyalty_discount, promo_discount, total_discount, amount_ht, tva_amount, total_ttc, remaining_amount, created_at, updated_at) VALUES
    (1, 2, 'CONFIRMED', NULL,         600.00,  30.00,  0.00, 30.00, 570.00, 114.00, 684.00,   0.00, '2025-11-01 10:00:00', NOW()),
    (2, 3, 'CONFIRMED', 'NEWCLIENT-10',900.00, 90.00, 45.00,135.00, 765.00,153.00, 918.00,   0.00, '2025-11-05 14:30:00', NOW()),
    (3, 4, 'PENDING',   NULL,        1500.00,225.00,  0.00,225.00,1275.00,255.00,1530.00, 530.00,'2025-11-10 09:15:00', NOW()),
    (4, 1, 'PENDING',   NULL,         350.00,  0.00,  0.00,  0.00, 350.00, 70.00, 420.00, 420.00,'2025-11-20 16:00:00', NOW()),
    (5, 5, 'CANCELED',  NULL,         800.00,  0.00,  0.00,  0.00, 800.00,160.00, 960.00, 960.00,'2025-11-15 11:30:00', NOW()),
    (6, 2, 'REJECTED',  NULL,           0.00,  0.00,  0.00,  0.00,   0.00,  0.00,   0.00,   0.00,'2025-11-18 13:00:00', NOW()),
    (7, 2, 'PENDING',   NULL,        1200.00, 60.00,  0.00, 60.00,1140.00,228.00,1368.00,   0.00,'2025-11-25 10:00:00', NOW());


-- ===============================
-- 6. ORDER ITEMS
-- ===============================
INSERT INTO order_items (id, order_id, product_id, quantity, unit_price, line_total) VALUES
    (1, 1, 3, 4, 150.00, 600.00),
    (2, 2, 6, 1,1050.00,1050.00),
    (3, 2, 8, 1, 100.00, 100.00),
    (4, 3, 2, 1,3300.00,3300.00),
    (5, 3,10, 1, 300.00, 300.00),
    (6, 4, 5, 1, 520.00, 520.00),
    (7, 5, 6, 1,1050.00,1050.00),
    (8, 7, 2, 1,3300.00,3300.00);


-- ===============================
-- 7. PAYMENTS
-- ===============================
INSERT INTO payments (id, order_id, payment_number, amount, payment_method, status, reference, payment_date, created_at) VALUES
    (1, 1, 1, 684.00, 'CASH',          'ENCAISSE',   'PAY-2025-0001', '2025-11-01 10:30:00', NOW()),
    (2, 2, 1, 500.00, 'CASH',          'ENCAISSE',   'PAY-2025-0002', '2025-11-05 14:45:00', NOW()),
    (3, 2, 2, 418.00, 'CHECK',         'ENCAISSE',   'CHK-2025-7788', '2025-11-10 09:00:00', NOW()),
    (4, 3, 1, 600.00, 'CASH',          'ENCAISSE',   'PAY-2025-0003', '2025-11-10 09:30:00', NOW()),
    (5, 3, 2, 400.00, 'CHECK',         'EN_ATTENTE', 'CHK-2025-9921', '2025-12-05 00:00:00', NOW()),
    (6, 7, 1,1368.00,'WIRE_TRANSFER', 'ENCAISSE',   'VIR-2025-1125', '2025-11-25 10:30:00', NOW());


-- ===============================
-- RESET SEQUENCES
-- ===============================
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('clients_id_seq', (SELECT MAX(id) FROM clients));
SELECT setval('promo_codes_id_seq', (SELECT MAX(id) FROM promo_codes));
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));
SELECT setval('orders_id_seq', (SELECT MAX(id) FROM orders));
SELECT setval('order_items_id_seq', (SELECT MAX(id) FROM order_items));
SELECT setval('payments_id_seq', (SELECT MAX(id) FROM payments));
