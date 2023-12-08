CREATE TABLE task_management.user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    passhash VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

INSERT INTO task_management.user (username, passhash, email, role)
VALUES ('user1', 'pass1', 'user1@example.com', 'user'),
             ('user2', 'pass2', 'user2@example.com', 'user'),
             ('user3', 'pass3', 'user3@example.com', 'user');
