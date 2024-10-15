CREATE DATABASE  IF NOT EXISTS `HiPetani` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `HiPetani`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: HiPetani
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `kodeAkses` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'wefwaa','afasdf','asdfasfas');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategori` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `admin` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `KATEGORI_ADMIN` (`admin`),
  CONSTRAINT `KATEGORI_ADMIN` FOREIGN KEY (`admin`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategori`
--

LOCK TABLES `kategori` WRITE;
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` VALUES (5,'cemara',1),(6,'mangga',1),(7,'alpukat',1),(8,'Pisang',1);
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `komentar`
--

DROP TABLE IF EXISTS `komentar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `komentar` (
  `komentator` int NOT NULL,
  `pertanyaan` int NOT NULL,
  `isi` varchar(15000) DEFAULT NULL,
  KEY `komentator` (`komentator`),
  KEY `pertanyaan` (`pertanyaan`),
  CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`komentator`) REFERENCES `member` (`id`),
  CONSTRAINT `komentar_ibfk_2` FOREIGN KEY (`pertanyaan`) REFERENCES `pertanyaan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `komentar`
--

LOCK TABLES `komentar` WRITE;
/*!40000 ALTER TABLE `komentar` DISABLE KEYS */;
INSERT INTO `komentar` VALUES (7,4,'When responding to your questions, I kept in the original answers and replied in ALL CAPITAL letters to make it easier to read.\n\n1a. Wintering - I read about people bringing their banana plants in for the winter. I\'ve also read that the roots can go 5 feet deep and about 10 feet wide. Do you dig up the rhizome and cut off the roots?'),(8,4,'yes');
/*!40000 ALTER TABLE `komentar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kontrakKategori`
--

DROP TABLE IF EXISTS `kontrakKategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kontrakKategori` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pertanyaan` int NOT NULL,
  `kategori` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PERTANYAAN_KATEGORI` (`pertanyaan`),
  KEY `KATEGORI_KATEGORI` (`kategori`),
  CONSTRAINT `KATEGORI_KATEGORI` FOREIGN KEY (`kategori`) REFERENCES `kategori` (`id`),
  CONSTRAINT `PERTANYAAN_KATEGORI` FOREIGN KEY (`pertanyaan`) REFERENCES `pertanyaan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kontrakKategori`
--

LOCK TABLES `kontrakKategori` WRITE;
/*!40000 ALTER TABLE `kontrakKategori` DISABLE KEYS */;
INSERT INTO `kontrakKategori` VALUES (6,4,8),(25,78,6);
/*!40000 ALTER TABLE `kontrakKategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `noHp` varchar(20) NOT NULL,
  `foto` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (6,'rasy_01','12345678910','Rasyad Amhar','081386417347','sadf'),(7,'rasy_02','12345678910','Rasyad Amhari','08723642314','sadf'),(8,'yudapermana03','yuda123456789','Yuda Permana','0896123456','sadf'),(9,'Morgan','morgan123123','Brayen','089698767','sadf');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pertanyaan`
--

DROP TABLE IF EXISTS `pertanyaan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pertanyaan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` int NOT NULL,
  `judul` varchar(1000) NOT NULL,
  `body` varchar(10000) NOT NULL,
  `like` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PERTANYAAN_MEMBER` (`author`),
  CONSTRAINT `PERTANYAAN_MEMBER` FOREIGN KEY (`author`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pertanyaan`
--

LOCK TABLES `pertanyaan` WRITE;
/*!40000 ALTER TABLE `pertanyaan` DISABLE KEYS */;
INSERT INTO `pertanyaan` VALUES (4,6,'Banana plant care','I just bought a house in Northwest Florida that has a banana plant in back. It currently has two suckers (I\'ve expanded my vocabulary today reading about banana plant care.. lol), one is about 2 feet tall and the other about 10 inches. I\'m not sure what kind of plant it is, the previous owner said he bought it at Wal-Mart.\n\nI\'ve read the first 3 pages of posts to see if I could find answers to my questions, but it\'s now late and work comes early in the morning and it only caused me to have more questions.\n\n',0),(78,8,'LAMA PERTUMBUHAN POHON MANGGA','Berapakah lama penanaman pohon mangga sampai panen?',3);
/*!40000 ALTER TABLE `pertanyaan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-25  0:54:31
