CREATE TABLE reviews
(
    review_id  INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT          NOT NULL,
    user_name  VARCHAR(100) NOT NULL,
    rating    TINYINT CHECK (rating BETWEEN 1 AND 5),
    comment   TEXT,
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);