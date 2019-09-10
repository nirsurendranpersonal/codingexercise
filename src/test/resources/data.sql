DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

INSERT INTO product(name) VALUES
('Obsidian'),
('Yeezy Boost 350'),
('Kith Beach Slide');

DROP TABLE IF EXISTS product_attributes;

CREATE TABLE product_attributes (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    size_fitness INT,
    foreign key (product_id) references product(id));