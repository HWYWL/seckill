/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-21 18:13:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT 'title',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `description` varchar(500) NOT NULL DEFAULT '' COMMENT '商品描述',
  `sales` int(11) NOT NULL DEFAULT '0' COMMENT '商品销量',
  `img_url` varchar(255) NOT NULL DEFAULT '' COMMENT '商品描述图片URL',
  `crt_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '库存ID',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '商品库存',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='库存表';

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) NOT NULL COMMENT '订单ID',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品id',
  `item_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '当时商品价格',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '购买商品的数量',
  `order_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '购买金额',
  `crt_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `promo_name` varchar(64) NOT NULL DEFAULT '' COMMENT '秒杀名称',
  `promo_item_price` decimal(10,2) NOT NULL COMMENT '商品秒杀价格',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '秒杀商品',
  `start_data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品秒杀表';

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info` (
  `name` varchar(255) NOT NULL COMMENT '所属表',
  `current_value` int(11) NOT NULL DEFAULT '0' COMMENT '当前值',
  `step` int(11) NOT NULL DEFAULT '1' COMMENT '步长',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单序列表';

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
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `encrpt_password` varchar(128) NOT NULL COMMENT '加密密码',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='密码表';
