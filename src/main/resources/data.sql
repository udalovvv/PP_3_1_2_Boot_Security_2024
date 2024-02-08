INSERT INTO users (`age`, `email`, `first_name`, `last_name`, `password`)
VALUES ('1', 'admin@mail.ru', 'admin', 'admin', '$2a$12$iCZIGBvFU6Yud/n15FBFzu95xo4413RCEr2ou4FVdAxiv1WGmnpXC');
-- password = admin

INSERT INTO users (`age`, `email`, `first_name`, `last_name`, `password`)
VALUES ('2', 'user@mail.ru', 'user', 'user', '$2a$12$7nQFArHFyDuVN0uH13O7ius6ij/D.Ywv0EoETOGgK4zcVCnkyVls6');
-- password = 1

INSERT INTO roles (`role`)
VALUES ('ADMIN');

INSERT INTO roles (`role`)
VALUES ('USER');

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 1);

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (2, 2);