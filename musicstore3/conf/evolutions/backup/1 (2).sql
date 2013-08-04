# --- !Ups




DROP TABLE IF EXISTS `entry`;
CREATE TABLE `entry` (
  `user_id` int(11) NOT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `img` varchar(1000) DEFAULT NULL,
  `entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `instrument_id` int(11) NOT NULL,
  PRIMARY KEY (`entry_id`)
);



INSERT INTO `entry` VALUES (1,'Trombone is a brass instrument','2/5/13','http://messengermusic.pbworks.com/f/1299257373/trombone_1.jpg',1,1),(2,'Trombone is the best instrument','2/7/13',NULL,2,1),(1,'Jonah plays trumpet','7/2/13','http://guitartime.ru/images/af/duxovie/trumpet_trumpeting.gif',3,2),(1,'Trumpet has three valves','7/4/13',NULL,4,2);



DROP TABLE IF EXISTS `instrument`;

CREATE TABLE `instrument` (
  `name` varchar(255) NOT NULL,
  `instrument_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`instrument_id`)
) ;



INSERT INTO `instrument` VALUES ('Trombone',1),('Trumpet',2);





DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `img` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `products` VALUES (1,'Alto sax',23,'http://www.gifs.net/Animation11/Everything_Else/Musical_Instruments/Sax_4.gif'),(2,'Tenor Sax',24,''),(3,'Baritone Sax',33,'http://bassic-sax.info/blog/wp-content/uploads/2013/01/Bari-With-Ray-Bans.jpg'),(4,'Alto flute',76,'http://www.musicalinstrumenthaven.com/ProductImages/ARMSTRONG%20703%20FLUTETMB.jpg'),(5,'Piccolo',754,''),(6,'Flute',27,''),(7,'Clarinet',485,''),(8,'Bass Clarinet',2222,''),(9,'Eb Clarinet',23,''),(10,'Bassoon',2009,''),(11,'Contrabassoon',2984,''),(12,'Oboe',2264,''),(13,'English Horn',2323,''),(14,'French Horn',823,''),(15,'Trumpet',723,''),(16,'Tuba',9923,''),(17,'Viola',423,''),(18,'Cello',233,''),(19,'Bass',523,''),(20,'Guitar',908,''),(21,'Marimba',2463,''),(22,'Xylophone',88883,''),(23,'Synthesizer',983,''),(24,'Organ',63,''),(25,'Drum Set',772773,''),(26,'Violin',23,'');


DROP TABLE IF EXISTS purchase;

CREATE TABLE `purchase` (
  `productid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `customerid` int(11) NOT NULL
);



INSERT INTO `purchase` VALUES (3,100,2),(1,1,2),(26,4,1),(15,3,3),(8,78,1);



DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `creditCard` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

INSERT INTO `user` VALUES (1,'Larry','Wang',9082,'larryoatmeal@gmail.com','password',NULL),(2,'Barack','Obama',8763,'president@gmail.com','whitehouse',NULL),(3,'Ferris','Bueller',8906,'dayoff@gmail.com','ferris','Chicago'),(4,'Jonah','Kallen',9289,'minecraft@gmail.com','fedora',NULL);




# --- !Downs
