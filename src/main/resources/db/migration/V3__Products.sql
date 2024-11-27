CREATE TABLE products
(
    product_id      INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(255)   NOT NULL,
    description     TEXT,
    price           DECIMAL(10, 2) NOT NULL,
    color           VARCHAR(50),
    country_id      INT            NOT NULL,
    category_id     INT            NOT NULL,
    rating          TINYINT CHECK (rating BETWEEN 1 AND 5),
    image_url       VARCHAR(255),
    stock_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (category_id) REFERENCES categories (category_id),
    FOREIGN KEY (country_id) REFERENCES countries (country_id)
);