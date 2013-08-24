
# --- !Ups


DROP TABLE IF EXISTS entry;


CREATE TABLE entry (
  user_id int(11) NOT NULL,
  comment varchar(1000) DEFAULT NULL,
  date varchar(255) NOT NULL,
  img varchar(1000) DEFAULT NULL,
  entry_id int(11) NOT NULL AUTO_INCREMENT,
  instrument_id int(11) NOT NULL,
  PRIMARY KEY (entry_id)
);





INSERT INTO entry VALUES (1,'Trombone is a brass instrument','2/5/13','http://messengermusic.pbworks.com/f/1299257373/trombone_1.jpg',1,1),(2,'Trombone is the best instrument','2/7/13',NULL,2,1),(1,'Jonah plays trumpet','7/2/13','http://guitartime.ru/images/af/duxovie/trumpet_trumpeting.gif',3,2),(1,'Trumpet has three valves','7/4/13',NULL,4,2),(1,'Rite of Spring','6/26/2013 -22:42',NULL,5,3);







DROP TABLE IF EXISTS instrument;


CREATE TABLE instrument (
  name varchar(255) NOT NULL,
  instrument_id int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (instrument_id)
) ;








INSERT INTO instrument VALUES ('Trombone',1),('Trumpet',2),('Bassoon',3);







DROP TABLE IF EXISTS likes;


CREATE TABLE likes (
  entry_id int(11) NOT NULL,
  user_id int(11) NOT NULL
);








INSERT INTO likes VALUES (1,1),(4,1),(5,1);









DROP TABLE IF EXISTS products;


CREATE TABLE products (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  price int(11) NOT NULL,
  img varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ;








INSERT INTO products VALUES (1,'Alto sax',23,'http://www.gifs.net/Animation11/Everything_Else/Musical_Instruments/Sax_4.gif'),(2,'Tenor Sax',24,''),(3,'Baritone Sax',33,'http://bassic-sax.info/blog/wp-content/uploads/2013/01/Bari-With-Ray-Bans.jpg'),(4,'Alto flute',76,'http://www.musicalinstrumenthaven.com/ProductImages/ARMSTRONG%20703%20FLUTETMB.jpg'),(5,'Piccolo',754,''),(6,'Flute',27,''),(7,'Clarinet',485,''),(8,'Bass Clarinet',2222,''),(9,'Eb Clarinet',23,''),(10,'Bassoon',2009,''),(11,'Contrabassoon',2984,''),(12,'Oboe',2264,''),(13,'English Horn',2323,''),(14,'French Horn',823,''),(15,'Trumpet',723,''),(16,'Tuba',9923,''),(17,'Viola',423,''),(18,'Cello',233,''),(19,'Bass',523,''),(20,'Guitar',908,''),(21,'Marimba',2463,''),(22,'Xylophone',88883,''),(23,'Synthesizer',983,''),(24,'Organ',63,''),(25,'Drum Set',772773,''),(26,'Violin',23,'');







DROP TABLE IF EXISTS purchase;


CREATE TABLE purchase (
  productid int(11) NOT NULL,
  quantity int(11) NOT NULL,
  customerid int(11) NOT NULL
) ;








INSERT INTO purchase VALUES (3,100,2),(1,1,2),(26,4,1),(15,3,3),(8,78,1),(3,33,5),(4,9,1);







DROP TABLE IF EXISTS songs;


CREATE TABLE songs (
  content varchar(3000) DEFAULT NULL,
  user_id int(11) NOT NULL,
  composer varchar(256) DEFAULT NULL,
  date varchar(256) DEFAULT NULL,
  song_id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(256) DEFAULT NULL,
  timeSig int(11) DEFAULT NULL,
  keysig varchar(256) DEFAULT NULL,
  PRIMARY KEY (song_id)
) ;








INSERT INTO songs VALUES ('|(Intro)|Fmaj9|Bb13|Fmaj9|Bb13\n\n|(Verse 1)|Abmaj7|Gm7 Gb7 | Fm7 Bb7 |Eb C7\n|Fm7 Bb7 | Gm7 Cm7 | Db | Eb/Bb Bb7\n|(Verse 1)|Abmaj7|Gm7 Gb7 | Fm7 Bb7 |Eb C7\n|Fm7 Bb7 | Gm7 Cm7 |Fm7 | Bb7\n|',1,'Megaman','',1,'Relaxation',NULL,NULL),('|C/G |\n',1,'','',2,'Power Plant',4,'C'),('|(Water Service Bureau: Key of C)\n|(Intro)|F |G |Am G |F#m7\n| F G| Ab Bb | C | C\n\n|(A)|C | C7/Bb | Am | Ab Bb7\n|C | C7/Bb | Am | Am Bb7\n|C | C7/Bb | Am | Ab Bb7\n|C | C7/Bb | Am | Bb7\n\n|(B)|Fmaj7|Bb7|Am|Ab Db\n|Bbsus7 | | Csus7 | \n|(C)|Fmaj7 | Em7 | Dm7 | Gsus G7 \n|Fmaj7 | Em7 | Ab | Bbsus7\n\n|(D)|Cmaj7 | C7/Bb | F/A |Fm7/Ab\n|C/G| D7/F# |Fmaj7 | G7\n|Cmaj7 | C7/Bb | F/A |Fm7/Ab\n|C/G| D7/F# |Fmaj7 | G7\n\n|(Out)|Ab | Bb | C | C \n|',1,'Megaman','',3,'Water Service Bureau',4,'C'),('|(A)|F▲ | Eb7 | Am7 | D7 \n|Gm |   | Eb7 |  \n| Am7 | Dm | Gm | Gm Gm/F\n|EØ7 A7 |Dm7 G7 | Gm7/C | C7b9\n|(B)|F▲ | Eb7 | Am7 | D7 \n|Gm |   | Eb7 |  \n| Am7 | Dm Dm/C| BØ7 | E7\n|Am Dm |Gm7 C7 | F▲ D7 | Gm7 C7b9\n| ',1,'Henry Mancini','',4,'Days of Wine and Roses',NULL,NULL);







DROP TABLE IF EXISTS user;




CREATE TABLE user (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  firstName varchar(255) NOT NULL,
  lastName varchar(255) NOT NULL,
  creditCard int(11) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  address varchar(255) DEFAULT NULL,
  PRIMARY KEY (user_id)
) ;








INSERT INTO user VALUES (1,'Larry','Wang',9082,'larryoatmeal@gmail.com','password',NULL),(2,'Barack','Obama',8763,'president@gmail.com','whitehouse',NULL),(3,'Ferris','Bueller',8906,'dayoff@gmail.com','ferris','Chicago'),(4,'Jonah','Kallen',9289,'minecraft@gmail.com','fedora',NULL),(5,'Ross','Teichman',80921,'ross@gmail.com','rossy','Ross Drive');









 
# --- !Downs
