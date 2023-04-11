-- MySQL dump 10.13  Distrib 5.7.37, for Win64 (x86_64)
--
-- Host: 10.8.18.176    Database: rule_db
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `t_condition_info`
--

DROP TABLE IF EXISTS `t_condition_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_condition_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `logic_type` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `priority` smallint NOT NULL,
  `relation_type` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rule_id` bigint NOT NULL,
  `variable_name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `variable_value` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tci_rule_id_IDX` (`rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_condition_info`
--

LOCK TABLES `t_condition_info` WRITE;
/*!40000 ALTER TABLE `t_condition_info` DISABLE KEYS */;
INSERT INTO `t_condition_info` VALUES (1,'AND','a1',10,'STR_EQUAL','test',2,'username','zhangsan'),(2,'AND','a2',9,'LESS','test',2,'age','20'),(3,'AND','a3',8,'DURATION_GREATER','test',2,'createTime','3'),(4,'AND','aa',10,'STR_EQUAL','aa',3,'username','dd'),(5,'AND','aaA',9,'LESS','aaA',3,'age','22'),(6,'AND','aaAA',8,'DURATION_GREATER','aAAa',3,'createTime','5');
/*!40000 ALTER TABLE `t_condition_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_rule_info`
--

DROP TABLE IF EXISTS `t_rule_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_rule_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `action` varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ruleset_id` bigint NOT NULL,
  `state` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `tri_ruleset_id_IDX` (`ruleset_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_rule_info`
--

LOCK TABLES `t_rule_info` WRITE;
/*!40000 ALTER TABLE `t_rule_info` DISABLE KEYS */;
INSERT INTO `t_rule_info` VALUES (2,'order:true,sendEmail:true','a规则','test',1,1),(3,'order:true,up:true','a','test',2,1);
/*!40000 ALTER TABLE `t_rule_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_ruleset_info`
--

DROP TABLE IF EXISTS `t_ruleset_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ruleset_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `expression` varchar(2048) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mode` tinyint NOT NULL DEFAULT '0',
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `state` tinyint NOT NULL DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tri_code_IDX` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ruleset_info`
--

LOCK TABLES `t_ruleset_info` WRITE;
/*!40000 ALTER TABLE `t_ruleset_info` DISABLE KEYS */;
INSERT INTO `t_ruleset_info` VALUES (1,'A','2023-04-11 12:13:56','let rMap = seq.map();\nif((equals(username,\'zhangsan\') && age < 20) && duration(createTime,3)){\nseq.put(rMap,\'order\', true);\nseq.put(rMap,\'sendEmail\', true);\n}\nreturn rMap;',1,'A规则集','test',1,'2023-04-11 15:43:02'),(2,'B','2023-04-11 16:55:34','let rMap = seq.map();\nif((equals(username,\'dd\') && age < 22) && duration(createTime,5)){\nseq.put(rMap,\'order\', true);\nseq.put(rMap,\'up\', true);\n}\nreturn rMap;',1,'B','test',1,'2023-04-11 17:02:15');
/*!40000 ALTER TABLE `t_ruleset_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'rule_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-11 18:14:36
