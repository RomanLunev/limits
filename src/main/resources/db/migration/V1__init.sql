CREATE TABLE user_limits (id bigserial PRIMARY KEY, user_id int8 NOT NULL UNIQUE, amount number NOT NULL);

insert into user_limits(user_id, amount) values (1, 10000.00), (2, 9999.99);
