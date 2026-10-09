CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Seed some baseline customer data
INSERT INTO customers (full_name) VALUES ('Jane Doe');
INSERT INTO customers (full_name) VALUES ('John Smith');