-- MySQL dump 10.13  Distrib 5.6.12, for osx10.7 (x86_64)
--
-- Host: localhost    Database: musicstore
-- ------------------------------------------------------
-- Server version	5.6.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `entry`
--

DROP TABLE IF EXISTS `entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry` (
  `user_id` int(11) NOT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `img` varchar(1000) DEFAULT NULL,
  `entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `instrument_id` int(11) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry`
--

LOCK TABLES `entry` WRITE;
/*!40000 ALTER TABLE `entry` DISABLE KEYS */;
INSERT INTO `entry` VALUES (1,'Trombone is a brass instrument','2/5/13','http://messengermusic.pbworks.com/f/1299257373/trombone_1.jpg',1,1),(2,'Trombone is the best instrument','2/7/13',NULL,2,1),(1,'Jonah plays trumpet','7/2/13','http://guitartime.ru/images/af/duxovie/trumpet_trumpeting.gif',3,2),(1,'Trumpet has three valves','7/4/13',NULL,4,2),(1,'veryfuntoplay','today',NULL,5,1),(1,'veryhardtoplay','tomorrow','blah',6,1),(1,'Hey','A long time ago',NULL,7,1),(1,'','A long time ago',NULL,8,2),(1,'It\'s a good day','A long time ago',NULL,9,2),(1,'It\'s a very good day','A long time ago',NULL,10,1),(1,'Trumpet is sopranoooo','6/22/2013 -16:56',NULL,11,2),(1,'Yo','6/22/2013 -16:57',NULL,12,2),(1,'Yo2','6/22/2013 -16:57',NULL,13,2),(1,'Yo3','6/22/2013 -16:58',NULL,14,2),(1,'Slide instrument','6/22/2013 -16:59',NULL,15,1),(1,'Yo4','6/22/2013 -17:1',NULL,16,2),(1,'Yo5','6/22/2013 -17:10',NULL,17,2),(4,'I play the trumpet','6/22/2013 -17:12',NULL,18,2),(4,NULL,'6/22/2013 -17:14',NULL,19,2),(4,NULL,'6/22/2013 -17:15',NULL,20,2),(4,'Beautiful trumpet','6/22/2013 -17:20','http://new.oberlin.edu/dotAsset/1316961.jpg',21,2),(1,'Slide trombone','6/23/2013 -9:3',NULL,22,1),(1,'Trumpet is cool','6/23/2013 -9:5',NULL,23,2),(1,'Should of capitalized it!','6/23/2013 -9:38',NULL,24,3),(1,'Beautiful sound','6/23/2013 -10:12',NULL,25,4),(1,'High','6/23/2013 -12:7',NULL,26,3),(1,'Mellow','6/23/2013 -13:53',NULL,27,5),(4,'String instrument','6/23/2013 -13:55',NULL,28,8),(4,'Double reed','6/23/2013 -14:8',NULL,29,6),(4,'Baritone','6/23/2013 -14:25',NULL,30,7),(4,'Gerry Mulligan','6/23/2013 -14:25',NULL,31,7),(4,'IJo','6/23/2013 -17:41',NULL,32,1),(1,'Rite of Spring','6/24/2013 -17:53',NULL,33,6);
/*!40000 ALTER TABLE `entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instrument`
--

DROP TABLE IF EXISTS `instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instrument` (
  `name` varchar(255) NOT NULL,
  `instrument_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`instrument_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrument`
--

LOCK TABLES `instrument` WRITE;
/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
INSERT INTO `instrument` VALUES ('Trombone',1),('Trumpet',2),('oboe',3),('French Horn',4),('Clarinet',5),('Bassoon',6),('Bari Sax',7),('Violin',8);
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `entry_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (24,1),(26,1),(25,1),(1,1),(27,1),(3,4),(29,4),(2,1),(5,1),(33,1),(29,1);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_evolutions`
--

DROP TABLE IF EXISTS `play_evolutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `play_evolutions` (
  `id` int(11) NOT NULL,
  `hash` varchar(255) NOT NULL,
  `applied_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `apply_script` text,
  `revert_script` text,
  `state` varchar(255) DEFAULT NULL,
  `last_problem` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_evolutions`
--

LOCK TABLES `play_evolutions` WRITE;
/*!40000 ALTER TABLE `play_evolutions` DISABLE KEYS */;
INSERT INTO `play_evolutions` VALUES (1,'411b6c2f33b9309eb215ac4c801152734ae0adb8','2013-07-20 03:36:35','DROP TABLE IF EXISTS `entry`;\nCREATE TABLE `entry` (\n`user_id` int(11) NOT NULL,\n`comment` varchar(1000) DEFAULT NULL,\n`date` varchar(255) NOT NULL,\n`img` varchar(1000) DEFAULT NULL,\n`entry_id` int(11) NOT NULL AUTO_INCREMENT,\n`instrument_id` int(11) NOT NULL,\nPRIMARY KEY (`entry_id`)\n);\n\n\n\nINSERT INTO `entry` VALUES (1,\'Trombone is a brass instrument\',\'2/5/13\',\'http://messengermusic.pbworks.com/f/1299257373/trombone_1.jpg\',1,1),(2,\'Trombone is the best instrument\',\'2/7/13\',NULL,2,1),(1,\'Jonah plays trumpet\',\'7/2/13\',\'http://guitartime.ru/images/af/duxovie/trumpet_trumpeting.gif\',3,2),(1,\'Trumpet has three valves\',\'7/4/13\',NULL,4,2);\n\n\n\nDROP TABLE IF EXISTS `instrument`;\n\nCREATE TABLE `instrument` (\n`name` varchar(255) NOT NULL,\n`instrument_id` int(11) NOT NULL AUTO_INCREMENT,\nPRIMARY KEY (`instrument_id`)\n) ;\n\n\n\nINSERT INTO `instrument` VALUES (\'Trombone\',1),(\'Trumpet\',2);\n\n\n\n\n\nDROP TABLE IF EXISTS `products`;\n\nCREATE TABLE `products` (\n`id` int(11) NOT NULL AUTO_INCREMENT,\n`name` varchar(255) NOT NULL,\n`price` int(11) NOT NULL,\n`img` varchar(255) NOT NULL,\nPRIMARY KEY (`id`)\n);\n\n\nINSERT INTO `products` VALUES (1,\'Alto sax\',23,\'http://www.gifs.net/Animation11/Everything_Else/Musical_Instruments/Sax_4.gif\'),(2,\'Tenor Sax\',24,\'\'),(3,\'Baritone Sax\',33,\'http://bassic-sax.info/blog/wp-content/uploads/2013/01/Bari-With-Ray-Bans.jpg\'),(4,\'Alto flute\',76,\'http://www.musicalinstrumenthaven.com/ProductImages/ARMSTRONG%20703%20FLUTETMB.jpg\'),(5,\'Piccolo\',754,\'\'),(6,\'Flute\',27,\'\'),(7,\'Clarinet\',485,\'\'),(8,\'Bass Clarinet\',2222,\'\'),(9,\'Eb Clarinet\',23,\'\'),(10,\'Bassoon\',2009,\'\'),(11,\'Contrabassoon\',2984,\'\'),(12,\'Oboe\',2264,\'\'),(13,\'English Horn\',2323,\'\'),(14,\'French Horn\',823,\'\'),(15,\'Trumpet\',723,\'\'),(16,\'Tuba\',9923,\'\'),(17,\'Viola\',423,\'\'),(18,\'Cello\',233,\'\'),(19,\'Bass\',523,\'\'),(20,\'Guitar\',908,\'\'),(21,\'Marimba\',2463,\'\'),(22,\'Xylophone\',88883,\'\'),(23,\'Synthesizer\',983,\'\'),(24,\'Organ\',63,\'\'),(25,\'Drum Set\',772773,\'\'),(26,\'Violin\',23,\'\');\n\n\nDROP TABLE IF EXISTS purchase;\n\nCREATE TABLE `purchase` (\n`productid` int(11) NOT NULL,\n`quantity` int(11) NOT NULL,\n`customerid` int(11) NOT NULL\n);\n\n\n\nINSERT INTO `purchase` VALUES (3,100,2),(1,1,2),(26,4,1),(15,3,3),(8,78,1);\n\n\n\nDROP TABLE IF EXISTS `user`;\n\nCREATE TABLE `user` (\n`user_id` int(11) NOT NULL AUTO_INCREMENT,\n`firstName` varchar(255) NOT NULL,\n`lastName` varchar(255) NOT NULL,\n`creditCard` int(11) NOT NULL,\n`email` varchar(255) NOT NULL,\n`password` varchar(255) NOT NULL,\n`address` varchar(255) DEFAULT NULL,\nPRIMARY KEY (`user_id`)\n);\n\nINSERT INTO `user` VALUES (1,\'Larry\',\'Wang\',9082,\'larryoatmeal@gmail.com\',\'password\',NULL),(2,\'Barack\',\'Obama\',8763,\'president@gmail.com\',\'whitehouse\',NULL),(3,\'Ferris\',\'Bueller\',8906,\'dayoff@gmail.com\',\'ferris\',\'Chicago\'),(4,\'Jonah\',\'Kallen\',9289,\'minecraft@gmail.com\',\'fedora\',NULL);','','applied','');
/*!40000 ALTER TABLE `play_evolutions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `img` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Alto sax',23,'http://www.gifs.net/Animation11/Everything_Else/Musical_Instruments/Sax_4.gif'),(2,'Tenor Sax',24,''),(3,'Baritone Sax',33,'http://bassic-sax.info/blog/wp-content/uploads/2013/01/Bari-With-Ray-Bans.jpg'),(4,'Alto flute',76,'http://www.musicalinstrumenthaven.com/ProductImages/ARMSTRONG%20703%20FLUTETMB.jpg'),(5,'Piccolo',754,''),(6,'Flute',27,''),(7,'Clarinet',485,''),(8,'Bass Clarinet',2222,''),(9,'Eb Clarinet',23,''),(10,'Bassoon',2009,''),(11,'Contrabassoon',2984,''),(12,'Oboe',2264,''),(13,'English Horn',2323,''),(14,'French Horn',823,''),(15,'Trumpet',723,''),(16,'Tuba',9923,''),(17,'Viola',423,''),(18,'Cello',233,''),(19,'Bass',523,''),(20,'Guitar',908,''),(21,'Marimba',2463,''),(22,'Xylophone',88883,''),(23,'Synthesizer',983,''),(24,'Organ',63,''),(25,'Drum Set',772773,''),(26,'Violin',23,'');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `productid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `customerid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (3,100,2),(1,1,2),(26,4,1),(15,3,3),(8,78,1),(12,9,1);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `songs` (
  `content` varchar(3000) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `composer` varchar(256) DEFAULT NULL,
  `date` varchar(256) DEFAULT NULL,
  `song_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`song_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES ('| Cm7 |',1,'Larry Wang','2009',1,'Weissnichtwo'),('| Dm7 | G7 | Dm7 | Gm7 \n| Cmaj7 Amaj7| Em/B |Gmaj7 | G G \n| C C | C D E Fsus9 |||',1,'Obama','2007',2,'Mary Go Round'),('|Ebmaj7 |Bbm7 Eb7 |Abmaj7| Abm7 Db7\n|Ebmaj7 Cm7| Fm7 Bb7 |Gm7 C7|Fm7 Bb7\n|',1,'Erroll Garner','1954',3,'Misty'),('|Cmaj7|',4,'Jonah','2009',13,'Fedora Fun'),('| Ebmaj7#5|',4,'Pixar','1980',14,'Blue Umbrella'),(NULL,1,NULL,NULL,15,NULL);
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `creditCard` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Larry','Wang',9082,'larryoatmeal@gmail.com','password',NULL),(2,'Barack','Obama',8763,'president@gmail.com','whitehouse',NULL),(3,'Ferris','Bueller',8906,'dayoff@gmail.com','ferris','Chicago'),(4,'Jonah','Kallen',9289,'minecraft@gmail.com','fedora',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-25 17:35:47
