# --- !Ups

USE musicstore;

DROP TABLE IF EXISTS products;
CREATE TABLE products
(
  id              int          NOT NULL,
  name            varchar(255) NOT NULL,
  price           int          NOT NULL,
  img			  varchar(255) NOT NULL,

  PRIMARY KEY     (id)

);


# --- !Downs
