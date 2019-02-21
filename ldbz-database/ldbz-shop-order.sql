/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机
Source Server Version : 50723
Source Host           : 192.168.136.134:3306
Source Database       : ldbz-shop-order

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-18 21:54:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ldbz_order_item
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_order_item`;
CREATE TABLE `ldbz_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '商品名称',
  `price` float(10,2) NOT NULL COMMENT '商品价格',
  `image` varchar(600) NOT NULL COMMENT '商品缩略图',
  `num` int(11) NOT NULL COMMENT '商品数量',
  `order_code` bigint(20) NOT NULL COMMENT '订单批次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品表';

-- ----------------------------
-- Records of ldbz_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for ldbz-order
-- ----------------------------
DROP TABLE IF EXISTS `ldbz-order`;
CREATE TABLE `ldbz-order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_code` bigint(20) NOT NULL COMMENT '订单批次',
  `created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `status` tinyint(255) NOT NULL DEFAULT '1' COMMENT '1未支付；2已支付；3已发货；4待评论；5已完成',
  `address` varchar(999) NOT NULL COMMENT '邮寄地址',
  `total_price` float NOT NULL COMMENT '总价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of ldbz-order
-- ----------------------------
