/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : rock

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 11/05/2021 13:35:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rbac_permission
-- ----------------------------
DROP TABLE IF EXISTS `rbac_permission`;
CREATE TABLE `rbac_permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) NULL DEFAULT 0,
  `name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `hide_in_menu` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否在导航菜单中',
  `hide_children_in_menu` tinyint(1) UNSIGNED NULL DEFAULT 1,
  `priority` int(10) UNSIGNED NULL DEFAULT 0,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rbac_permission
-- ----------------------------
INSERT INTO `rbac_permission` VALUES (1, 0, '权限管理', 'icon-quanxian', '/rbac', 2, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (2, 1, '角色管理', NULL, '/rbac/role/index', 2, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (3, 1, '用户管理', NULL, '/rbac/user/index', 2, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (21, 10, '添加资源', NULL, '/rbac/permission/create', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (14, 10, '删除资源', NULL, '/rbac/permission/delete', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (6, 3, '用户信息', NULL, '/rbac/user/current', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (7, 2, '添加角色', NULL, '/rbac/role/create', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (8, 2, '删除角色', NULL, '/rbac/role/delete', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (9, 2, '编辑角色', NULL, '/rbac/role/update', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (10, 1, '资源管理', NULL, '/rbac/permission/index', 2, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (26, 1, '操作日志', '', '/rbac/logs/index', 2, 2, 1, '2021-04-11 09:04:14', '2021-04-25 22:14:27');
INSERT INTO `rbac_permission` VALUES (27, 10, '更新权限', NULL, '/rbac/permission/update', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (28, 2, '权限分配', NULL, '/rbac/role/permission/index', 1, 2, 2, '2021-04-11 09:12:15', NULL);
INSERT INTO `rbac_permission` VALUES (29, 3, '用户菜单', NULL, '/rbac/user/menu', 1, 2, 0, NULL, NULL);

-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `code` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
INSERT INTO `rbac_role` VALUES (1, '系统管理员', 'admin', '系统总管理员');
INSERT INTO `rbac_role` VALUES (4, '财务管理', 'finance', '负责平台财务管理，提现等处理。');

-- ----------------------------
-- Table structure for rbac_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_permission`;
CREATE TABLE `rbac_role_permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(10) UNSIGNED NULL DEFAULT 0,
  `permission_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_id,permission_id`(`role_id`, `permission_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of rbac_role_permission
-- ----------------------------
INSERT INTO `rbac_role_permission` VALUES (1, 1, 1);
INSERT INTO `rbac_role_permission` VALUES (2, 1, 2);
INSERT INTO `rbac_role_permission` VALUES (3, 1, 3);
INSERT INTO `rbac_role_permission` VALUES (4, 1, 4);
INSERT INTO `rbac_role_permission` VALUES (5, 1, 5);
INSERT INTO `rbac_role_permission` VALUES (6, 1, 6);
INSERT INTO `rbac_role_permission` VALUES (7, 1, 7);
INSERT INTO `rbac_role_permission` VALUES (8, 1, 8);
INSERT INTO `rbac_role_permission` VALUES (9, 1, 9);
INSERT INTO `rbac_role_permission` VALUES (10, 1, 10);
INSERT INTO `rbac_role_permission` VALUES (11, 1, 14);
INSERT INTO `rbac_role_permission` VALUES (12, 1, 21);
INSERT INTO `rbac_role_permission` VALUES (13, 1, 27);
INSERT INTO `rbac_role_permission` VALUES (14, 1, 29);
INSERT INTO `rbac_role_permission` VALUES (15, 1, 28);
INSERT INTO `rbac_role_permission` VALUES (16, 1, 26);

-- ----------------------------
-- Table structure for rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user`;
CREATE TABLE `rbac_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `mobile` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `status` tinyint(1) UNSIGNED NULL DEFAULT 1,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
INSERT INTO `rbac_user` VALUES (1, '', 'admin', '$2a$10$xexcn0GDHSHGfRbch6i1G.tAfQCwgG6yuH4H6ODAf.dcOqJ3WdtFi', '13525522593', 1, '2020-12-07 21:06:58', '2020-12-07 21:07:01');

-- ----------------------------
-- Table structure for rbac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_role`;
CREATE TABLE `rbac_user_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NULL DEFAULT 0,
  `role_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id,role_id`(`user_id`, `role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of rbac_user_role
-- ----------------------------
INSERT INTO `rbac_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
