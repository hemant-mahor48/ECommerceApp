INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronic gadgets and devices', 'Electronics'),
    (nextval('category_seq'), 'Clothing and accessories', 'Apparel'),
    (nextval('category_seq'), 'Kitchen and home appliances', 'Home & Kitchen'),
    (nextval('category_seq'), 'Sports equipment and gear', 'Sports'),
    (nextval('category_seq'), 'Books and stationery items', 'Books');


INSERT INTO product (id, name, description, available_qty, price, category_id)
VALUES
    (nextval('product_seq'), 'Smartphone', 'Latest Android smartphone with 5G', 50, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Laptop', 'Lightweight laptop with 16GB RAM', 20, 1199.50, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'T-Shirt', 'Cotton round-neck T-shirt', 100, 15.99, (SELECT id FROM category WHERE name = 'Apparel')),
    (nextval('product_seq'), 'Jeans', 'Denim jeans for casual wear', 80, 39.99, (SELECT id FROM category WHERE name = 'Apparel')),
    (nextval('product_seq'), 'Microwave Oven', 'Convection microwave oven', 15, 199.00, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    (nextval('product_seq'), 'Blender', 'Multi-speed blender with jar', 40, 49.50, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    (nextval('product_seq'), 'Cricket Bat', 'English willow cricket bat', 25, 120.00, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('product_seq'), 'Football', 'FIFA-approved size 5 football', 60, 25.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('product_seq'), 'Novel', 'Bestselling mystery novel', 200, 9.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Notebook', 'Hardcover ruled notebook', 150, 3.49, (SELECT id FROM category WHERE name = 'Books'));
