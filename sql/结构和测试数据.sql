/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-20 17:33:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT 'title',
  `price` decimal(10,0) NOT NULL COMMENT '商品价格',
  `description` varchar(500) NOT NULL DEFAULT '' COMMENT '商品描述',
  `sales` int(11) NOT NULL DEFAULT '0' COMMENT '商品销量',
  `img_url` varchar(255) NOT NULL DEFAULT '' COMMENT '商品描述图片URL',
  `crt_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '宝宝秋装', '10', '男女宝宝秋冬装套装0一1岁婴儿衣服潮加厚连体衣保暖冬季外出抱衣', '0', 'https://i.imgur.com/hkO4BMl.jpg', '2018-12-20 14:30:36');
INSERT INTO `item` VALUES ('2', '宝宝粉色连体秋裤', '4', '男女宝宝秋连体冬装2一4岁婴儿衣服潮加厚连体衣保暖冬季外出抱衣', '0', 'https://i.imgur.com/yPHeCEz.jpg', '2018-12-20 14:51:25');
INSERT INTO `item` VALUES ('3', '毛绒鞋子', '25', '宝宝鞋子婴儿冬鞋新生儿鞋子男女宝宝学步鞋保暖加绒软底鞋0-1岁', '0', 'https://i.imgur.com/h3jUoik.jpg', '2018-12-20 17:32:01');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '商品库存',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='库存表';

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES ('1', '10000', '1');
INSERT INTO `item_stock` VALUES ('2', '500', '2');
INSERT INTO `item_stock` VALUES ('3', '666', '3');

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
  `third_party_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '第三方登录id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `telphone` (`telphone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '校花', '2', '18', '18819478777', 'phone', 'appidfsdufasfafa');
INSERT INTO `user_info` VALUES ('2', '木兮', '1', '25', '18819478773', 'phone', '0');
INSERT INTO `user_info` VALUES ('3', '校花', '2', '25', '18819478774', 'phone', '0');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `encrpt_password` varchar(128) NOT NULL COMMENT '加密密码',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='密码表';

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', 'ehyqwiehqie283782dw', '1');
INSERT INTO `user_password` VALUES ('2', 'e10adc3949ba59abbe56e057f20f883e', '2');
INSERT INTO `user_password` VALUES ('3', 'e10adc3949ba59abbe56e057f20f883e', '3');
