-- Insert a dummy store with ID 4
INSERT INTO store (id, store_name, register_number, building_number, shop_number, street, zipcode, city, state, country)
VALUES
    (4, 'Dummy Store', 'REG1234', '001', 'A', 'Dummy Street', '12345', 'Dummy City', 'DC', 'Dummy Country');

-- Insert dummy data into Barcode
INSERT INTO barcode (code, tenant_id)
SELECT
    'CODE' || i,
    (RANDOM() * 10)::BIGINT
FROM generate_series(1, 2000) AS s(i);

-- Insert dummy data into Category with store_id = 4
INSERT INTO category (name, store_id)
SELECT
    'Category ' || i,
    4
FROM generate_series(1, 2000) AS s(i);

-- Insert dummy data into Supplier with store_id = 4
INSERT INTO supplier (name, building_number, shop_number, street, zipcode, city, state, country, tenant_id, store_id)
SELECT
    'Supplier ' || i,
    'Building ' || i,
    'Shop ' || i,
    'Street ' || i,
    -- Ensure the length of zipcode is within the allowed limit, e.g., 5 characters
    LPAD(i::TEXT, 5, '0'),  -- Use LPAD to ensure zipcode has a fixed length
    'City ' || i,
    'State ' || i,
    'Country ' || i,
    (RANDOM() * 10)::BIGINT,
    4
FROM generate_series(1, 2000) AS s(i);

-- Insert dummy data into Product with store_id = 4
INSERT INTO product (id, name, description, tenant_id, store_id, price, stock_quantity, supplier_id, barcode_id, category_id, created_date, updated_date)
SELECT
    i,
    'Product ' || i,
    'Description for product ' || i,
    (RANDOM() * 10)::BIGINT,
    4,
    123.23,
    (RANDOM() * 100)::INT,
    (i % 2000) + 1,
    i,
    (i % 2000) + 1,
    -- Random created_date between '2023-01-01' and '2024-12-31'
    (CURRENT_DATE - (RANDOM() * 365 * 2) * INTERVAL '1 day')::DATE,
    -- Random updated_date between created_date and 30 days after
    ((CURRENT_DATE - (RANDOM() * 365 * 2) * INTERVAL '1 day') + (RANDOM() * 30) * INTERVAL '1 day')::DATE
FROM generate_series(1, 2000) AS s(i);




-- Insert dummy data into Bill with store_id = 4
INSERT INTO bill (id, is_parked, store_id)
SELECT
    i,
    false,
    4
FROM generate_series(1, 2000) AS s(i);


-- Insert dummy data into BilledItem
INSERT INTO billed_item (bill_id, product_id, quantity)
SELECT
    (RANDOM() * 2000)::BIGINT,
    (RANDOM() * 2000)::BIGINT,
    (RANDOM() * 10)::INT
FROM generate_series(1, 2000) AS s(i);

-- Ensure the sequence for id fields starts after inserting data
ALTER SEQUENCE product_id_seq RESTART WITH 2001;
ALTER SEQUENCE category_id_seq RESTART WITH 2001;
ALTER SEQUENCE store_id_seq RESTART WITH 2001;
ALTER SEQUENCE supplier_id_seq RESTART WITH 2001;
ALTER SEQUENCE bill_id_seq RESTART WITH 2001;
ALTER SEQUENCE billed_item_id_seq RESTART WITH 2001;
