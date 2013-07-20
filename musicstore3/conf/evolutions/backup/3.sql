# --- !Ups



CREATE TABLE purchase
(
  id              int          NOT NULL,
  firstName            varchar(255) NOT NULL,
  lastName            varchar(255) NOT NULL,
  quantity           int          NOT NULL,

  PRIMARY KEY     (id)

);




# --- !Downs
