# --- !Ups

ALTER TABLE products
	MODIFY id int NOT NULL AUTO_INCREMENT;

INSERT INTO products (name, price, img) 
VALUES ("Violin", 23, "");





# --- !Downs
