DROP TABLE IF EXISTS t_actor;

CREATE TABLE t_actor (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL
);
