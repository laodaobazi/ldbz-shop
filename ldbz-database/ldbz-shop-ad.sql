/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机
Source Server Version : 50723
Source Host           : 192.168.136.134:3306
Source Database       : ldbz-shop-ad

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-13 15:07:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ldbz_index_recommend_ad
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_index_recommend_ad`;
CREATE TABLE `ldbz_index_recommend_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alt` varchar(255) DEFAULT NULL COMMENT '提示',
  `href` varchar(500) DEFAULT '#' COMMENT '网址',
  `src` varchar(255) NOT NULL COMMENT '图片地址',
  `ad_desc` varchar(255) DEFAULT NULL COMMENT '广告说明',
  `ad_key` varchar(20) NOT NULL DEFAULT '' COMMENT '广告位Key',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人ID',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='首页推荐广告位';

-- ----------------------------
-- Records of ldbz_index_recommend_ad
-- ----------------------------
INSERT INTO `ldbz_index_recommend_ad` VALUES ('1', '老刀把子', 'http://laodaobazi.iteye.com', '/uploadfiles/ad/2019/01/20/1547966694359.jpg', '首页左侧第一位置', 'AD_RECOMMEND_1', null, '2019-01-20 14:45:00', null, null, null, null);
INSERT INTO `ldbz_index_recommend_ad` VALUES ('2', '老刀把子', 'http://laodaobazi.iteye.com', '/uploadfiles/ad/2019/01/20/1547966735274.jpg', '首页左侧第二位置', 'AD_RECOMMEND_2', null, '2019-01-20 14:45:38', null, null, null, null);
INSERT INTO `ldbz_index_recommend_ad` VALUES ('3', '老刀把子', 'http://laodaobazi.iteye.com', '/uploadfiles/ad/2019/01/20/1547966767484.jpg', '首页左侧banner位置', 'AD_RECOMMEND_3', null, '2019-01-20 14:46:11', null, null, null, null);
INSERT INTO `ldbz_index_recommend_ad` VALUES ('4', '老刀把子', 'http://laodaobazi.iteye.com', '/uploadfiles/ad/2019/01/20/1547966804400.jpg', '首页右侧位置', 'AD_RECOMMEND_4', null, '2019-01-20 14:46:46', null, null, null, null);

-- ----------------------------
-- Table structure for ldbz_index_slide_ad
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_index_slide_ad`;
CREATE TABLE `ldbz_index_slide_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alt` varchar(255) DEFAULT NULL COMMENT '提示',
  `href` varchar(500) DEFAULT NULL COMMENT '网址',
  `src` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `status` int(1) DEFAULT '1' COMMENT '状态；1启用，2禁用',
  `sort_order` int(2) DEFAULT '1' COMMENT '排序',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人ID',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='首页轮播广告位';

-- ----------------------------
-- Records of ldbz_index_slide_ad
-- ----------------------------
INSERT INTO `ldbz_index_slide_ad` VALUES ('14', '首页轮播A', 'http://www.baidu.com', '/uploadfiles/ad/2018/12/23/1545554293838.png', '1', '1', '2018-12-23 16:38:21', '2018-12-23 16:38:21', null, null, null, null);
INSERT INTO `ldbz_index_slide_ad` VALUES ('15', '广告B', 'http://www.jd.com', '/uploadfiles/ad/2018/12/23/1545554537734.png', '1', '2', '2018-12-23 16:42:46', '2018-12-23 16:42:46', null, null, null, null);

-- ----------------------------
-- Table structure for ldbz_search_left_ad
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_search_left_ad`;
CREATE TABLE `ldbz_search_left_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alt` varchar(255) DEFAULT NULL COMMENT '提示',
  `href` varchar(500) DEFAULT NULL COMMENT '网址',
  `src` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `status` int(1) DEFAULT '1' COMMENT '状态；1启用，2禁用',
  `sort_order` int(2) DEFAULT '1' COMMENT '排序',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人ID',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='检索页左侧广告位';

-- ----------------------------
-- Records of ldbz_search_left_ad
-- ----------------------------
INSERT INTO `ldbz_search_left_ad` VALUES ('1', '京东', 'http://www.jd.com', '/uploadfiles/ad/2019/01/31/1548914998686.png', '1', '1', '2019-01-31 14:10:56', '2019-01-31 14:10:56', null, null, null, null);

-- ----------------------------
-- Table structure for ldbz_search_slide_ad
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_search_slide_ad`;
CREATE TABLE `ldbz_search_slide_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alt` varchar(255) DEFAULT NULL COMMENT '提示',
  `href` varchar(500) DEFAULT NULL COMMENT '网址',
  `src` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `status` int(1) DEFAULT '1' COMMENT '状态；1启用，2禁用',
  `sort_order` int(2) DEFAULT '1' COMMENT '排序',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人ID',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='检索页轮播广告位';

-- ----------------------------
-- Records of ldbz_search_slide_ad
-- ----------------------------
INSERT INTO `ldbz_search_slide_ad` VALUES ('1', '百度banner', 'http://www.baidu.com', '/uploadfiles/ad/2019/01/31/1548914450446.png', '1', '1', '2019-01-31 14:01:13', '2019-01-31 14:01:13', null, null, null, null);
INSERT INTO `ldbz_search_slide_ad` VALUES ('2', '新浪链接', 'http://www.sin.com', '/uploadfiles/ad/2019/01/31/1548914517274.png', '1', '2', '2019-01-31 14:02:21', '2019-01-31 14:02:21', null, null, null, null);
