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

 Date: 14/06/2021 21:18:23
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
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rbac_permission
-- ----------------------------
INSERT INTO `rbac_permission` VALUES (1, 0, '权限管理', 'icon-quanxian', '/rbac', 2, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (2, 1, '角色管理', NULL, '/rbac/role/index', 2, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (3, 1, '用户管理', NULL, '/rbac/user/index', 2, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (7, 2, '添加角色', NULL, '/rbac/role/create', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (8, 2, '删除角色', NULL, '/rbac/role/delete', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (9, 2, '编辑角色', NULL, '/rbac/role/update', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (10, 1, '资源管理', NULL, '/rbac/permission/index', 2, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (14, 10, '删除资源', NULL, '/rbac/permission/delete', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (21, 10, '添加资源', NULL, '/rbac/permission/create', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (27, 10, '更新权限', NULL, '/rbac/permission/update', 1, 2, 0, NULL, NULL);
INSERT INTO `rbac_permission` VALUES (28, 2, '权限列表', '', '/rbac/role/permission/index', 1, 2, 2, '2021-04-11 09:12:15', '2021-05-14 11:21:11');
INSERT INTO `rbac_permission` VALUES (30, 2, '权限分配', NULL, '/rbac/role/auth/permission', 1, 1, 0, '2021-05-14 11:20:38', NULL);
INSERT INTO `rbac_permission` VALUES (31, 2, '角色id', NULL, '/rbac/role/permission/ids', 1, 1, 0, '2021-05-14 11:41:21', NULL);
INSERT INTO `rbac_permission` VALUES (32, 3, '添加用户', NULL, '/rbac/user/create', 1, 1, 0, '2021-05-14 15:07:04', NULL);
INSERT INTO `rbac_permission` VALUES (33, 3, '编辑用户', NULL, '/rbac/user/update', 1, 1, 0, '2021-05-14 15:07:25', NULL);
INSERT INTO `rbac_permission` VALUES (34, 3, '删除用户', NULL, '/rbac/user/delete', 1, 1, 0, '2021-05-14 15:07:40', NULL);
INSERT INTO `rbac_permission` VALUES (35, 3, '用户状态', NULL, '/rbac/user/status', 1, 1, 0, '2021-05-14 15:09:17', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 330 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rbac_role_permission
-- ----------------------------
INSERT INTO `rbac_role_permission` VALUES (313, 1, 1);
INSERT INTO `rbac_role_permission` VALUES (314, 1, 2);
INSERT INTO `rbac_role_permission` VALUES (329, 1, 3);
INSERT INTO `rbac_role_permission` VALUES (315, 1, 7);
INSERT INTO `rbac_role_permission` VALUES (316, 1, 8);
INSERT INTO `rbac_role_permission` VALUES (317, 1, 9);
INSERT INTO `rbac_role_permission` VALUES (318, 1, 10);
INSERT INTO `rbac_role_permission` VALUES (319, 1, 14);
INSERT INTO `rbac_role_permission` VALUES (320, 1, 21);
INSERT INTO `rbac_role_permission` VALUES (321, 1, 27);
INSERT INTO `rbac_role_permission` VALUES (322, 1, 28);
INSERT INTO `rbac_role_permission` VALUES (323, 1, 30);
INSERT INTO `rbac_role_permission` VALUES (324, 1, 31);
INSERT INTO `rbac_role_permission` VALUES (325, 1, 32);
INSERT INTO `rbac_role_permission` VALUES (326, 1, 33);
INSERT INTO `rbac_role_permission` VALUES (327, 1, 34);
INSERT INTO `rbac_role_permission` VALUES (328, 1, 35);
INSERT INTO `rbac_role_permission` VALUES (293, 4, 1);
INSERT INTO `rbac_role_permission` VALUES (294, 4, 2);
INSERT INTO `rbac_role_permission` VALUES (295, 4, 28);
INSERT INTO `rbac_role_permission` VALUES (296, 4, 31);

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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_idx`(`username`) USING BTREE,
  UNIQUE INDEX `mobile_idx`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
INSERT INTO `rbac_user` VALUES (1, '', 'admin', '$2a$10$xexcn0GDHSHGfRbch6i1G.tAfQCwgG6yuH4H6ODAf.dcOqJ3WdtFi', '13525522593', 1, '2020-12-07 21:06:58', '2021-05-14 18:09:01');
INSERT INTO `rbac_user` VALUES (4, '', 'test', '$2a$10$26IwJdi27wgV2mBSzmP6luGX7gMmHdjwfofuleX4Ed8nBfJKrYnXC', '18567639989', 1, '2021-06-14 15:46:17', '2021-06-14 15:46:17');

-- ----------------------------
-- Table structure for rbac_user_action_log
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_action_log`;
CREATE TABLE `rbac_user_action_log`  (
  ` id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `operation` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作内容',
  `item_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '相关id',
  `ip` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (` id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rbac_user_role
-- ----------------------------
INSERT INTO `rbac_user_role` VALUES (1, 1, 1);
INSERT INTO `rbac_user_role` VALUES (4, 4, 4);

SET FOREIGN_KEY_CHECKS = 1;
