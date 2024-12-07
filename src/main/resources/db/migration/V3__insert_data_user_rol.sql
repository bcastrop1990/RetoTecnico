INSERT INTO user_login (username, password, email, locked, disabled)
VALUES 
  ('john_doe', '$2a$13$DhdPa3soBqQ/HvORfNq/NOIsQNRjhAH6ujW1p7Zdb5gmF8GaMGdju', 'john.doe@example.com', 0, 0),
  ('jane_smith', '$2a$13$7yJTkmQnYeW7kMGKcMtNQue.VjF9i55RgM3E8PHiLdYnaaIHXA14y', 'jane.smith@example.com', 0, 0);

INSERT INTO user_role (username, role, granted_date)
VALUES 
  ('john_doe', 'CANDIDATO', '2023-04-01 12:00:00'),
  ('jane_smith', 'ADMIN', '2023-04-01 12:00:00');