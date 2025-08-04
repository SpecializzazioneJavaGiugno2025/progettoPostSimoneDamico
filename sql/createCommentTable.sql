CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    body TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    article_id BIGINT,
    FOREIGN KEY (article_id) REFERENCES articles(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);