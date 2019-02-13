/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机
Source Server Version : 50723
Source Host           : 192.168.136.134:3306
Source Database       : ldbz-shop

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-13 15:06:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ldbz_user
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_user`;
CREATE TABLE `ldbz_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of ldbz_user
-- ----------------------------
INSERT INTO `ldbz_user` VALUES ('37', 'ldbz', 'a54d26d7cc08e20da6a85679d785b6d8', '+008615669970077', 'ldbz@gmail.com', '2017-02-08 17:58:23', '2017-02-08 17:58:23');
INSERT INTO `ldbz_user` VALUES ('58', 'libiao', 'e10adc3949ba59abbe56e057f20f883e', null, 'biao.li@neusoft.com', '2018-09-29 13:17:22', '2018-09-29 13:17:22');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `addr_id` bigint(50) DEFAULT NULL COMMENT '地址id',
  `payment` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `payment_type` int(2) DEFAULT NULL COMMENT '支付类型，1、货到付款，2、在线支付，3、微信支付，4、支付宝支付',
  `post_fee` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `status` int(10) DEFAULT NULL COMMENT '状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
  `shipping_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
  `no_annoyance` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '退换无忧',
  `service_price` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '服务费',
  `return_price` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '返现',
  `total_weight` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单总重 单位/克',
  `buyer_rate` int(2) DEFAULT NULL COMMENT '买家是否已经评价',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`order_id`),
  KEY `create_time` (`create_time`),
  KEY `buyer_nick` (`addr_id`),
  KEY `status` (`status`),
  KEY `payment_type` (`payment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('12047608109420037', '37', '1', '519900', '1', '0', '1', '顺丰速运', null, '0', '0', '0', null, '6', null, null, null, null, '2017-04-02 16:08:25', null);
INSERT INTO `tb_order` VALUES ('13060403403290037', '37', '1', '15041600', '1', '0', '1', '顺丰速运', null, '0', '0', '0', null, '6', null, null, null, null, '2017-03-03 16:46:46', null);
INSERT INTO `tb_order` VALUES ('17208483627260037', '37', '1', '519900', '1', '0', '1', '顺丰速运', null, '0', '0', '0', null, '6', null, null, null, null, '2017-03-20 09:08:17', null);

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int(10) DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
  `price` bigint(50) DEFAULT NULL COMMENT '商品单价',
  `total_fee` bigint(50) DEFAULT NULL COMMENT '商品总金额',
  `pic_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址',
  `weights` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '总重量 单位/克',
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------
INSERT INTO `tb_order_item` VALUES ('201703038058255181', '148630831972863', '13060403403290037', '7', 'Apple MacBook Pro 15.4英寸笔记本电脑 深空灰色（Multi-Touch Bar/Core i7/16GB/512GB MLH42CH/A）', '2148800', '15041600', 'http://127.0.0.132/group1/M00/00/00/wKh9hFiM0l-AQuvEAABmx7u5QSA128.jpg', 'null');
INSERT INTO `tb_order_item` VALUES ('201703200974296817', '148630639229938', '17208483627260037', '1', 'Apple iPhone 7 (A1660) 32G 黑色 移动联通电信4G手机', '519900', '519900', 'http://127.0.0.132/group1/M00/00/00/wKh9hFiMy4eAV5lwAAB25IS6WjM274.jpg', 'null');
INSERT INTO `tb_order_item` VALUES ('201704025045880695', '148630639229938', '12047608109420037', '1', 'Apple iPhone 7 (A1660) 32G 黑色 移动联通电信4G手机', '519900', '519900', 'http://127.0.0.132/group1/M00/00/00/wKh9hFiMy4eAV5lwAAB25IS6WjM274.jpg', 'null');

-- ----------------------------
-- Table structure for tb_user_addr
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_addr`;
CREATE TABLE `tb_user_addr` (
  `addr_id` varchar(50) NOT NULL COMMENT '地址ID',
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货人全名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '固定电话',
  `receiver_mobile` varchar(30) DEFAULT NULL COMMENT '移动电话',
  `receiver_state` varchar(10) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(10) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮政编码,如：110000',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`addr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_addr
-- ----------------------------
