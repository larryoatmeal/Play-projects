DROP TABLE IF EXISTS purchase;

 CREATE TABLE purchase
(
  productid              int          NOT NULL,
  firstName            varchar(255) NOT NULL,
  lastName            varchar(255) NOT NULL,
  quantity           int          NOT NULL,
  customerid		int			NOT NULL AUTO_INCREMENT,

  PRIMARY KEY     (customerid)

);

CREATE TABLE products
(
  id              int          NOT NULL AUTO_INCREMENT,
  name            varchar(255) NOT NULL,
  price           int          NOT NULL,
  img			  varchar(255) NOT NULL,

  PRIMARY KEY     (id)

);
INSERT INTO products (name, price, img) 
VALUES ("Alto sax", 23, "");
INSERT INTO products (name, price, img) 
VALUES ("Tenor Sax", 24, "");
INSERT INTO products (name, price, img) 
VALUES ("Baritone Sax", 33, "");
INSERT INTO products (name, price, img) 
VALUES ("Alto flute", 76, "");
INSERT INTO products (name, price, img) 
VALUES ("Piccolo", 754, "");
INSERT INTO products (name, price, img) 
VALUES ("Flute", 27, "");
INSERT INTO products (name, price, img) 
VALUES ("Clarinet", 485, "");
INSERT INTO products (name, price, img) 
VALUES ("Bass Clarinet", 2222, "");
INSERT INTO products (name, price, img) 
VALUES ("Eb Clarinet", 23, "");
INSERT INTO products (name, price, img) 
VALUES ("Bassoon", 2009, "");
INSERT INTO products (name, price, img) 
VALUES ("Contrabassoon", 2984, "");
INSERT INTO products (name, price, img) 
VALUES ("Oboe", 2264, "");
INSERT INTO products (name, price, img) 
VALUES ("English Horn", 2323, "");
INSERT INTO products (name, price, img) 
VALUES ("French Horn", 823, "");
INSERT INTO products (name, price, img) 
VALUES ("Trumpet", 723, "");
INSERT INTO products (name, price, img) 
VALUES ("Tuba", 9923, "");
INSERT INTO products (name, price, img) 
VALUES ("Viola", 423, "");
INSERT INTO products (name, price, img) 
VALUES ("Cello", 233, "");
INSERT INTO products (name, price, img) 
VALUES ("Bass", 523, "");
INSERT INTO products (name, price, img) 
VALUES ("Guitar", 908, "");
INSERT INTO products (name, price, img) 
VALUES ("Marimba", 2463, "");
INSERT INTO products (name, price, img) 
VALUES ("Xylophone", 88883, "");
INSERT INTO products (name, price, img) 
VALUES ("Synthesizer", 983, "");
INSERT INTO products (name, price, img) 
VALUES ("Organ", 63, "");
INSERT INTO products (name, price, img) 
VALUES ("Drum Set", 772773, "");