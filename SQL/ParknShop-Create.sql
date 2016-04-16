CREATE DATABASE  IF NOT EXISTS `parknshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `parknshop`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: parknshop
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `tb_admin`
--

DROP TABLE IF EXISTS `tb_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_admin` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `admin_psw` varchar(200) NOT NULL,
  `admin_phone` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_njnfilmghpqj51hlt57vtang4` (`admin_name`),
  UNIQUE KEY `UK_m1vlan9p8drif608idsfrclrr` (`admin_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_advertisement`
--

DROP TABLE IF EXISTS `tb_advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_advertisement` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ad_content` varchar(200) NOT NULL,
  `ad_img` varchar(100) DEFAULT NULL,
  `ad_type` int(11) DEFAULT NULL,
  `ad_url` varchar(255) DEFAULT NULL,
  `commodity_no` bigint(20) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_56131w6lkbyhek0wi8ie5gbon` (`commodity_no`),
  KEY `FK_28s196sjxrp35xn70y2mm6mu2` (`shop_no`),
  CONSTRAINT `FK_28s196sjxrp35xn70y2mm6mu2` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`),
  CONSTRAINT `FK_56131w6lkbyhek0wi8ie5gbon` FOREIGN KEY (`commodity_no`) REFERENCES `tb_commodity` (`commodity_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_cart`
--

DROP TABLE IF EXISTS `tb_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cart` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` date DEFAULT NULL,
  `commodity_count` int(11) DEFAULT NULL,
  `sum_price` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `commodity_no` bigint(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_s9qfu91ir2wlgo1e6k8ngu6j4` (`commodity_no`),
  KEY `FK_s17smranqm9024n1dcesvti1j` (`username`),
  CONSTRAINT `FK_s17smranqm9024n1dcesvti1j` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`),
  CONSTRAINT `FK_s9qfu91ir2wlgo1e6k8ngu6j4` FOREIGN KEY (`commodity_no`) REFERENCES `tb_commodity` (`commodity_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_collectcommodity`
--

DROP TABLE IF EXISTS `tb_collectcommodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_collectcommodity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` date DEFAULT NULL,
  `commodity_no` bigint(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bhbbudx9lecuxyd8ay5fi3go4` (`commodity_no`),
  KEY `FK_gs5hny7kn92fpqj61i2qfqxfb` (`username`),
  CONSTRAINT `FK_gs5hny7kn92fpqj61i2qfqxfb` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`),
  CONSTRAINT `FK_bhbbudx9lecuxyd8ay5fi3go4` FOREIGN KEY (`commodity_no`) REFERENCES `tb_commodity` (`commodity_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_collectshop`
--

DROP TABLE IF EXISTS `tb_collectshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_collectshop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addTime` date DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_obtu5ypll4av5m90wtdv53j3y` (`shop_no`),
  KEY `FK_e09nta8y1dmfgqqa6u243r6a6` (`username`),
  CONSTRAINT `FK_e09nta8y1dmfgqqa6u243r6a6` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`),
  CONSTRAINT `FK_obtu5ypll4av5m90wtdv53j3y` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_comment`
--

DROP TABLE IF EXISTS `tb_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_comment` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(200) NOT NULL,
  `commentsTime` date DEFAULT NULL,
  `comment_isRead` int(11) DEFAULT NULL,
  `comment_rank` double DEFAULT NULL,
  `commodity_no` bigint(20) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_tcvpo5mvu60dhd6yho7neueao` (`commodity_no`),
  KEY `FK_kueanb0dxg41s8m5i2n27mfmg` (`shop_no`),
  KEY `FK_4iq3ptiu4to7yg2m0e2fergr9` (`username`),
  CONSTRAINT `FK_4iq3ptiu4to7yg2m0e2fergr9` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`),
  CONSTRAINT `FK_kueanb0dxg41s8m5i2n27mfmg` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`),
  CONSTRAINT `FK_tcvpo5mvu60dhd6yho7neueao` FOREIGN KEY (`commodity_no`) REFERENCES `tb_commodity` (`commodity_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_commodity`
--

DROP TABLE IF EXISTS `tb_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_commodity` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_addTime` date NOT NULL,
  `commodity_avgRank` double DEFAULT NULL,
  `commodity_category` int(11) NOT NULL,
  `commodity_count` int(11) NOT NULL,
  `commodity_detail` longtext,
  `commodity_image` varchar(100) DEFAULT NULL,
  `commodity_brief_info` varchar(200) DEFAULT NULL,
  `commodity_name` varchar(200) NOT NULL,
  `commodity_no` bigint(20) NOT NULL,
  `commodity_price` double NOT NULL,
  `commodity_salevolumn` bigint(20) NOT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_an3jhrgjechkspastreio75m7` (`commodity_no`),
  KEY `FK_fp92vdto00l30ms5hrlsqbqna` (`shop_no`),
  CONSTRAINT `FK_fp92vdto00l30ms5hrlsqbqna` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_commodity_ad`
--

DROP TABLE IF EXISTS `tb_commodity_ad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_commodity_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ad_rate` double DEFAULT NULL,
  `commodity_link` varchar(200) NOT NULL,
  `days` int(11) NOT NULL,
  `startTime` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `commodity_no` bigint(20) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_miv6oe7r66fnxra4luojuyhcg` (`commodity_no`),
  KEY `FK_ff1so4tf6peb1fkscvmrnkr1s` (`shop_no`),
  CONSTRAINT `FK_ff1so4tf6peb1fkscvmrnkr1s` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`),
  CONSTRAINT `FK_miv6oe7r66fnxra4luojuyhcg` FOREIGN KEY (`commodity_no`) REFERENCES `tb_commodity` (`commodity_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_complaint`
--

DROP TABLE IF EXISTS `tb_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_complaint` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `complaint_no` bigint(20) NOT NULL,
  `complaint_time` date NOT NULL,
  `complaint_state` int(11) DEFAULT NULL,
  `complaint_view` int(11) DEFAULT NULL,
  `complaint_result` int(11) DEFAULT NULL,
  `complaint_reason` varchar(200) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_5b0hqqss36ueu05yeghdme08g` (`complaint_no`),
  KEY `FK_jkcbook5yl8xt6ueja64rlt0q` (`username`),
  KEY `FK_2flxvsfiuij7r8naoome0a18k` (`shop_no`),
  CONSTRAINT `FK_2flxvsfiuij7r8naoome0a18k` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`),
  CONSTRAINT `FK_jkcbook5yl8xt6ueja64rlt0q` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_express`
--

DROP TABLE IF EXISTS `tb_express`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_express` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `express_companyName` varchar(100) NOT NULL,
  `express_no` bigint(20) NOT NULL,
  `express_type` varchar(20) DEFAULT NULL,
  `from_address` varchar(255) NOT NULL,
  `price` double DEFAULT NULL,
  `to_address` varchar(255) NOT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_mpdrrbpa8qi1hotdjycx83lri` (`express_no`),
  KEY `FK_27thvbr79wknihe5sq29t2no0` (`order_no`),
  CONSTRAINT `FK_27thvbr79wknihe5sq29t2no0` FOREIGN KEY (`order_no`) REFERENCES `tb_order` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_message`
--

DROP TABLE IF EXISTS `tb_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_message` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `message` varchar(200) NOT NULL,
  `receiver_name` varchar(200) NOT NULL,
  `sender_name` varchar(200) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_odium`
--

DROP TABLE IF EXISTS `tb_odium`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_odium` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(200) NOT NULL,
  `commentsTime` date DEFAULT NULL,
  `comment_isRead` int(11) DEFAULT NULL,
  `odium_no` bigint(20) NOT NULL,
  `commodity_rank` double DEFAULT NULL,
  `odium_reason` varchar(255) DEFAULT NULL,
  `comments_state` int(11) DEFAULT NULL,
  `commodity_no` bigint(20) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_1ib7wo504dth6krndqvmbi8a8` (`odium_no`),
  KEY `FK_16jenu6ap2fjc2l6gutnem10v` (`commodity_no`),
  KEY `FK_cv1mq0ogiufdpffl9ibpjhjlx` (`shop_no`),
  KEY `FK_6jsyexngs1146i9jl459g9441` (`username`),
  CONSTRAINT `FK_6jsyexngs1146i9jl459g9441` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`),
  CONSTRAINT `FK_16jenu6ap2fjc2l6gutnem10v` FOREIGN KEY (`commodity_no`) REFERENCES `tb_commodity` (`commodity_no`),
  CONSTRAINT `FK_cv1mq0ogiufdpffl9ibpjhjlx` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` date DEFAULT NULL,
  `delivery_status` int(11) DEFAULT NULL,
  `order_no` bigint(20) NOT NULL,
  `order_price` double NOT NULL,
  `pay_way` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `toAddr` varchar(200) NOT NULL,
  `buyer_name` varchar(50) DEFAULT NULL,
  `seller_name` varchar(50) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `UK_dbdb97r33rb68orr45g0pdjbu` (`order_no`),
  KEY `FK_ay3rxg2lxva15o0uiyptblk1v` (`buyer_name`),
  KEY `FK_g6aplx7jqdhkuq8wrlt6eel8s` (`seller_name`),
  KEY `FK_8fk4yyllp7tryifmdo4upbpah` (`shop_no`),
  CONSTRAINT `FK_8fk4yyllp7tryifmdo4upbpah` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`),
  CONSTRAINT `FK_ay3rxg2lxva15o0uiyptblk1v` FOREIGN KEY (`buyer_name`) REFERENCES `tb_user` (`username`),
  CONSTRAINT `FK_g6aplx7jqdhkuq8wrlt6eel8s` FOREIGN KEY (`seller_name`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_orderdetail`
--

DROP TABLE IF EXISTS `tb_orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_orderdetail` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_count` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `sum_price` double DEFAULT NULL,
  `commodity_no` bigint(20) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_sbppmfvjaqwm1qoo8pwd46nmc` (`commodity_no`),
  KEY `FK_1x8o65d72550arvkce9le04iw` (`order_no`),
  CONSTRAINT `FK_1x8o65d72550arvkce9le04iw` FOREIGN KEY (`order_no`) REFERENCES `tb_order` (`order_no`),
  CONSTRAINT `FK_sbppmfvjaqwm1qoo8pwd46nmc` FOREIGN KEY (`commodity_no`) REFERENCES `tb_commodity` (`commodity_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_service`
--

DROP TABLE IF EXISTS `tb_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_service` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reasons` varchar(200) DEFAULT NULL,
  `service_no` bigint(20) NOT NULL,
  `service_type` int(11) DEFAULT NULL,
  `service_status` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `commodity_no` bigint(20) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_ikodihmv005jwamgi4fdli1ty` (`service_no`),
  KEY `FK_r512l53649byl4q9jd0y4j3ov` (`username`),
  KEY `FK_b4ckb2uo965j78ughefmtjdw8` (`commodity_no`),
  KEY `FK_ciwrdhyhx5em1xy0bx3un23q6` (`shop_no`),
  CONSTRAINT `FK_ciwrdhyhx5em1xy0bx3un23q6` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`),
  CONSTRAINT `FK_b4ckb2uo965j78ughefmtjdw8` FOREIGN KEY (`commodity_no`) REFERENCES `tb_commodity` (`commodity_no`),
  CONSTRAINT `FK_r512l53649byl4q9jd0y4j3ov` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_share`
--

DROP TABLE IF EXISTS `tb_share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_share` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `share_rate` double NOT NULL,
  `share_money` double NOT NULL,
  `share_time` date NOT NULL,
  `turnover` double NOT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_n9jadxc9ogabhvs3xdfgtrnj6` (`shop_no`),
  CONSTRAINT `FK_n9jadxc9ogabhvs3xdfgtrnj6` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_shop`
--

DROP TABLE IF EXISTS `tb_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shop` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_telephone` varchar(20) DEFAULT NULL,
  `registerTime` date NOT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `shop_address` varchar(100) DEFAULT NULL,
  `shop_category` int(11) NOT NULL,
  `shop_description` longtext,
  `shop_icon` varchar(200) DEFAULT NULL,
  `shop_name` varchar(100) NOT NULL,
  `shop_no` bigint(20) NOT NULL,
  `shop_rank` double NOT NULL,
  `shop_source` int(11) NOT NULL,
  `shop_state` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_i497l8nekdr67qitq3dq6y1u5` (`shop_no`),
  KEY `FK_erwyjd09cgbsaaiqtcwhor93b` (`username`),
  CONSTRAINT `FK_erwyjd09cgbsaaiqtcwhor93b` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_shop_ad`
--

DROP TABLE IF EXISTS `tb_shop_ad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shop_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ad_rate` double DEFAULT NULL,
  `days` int(11) NOT NULL,
  `shop_link` varchar(200) NOT NULL,
  `startTime` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t36yfh457dqof19vjsvkc4rga` (`shop_no`),
  CONSTRAINT `FK_t36yfh457dqof19vjsvkc4rga` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_shopearneddetail`
--

DROP TABLE IF EXISTS `tb_shopearneddetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shopearneddetail` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `realEarn` double NOT NULL,
  `share_rate` double NOT NULL,
  `finishTime` date NOT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_i38pwylis9krr8pm2juoty0g9` (`order_no`),
  KEY `FK_f57swj95pxxh5s07x4ec5s868` (`shop_no`),
  CONSTRAINT `FK_f57swj95pxxh5s07x4ec5s868` FOREIGN KEY (`shop_no`) REFERENCES `tb_shop` (`shop_no`),
  CONSTRAINT `FK_i38pwylis9krr8pm2juoty0g9` FOREIGN KEY (`order_no`) REFERENCES `tb_order` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_shoplink`
--

DROP TABLE IF EXISTS `tb_shoplink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shoplink` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `linkedShopImg` varchar(255) DEFAULT NULL,
  `linkedShop_no` bigint(20) NOT NULL,
  `shop_no` bigint(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_shopowner`
--

DROP TABLE IF EXISTS `tb_shopowner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shopowner` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idCard` varchar(50) DEFAULT NULL,
  `portrait` varchar(255) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_ffbwsdjwgno9wn1f4tq1vc0dr` (`username`),
  CONSTRAINT `FK_ffbwsdjwgno9wn1f4tq1vc0dr` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `permission` bit(1) DEFAULT NULL,
  `nick_name` varchar(200) NOT NULL,
  `password` varchar(50) NOT NULL,
  `registerTime` date DEFAULT NULL,
  `state` int(11) NOT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-16 13:13:00
