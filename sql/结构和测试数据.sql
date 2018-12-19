/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-19 21:37:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(255) NOT NULL COMMENT '用户名',
  `gender` tinyint(4) NOT NULL COMMENT '性别',
  `age` int(11) NOT NULL COMMENT '年龄',
  `telphone` varchar(255) NOT NULL COMMENT '电话号码',
  `register_mode` varchar(64) NOT NULL COMMENT '注册方式',
  `third_party_id` varchar(255) NOT NULL DEFAULT '-1' COMMENT '第三方登录id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '校花', '1', '18', '18819478777', 'phone', 'appidfsdufasfafa');
INSERT INTO `user_info` VALUES ('2', '木兮', '1', '25', '18819478773', 'phone', '-1');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `encrpt_password` varchar(128) NOT NULL COMMENT '加密密码',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='密码表';

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', 'ehyqwiehqie283782dw', '1');
INSERT INTO `user_password` VALUES ('2', 'e10adc3949ba59abbe56e057f20f883e', '2');
