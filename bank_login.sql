/*
Navicat MySQL Data Transfer

Source Server         : Emilio2
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : bank_login

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-07-09 15:21:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login_log`
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `log_id` int(12) NOT NULL AUTO_INCREMENT,
  `user_id` int(12) unsigned zerofill NOT NULL,
  `login_ip` varchar(40) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('1', '000000000007', '172.16.205.13', '2014-07-08 09:59:21');
INSERT INTO `login_log` VALUES ('2', '000000000009', '172.16.205.13', '2014-07-08 09:59:23');
INSERT INTO `login_log` VALUES ('3', '000000000008', '172.16.205.13', '2014-07-08 09:59:26');
INSERT INTO `login_log` VALUES ('4', '000000000007', '172.16.205.13', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(32) NOT NULL,
  `password` varchar(256) NOT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('bob', '崠烇n视職:b拃鎲�?]Z啹笫�躭�', '7');
INSERT INTO `user` VALUES ('citi', '+�rx\rec徎駫s蝩y9櫻绥f曎f�', '8');
INSERT INTO `user` VALUES ('citi', '+�rx\rec徎駫s蝩y9櫻绥f曎f�', '9');
INSERT INTO `user` VALUES ('he', ' B/滱~Hg镘O笭J?�爚檸嗺鳍z�', '10');
