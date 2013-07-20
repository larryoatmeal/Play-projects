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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry`
--

LOCK TABLES `entry` WRITE;
/*!40000 ALTER TABLE `entry` DISABLE KEYS */;
INSERT INTO `entry` VALUES (1,'Trombone is a brass instrument','2/5/13','http://messengermusic.pbworks.com/f/1299257373/trombone_1.jpg',1,1),(2,'Trombone is the best instrument','2/7/13',NULL,2,1),(1,'Jonah plays trumpet','7/2/13','http://guitartime.ru/images/af/duxovie/trumpet_trumpeting.gif',3,2),(1,'Trumpet has three valves','7/4/13',NULL,4,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrument`
--

LOCK TABLES `instrument` WRITE;
/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
INSERT INTO `instrument` VALUES ('Trombone',1),('Trumpet',2);
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;
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
INSERT INTO `play_evolutions` VALUES (1,'3f0163ce61a55a74d9223f4931a4baafa2eaa4ea','2013-07-01 21:46:45','USE musicstore;\n\nDROP TABLE IF EXISTS products;\nCREATE TABLE products\n(\nid              int          NOT NULL,\nname            varchar(255) NOT NULL,\nprice           int          NOT NULL,\n\n\n\nPRIMARY KEY     (id)\n\n);','','applied',''),(2,'ae93db4d0088db74681e86e8131405eaf092a592','2013-07-16 20:23:05','USE musicstore;\nDROP TABLE IF EXISTS products;\n\nCREATE TABLE products\n(\nid              int          NOT NULL,\nname            varchar(255) NOT NULL,\nprice           int          NOT NULL,\nimg			  varchar(255) NOT NULL,\n\nPRIMARY KEY     (id)\n\n);','','applied',''),(3,'a69efa64c7494c13a59e825a903611ce56e19195','2013-07-16 20:25:43','CREATE TABLE purchase\n(\nid              int          NOT NULL,\nfirstName            varchar(255) NOT NULL,\nlastName            varchar(255) NOT NULL,\nquantity           int          NOT NULL,\n\nPRIMARY KEY     (id)\n\n);','','applied',''),(4,'7de9cc5f0c022e8dcb4dd79a1c6b623f57c24664','2013-07-16 20:43:38','ALTER TABLE products\nMODIFY id int NOT NULL AUTO_INCREMENT;\n\nINSERT INTO products (name, price, img)\nVALUES (\"Violin\", 23, \"\");','','applied',''),(5,'1b8d4467ca0c04a2d7d2be5885b25e9bf98fe03d','2013-07-16 20:52:01','INSERT INTO products (name, price, img)\nVALUES (\"Alto sax\", 23, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Tenor Sax\", 24, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Baritone Sax\", 33, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Alto flute\", 76, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Piccolo\", 754, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Flute\", 27, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Clarinet\", 485, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bass Clarinet\", 2222, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Eb Clarinet\", 23, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bassoon\", 2009, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Contrabassoon\", 2984, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Oboe\", 2264, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"English Horn\", 2323, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"French Horn\", 823, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Trumpet\", 723, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Tuba\", 9923, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Viola\", 423, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Cello\", 233, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bass\", 523, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Guitar\", 908, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Marimba\", 2463, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Xylophone\", 88883, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Synthesizer\", 983, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Organ\", 63, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Drum Set\", 772773, \"\");\nINSERT INTO products (name, price, img)','','applied','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1 [ERROR:1064, SQLSTATE:42000]'),(6,'da39a3ee5e6b4b0d3255bfef95601890afd80709','2013-07-16 20:52:01','','','applied',''),(7,'9821e8d97d5e4afbce0f732ba001a3601bf5ab79','2013-07-17 21:25:49','DROP TABLE IF EXISTS purchase;\n\nCREATE TABLE purchase\n(\nproductid              int          NOT NULL,\nfirstName            varchar(255) NOT NULL,\nlastName            varchar(255) NOT NULL,\nquantity           int          NOT NULL,\ncustomerid		int			NOT NULL,\n\nPRIMARY KEY     (customerid)\n\n);\n\nCREATE TABLE products\n(\nid              int          NOT NULL,\nname            varchar(255) NOT NULL,\nprice           int          NOT NULL,\nimg			  varchar(255) NOT NULL,\n\nPRIMARY KEY     (id)\n\n);\nINSERT INTO products (name, price, img)\nVALUES (\"Alto sax\", 23, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Tenor Sax\", 24, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Baritone Sax\", 33, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Alto flute\", 76, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Piccolo\", 754, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Flute\", 27, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Clarinet\", 485, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bass Clarinet\", 2222, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Eb Clarinet\", 23, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bassoon\", 2009, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Contrabassoon\", 2984, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Oboe\", 2264, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"English Horn\", 2323, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"French Horn\", 823, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Trumpet\", 723, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Tuba\", 9923, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Viola\", 423, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Cello\", 233, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bass\", 523, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Guitar\", 908, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Marimba\", 2463, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Xylophone\", 88883, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Synthesizer\", 983, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Organ\", 63, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Drum Set\", 772773, \"\");','','applied','Field \'id\' doesn\'t have a default value [ERROR:1364, SQLSTATE:HY000]'),(8,'da39a3ee5e6b4b0d3255bfef95601890afd80709','2013-07-17 21:25:49','','','applied',''),(9,'da39a3ee5e6b4b0d3255bfef95601890afd80709','2013-07-17 21:27:33','','','applied',''),(10,'4169921a6d15972b6ca8e4440a9ae8963cb82781','2013-07-17 21:33:53','DROP TABLE IF EXISTS purchase;\n\nCREATE TABLE purchase\n(\nproductid              int          NOT NULL,\nfirstName            varchar(255) NOT NULL,\nlastName            varchar(255) NOT NULL,\nquantity           int          NOT NULL,\ncustomerid		int			NOT NULL AUTO_INCREMENT,\n\nPRIMARY KEY     (customerid)\n\n);\n\nDROP TABLE IF EXISTS products\n\nCREATE TABLE products\n(\nid              int          NOT NULL AUTO_INCREMENT,\nname            varchar(255) NOT NULL,\nprice           int          NOT NULL,\nimg			  varchar(255) NOT NULL,\n\nPRIMARY KEY     (id)\n\n);\nINSERT INTO products (name, price, img)\nVALUES (\"Alto sax\", 23, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Tenor Sax\", 24, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Baritone Sax\", 33, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Alto flute\", 76, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Piccolo\", 754, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Flute\", 27, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Clarinet\", 485, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bass Clarinet\", 2222, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Eb Clarinet\", 23, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bassoon\", 2009, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Contrabassoon\", 2984, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Oboe\", 2264, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"English Horn\", 2323, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"French Horn\", 823, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Trumpet\", 723, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Tuba\", 9923, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Viola\", 423, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Cello\", 233, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Bass\", 523, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Guitar\", 908, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Marimba\", 2463, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Xylophone\", 88883, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Synthesizer\", 983, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Organ\", 63, \"\");\nINSERT INTO products (name, price, img)\nVALUES (\"Drum Set\", 772773, \"\");','','applied','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'CREATE TABLE products\n(\nid              int          NOT NULL AUTO_INCREMENT,\nna\' at line 3 [ERROR:1064, SQLSTATE:42000]'),(11,'da39a3ee5e6b4b0d3255bfef95601890afd80709','2013-07-17 23:11:24','','','applied','');
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
INSERT INTO `purchase` VALUES (3,100,2),(1,1,2),(26,4,1),(15,3,3),(8,78,1);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
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

-- Dump completed on 2013-07-19 19:34:58
