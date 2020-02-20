-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 chaoweifish 的数据库结构
CREATE DATABASE IF NOT EXISTS `chaoweifish` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `chaoweifish`;


-- 导出  表 chaoweifish.admin 结构
CREATE TABLE IF NOT EXISTS `admin` (
  `ID` bigint(13) NOT NULL,
  `loginID` varchar(50) NOT NULL,
  `loginKey` varchar(50) NOT NULL,
  `loginTime` bigint(13) unsigned zerofill NOT NULL,
  `wrongTime` int(2) unsigned zerofill NOT NULL,
  `lastLoginTime` bigint(13) unsigned zerofill NOT NULL,
  `role` int(2) unsigned zerofill NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `loginID` (`loginID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 chaoweifish.carousel_img 结构
CREATE TABLE IF NOT EXISTS `carousel_img` (
  `ID` bigint(13) NOT NULL,
  `showType` varchar(50) NOT NULL COMMENT 'pc端或移动端',
  `title` varchar(50) NOT NULL,
  `url` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 chaoweifish.contact 结构
CREATE TABLE IF NOT EXISTS `contact` (
  `ID` bigint(13) NOT NULL,
  `detail` text NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 chaoweifish.index_img 结构
CREATE TABLE IF NOT EXISTS `index_img` (
  `ID` bigint(13) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 chaoweifish.mydemo 结构
CREATE TABLE IF NOT EXISTS `mydemo` (
  `ID` bigint(13) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `pictureUrl` varchar(50) DEFAULT NULL,
  `detail` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 chaoweifish.produces 结构
CREATE TABLE IF NOT EXISTS `produces` (
  `ID` bigint(13) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `pictureUrl` varchar(50) DEFAULT NULL,
  `detail` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
