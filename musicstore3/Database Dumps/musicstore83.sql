-- MySQL dump 10.13  Distrib 5.6.12, for Win64 (x86_64)
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry`
--

LOCK TABLES `entry` WRITE;
/*!40000 ALTER TABLE `entry` DISABLE KEYS */;
INSERT INTO `entry` VALUES (1,'Trombone is a brass instrument','2/5/13','http://messengermusic.pbworks.com/f/1299257373/trombone_1.jpg',1,1),(2,'Trombone is the best instrument','2/7/13',NULL,2,1),(1,'Jonah plays trumpet','7/2/13','http://guitartime.ru/images/af/duxovie/trumpet_trumpeting.gif',3,2),(1,'Trumpet has three valves','7/4/13',NULL,4,2),(1,'Rite of Spring','6/26/2013 -22:42',NULL,5,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrument`
--

LOCK TABLES `instrument` WRITE;
/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
INSERT INTO `instrument` VALUES ('Trombone',1),('Trumpet',2),('Bassoon',3);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,1),(4,1),(5,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_evolutions`
--

LOCK TABLES `play_evolutions` WRITE;
/*!40000 ALTER TABLE `play_evolutions` DISABLE KEYS */;
INSERT INTO `play_evolutions` VALUES (1,'411b6c2f33b9309eb215ac4c801152734ae0adb8','2013-07-20 03:30:41','DROP TABLE IF EXISTS `entry`;\nCREATE TABLE `entry` (\n`user_id` int(11) NOT NULL,\n`comment` varchar(1000) DEFAULT NULL,\n`date` varchar(255) NOT NULL,\n`img` varchar(1000) DEFAULT NULL,\n`entry_id` int(11) NOT NULL AUTO_INCREMENT,\n`instrument_id` int(11) NOT NULL,\nPRIMARY KEY (`entry_id`)\n);\n\n\n\nINSERT INTO `entry` VALUES (1,\'Trombone is a brass instrument\',\'2/5/13\',\'http://messengermusic.pbworks.com/f/1299257373/trombone_1.jpg\',1,1),(2,\'Trombone is the best instrument\',\'2/7/13\',NULL,2,1),(1,\'Jonah plays trumpet\',\'7/2/13\',\'http://guitartime.ru/images/af/duxovie/trumpet_trumpeting.gif\',3,2),(1,\'Trumpet has three valves\',\'7/4/13\',NULL,4,2);\n\n\n\nDROP TABLE IF EXISTS `instrument`;\n\nCREATE TABLE `instrument` (\n`name` varchar(255) NOT NULL,\n`instrument_id` int(11) NOT NULL AUTO_INCREMENT,\nPRIMARY KEY (`instrument_id`)\n) ;\n\n\n\nINSERT INTO `instrument` VALUES (\'Trombone\',1),(\'Trumpet\',2);\n\n\n\n\n\nDROP TABLE IF EXISTS `products`;\n\nCREATE TABLE `products` (\n`id` int(11) NOT NULL AUTO_INCREMENT,\n`name` varchar(255) NOT NULL,\n`price` int(11) NOT NULL,\n`img` varchar(255) NOT NULL,\nPRIMARY KEY (`id`)\n);\n\n\nINSERT INTO `products` VALUES (1,\'Alto sax\',23,\'http://www.gifs.net/Animation11/Everything_Else/Musical_Instruments/Sax_4.gif\'),(2,\'Tenor Sax\',24,\'\'),(3,\'Baritone Sax\',33,\'http://bassic-sax.info/blog/wp-content/uploads/2013/01/Bari-With-Ray-Bans.jpg\'),(4,\'Alto flute\',76,\'http://www.musicalinstrumenthaven.com/ProductImages/ARMSTRONG%20703%20FLUTETMB.jpg\'),(5,\'Piccolo\',754,\'\'),(6,\'Flute\',27,\'\'),(7,\'Clarinet\',485,\'\'),(8,\'Bass Clarinet\',2222,\'\'),(9,\'Eb Clarinet\',23,\'\'),(10,\'Bassoon\',2009,\'\'),(11,\'Contrabassoon\',2984,\'\'),(12,\'Oboe\',2264,\'\'),(13,\'English Horn\',2323,\'\'),(14,\'French Horn\',823,\'\'),(15,\'Trumpet\',723,\'\'),(16,\'Tuba\',9923,\'\'),(17,\'Viola\',423,\'\'),(18,\'Cello\',233,\'\'),(19,\'Bass\',523,\'\'),(20,\'Guitar\',908,\'\'),(21,\'Marimba\',2463,\'\'),(22,\'Xylophone\',88883,\'\'),(23,\'Synthesizer\',983,\'\'),(24,\'Organ\',63,\'\'),(25,\'Drum Set\',772773,\'\'),(26,\'Violin\',23,\'\');\n\n\nDROP TABLE IF EXISTS purchase;\n\nCREATE TABLE `purchase` (\n`productid` int(11) NOT NULL,\n`quantity` int(11) NOT NULL,\n`customerid` int(11) NOT NULL\n);\n\n\n\nINSERT INTO `purchase` VALUES (3,100,2),(1,1,2),(26,4,1),(15,3,3),(8,78,1);\n\n\n\nDROP TABLE IF EXISTS `user`;\n\nCREATE TABLE `user` (\n`user_id` int(11) NOT NULL AUTO_INCREMENT,\n`firstName` varchar(255) NOT NULL,\n`lastName` varchar(255) NOT NULL,\n`creditCard` int(11) NOT NULL,\n`email` varchar(255) NOT NULL,\n`password` varchar(255) NOT NULL,\n`address` varchar(255) DEFAULT NULL,\nPRIMARY KEY (`user_id`)\n);\n\nINSERT INTO `user` VALUES (1,\'Larry\',\'Wang\',9082,\'larryoatmeal@gmail.com\',\'password\',NULL),(2,\'Barack\',\'Obama\',8763,\'president@gmail.com\',\'whitehouse\',NULL),(3,\'Ferris\',\'Bueller\',8906,\'dayoff@gmail.com\',\'ferris\',\'Chicago\'),(4,\'Jonah\',\'Kallen\',9289,\'minecraft@gmail.com\',\'fedora\',NULL);','','applied','');
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (3,100,2),(1,1,2),(26,4,1),(15,3,3),(8,78,1),(3,33,5),(4,9,1);
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
  `timeSig` int(11) DEFAULT NULL,
  `keysig` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`song_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES ('|(Intro)|Fmaj9|Bb13|Fmaj9|Bb13\n\n|(Verse 1)|Abmaj7|Gm7 Gb7 | Fm7 Bb7 |Eb C7\n|Fm7 Bb7 | Gm7 Cm7 | Db | Eb/Bb Bb7\n|(Verse 1)|Abmaj7|Gm7 Gb7 | Fm7 Bb7 |Eb C7\n|Fm7 Bb7 | Gm7 Cm7 |Fm7 | Bb7\n|',1,'Megaman','',1,'Relaxation',NULL,NULL),('|C/G |\n',1,'','',2,'Power Plant',4,'C'),('|(Water Service Bureau: Key of C)\n|(Intro)|F |G |Am G |F#m7\n| F G| Ab Bb | C | C\n\n|(A)|C | C7/Bb | Am | Ab Bb7\n|C | C7/Bb | Am | Am Bb7\n|C | C7/Bb | Am | Ab Bb7\n|C | C7/Bb | Am | Bb7\n\n|(B)|Fmaj7|Bb7|Am|Ab Db\n|Bbsus7 | | Csus7 | \n|(C)|Fmaj7 | Em7 | Dm7 | Gsus G7 \n|Fmaj7 | Em7 | Ab | Bbsus7\n\n|(D)|Cmaj7 | C7/Bb | F/A |Fm7/Ab\n|C/G| D7/F# |Fmaj7 | G7\n|Cmaj7 | C7/Bb | F/A |Fm7/Ab\n|C/G| D7/F# |Fmaj7 | G7\n\n|(Out)|Ab | Bb | C | C \n|',1,'Megaman','',3,'Water Service Bureau',4,'C'),('|(A)|F▲ | Eb7 | Am7 | D7 \n|Gm |   | Eb7 |  \n| Am7 | Dm | Gm | Gm Gm/F\n|EØ7 A7 |Dm7 G7 | Gm7/C | C7b9\n|(B)|F▲ | Eb7 | Am7 | D7 \n|Gm |   | Eb7 |  \n| Am7 | Dm Dm/C| BØ7 | E7\n|Am Dm |Gm7 C7 | F▲ D7 | Gm7 C7b9\n| ',1,'Henry Mancini','',4,'Days of Wine and Roses',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Larry','Wang',9082,'larryoatmeal@gmail.com','password',NULL),(2,'Barack','Obama',8763,'president@gmail.com','whitehouse',NULL),(3,'Ferris','Bueller',8906,'dayoff@gmail.com','ferris','Chicago'),(4,'Jonah','Kallen',9289,'minecraft@gmail.com','fedora',NULL),(5,'Ross','Teichman',80921,'ross@gmail.com','rossy','Ross Drive');
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

-- Dump completed on 2013-08-03 15:41:03
