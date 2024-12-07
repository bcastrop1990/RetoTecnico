CREATE TABLE IF NOT EXISTS user_login (
  username VARCHAR(20) PRIMARY KEY,
  password VARCHAR(200) NOT NULL,
  email VARCHAR(50),
  locked TINYINT NOT NULL,
  disabled TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role (
  username VARCHAR(20) NOT NULL,
  role VARCHAR(20) NOT NULL,
  granted_date DATETIME NOT NULL,
  PRIMARY KEY (username, role),
  FOREIGN KEY (username) REFERENCES user_login(username)
);