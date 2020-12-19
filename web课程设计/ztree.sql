/*
Navicat MySQL Data Transfer

Source Server         : 00
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : ztree

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2020-12-19 10:05:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `iconOpen` varchar(255) DEFAULT NULL,
  `iconClose` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '中国', '0', null, 'img/1_open.png', 'img/1_close.png');
INSERT INTO `city` VALUES ('2', '湖北', '1', 'img/4.png', '', null);
INSERT INTO `city` VALUES ('3', '武汉', '2', 'img/2.png', null, null);
INSERT INTO `city` VALUES ('4', '襄阳', '2', 'img/7.png', null, null);
INSERT INTO `city` VALUES ('5', '湖南', '1', 'img/4.png', null, null);
INSERT INTO `city` VALUES ('6', '长沙', '5', 'img/7.png', null, null);
INSERT INTO `city` VALUES ('7', '广东', '1', 'img/2.png', null, null);
INSERT INTO `city` VALUES ('8', '广州', '7', 'img/7.png', null, null);
