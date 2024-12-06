-- Inserto los datos de usuarios:
INSERT INTO recruitment_db.user_login (username, password, email, locked, disabled)
VALUES 
  ('john_doe', 'password123', 'john.doe@example.com', 0, 0),
  ('jane_smith', 'admin123', 'jane.smith@example.com', 0, 0);

-- Finalmente, inserto los datos de roles:
INSERT INTO recruitment_db.user_role (username, role, granted_date)
VALUES 
  ('john_doe', 'CANDIDATO', '2023-04-01 12:00:00'),
  ('jane_smith', 'ADMIN', '2023-04-01 12:00:00');
