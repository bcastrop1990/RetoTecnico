CREATE TABLE IF NOT EXISTS candidates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    gender VARCHAR(20),
    salary_expected DECIMAL(10,2),
    phone VARCHAR(20),
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO candidates (name, email, gender, salary_expected) VALUES
('Juan Pérez', 'juan@email.com', 'MALE', 50000),
('María García', 'maria@email.com', 'FEMALE', 55000);