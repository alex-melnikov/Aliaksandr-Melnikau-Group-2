/*
SQLyog Enterprise - MySQL GUI v7.15 
MySQL - 5.1.40-community : Database - BankApplication
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`BankApplication` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `BankApplication`;

/*Table structure for table `Account` */

DROP TABLE IF EXISTS `Account`;

CREATE TABLE `Account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `balance` double NOT NULL,
  `currency` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `ExchangeRate` */

DROP TABLE IF EXISTS `ExchangeRate`;

CREATE TABLE `ExchangeRate` (
  `id` int(11) NOT NULL,
  `fromCurrency` varchar(3) NOT NULL,
  `toCurrency` varchar(3) NOT NULL,
  `rate` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
