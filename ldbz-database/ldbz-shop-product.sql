/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机
Source Server Version : 50723
Source Host           : 192.168.136.134:3306
Source Database       : ldbz-shop-product

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-13 15:07:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ldbz_category
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_category`;
CREATE TABLE `ldbz_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `category_level` tinyint(255) DEFAULT NULL COMMENT '分类级别',
  `status` int(11) DEFAULT NULL COMMENT '1启用；0禁用',
  `fid` int(11) DEFAULT NULL COMMENT '所属父ID',
  `sort_order` tinyint(4) DEFAULT NULL COMMENT '排序',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人ID',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ldbz_category
-- ----------------------------
INSERT INTO `ldbz_category` VALUES ('1', '商品分类根', '0', '1', null, '0', '2018-12-15 03:49:03', '2018-12-15 03:49:03', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('6', '热卖商品', '1', '1', '1', '1', '2018-12-15 19:46:21', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('7', '数码世界', '1', '1', '1', '2', '2018-12-15 19:48:45', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('8', '家居生活', '1', '1', '1', '3', '2018-12-15 19:49:26', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('9', '运动图书', '1', '1', '1', '4', '2018-12-15 19:49:46', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('10', '服饰母婴', '1', '1', '1', '5', '2018-12-15 19:50:05', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('11', '设备办公', '1', '1', '1', '6', '2018-12-15 19:50:23', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('12', '闲置礼品', '1', '1', '1', '7', '2018-12-15 19:50:43', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('13', '果粉专区', '2', '1', '6', '1', '2018-12-15 19:52:22', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('14', '推荐手机', '2', '1', '6', '2', '2018-12-15 19:53:10', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('15', '热销电脑', '2', '1', '6', '3', '2018-12-15 19:53:57', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('16', '最热数码', '2', '1', '6', '4', '2018-12-15 19:54:22', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('17', '生活用品', '2', '1', '6', '5', '2018-12-15 19:54:57', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('18', '手机配件', '2', '1', '7', '1', '2018-12-15 19:56:22', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('19', '笔记本', '2', '1', '7', '2', '2018-12-15 19:56:47', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('20', '台式电脑', '2', '1', '7', '3', '2018-12-15 19:57:22', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('21', '摄影摄像', '2', '1', '7', '4', '2018-12-15 19:57:47', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('22', '影音娱乐', '2', '1', '7', '5', '2018-12-15 19:58:29', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('23', '实用家居', '2', '1', '8', '1', '2018-12-15 20:00:19', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('24', '家用电器', '2', '1', '8', '2', '2018-12-15 20:00:43', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('25', '出行车辆', '2', '1', '8', '3', '2018-12-15 20:01:09', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('26', '家居百货', '2', '1', '8', '4', '2018-12-15 20:01:40', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('27', '运动器材', '2', '1', '9', '1', '2018-12-15 20:31:26', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('28', '各类书籍', '2', '1', '9', '2', '2018-12-15 20:31:55', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('29', '母婴用品', '2', '1', '10', '1', '2018-12-15 20:32:51', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('30', '服饰箱包', '2', '1', '10', '2', '2018-12-15 20:33:19', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('31', '美容化妆', '2', '1', '10', '3', '2018-12-15 20:33:45', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('32', '办公用品', '2', '1', '11', '1', '2018-12-15 20:34:33', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('33', '机械设备', '2', '1', '11', '2', '2018-12-15 20:35:16', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('34', '礼品卡券', '2', '1', '12', '1', '2018-12-15 20:36:03', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('35', '食品保健', '2', '1', '12', '2', '2018-12-15 20:36:32', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('36', '收藏鉴赏', '2', '1', '12', '3', '2018-12-15 20:37:29', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('37', 'MacBook Pro', '3', '1', '13', '1', '2018-12-15 22:05:05', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('38', 'MacBook', '3', '1', '13', '2', '2018-12-15 22:08:01', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('39', 'iPhone', '3', '1', '13', '3', '2018-12-15 22:08:36', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('40', 'iPad', '3', '1', '13', '3', '2018-12-15 22:08:51', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('41', '小米', '3', '1', '14', '1', '2018-12-15 22:10:06', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('42', '三星', '3', '1', '14', '2', '2018-12-15 22:10:28', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('43', '华为', '3', '1', '14', '3', '2018-12-15 22:10:41', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('44', '苹果', '3', '1', '15', '1', '2018-12-16 16:15:05', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('45', '联想', '3', '1', '15', '2', '2018-12-16 16:15:32', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('46', '戴尔', '3', '1', '15', '3', '2018-12-16 16:15:56', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('47', '华硕', '3', '1', '15', '4', '2018-12-16 16:16:20', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('48', '尼康', '3', '1', '16', '1', '2018-12-16 16:16:50', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('49', '佳能', '3', '1', '16', '2', '2018-12-16 16:17:05', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('50', 'PSV', '3', '1', '16', '3', '2018-12-16 16:17:47', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('51', '电风扇', '3', '1', '17', '1', '2018-12-16 16:18:29', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('52', '双人床', '3', '1', '17', '2', '2018-12-16 16:18:46', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('53', '儿童车', '3', '1', '17', '3', '2018-12-16 16:19:09', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('54', '蓝牙耳机', '3', '1', '18', '1', '2018-12-16 16:21:22', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('55', '充电器', '3', '1', '18', '2', '2018-12-16 16:21:48', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('56', '手机壳', '3', '1', '18', '3', '2018-12-16 16:22:11', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('57', '品牌本', '3', '1', '19', '1', '2018-12-16 16:23:00', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('58', '上网本', '3', '1', '19', '2', '2018-12-16 16:23:22', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('59', '商务本', '3', '1', '19', '3', '2018-12-16 16:23:47', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('60', '台式机', '3', '1', '20', '1', '2018-12-16 16:24:16', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('61', '服务器', '3', '1', '20', '2', '2018-12-16 16:24:40', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('62', '单反相机', '3', '1', '21', '1', '2018-12-16 16:25:37', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('63', '摄像机', '3', '1', '21', '2', '2018-12-16 16:25:58', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('64', '镜头', '3', '1', '21', '3', '2018-12-16 16:26:20', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('65', '音响', '3', '1', '22', '1', '2018-12-16 16:28:07', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('66', 'MP4', '3', '1', '22', '2', '2018-12-16 16:28:48', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('67', '床', '3', '1', '23', '1', '2018-12-16 16:30:13', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('68', '柜子', '3', '1', '23', '2', '2018-12-16 16:30:32', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('69', '沙发', '3', '1', '23', '3', '2018-12-16 16:30:56', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('70', '家居百货', '3', '1', '23', '4', '2018-12-16 16:31:19', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('71', '电视', '3', '1', '24', '1', '2018-12-16 16:31:45', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('72', '空调', '3', '1', '24', '2', '2018-12-16 16:31:59', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('73', '冰箱', '3', '1', '24', '3', '2018-12-16 16:32:19', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('74', '洗衣机', '3', '1', '24', '4', '2018-12-16 16:32:33', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('75', '自行车', '3', '1', '25', '1', '2018-12-16 16:33:02', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('76', '电动车', '3', '1', '25', '2', '2018-12-16 16:33:16', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('77', '三轮车', '3', '1', '25', '3', '2018-12-16 16:33:40', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('80', '装饰摆设', '3', '1', '26', '1', '2018-12-16 03:11:37', '2018-12-16 03:11:37', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('81', '床上用品', '3', '1', '26', '2', '2018-12-16 03:11:49', '2018-12-16 03:11:49', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('82', '跑步机', '3', '1', '27', '1', '2018-12-16 03:11:50', '2018-12-16 03:11:50', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('83', '哑铃', '3', '1', '27', '2', '2018-12-16 03:11:54', '2018-12-16 03:11:54', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('84', '羽毛球拍', '3', '1', '27', '3', '2018-12-16 03:11:53', '2018-12-16 03:11:53', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('85', '专业技术类', '3', '1', '28', '1', '2018-12-16 03:11:55', '2018-12-16 03:11:55', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('86', '考试辅导类', '3', '1', '28', '2', '2018-12-16 03:11:56', '2018-12-16 03:11:56', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('87', '小说文学', '3', '1', '28', '3', '2018-12-16 03:11:58', '2018-12-16 03:11:58', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('88', '学步车', '3', '1', '29', '1', '2018-12-16 03:11:59', '2018-12-16 03:11:59', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('89', '婴儿车', '3', '1', '29', '2', '2018-12-16 03:12:01', '2018-12-16 03:12:01', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('90', '玩具', '3', '1', '29', '3', '2018-12-16 03:12:03', '2018-12-16 03:12:03', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('91', '奶粉', '3', '1', '29', '4', '2018-12-16 03:12:02', '2018-12-16 03:12:02', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('92', '连衣裙', '3', '1', '30', '1', '2018-12-16 03:12:04', '2018-12-16 03:12:04', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('93', '高跟鞋', '3', '1', '30', '2', '2018-12-16 03:12:06', '2018-12-16 03:12:06', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('94', '双肩包', '3', '1', '30', '3', '2018-12-16 03:12:07', '2018-12-16 03:12:07', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('95', '手提包', '3', '1', '30', '4', '2018-12-16 03:11:32', '2018-12-16 03:11:32', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('96', '化妆品', '3', '1', '31', '1', '2018-12-16 03:11:30', '2018-12-16 03:11:30', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('97', '香水', '3', '1', '31', '2', '2018-12-16 03:11:28', '2018-12-16 03:11:28', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('98', '护肤品', '3', '1', '31', '3', '2018-12-16 03:11:27', '2018-12-16 03:11:27', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('99', '打印机', '3', '1', '32', '1', '2018-12-16 03:11:24', '2018-12-16 03:11:24', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('100', '电脑桌', '3', '1', '32', '2', '2018-12-16 03:11:23', '2018-12-16 03:11:23', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('101', '货架', '3', '1', '32', '3', '2018-12-16 03:11:21', '2018-12-16 03:11:21', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('102', '农业机械', '3', '1', '33', '1', '2018-12-16 03:11:20', '2018-12-16 03:11:20', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('103', '拖拉机', '3', '1', '33', '2', '2018-12-16 03:11:16', '2018-12-16 03:11:16', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('104', '送礼佳品', '3', '1', '34', '1', '2018-12-16 03:11:12', '2018-12-16 03:11:12', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('105', '卡类', '3', '1', '34', '2', '2018-12-16 03:11:10', '2018-12-16 03:11:10', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('106', '茶叶', '3', '1', '34', '3', '2018-12-16 03:11:09', '2018-12-16 03:11:09', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('107', '酒水', '3', '1', '34', '4', '2018-12-16 03:11:07', '2018-12-16 03:11:07', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('108', '邮票钱币', '3', '1', '36', '1', '2018-12-16 03:11:06', '2018-12-16 03:11:06', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('109', '古玩字画', '3', '1', '36', '2', '2018-12-16 03:11:05', '2018-12-16 03:11:05', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('110', '金银珠宝', '3', '1', '36', '3', '2018-12-16 03:11:04', '2018-12-16 03:11:04', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('111', '艺术品', '3', '1', '36', '4', '2018-12-16 03:11:03', '2018-12-16 03:11:03', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('112', '冬虫夏草', '3', '1', '35', '1', '2018-12-16 03:11:01', '2018-12-16 03:11:01', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('113', '人参', '3', '1', '35', '2', '2018-12-16 03:11:00', '2018-12-16 03:11:00', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('114', '海参', '3', '1', '35', '3', '2018-12-16 03:10:58', '2018-12-16 03:10:58', null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('115', '萌宠卡券', '1', '1', '1', '8', '2018-12-16 20:04:23', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('116', '消费卡券', '2', '1', '115', '1', '2018-12-16 20:05:10', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('117', '电影演出', '2', '1', '115', '2', '2018-12-16 20:05:30', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('118', '奇趣小宠', '2', '1', '115', '3', '2018-12-16 20:05:55', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('119', '购物卡', '3', '1', '116', '1', '2018-12-16 20:06:39', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('120', '健身卡', '3', '1', '116', '2', '2018-12-16 20:06:58', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('121', '游泳卡', '3', '1', '116', '3', '2018-12-16 20:07:11', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('122', '电影票', '3', '1', '117', '1', '2018-12-16 20:07:37', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('123', '体育赛事', '3', '1', '117', '2', '2018-12-16 20:08:04', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('124', '演唱会', '3', '1', '117', '3', '2018-12-16 20:08:27', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('125', '喵星人', '3', '1', '118', '1', '2018-12-16 20:08:51', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('126', '龙猫', '3', '1', '118', '2', '2018-12-16 20:09:11', null, null, null, null, null);
INSERT INTO `ldbz_category` VALUES ('127', '锦鲤', '3', '1', '118', '3', '2018-12-16 20:09:52', null, null, null, null, null);

-- ----------------------------
-- Table structure for ldbz_item
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_item`;
CREATE TABLE `ldbz_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id，同时也是商品编号',
  `code` bigint(255) NOT NULL,
  `title` varchar(100) NOT NULL COMMENT '商品标题',
  `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
  `old_price` float(10,2) DEFAULT NULL COMMENT '原价',
  `price` float(20,2) NOT NULL COMMENT '商品价格，单位为：分',
  `num` int(10) NOT NULL COMMENT '库存数量',
  `barcode` varchar(500) DEFAULT NULL COMMENT '商品条形码',
  `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `cid` bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
  `weight` int(10) NOT NULL COMMENT '重量',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `colour` varchar(10) NOT NULL COMMENT '颜色',
  `size` varchar(200) NOT NULL COMMENT '尺寸',
  `sort_order` tinyint(4) DEFAULT NULL COMMENT '排序',
  `detail` varchar(3000) DEFAULT NULL COMMENT '商品描述',
  `detail_image` varchar(2000) DEFAULT NULL COMMENT '商品描述图解',
  `preview_image` varchar(2000) DEFAULT NULL COMMENT '商品预览图',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING HASH,
  KEY `cid` (`cid`),
  KEY `status` (`status`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB AUTO_INCREMENT=148630831972915 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of ldbz_item
-- ----------------------------
INSERT INTO `ldbz_item` VALUES ('148630831972868', '1546693041205', '无线蓝牙耳机 迷你超小运动Air双耳pods入耳式 白色', '无线蓝牙', '48.85', '36.00', '22', '', '/uploadfiles/item_image/2019/01/05/1546692944934.jpg', '54', '1', '2', null, null, '2019-01-05 20:57:21', null, null, '2019-01-20 11:35:58', '白色', '一对', '1', 'aaa', null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972869', '1546693814715', 'Apple iPad mini 4 平板电脑 7.9英寸（128G WLAN版/A8芯片/Retina显示屏/Touch ID技术 MK9Q2CH）', '', '2800.00', '2686.00', '90', '', '/uploadfiles/item_image/2019/01/05/1546693810229.jpg', '40', '1', '16', null, null, '2019-01-05 21:10:15', null, null, '2019-01-05 21:11:05', '白色', '7.9', '2', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972870', '1546693967755', 'Apple iMac 27英寸一体机（四核Core i5/8GB内存/1TB） ', '', '12680.00', '12599.00', '13', '', '/uploadfiles/item_image/2019/01/05/1546693964786.jpg', '60', '1', '120', null, null, '2019-01-05 21:12:48', null, null, '2019-01-20 11:46:55', '白色', '27英寸', '3', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972871', '1546694101365', '微单数码相机标准套装', '', '3500.00', '3499.00', '9', '', '/uploadfiles/item_image/2019/01/16/1547645478440.png', '62', '1', '16', null, null, '2019-01-05 21:15:01', null, null, '2019-01-16 21:31:23', '白色', '2430万像素', '4', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972872', '1546694295365', '无人机 御Mavic Pro 铂金版 迷你可折叠4K超清航拍无人机', '', '6400.00', '6499.00', '4', '', '/uploadfiles/item_image/2019/01/05/1546694292539.jpg', '63', '1', '12', null, null, '2019-01-05 21:18:15', null, null, null, '白色', '16英寸', '5', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972873', '1546694466988', '十字对开门电冰箱 变频风冷无霜 家用冰箱节能省电', '', '5000.00', '4899.00', '6', '', '/uploadfiles/item_image/2019/01/05/1546694460388.jpg', '73', '1', '1200', null, null, '2019-01-05 21:21:07', null, null, null, '灰色', '401-500升', '8', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972874', '1546694638143', '米动手表青春版 华米科技出品 智能手表 运动手表', '', '420.00', '399.00', '16', '', '/uploadfiles/item_image/2019/01/05/1546694630524.jpg', '41', '1', '19', null, null, '2019-01-05 21:23:58', null, null, null, '棕色', '', '9', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972875', '1546694754124', 'Apple iPhone 6s Plus (A1699) 32G', '', '2800.00', '2749.00', '80', '', '/uploadfiles/item_image/2019/01/05/1546694751575.jpg', '39', '1', '120', null, null, '2019-01-05 21:25:54', null, null, null, '玫瑰金', '', '9', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972876', '1546694856179', '苹果数据线 Xs Max/XR/X/8手机充电线', '', '2200.00', '2190.00', '12', '', '/uploadfiles/item_image/2019/01/05/1546694818890.jpg', '55', '1', '10', null, null, '2019-01-05 21:27:36', null, null, null, '黑色', '1m', '8', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972877', '1546694972428', 'Apple MacBook Air 13.3英寸笔记本电脑', '', '7500.00', '7488.00', '67', '', '/uploadfiles/item_image/2019/01/05/1546694931578.jpg', '38', '1', '126', null, null, '2019-01-05 21:29:32', null, null, null, '银白', '13.3英寸', '10', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972878', '1546695062875', '蓝牙无线头戴式线控苹果耳机', '', '1700.00', '1699.00', '15', '', '/uploadfiles/item_image/2019/01/05/1546695058230.jpg', '54', '1', '15', null, null, '2019-01-05 21:31:03', null, null, null, '蓝色', '', '11', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972879', '1546696893811', '三星 Galaxy S8（SM-G9500）4GB+64GB', '', '3400.00', '3299.00', '5', '', '/uploadfiles/item_image/2019/01/05/1546696890104.jpg', '42', '1', '120', null, null, '2019-01-05 22:01:34', null, null, null, '黑色', '64G', '13', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972880', '1546697001591', '爱丽思（IRIS）日本爱丽思电风扇', '', '150.00', '128.00', '4', '', '/uploadfiles/item_image/2019/01/05/1546696998842.jpg', '51', '1', '120', null, null, '2019-01-05 22:03:22', null, null, null, '白色', '12', '13', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972881', '1546697160327', '小米电视4X 55英寸', '', '2300.00', '2298.00', '5', '', '/uploadfiles/item_image/2019/01/05/1546697136081.jpg', '71', '1', '320', null, null, '2019-01-05 22:06:00', null, null, null, '黑色', '55', '14', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972882', '1546697275489', '漫步者（EDIFIER） R101V 2.1声道多媒体音箱', '', '150.00', '130.00', '9', '', '/uploadfiles/item_image/2019/01/05/1546697257447.jpg', '65', '1', '86', null, null, '2019-01-05 22:07:55', null, null, null, '黑色', '20', '15', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972883', '1546697355941', '尼康（Nikon）D5300 18-140VR防抖单反数码照相机', '', '4200.00', '4199.23', '2', '', '/uploadfiles/item_image/2019/01/05/1546697351403.jpg', '48', '1', '14', null, null, '2019-01-05 22:09:16', null, null, '2019-01-16 22:05:34', '黑色', '8', '16', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972884', '1547817448105', '佳能（Canon）数码微单相机 EOS M100  15-45mm套机礼包版 黑色', '', '2600.00', '2549.00', '5', '', '/uploadfiles/item_image/2019/01/18/1547817299297.png', '62', '1', '200', null, null, '2019-01-18 21:17:28', null, null, null, '黑', '', '20', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972885', '1547818094887', '山海经(全译插图典藏版)（京东专享小岩井限量签名本）', '', '38.00', '34.00', '2', '', '/uploadfiles/item_image/2019/01/18/1547818031839.png', '87', '1', '12', null, null, '2019-01-18 21:28:15', null, null, null, '黑', '', '21', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972886', '1547818224398', '繁花（新版）', '', '40.00', '28.00', '6', '', '/uploadfiles/item_image/2019/01/18/1547818220544.png', '87', '1', '21', null, null, '2019-01-18 21:30:24', null, null, null, '', '', '23', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972887', '1547818295982', '一个刑警的日子', '', '42.00', '35.00', '2', '', '/uploadfiles/item_image/2019/01/18/1547818285314.png', '87', '1', '21', null, null, '2019-01-18 21:31:36', null, null, null, '', '', '24', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972888', '1547818364041', '岁时花艺设计指南', '', '41.00', '36.00', '4', '', '/uploadfiles/item_image/2019/01/18/1547818361751.png', '86', '1', '12', null, null, '2019-01-18 21:32:44', null, null, null, '', '', '25', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972889', '1547818605111', '奥克斯1.5匹一级变频', '', '2400.00', '2346.00', '4', '', '/uploadfiles/item_image/2019/01/18/1547818596443.png', '72', '1', '43', null, null, '2019-01-18 21:36:45', null, null, null, '白色', '', '26', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972890', '1547818737661', '海信 65英寸 超薄4K 无边全面屏', '', '1250.00', '1200.00', '6', '', '/uploadfiles/item_image/2019/01/18/1547818721401.png', '71', '1', '34', null, null, '2019-01-18 21:38:58', null, null, null, '', '', '27', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972891', '1547818987285', 'TCL 65Q1D 65英寸34核人工智能 超薄全面屏HDR4K网络液晶电视机', '', '4900.00', '4812.00', '4', '', '/uploadfiles/item_image/2019/01/18/1547818976127.png', '71', '1', '321', null, null, '2019-01-18 21:43:07', null, null, null, '黑色', '', '31', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972892', '1547819084516', '创维55H6 55英寸超薄护眼全面屏 2+32G AI人工智能', '', '4700.00', '4622.00', '6', '', '/uploadfiles/item_image/2019/01/18/1547819083254.png', '71', '1', '121', null, null, '2019-01-18 21:44:45', null, null, null, '黑色', '', '32', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972893', '1547819144162', '飞利浦 55PUF6693/T3 55英寸 超薄HDR 金属边框 人工智能', '', '4700.00', '4688.00', '2', '', '/uploadfiles/item_image/2019/01/18/1547819141011.png', '71', '1', '43', null, null, '2019-01-18 21:45:44', null, null, null, '黑色', '', '33', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972894', '1547819666998', '凤凰牌儿童自行车山地车3-10岁童车男孩', '', '250.00', '233.00', '3', '', '/uploadfiles/item_image/2019/01/18/1547819657747.png', '53', '1', '32', null, null, '2019-01-18 21:54:27', null, null, null, '白色', '', '41', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972895', '1547819823179', 'hd小龙哈彼 儿童自行车女童款小孩12/14寸公主山地单车', '', '252.00', '233.00', '5', '', '/uploadfiles/item_image/2019/01/18/1547819817891.png', '53', '1', '21', null, null, '2019-01-18 21:57:03', null, null, null, '粉色', '', '42', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972896', '1547820016222', '千禧盛世 简约现代中式实木床主卧双人', '', '1521.00', '1433.00', '6', '', '/uploadfiles/item_image/2019/01/18/1547820006494.png', '52', '1', '344', null, null, '2019-01-18 22:00:16', null, null, null, '', '1.8m', '44', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972897', '1547821339617', '美旅AmericanTourister明星同款韩版双肩背包', '', '320.00', '300.00', '5', '', '/uploadfiles/item_image/2019/01/18/1547821337442.png', '94', '1', '1', null, null, '2019-01-18 22:22:20', null, null, null, '黑色', '', '48', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972898', '1547821444001', 'Sai Lv 双肩包 女生双肩包 休闲包', '', '230.00', '211.00', '6', '', '/uploadfiles/item_image/2019/01/18/1547821441551.png', '30', '1', '11', null, null, '2019-01-18 22:24:04', null, null, null, '', '', '49', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972899', '1547821683677', '依波(EBOHR)手表 都市经典系列钟表', '', '700.00', '680.00', '2', '', '/uploadfiles/item_image/2019/01/18/1547821674167.png', '104', '1', '12', null, null, '2019-01-18 22:28:04', null, null, null, '银白', '', '49', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972900', '1547822475353', '公鸡定做婚鞋女2018新款中式婚礼新娘鞋', '', '320.00', '311.00', '4', '', '/uploadfiles/item_image/2019/01/18/1547822458032.png', '93', '1', '2', null, null, '2019-01-18 22:41:15', null, null, null, '红色', '', '51', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972901', '1547822575821', '金利来（goldlion）女士时尚反绒皮浅口单细高跟鞋', '', '160.00', '136.00', '2', '', '/uploadfiles/item_image/2019/01/18/1547822547803.png', '93', '1', '2', null, null, '2019-01-18 22:42:56', null, null, null, '黑色', '', '52', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972902', '1547823390490', '爱华仕（OIWAS）旅行袋 手提旅行包男女', '', '130.00', '126.00', '3', '', '/uploadfiles/item_image/2019/01/18/1547823371825.png', '94', '1', '12', null, null, '2019-01-18 22:56:30', null, null, null, '黑色', '', '55', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972903', '1547823502424', '国蓝贵宾白酒52度浓香型纯粮食白酒整箱特价2瓶装礼盒酒水', '', '420.00', '400.00', '3', '', '/uploadfiles/item_image/2019/01/18/1547823496916.png', '107', '1', '58', null, null, '2019-01-18 22:58:22', null, null, null, '', '', '52', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972904', '1547823628489', '贵州茅台镇 陈年老酱 酱香型白酒', '', '500.00', '468.00', '3', '', '/uploadfiles/item_image/2019/01/18/1547823622243.png', '107', '1', '256', null, null, '2019-01-18 23:00:28', null, null, null, '', '', '56', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972905', '1547824041239', '荷兰牛栏 诺优能Nutrilon 儿童配方奶粉', '', '380.00', '360.00', '6', '', '/uploadfiles/item_image/2019/01/18/1547824020903.png', '91', '1', '36', null, null, '2019-01-18 23:07:21', null, null, null, '', '', '56', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972906', '1547824161292', '乐高(LEGO)积木 机械组Technic高速赛车', '', '250.00', '236.00', '4', '', '/uploadfiles/item_image/2019/01/18/1547824156475.png', '90', '1', '23', null, null, '2019-01-18 23:09:21', null, null, null, '', '', '58', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972907', '1547824266221', '精美礼盒亚菲儿女士香水女士持久淡香清新50ml', '', '90.00', '89.00', '4', '', '/uploadfiles/item_image/2019/01/18/1547824262376.png', '97', '1', '1', null, null, '2019-01-18 23:11:06', null, null, null, '', '', '59', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972908', '1547954244913', '小米(MI)Air 13.3英寸全金属超轻薄笔记本电脑', '', '4510.00', '4500.00', '6', '', '/uploadfiles/item_image/2019/01/20/1547954228939.png', '41', '1', '21', null, null, '2019-01-20 11:17:25', null, null, null, '银灰色', 'i5', '60', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972909', '1547954398603', '希洛普（SEALUP） 锂电电动滑板车 折叠电动车', '', '3520.00', '3500.00', '3', '', '/uploadfiles/item_image/2019/01/20/1547954387802.png', '76', '1', '126', null, null, '2019-01-20 11:19:59', null, null, null, '黑色', '', '61', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972910', '1547954545986', '永久24速自行车 前后减震折叠山地车', '', '1250.00', '1200.00', '5', '', '/uploadfiles/item_image/2019/01/20/1547954529224.png', '75', '1', '28', null, null, '2019-01-20 11:22:26', null, null, null, '黑色', '', '63', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972911', '1547954716229', '迷你苹果平板电脑 Apple iPad Mini2系列', '', '1130.00', '1128.00', '4', '', '/uploadfiles/item_image/2019/01/20/1547954693300.png', '40', '1', '2', null, null, '2019-01-20 11:25:16', null, null, null, '银灰色', '', '63', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972912', '1547954837980', '南极人NanJiren 被子 水洗棉夏被空调被', '', '80.00', '78.00', '3', '', '/uploadfiles/item_image/2019/01/20/1547954830466.png', '81', '1', '1', null, null, '2019-01-20 11:27:18', null, null, null, '', '', '64', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972913', '1547955203765', '爱普生（EPSON）LQ-680KII 针式打印机', '', '3702.00', '3666.00', '2', '', '/uploadfiles/item_image/2019/01/20/1547955187993.png', '99', '1', '12', null, null, '2019-01-20 11:33:24', null, null, '2019-01-20 18:42:55', '白色', '', '65', null, null, null);
INSERT INTO `ldbz_item` VALUES ('148630831972914', '1548642115452', '飞利浦（PHILIPS）电视机55吋液晶电视机4k超高清智能网络彩电', null, '2599.00', '2399.00', '6', '', '/uploadfiles/item_image/2019/01/28/1548641331089.png', '71', '1', '1833', null, null, '2019-01-28 10:21:55', null, null, '2019-01-28 14:00:37', '黑色', '4k超高清（3840×2160）', '70', '品牌： 飞利浦（PHILIPS）\n商品名称：飞利浦55PUF6192/T3\n商品毛重：13.5kg\n分辨率：4k超高清（3840×2160）\n电视类型：4K超清\n屏幕尺寸：55英寸\n', '/uploadfiles/item_image_detail/2019/01/28/1548652637099.jpg,/uploadfiles/item_image_detail/2019/01/28/1548652633912.jpg,/uploadfiles/item_image_detail/2019/01/28/1548652630801.jpg,', '/uploadfiles/item_image_detail/2019/01/28/1548654392667.jpg,/uploadfiles/item_image_detail/2019/01/28/1548654388605.jpg,/uploadfiles/item_image_detail/2019/01/28/1548654380593.jpg,/uploadfiles/item_image_detail/2019/01/28/1548653346686.jpg,/uploadfiles/item_image_detail/2019/01/28/1548652854100.png,');

-- ----------------------------
-- Table structure for ldbz_item_comment
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_item_comment`;
CREATE TABLE `ldbz_item_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_code` bigint(15) NOT NULL COMMENT '商品code',
  `creator` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `creator_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人姓名',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `comment` varchar(900) DEFAULT NULL COMMENT '评论',
  `star` int(11) DEFAULT NULL COMMENT '评星',
  `status` tinyint(4) DEFAULT '1' COMMENT '1启用;0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ldbz_item_comment
-- ----------------------------
INSERT INTO `ldbz_item_comment` VALUES ('1', '1548642115452', '1', '刘德华', '2019-01-28 21:46:17', '商品清晰，看着就爽', '5', '1');

-- ----------------------------
-- Table structure for ldbz_item_sheet
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_item_sheet`;
CREATE TABLE `ldbz_item_sheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sheet_key` varchar(255) DEFAULT NULL COMMENT '板块关键字',
  `sheet_name` varchar(255) NOT NULL DEFAULT '默认板块' COMMENT '版面名称',
  `sort_order` tinyint(4) NOT NULL DEFAULT '1',
  `item_count` tinyint(4) DEFAULT '20' COMMENT '面板显示商品数量',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `creator_name` varchar(255) DEFAULT NULL,
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updator` varchar(255) DEFAULT NULL,
  `updator_name` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1启用；0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ldbz_item_sheet
-- ----------------------------
INSERT INTO `ldbz_item_sheet` VALUES ('1', 'New_Arrivals', '最新发布', '1', '16', '2019-01-14 06:58:51', null, null, '2019-01-14 06:58:51', null, null, '1');
INSERT INTO `ldbz_item_sheet` VALUES ('2', 'Featured', '特色商品', '2', '16', '2019-01-14 06:59:01', null, null, '2019-01-14 06:59:01', null, null, '1');
INSERT INTO `ldbz_item_sheet` VALUES ('3', 'Top_Selling', '热销商品', '3', '16', '2019-01-14 06:59:10', null, null, '2019-01-14 06:59:10', null, null, '1');
INSERT INTO `ldbz_item_sheet` VALUES ('4', 'Recommend', '商品推荐', '4', '30', '2019-01-14 07:01:16', null, null, '2019-01-14 07:01:16', null, null, '1');
INSERT INTO `ldbz_item_sheet` VALUES ('5', 'Most_Viewed', '最受欢迎', '5', '20', '2019-01-14 07:00:24', null, null, '2019-01-14 07:00:24', null, null, '1');
INSERT INTO `ldbz_item_sheet` VALUES ('6', 'Bestsellers', '畅销', '6', '3', '2019-01-14 07:00:13', null, null, '2019-01-14 07:00:13', null, null, '1');
INSERT INTO `ldbz_item_sheet` VALUES ('7', 'Featured_2', '特色', '7', '3', '2019-01-14 07:00:07', null, null, '2019-01-14 07:00:07', null, null, '1');
INSERT INTO `ldbz_item_sheet` VALUES ('8', 'Hot_Sale', '热卖', '8', '3', '2019-01-14 06:59:57', null, null, '2019-01-14 06:59:57', null, null, '1');

-- ----------------------------
-- Table structure for ldbz_item_sheet_assign
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_item_sheet_assign`;
CREATE TABLE `ldbz_item_sheet_assign` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `sheet_id` bigint(11) NOT NULL COMMENT '板块ID',
  `sheet_name` varchar(255) DEFAULT NULL COMMENT '板块名称',
  `item_id` bigint(11) NOT NULL COMMENT '商品ID',
  `item_code` bigint(20) DEFAULT NULL COMMENT '商品编号',
  `item_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `creator` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `creator_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人姓名',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updator_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人姓名',
  `updator` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `sort_order` tinyint(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='商品板块的分配表';

-- ----------------------------
-- Records of ldbz_item_sheet_assign
-- ----------------------------
INSERT INTO `ldbz_item_sheet_assign` VALUES ('1', '1', '最新发布', '148630831972883', '1546697355941', '尼康（Nikon）D5300 18-140VR防抖单反数码照相机', null, null, '2019-01-05 22:09:39', null, null, null, '1');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('2', '1', '最新发布', '148630831972882', '1546697275489', '漫步者（EDIFIER） R101V 2.1声道多媒体音箱', null, null, '2019-01-05 22:09:51', null, null, null, '2');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('3', '1', '最新发布', '148630831972881', '1546697160327', '小米电视4X 55英寸', null, null, '2019-01-05 22:10:00', null, null, null, '3');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('4', '1', '最新发布', '148630831972879', '1546696893811', '三星 Galaxy S8（SM-G9500）4GB+64GB', null, null, '2019-01-05 22:10:10', null, null, null, '4');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('5', '1', '最新发布', '148630831972875', '1546694754124', 'Apple iPhone 6s Plus (A1699) 32G', null, null, '2019-01-05 22:10:33', null, null, null, '5');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('7', '1', '最新发布', '148630831972869', '1546693814715', 'Apple iPad mini 4 平板电脑 7.9英寸（128G WLAN版/A8芯片/Retina显示屏/Touch ID技术 MK9Q2CH）', null, null, '2019-01-05 22:11:08', null, null, null, '7');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('8', '1', '最新发布', '148630831972870', '1546693967755', 'Apple iMac 27英寸一体机（2017款四核Core i5/8GB内存/1TB Fusion Drive/RP570显卡/5K屏 MNE92CH/A） ', null, null, '2019-01-05 22:11:20', null, null, null, '8');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('10', '1', '最新发布', '148630831972872', '1546694295365', '无人机 御Mavic Pro 铂金版 迷你可折叠4K超清航拍无人机', null, null, '2019-01-05 22:13:23', null, null, null, '6');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('11', '3', '热销商品', '148630831972878', '1546695062875', '蓝牙无线头戴式线控苹果耳机', null, null, '2019-01-16 21:23:16', null, null, null, '1');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('12', '3', '热销商品', '148630831972874', '1546694638143', '米动手表青春版 华米科技出品 智能手表 运动手表', null, null, '2019-01-16 21:23:42', null, null, null, '2');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('13', '3', '热销商品', '148630831972873', '1546694466988', '十字对开门电冰箱 变频风冷无霜 家用冰箱节能省电', null, null, '2019-01-16 21:24:00', null, null, null, '3');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('14', '3', '热销商品', '148630831972871', '1546694101365', '微单数码相机标准套装', null, null, '2019-01-16 21:24:11', null, null, null, '4');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('15', '3', '热销商品', '148630831972870', '1546693967755', 'Apple iMac 27英寸一体机（2017款四核Core i5/8GB内存/1TB Fusion Drive/RP570显卡/5K屏 MNE92CH/A） ', null, null, '2019-01-16 21:24:23', null, null, null, '5');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('16', '3', '热销商品', '148630831972868', '1546693041205', '无线蓝牙耳机 迷你超小运动Air适用于安卓/华为/苹果/oppo/vivo双耳pods入耳式 白色', null, null, '2019-01-16 21:24:38', null, null, null, '6');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('17', '3', '热销商品', '148630831972880', '1546697001591', '爱丽思（IRIS）日本爱丽思电风扇', null, null, '2019-01-16 21:24:56', null, null, null, '7');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('18', '3', '热销商品', '148630831972876', '1546694856179', '苹果数据线 Xs Max/XR/X/8手机充电线', null, null, '2019-01-16 21:25:11', null, null, null, '8');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('19', '2', '特色商品', '148630831972890', '1547818737661', '海信 65英寸 超薄4K 无边全面屏', null, null, '2019-01-18 21:39:50', null, null, null, '1');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('20', '2', '特色商品', '148630831972889', '1547818605111', '奥克斯1.5匹一级变频', null, null, '2019-01-18 21:39:59', null, null, null, '2');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('21', '2', '特色商品', '148630831972888', '1547818364041', '岁时花艺设计指南', null, null, '2019-01-18 21:40:08', null, null, null, '3');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('22', '2', '特色商品', '148630831972887', '1547818295982', '一个刑警的日子', null, null, '2019-01-18 21:40:18', null, null, null, '4');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('23', '2', '特色商品', '148630831972886', '1547818224398', '繁花（新版）', null, null, '2019-01-18 21:40:29', null, null, null, '5');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('24', '2', '特色商品', '148630831972885', '1547818094887', '山海经(全译插图典藏版)（京东专享小岩井限量签名本）', null, null, '2019-01-18 21:40:38', null, null, null, '6');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('25', '2', '特色商品', '148630831972884', '1547817448105', '佳能（Canon）数码微单相机 EOS M100  15-45mm套机礼包版 黑色', null, null, '2019-01-18 21:40:48', null, null, null, '7');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('26', '2', '特色商品', '148630831972881', '1546697160327', '小米电视4X 55英寸', null, null, '2019-01-18 21:41:00', null, null, null, '8');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('27', '4', '商品推荐', '148630831972893', '1547819144162', '飞利浦 55PUF6693/T3 55英寸 超薄HDR 金属边框 人工智能', null, null, '2019-01-18 21:46:17', null, null, null, '1');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('28', '5', '最受欢迎', '148630831972892', '1547819084516', '创维55H6 55英寸超薄护眼全面屏 2+32G AI人工智能', null, null, '2019-01-18 21:46:40', null, null, null, '1');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('29', '5', '最受欢迎', '148630831972890', '1547818737661', '海信 65英寸 超薄4K 无边全面屏', null, null, '2019-01-18 21:46:51', null, null, null, '2');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('30', '4', '商品推荐', '148630831972891', '1547818987285', 'TCL 65Q1D 65英寸34核人工智能 超薄全面屏HDR4K网络液晶电视机', null, null, '2019-01-18 21:47:06', null, null, null, '2');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('31', '4', '商品推荐', '148630831972894', '1547819666998', '凤凰牌儿童自行车山地车3-10岁童车男孩', null, null, '2019-01-18 21:54:59', null, null, null, '3');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('32', '4', '商品推荐', '148630831972895', '1547819823179', 'hd小龙哈彼 儿童自行车女童款小孩12/14寸公主山地单车', null, null, '2019-01-18 21:57:23', null, null, null, '4');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('33', '4', '商品推荐', '148630831972896', '1547820016222', '千禧盛世 简约现代中式实木床主卧双人', null, null, '2019-01-18 22:00:44', null, null, null, '5');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('34', '4', '商品推荐', '148630831972897', '1547821339617', '美旅AmericanTourister明星同款韩版双肩背包', null, null, '2019-01-18 22:22:35', null, null, null, '6');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('35', '4', '商品推荐', '148630831972898', '1547821444001', 'Sai Lv 双肩包 女生双肩包 休闲包', null, null, '2019-01-18 22:24:17', null, null, null, '7');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('36', '4', '商品推荐', '148630831972899', '1547821683677', '依波(EBOHR)手表 都市经典系列钟表', null, null, '2019-01-18 22:28:20', null, null, null, '8');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('37', '4', '商品推荐', '148630831972873', '1546694466988', '十字对开门电冰箱 变频风冷无霜 家用冰箱节能省电', null, null, '2019-01-18 22:33:32', null, null, null, '9');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('38', '4', '商品推荐', '148630831972871', '1546694101365', '微单数码相机标准套装', null, null, '2019-01-18 22:33:43', null, null, null, '10');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('39', '4', '商品推荐', '148630831972879', '1546696893811', '三星 Galaxy S8（SM-G9500）4GB+64GB', null, null, '2019-01-18 22:33:52', null, null, null, '11');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('40', '4', '商品推荐', '148630831972882', '1546697275489', '漫步者（EDIFIER） R101V 2.1声道多媒体音箱', null, null, '2019-01-18 22:34:08', null, null, null, '12');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('42', '5', '最受欢迎', '148630831972891', '1547818987285', 'TCL 65Q1D 65英寸34核人工智能 超薄全面屏HDR4K网络液晶电视机', null, null, '2019-01-18 22:43:12', null, null, null, '6');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('43', '5', '最受欢迎', '148630831972902', '1547823390490', '爱华仕（OIWAS）旅行袋 手提旅行包男女', null, null, '2019-01-18 22:56:48', null, null, null, '4');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('44', '5', '最受欢迎', '148630831972903', '1547823502424', '国蓝贵宾白酒52度浓香型纯粮食白酒整箱特价2瓶装礼盒酒水', null, null, '2019-01-18 22:58:36', null, null, null, '5');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('45', '5', '最受欢迎', '148630831972904', '1547823628489', '贵州茅台镇 陈年老酱 酱香型白酒', null, null, '2019-01-18 23:00:41', null, null, null, '3');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('46', '6', '畅销', '148630831972905', '1547824041239', '荷兰牛栏 诺优能Nutrilon 儿童配方奶粉', null, null, '2019-01-18 23:07:41', null, null, null, '1');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('47', '6', '畅销', '148630831972906', '1547824161292', '乐高(LEGO)积木 机械组Technic高速赛车', null, null, '2019-01-18 23:09:30', null, null, null, '2');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('48', '6', '畅销', '148630831972907', '1547824266221', '精美礼盒亚菲儿女士香水女士持久淡香清新50ml', null, null, '2019-01-18 23:11:15', null, null, null, '3');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('49', '7', '特色', '148630831972908', '1547954244913', '小米(MI)Air 13.3英寸全金属超轻薄笔记本电脑', null, null, '2019-01-20 11:17:41', null, null, null, '1');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('50', '7', '特色', '148630831972909', '1547954398603', '希洛普（SEALUP） 锂电电动滑板车 折叠电动车', null, null, '2019-01-20 11:20:06', null, null, null, '2');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('51', '7', '特色', '148630831972910', '1547954545986', '永久24速自行车 前后减震折叠山地车', null, null, '2019-01-20 11:22:35', null, null, null, '3');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('52', '8', '热卖', '148630831972911', '1547954716229', '迷你苹果平板电脑 Apple iPad Mini2系列', null, null, '2019-01-20 11:25:36', null, null, null, '1');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('53', '8', '热卖', '148630831972912', '1547954837980', '南极人NanJiren 被子 水洗棉夏被空调被', null, null, '2019-01-20 11:27:26', null, null, null, '2');
INSERT INTO `ldbz_item_sheet_assign` VALUES ('54', '8', '热卖', '148630831972913', '1547955203765', '爱普生（EPSON）LQ-680KII 针式打印机', null, null, '2019-01-20 11:33:33', null, null, null, '3');

-- ----------------------------
-- Function structure for getCategoryTree
-- ----------------------------
DROP FUNCTION IF EXISTS `getCategoryTree`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `getCategoryTree`(rootId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR (4000);
DECLARE sTempChd VARCHAR (4000);
SET sTemp = '$';
SET sTempChd = CAST(rootId AS CHAR);
WHILE sTempChd IS NOT NULL DO

SET sTemp = CONCAT(sTemp, ',', sTempChd);
SELECT
	GROUP_CONCAT(id) INTO sTempChd
FROM
	ldbz_category
WHERE
	FIND_IN_SET(fid, sTempChd) > 0;
END
WHILE;
RETURN sTemp;
END
;;
DELIMITER ;
