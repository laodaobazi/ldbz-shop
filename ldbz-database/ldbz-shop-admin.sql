/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机
Source Server Version : 50723
Source Host           : 192.168.136.134:3306
Source Database       : ldbz-shop-admin

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-13 15:07:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ldbz_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_admin_menu`;
CREATE TABLE `ldbz_admin_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人ID',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
  `menu_parent` int(11) DEFAULT NULL COMMENT '父节点',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单的跳转地址',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `sort_order` tinyint(4) NOT NULL DEFAULT '1' COMMENT '菜单排序',
  `menu_type` tinyint(4) DEFAULT '21' COMMENT '菜单类型：1普通菜单，2分组菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单';

-- ----------------------------
-- Records of ldbz_admin_menu
-- ----------------------------
INSERT INTO `ldbz_admin_menu` VALUES ('2', '2018-10-18 21:23:45', '2018-10-18 21:23:45', null, null, null, null, '额鹅鹅鹅', '0', 'ffff', 'fd', '1', '1');
INSERT INTO `ldbz_admin_menu` VALUES ('3', '2018-10-18 22:14:28', '2018-10-18 22:14:28', null, null, null, null, 'fff', '0', 'wqw', 's', '22', '1');
INSERT INTO `ldbz_admin_menu` VALUES ('4', '2018-10-18 22:14:47', '2018-10-18 22:14:47', null, null, null, null, 'ddd', '3', 'eee', 'dsds', '2', '1');

-- ----------------------------
-- Table structure for ldbz_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_admin_role`;
CREATE TABLE `ldbz_admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人ID',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `sort_order` tinyint(4) NOT NULL DEFAULT '1' COMMENT '菜单排序',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色';

-- ----------------------------
-- Records of ldbz_admin_role
-- ----------------------------
INSERT INTO `ldbz_admin_role` VALUES ('1', '2018-10-20 11:34:16', '2018-10-20 11:34:16', null, null, null, null, '1', '管理员');
INSERT INTO `ldbz_admin_role` VALUES ('2', '2018-10-20 11:34:30', '2018-10-20 11:34:30', null, null, null, null, '2', '测试人员');
INSERT INTO `ldbz_admin_role` VALUES ('4', null, '2018-10-20 15:30:34', null, null, null, null, '3', 'RRR');

-- ----------------------------
-- Table structure for ldbz_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_admin_role_menu`;
CREATE TABLE `ldbz_admin_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联';

-- ----------------------------
-- Records of ldbz_admin_role_menu
-- ----------------------------
INSERT INTO `ldbz_admin_role_menu` VALUES ('1', '2', '2018-11-19 09:52:04', null);
INSERT INTO `ldbz_admin_role_menu` VALUES ('1', '3', '2018-11-19 09:48:35', null);

-- ----------------------------
-- Table structure for ldbz_admin_role_user
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_admin_role_user`;
CREATE TABLE `ldbz_admin_role_user` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联';

-- ----------------------------
-- Records of ldbz_admin_role_user
-- ----------------------------
INSERT INTO `ldbz_admin_role_user` VALUES ('1', '1', '2019-01-04 22:09:11', null);
INSERT INTO `ldbz_admin_role_user` VALUES ('2', '1', '2019-01-04 22:09:21', null);

-- ----------------------------
-- Table structure for ldbz_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `ldbz_admin_user`;
CREATE TABLE `ldbz_admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updator` varchar(255) DEFAULT NULL COMMENT '修改人ID',
  `updator_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `sort_order` tinyint(4) NOT NULL DEFAULT '1' COMMENT '菜单排序',
  `user_name` varchar(255) NOT NULL COMMENT '账号',
  `user_pwd` varchar(255) NOT NULL COMMENT '密码',
  `real_name` varchar(255) NOT NULL COMMENT '姓名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(255) DEFAULT NULL COMMENT '手机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='后台用户';

-- ----------------------------
-- Records of ldbz_admin_user
-- ----------------------------
INSERT INTO `ldbz_admin_user` VALUES ('1', '2018-11-18 04:21:37', '2018-11-18 04:21:37', null, null, null, null, '1', '09999', 'aaaaaa', 'sssss', 'sdsf@qq.com', '123123123');
INSERT INTO `ldbz_admin_user` VALUES ('2', null, '2019-01-04 22:07:11', null, null, null, null, '1', 'aaa', 'qqqqqq', 'cccccssss', 'sss@qq.com', '');
