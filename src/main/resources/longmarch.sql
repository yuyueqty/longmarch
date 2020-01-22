/*
 Navicat MySQL Data Transfer

 Source Server         : longmarch
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 122.51.244.159:3306
 Source Schema         : longmarch

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 22/01/2020 21:02:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据值',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `sort` int(100) NULL DEFAULT NULL COMMENT '字典排序',
  `status` tinyint(2) NULL DEFAULT 1 COMMENT '状态（1 停用， 0 启用， 默认 0）',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` bigint(20) NULL DEFAULT 1 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_type_index`(`code`, `value`) USING BTREE,
  INDEX `index_type`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 233 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES (1, 'status_dict', '0', '启用', 1, 0, '数据状态', 1, '2020-01-13 23:43:02');
INSERT INTO `sys_dictionary` VALUES (2, 'status_dict', '1', '停用', 2, 0, '数据状态', 1, '2020-01-13 23:43:02');
INSERT INTO `sys_dictionary` VALUES (3, 'style_dict', '0', 'success', 1, 0, '信息按钮', 1, '2020-01-13 23:43:02');
INSERT INTO `sys_dictionary` VALUES (4, 'style_dict', '1', 'info', 2, 0, '成功按钮', 1, '2020-01-13 23:43:02');
INSERT INTO `sys_dictionary` VALUES (5, 'style_dict', '2', 'primary', 3, 0, '主要按钮', 1, '2020-01-13 23:43:02');
INSERT INTO `sys_dictionary` VALUES (6, 'style_dict', '3', 'warning', 4, 0, '警告按钮', 1, '2020-01-13 23:43:02');
INSERT INTO `sys_dictionary` VALUES (7, 'style_dict', '4', 'danger', 4, 0, '危险按钮', 1, '2020-01-13 23:43:02');
INSERT INTO `sys_dictionary` VALUES (8, 'perm_dict', '1', '菜单', 1, 0, '权限类型', 1, '2020-01-13 23:43:02');
INSERT INTO `sys_dictionary` VALUES (9, 'perm_dict', '2', '按钮', 2, 0, '权限类型', 1, '2020-01-13 23:43:02');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆人ip',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登陆时间',
  `user_agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆设备信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (1, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-14 14:23:09', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (2, 14, 'test12', '0:0:0:0:0:0:0:1', '2020-01-14 14:30:37', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (3, 14, 'test12', '0:0:0:0:0:0:0:1', '2020-01-14 14:33:17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (4, 14, 'test12', '0:0:0:0:0:0:0:1', '2020-01-14 14:35:14', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (5, 14, 'test12', '0:0:0:0:0:0:0:1', '2020-01-14 14:36:16', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (6, 14, 'test12', '0:0:0:0:0:0:0:1', '2020-01-14 14:37:08', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (7, 14, 'test12', '0:0:0:0:0:0:0:1', '2020-01-14 14:37:21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (8, 14, 'test12', '0:0:0:0:0:0:0:1', '2020-01-14 14:37:30', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (9, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-14 14:44:32', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (10, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-14 15:25:58', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (11, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-14 15:53:18', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (12, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-14 15:55:30', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (13, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-14 15:57:39', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (14, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-14 15:58:34', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (15, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-14 15:58:47', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (16, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-15 00:00:46', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (17, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-15 21:35:22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (18, 1, 'admin', '192.168.56.1', '2020-01-15 22:54:11', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (19, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-15 23:39:17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (20, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-16 00:02:12', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (21, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-16 00:04:11', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (22, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-17 21:12:42', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (23, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 12:03:38', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (24, 1, 'admin', NULL, '2020-01-18 12:53:47', NULL);
INSERT INTO `sys_login_log` VALUES (25, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 13:09:35', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (26, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 13:10:45', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (27, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 16:28:28', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (28, 1, 'admin', NULL, '2020-01-18 16:41:49', NULL);
INSERT INTO `sys_login_log` VALUES (29, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 16:57:28', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (30, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 16:59:51', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (31, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 17:12:03', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (32, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 21:13:19', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (33, 1, 'admin', NULL, '2020-01-18 22:43:32', NULL);
INSERT INTO `sys_login_log` VALUES (34, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 22:44:45', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (35, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 22:49:17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (36, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 22:56:58', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (37, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 22:58:52', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (38, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:02:02', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (39, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:02:09', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (40, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:06:44', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (41, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:09:55', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (42, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:10:15', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (43, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:11:27', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (44, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:12:46', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (45, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:13:47', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (46, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:14:29', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (47, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:15:49', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (48, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:15:58', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (49, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:16:27', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (50, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-18 23:17:27', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (51, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 23:18:58', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (52, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 23:20:03', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (53, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 23:20:15', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (54, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 23:20:39', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (55, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 23:20:54', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (56, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 23:21:03', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (57, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-18 23:21:19', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (58, 1, 'admin', NULL, '2020-01-18 23:22:29', NULL);
INSERT INTO `sys_login_log` VALUES (59, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-19 21:43:48', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (60, 2, 'test', '0:0:0:0:0:0:0:1', '2020-01-20 00:04:41', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (61, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-20 00:06:13', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (62, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-20 21:58:21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (63, 1, 'admin', NULL, '2020-01-20 22:18:39', NULL);
INSERT INTO `sys_login_log` VALUES (64, 1, 'admin', '220.112.127.99', '2020-01-21 01:34:23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (65, 1, 'admin', NULL, '2020-01-21 01:37:46', NULL);
INSERT INTO `sys_login_log` VALUES (66, 16, '331044508', NULL, '2020-01-21 08:47:57', NULL);
INSERT INTO `sys_login_log` VALUES (67, 1, 'admin', NULL, '2020-01-21 10:42:53', NULL);
INSERT INTO `sys_login_log` VALUES (68, 1, 'admin', NULL, '2020-01-21 11:15:17', NULL);
INSERT INTO `sys_login_log` VALUES (69, 16, '331044508', NULL, '2020-01-21 11:31:53', NULL);
INSERT INTO `sys_login_log` VALUES (70, 1, 'admin', NULL, '2020-01-21 12:48:59', NULL);
INSERT INTO `sys_login_log` VALUES (71, 1, 'admin', NULL, '2020-01-22 07:37:57', NULL);
INSERT INTO `sys_login_log` VALUES (72, 1, 'admin', NULL, '2020-01-22 17:30:30', NULL);
INSERT INTO `sys_login_log` VALUES (73, 1, 'admin', NULL, '2020-01-22 17:37:07', NULL);
INSERT INTO `sys_login_log` VALUES (74, 1, 'admin', NULL, '2020-01-22 17:38:15', NULL);
INSERT INTO `sys_login_log` VALUES (75, 1, 'admin', NULL, '2020-01-22 17:51:52', NULL);
INSERT INTO `sys_login_log` VALUES (76, 1, 'admin', '192.168.56.1', '2020-01-22 17:55:31', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (77, 1, 'admin', '192.168.56.1', '2020-01-22 17:58:29', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (78, 1, 'admin', '192.168.56.1', '2020-01-22 18:01:05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (79, 1, 'admin', '192.168.56.1', '2020-01-22 18:03:29', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (80, 1, 'admin', '192.168.56.1', '2020-01-22 18:05:57', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (81, 1, 'admin', NULL, '2020-01-22 19:41:11', NULL);
INSERT INTO `sys_login_log` VALUES (82, 1, 'admin', '113.138.117.111', '2020-01-22 19:43:05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (83, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-22 19:55:49', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (84, 1, 'admin', '0:0:0:0:0:0:0:1', '2020-01-22 20:48:23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (85, 1, 'admin', NULL, '2020-01-22 20:57:05', NULL);

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `bus_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '模块名称',
  `operate_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '操作类型',
  `operate_detail` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '操作详情',
  `operate_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 134 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
INSERT INTO `sys_operate_log` VALUES (1, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"password\":\"d89267ba6e888426c8f798a04f2fb874\",\"createBy\":1,\"createTime\":1578837413000,\"updateBy\":1,\"updateTime\":1578932560000,\"id\":12,\"username\":\"test10\",\"status\":0}]', '2020-01-14 14:23:27');
INSERT INTO `sys_operate_log` VALUES (2, 1, 'admin', '用户信息模块', '创建用户信息', '[{\"password\":\"d89267ba6e888426c8f798a04f2fb874\",\"createBy\":1,\"createTime\":1579011869519,\"id\":14,\"username\":\"test12\",\"status\":0}]', '2020-01-14 14:24:30');
INSERT INTO `sys_operate_log` VALUES (3, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578957508000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578929011000,\"id\":2,\"status\":0}]', '2020-01-14 15:33:26');
INSERT INTO `sys_operate_log` VALUES (4, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:operatelog:manage\",\"createBy\":1,\"createTime\":1579014617000,\"updateBy\":1,\"description\":\"操作日志模块\",\"updateTime\":1579266896870,\"id\":25,\"type\":1,\"parentId\":7,\"permissionName\":\"操作日志\",\"status\":0}]', '2020-01-17 21:14:57');
INSERT INTO `sys_operate_log` VALUES (5, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 13:11:27');
INSERT INTO `sys_operate_log` VALUES (6, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 13:29:51');
INSERT INTO `sys_operate_log` VALUES (7, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 13:30:01');
INSERT INTO `sys_operate_log` VALUES (8, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 13:30:12');
INSERT INTO `sys_operate_log` VALUES (9, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 13:30:52');
INSERT INTO `sys_operate_log` VALUES (10, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 13:40:51');
INSERT INTO `sys_operate_log` VALUES (11, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 13:43:31');
INSERT INTO `sys_operate_log` VALUES (12, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 13:45:13');
INSERT INTO `sys_operate_log` VALUES (13, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[2,3]}]', '2020-01-18 13:54:58');
INSERT INTO `sys_operate_log` VALUES (14, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[2,3,7,1]}]', '2020-01-18 13:55:49');
INSERT INTO `sys_operate_log` VALUES (15, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 13:56:01');
INSERT INTO `sys_operate_log` VALUES (16, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[3,7,1]}]', '2020-01-18 13:56:09');
INSERT INTO `sys_operate_log` VALUES (17, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,9,10,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,7,8]}]', '2020-01-18 13:56:24');
INSERT INTO `sys_operate_log` VALUES (18, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,9,10,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,7,8]}]', '2020-01-18 13:56:50');
INSERT INTO `sys_operate_log` VALUES (19, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 14:04:24');
INSERT INTO `sys_operate_log` VALUES (20, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[2,7,1]}]', '2020-01-18 14:06:29');
INSERT INTO `sys_operate_log` VALUES (21, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 14:06:41');
INSERT INTO `sys_operate_log` VALUES (22, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[3,7,1]}]', '2020-01-18 14:06:58');
INSERT INTO `sys_operate_log` VALUES (23, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[2,3,4,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 14:12:43');
INSERT INTO `sys_operate_log` VALUES (24, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[2,3,4,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 14:12:49');
INSERT INTO `sys_operate_log` VALUES (25, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[2,3,4,6,8,9,10,11,12,14,15,18,19,20,21,22,23,24]}]', '2020-01-18 14:13:17');
INSERT INTO `sys_operate_log` VALUES (26, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[2,3,4,6,8,9,10,11,12,14,15,17,18,19,20,21,22,23,24]}]', '2020-01-18 14:13:22');
INSERT INTO `sys_operate_log` VALUES (27, 1, 'admin', '角色信息模块', '创建角色信息', '[{\"createBy\":1,\"createTime\":1579329171849,\"roleName\":\"测试角色2\",\"description\":\"测试角色2\",\"id\":6,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 14:32:52');
INSERT INTO `sys_operate_log` VALUES (28, 1, 'admin', '角色信息模块', '创建角色信息', '[{\"createBy\":1,\"createTime\":1579329369163,\"roleName\":\"测试用户3\",\"description\":\"测试用户3\",\"id\":7,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 14:36:09');
INSERT INTO `sys_operate_log` VALUES (29, 1, 'admin', '角色信息模块', '删除角色信息', '[[7,6]]', '2020-01-18 14:41:50');
INSERT INTO `sys_operate_log` VALUES (30, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579332392340,\"id\":5,\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 15:26:32');
INSERT INTO `sys_operate_log` VALUES (31, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579333005779,\"id\":5,\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 15:36:46');
INSERT INTO `sys_operate_log` VALUES (32, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579333023918,\"id\":5,\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 15:37:04');
INSERT INTO `sys_operate_log` VALUES (33, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579333125794,\"id\":5,\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 15:38:46');
INSERT INTO `sys_operate_log` VALUES (34, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579333170802,\"id\":5,\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 15:39:31');
INSERT INTO `sys_operate_log` VALUES (35, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335143266,\"id\":5,\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:12:23');
INSERT INTO `sys_operate_log` VALUES (36, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"2\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335242920,\"id\":5,\"roleNames\":\"测试角色,管理员角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:14:03');
INSERT INTO `sys_operate_log` VALUES (37, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"2,1\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335379556,\"id\":5,\"roleNames\":\"测试角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:16:20');
INSERT INTO `sys_operate_log` VALUES (38, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335383627,\"id\":5,\"roleNames\":\"测试角色,管理员角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:16:24');
INSERT INTO `sys_operate_log` VALUES (39, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335387256,\"id\":5,\"roleNames\":\"管理员角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:16:27');
INSERT INTO `sys_operate_log` VALUES (40, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335393967,\"id\":5,\"roleNames\":\"管理员角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:16:34');
INSERT INTO `sys_operate_log` VALUES (41, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335397797,\"id\":5,\"roleNames\":\"管理员角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:16:38');
INSERT INTO `sys_operate_log` VALUES (42, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335423728,\"id\":5,\"roleNames\":\"管理员角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:17:31');
INSERT INTO `sys_operate_log` VALUES (43, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335599846,\"id\":5,\"roleNames\":\"管理员角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:20:07');
INSERT INTO `sys_operate_log` VALUES (44, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"\",\"phone\":\"15001924470\",\"createTime\":1563655331000,\"updateBy\":1,\"updateTime\":1579335618569,\"id\":2,\"roleNames\":\"测试角色\",\"loginCount\":0,\"username\":\"test\",\"status\":1}]', '2020-01-18 16:20:19');
INSERT INTO `sys_operate_log` VALUES (45, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1563655331000,\"updateBy\":1,\"updateTime\":1579335622855,\"id\":2,\"loginCount\":0,\"username\":\"test\",\"status\":1}]', '2020-01-18 16:20:23');
INSERT INTO `sys_operate_log` VALUES (46, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1578808569000,\"updateBy\":1,\"updateTime\":1579335638034,\"id\":6,\"loginCount\":0,\"username\":\"test4\",\"status\":0}]', '2020-01-18 16:20:38');
INSERT INTO `sys_operate_log` VALUES (47, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"2,1\",\"phone\":\"15001924470\",\"createTime\":1578808596000,\"updateBy\":1,\"updateTime\":1579335678940,\"id\":10,\"loginCount\":0,\"username\":\"test8\",\"status\":0}]', '2020-01-18 16:21:19');
INSERT INTO `sys_operate_log` VALUES (48, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"2\",\"phone\":\"15001924470\",\"createTime\":1578808596000,\"updateBy\":1,\"updateTime\":1579335780905,\"id\":10,\"roleNames\":\"管理员角色,测试角色\",\"loginCount\":0,\"username\":\"test8\",\"status\":0}]', '2020-01-18 16:23:01');
INSERT INTO `sys_operate_log` VALUES (49, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"lastLoginTime\":1579324245000,\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1563655331000,\"updateBy\":1,\"updateTime\":1579335861112,\"id\":1,\"roleNames\":\"管理员角色\",\"loginCount\":13,\"username\":\"admin\",\"status\":0}]', '2020-01-18 16:24:21');
INSERT INTO `sys_operate_log` VALUES (50, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579335884665,\"id\":5,\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-18 16:24:45');
INSERT INTO `sys_operate_log` VALUES (51, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1578808575000,\"updateBy\":1,\"updateTime\":1579335920801,\"id\":7,\"loginCount\":0,\"username\":\"test5\",\"status\":0}]', '2020-01-18 16:25:21');
INSERT INTO `sys_operate_log` VALUES (52, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"lastLoginTime\":1579336108000,\"roleIds\":\"1\",\"phone\":\"15001924470\",\"createTime\":1563655331000,\"updateBy\":1,\"updateTime\":1579336594331,\"id\":1,\"roleNames\":\"测试角色,管理员角色\",\"loginCount\":14,\"username\":\"admin\",\"status\":0}]', '2020-01-18 16:36:34');
INSERT INTO `sys_operate_log` VALUES (53, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 16:36:43');
INSERT INTO `sys_operate_log` VALUES (54, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 17:27:40');
INSERT INTO `sys_operate_log` VALUES (55, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]}]', '2020-01-18 17:27:50');
INSERT INTO `sys_operate_log` VALUES (56, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 17:28:39');
INSERT INTO `sys_operate_log` VALUES (57, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 21:13:34');
INSERT INTO `sys_operate_log` VALUES (58, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 21:19:17');
INSERT INTO `sys_operate_log` VALUES (59, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 21:19:31');
INSERT INTO `sys_operate_log` VALUES (60, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 21:19:44');
INSERT INTO `sys_operate_log` VALUES (61, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 21:21:04');
INSERT INTO `sys_operate_log` VALUES (62, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 21:22:38');
INSERT INTO `sys_operate_log` VALUES (63, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 21:28:01');
INSERT INTO `sys_operate_log` VALUES (64, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 21:29:19');
INSERT INTO `sys_operate_log` VALUES (65, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,7]}]', '2020-01-18 21:29:28');
INSERT INTO `sys_operate_log` VALUES (66, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 21:33:24');
INSERT INTO `sys_operate_log` VALUES (67, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[4,7,1]}]', '2020-01-18 21:58:29');
INSERT INTO `sys_operate_log` VALUES (68, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,7]}]', '2020-01-18 22:11:14');
INSERT INTO `sys_operate_log` VALUES (69, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 22:11:34');
INSERT INTO `sys_operate_log` VALUES (70, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[]}]', '2020-01-18 22:11:40');
INSERT INTO `sys_operate_log` VALUES (71, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[25,26,7]}]', '2020-01-18 22:11:46');
INSERT INTO `sys_operate_log` VALUES (72, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]}]', '2020-01-18 22:23:16');
INSERT INTO `sys_operate_log` VALUES (73, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[18,19,20,21,22,25,26,7]}]', '2020-01-18 22:32:14');
INSERT INTO `sys_operate_log` VALUES (74, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,7]}]', '2020-01-18 22:32:21');
INSERT INTO `sys_operate_log` VALUES (75, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,23,24,7]}]', '2020-01-18 22:32:30');
INSERT INTO `sys_operate_log` VALUES (76, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,9,10,11,13,14,15,16,17,23,24,7,8]}]', '2020-01-18 22:32:46');
INSERT INTO `sys_operate_log` VALUES (77, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26]}]', '2020-01-18 22:32:57');
INSERT INTO `sys_operate_log` VALUES (78, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[2,9,14,19,7,1,8,13,18]}]', '2020-01-18 22:35:17');
INSERT INTO `sys_operate_log` VALUES (79, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:operatelog:show\",\"createTime\":1579014646000,\"updateBy\":1,\"description\":\"测试\",\"updateTime\":1579446298697,\"id\":26,\"type\":2,\"permissionName\":\"查看日志\",\"status\":0}]', '2020-01-19 23:04:59');
INSERT INTO `sys_operate_log` VALUES (80, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:operatelog:show\",\"createTime\":1579014646000,\"updateBy\":1,\"description\":\"测试\",\"updateTime\":1579446356817,\"id\":26,\"type\":2,\"permissionName\":\"查看日志\",\"status\":0}]', '2020-01-19 23:05:57');
INSERT INTO `sys_operate_log` VALUES (81, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:operatelog:show\",\"createTime\":1579014646000,\"updateBy\":1,\"description\":\"测试\",\"updateTime\":1579446507833,\"id\":26,\"type\":2,\"parentId\":23,\"permissionName\":\"查看日志\",\"status\":0}]', '2020-01-19 23:08:28');
INSERT INTO `sys_operate_log` VALUES (82, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:operatelog:show\",\"createTime\":1579014646000,\"updateBy\":1,\"description\":\"测试\",\"updateTime\":1579446597680,\"id\":26,\"type\":2,\"parentId\":25,\"permissionName\":\"查看日志\",\"status\":0}]', '2020-01-19 23:09:58');
INSERT INTO `sys_operate_log` VALUES (83, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:operatelog:show\",\"createTime\":1579014646000,\"updateBy\":1,\"description\":\"测试\",\"updateTime\":1579446619737,\"id\":26,\"type\":2,\"parentId\":23,\"permissionName\":\"查看日志\",\"status\":0}]', '2020-01-19 23:10:20');
INSERT INTO `sys_operate_log` VALUES (84, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:operatelog:show\",\"createTime\":1579014646000,\"updateBy\":1,\"description\":\"测试\",\"updateTime\":1579446646311,\"id\":26,\"type\":2,\"parentId\":25,\"permissionName\":\"查看日志\",\"status\":0}]', '2020-01-19 23:10:46');
INSERT INTO `sys_operate_log` VALUES (85, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sys:test:manage\",\"createBy\":1,\"createTime\":1579448316207,\"description\":\"测试权限\",\"id\":27,\"type\":1,\"permissionName\":\"测试权限\",\"status\":0}]', '2020-01-19 23:38:36');
INSERT INTO `sys_operate_log` VALUES (86, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sys:test:create\",\"createBy\":1,\"createTime\":1579448385572,\"description\":\"添加添加\",\"id\":28,\"type\":2,\"permissionName\":\"添加添加\",\"status\":0}]', '2020-01-19 23:39:46');
INSERT INTO `sys_operate_log` VALUES (87, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:test:create\",\"createTime\":1579448386000,\"updateBy\":1,\"description\":\"添加添加\",\"updateTime\":1579448427837,\"id\":28,\"type\":2,\"parentId\":27,\"permissionName\":\"添加添加\",\"status\":0}]', '2020-01-19 23:40:28');
INSERT INTO `sys_operate_log` VALUES (88, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sys:test:update\",\"createBy\":1,\"createTime\":1579448510258,\"description\":\"测试更新\",\"id\":29,\"type\":2,\"permissionName\":\"测试更新\",\"status\":0}]', '2020-01-19 23:41:50');
INSERT INTO `sys_operate_log` VALUES (89, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:test:update\",\"createTime\":1579448510000,\"updateBy\":1,\"description\":\"测试更新\",\"updateTime\":1579448526538,\"id\":29,\"type\":2,\"parentId\":27,\"permissionName\":\"测试更新\",\"status\":0}]', '2020-01-19 23:42:07');
INSERT INTO `sys_operate_log` VALUES (90, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sys:test:delete\",\"createBy\":1,\"createTime\":1579448571368,\"description\":\"测试删除\",\"id\":30,\"type\":2,\"permissionName\":\"测试删除\",\"status\":0}]', '2020-01-19 23:42:51');
INSERT INTO `sys_operate_log` VALUES (91, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sssss\",\"createBy\":1,\"createTime\":1579448699092,\"description\":\"sss\",\"id\":31,\"type\":2,\"permissionName\":\"testssss\",\"status\":0}]', '2020-01-19 23:44:59');
INSERT INTO `sys_operate_log` VALUES (92, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sdf\",\"createBy\":1,\"createTime\":1579448760114,\"description\":\"sdfd\",\"id\":32,\"type\":2,\"parentId\":27,\"permissionName\":\"ddfdfd\",\"status\":0}]', '2020-01-19 23:46:00');
INSERT INTO `sys_operate_log` VALUES (93, 1, 'admin', '权限信息模块', '删除权限信息', '[[32]]', '2020-01-19 23:46:04');
INSERT INTO `sys_operate_log` VALUES (94, 1, 'admin', '权限信息模块', '删除权限信息', '[[30]]', '2020-01-19 23:46:11');
INSERT INTO `sys_operate_log` VALUES (95, 1, 'admin', '权限信息模块', '删除权限信息', '[[31]]', '2020-01-19 23:46:13');
INSERT INTO `sys_operate_log` VALUES (96, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sys:test:delete\",\"createBy\":1,\"createTime\":1579448822620,\"description\":\"测试删除\",\"id\":33,\"type\":2,\"parentId\":27,\"permissionName\":\"测试删除\",\"status\":0}]', '2020-01-19 23:47:03');
INSERT INTO `sys_operate_log` VALUES (97, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:test:delete\",\"createTime\":1579448823000,\"updateBy\":1,\"description\":\"测试删除\",\"updateTime\":1579448840121,\"id\":33,\"type\":2,\"parentId\":7,\"permissionName\":\"测试删除\",\"status\":0}]', '2020-01-19 23:47:20');
INSERT INTO `sys_operate_log` VALUES (98, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:test:delete\",\"createTime\":1579448823000,\"updateBy\":1,\"description\":\"测试删除\",\"updateTime\":1579448851489,\"id\":33,\"type\":2,\"parentId\":1,\"permissionName\":\"测试删除\",\"status\":0}]', '2020-01-19 23:47:31');
INSERT INTO `sys_operate_log` VALUES (99, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:test:delete\",\"createTime\":1579448823000,\"updateBy\":1,\"description\":\"测试删除\",\"updateTime\":1579448867024,\"id\":33,\"type\":2,\"parentId\":27,\"permissionName\":\"测试删除\",\"status\":0}]', '2020-01-19 23:47:47');
INSERT INTO `sys_operate_log` VALUES (100, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sys:parameter:manage\",\"createBy\":1,\"createTime\":1579529989423,\"description\":\"参数管理\",\"id\":34,\"type\":1,\"parentId\":7,\"permissionName\":\"参数管理\",\"status\":0}]', '2020-01-20 22:19:49');
INSERT INTO `sys_operate_log` VALUES (101, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sys:parameter:update\",\"createBy\":1,\"createTime\":1579530030450,\"description\":\"修改参数\",\"id\":35,\"type\":2,\"parentId\":34,\"permissionName\":\"修改参数\",\"status\":0}]', '2020-01-20 22:20:30');
INSERT INTO `sys_operate_log` VALUES (102, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,34,35]}]', '2020-01-20 22:20:44');
INSERT INTO `sys_operate_log` VALUES (103, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,7]}]', '2020-01-20 23:22:18');
INSERT INTO `sys_operate_log` VALUES (104, 1, 'admin', '权限信息模块', '创建权限信息', '[{\"permissionString\":\"sys:parameter:show\",\"createBy\":1,\"createTime\":1579533837883,\"description\":\"查看参数\",\"id\":36,\"type\":2,\"parentId\":34,\"permissionName\":\"查看参数\",\"status\":0}]', '2020-01-20 23:23:58');
INSERT INTO `sys_operate_log` VALUES (105, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,36,7,34]}]', '2020-01-20 23:24:12');
INSERT INTO `sys_operate_log` VALUES (106, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,34,35,36]}]', '2020-01-20 23:24:35');
INSERT INTO `sys_operate_log` VALUES (107, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"headImgUrl\\\":\\\"http://q35smspdq.bkt.clouddn.com/Fvh8o2WLEaviNBu0jLSxGCZseGgH\\\",\\\"title\\\":\\\"长征系统2\\\"}\"}]', '2020-01-20 23:52:34');
INSERT INTO `sys_operate_log` VALUES (108, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"headImgUrl\\\":\\\"http://q35smspdq.bkt.clouddn.com/Fvh8o2WLEaviNBu0jLSxGCZseGgH\\\",\\\"title\\\":\\\"长征系统\\\"}\"}]', '2020-01-20 23:55:39');
INSERT INTO `sys_operate_log` VALUES (109, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"headImgUrl\\\":\\\"http://q35smspdq.bkt.clouddn.com/Fvh8o2WLEaviNBu0jLSxGCZseGgH\\\",\\\"title\\\":\\\"长征系统5\\\"}\"}]', '2020-01-20 23:55:46');
INSERT INTO `sys_operate_log` VALUES (110, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"headImgUrl\\\":\\\"http://q35smspdq.bkt.clouddn.com/FqqwWxuKPK6VQ9PEtXlSVNcRSYuw\\\",\\\"title\\\":\\\"长征系统\\\"}\"}]', '2020-01-20 23:55:56');
INSERT INTO `sys_operate_log` VALUES (111, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"headImgUrl\\\":\\\"http://q35smspdq.bkt.clouddn.com/Fi4WKHaEUI5WfO42Xm-A0ljU-3QV\\\",\\\"title\\\":\\\"长征系统\\\"}\"}]', '2020-01-20 23:56:10');
INSERT INTO `sys_operate_log` VALUES (112, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"headImgUrl\\\":\\\"http://q35smspdq.bkt.clouddn.com/Ft1vYuMdiFnqMdEDUgk_GERFLM2k\\\",\\\"title\\\":\\\"长征系统\\\"}\"}]', '2020-01-20 23:56:47');
INSERT INTO `sys_operate_log` VALUES (113, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"headImgUrl\\\":\\\"http://q35smspdq.bkt.clouddn.com/Ft__P9zJEmuucsaPRtwZXPtsEtQJ\\\",\\\"title\\\":\\\"长征系统\\\"}\"}]', '2020-01-20 23:58:53');
INSERT INTO `sys_operate_log` VALUES (114, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"headImgUrl\\\":\\\"http://q35smspdq.bkt.clouddn.com/FqqwWxuKPK6VQ9PEtXlSVNcRSYuw\\\",\\\"title\\\":\\\"长征系统\\\",\\\"logo\\\":\\\"http://q35smspdq.bkt.clouddn.com/Fi4WKHaEUI5WfO42Xm-A0ljU-3QV\\\"}\"}]', '2020-01-21 00:06:11');
INSERT INTO `sys_operate_log` VALUES (115, 16, '331044508', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1\",\"phone\":\"15001924470\",\"createTime\":1578808583000,\"updateBy\":16,\"updateTime\":1579567508189,\"id\":8,\"loginCount\":0,\"username\":\"test6\",\"status\":0}]', '2020-01-21 08:45:08');
INSERT INTO `sys_operate_log` VALUES (116, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"copyright\\\":\\\"Copyright © 2020 晴天雨\\\",\\\"code\\\":\\\"<script type=\\\\\\\"text/javascript\\\\\\\" src=\\\\\\\"https://js.users.51.la/19400986.js\\\\\\\"><\\/script>\\\",\\\"headImgUrl\\\":\\\"http://upload.longmarch.top/FqqwWxuKPK6VQ9PEtXlSVNcRSYuw\\\",\\\"keywords\\\":\\\"长征CMS系统\\\",\\\"defaultUserRole\\\":\\\"1\\\",\\\"logo\\\":\\\"http://q35smspdq.bkt.clouddn.com/Fmn6EGo4ZelbTAJCuDxUbvS1P3gH\\\",\\\"description\\\":\\\"长征CMS系统，用户管理后台系统\\\",\\\"title\\\":\\\"长征系统\\\",\\\"url\\\":\\\"http://www.longmarch.top\\\"}\"}]', '2020-01-21 10:57:48');
INSERT INTO `sys_operate_log` VALUES (117, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"copyright\\\":\\\"Copyright © 2020 晴天雨\\\",\\\"code\\\":\\\"<script type=\\\\\\\"text/javascript\\\\\\\" src=\\\\\\\"https://js.users.51.la/19400986.js\\\\\\\"><\\/script>\\\",\\\"headImgUrl\\\":\\\"http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r\\\",\\\"keywords\\\":\\\"长征CMS系统\\\",\\\"defaultUserRole\\\":\\\"1\\\",\\\"logo\\\":\\\"http://q35smspdq.bkt.clouddn.com/Fmn6EGo4ZelbTAJCuDxUbvS1P3gH\\\",\\\"description\\\":\\\"长征CMS系统，用户管理后台系统\\\",\\\"title\\\":\\\"长征系统\\\",\\\"url\\\":\\\"http://www.longmarch.top\\\"}\"}]', '2020-01-21 10:58:06');
INSERT INTO `sys_operate_log` VALUES (118, 16, '331044508', '用户信息模块', '更新用户信息', '[{\"lastLoginTime\":1579567677000,\"roleIds\":\"1\",\"headImgUrl\":\"http://q35smspdq.bkt.clouddn.com/FqqwWxuKPK6VQ9PEtXlSVNcRSYuw\",\"phone\":\"15001924470\",\"createTime\":1579567037000,\"updateBy\":16,\"updateTime\":1579575672330,\"id\":16,\"roleNames\":\"管理员角色\",\"loginCount\":1,\"username\":\"331044508\",\"status\":1}]', '2020-01-21 11:01:12');
INSERT INTO `sys_operate_log` VALUES (119, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1578808560000,\"updateBy\":1,\"updateTime\":1579576548826,\"id\":5,\"roleNames\":\"测试角色,管理员角色\",\"loginCount\":0,\"username\":\"test3\",\"status\":0}]', '2020-01-21 11:15:49');
INSERT INTO `sys_operate_log` VALUES (120, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1578808575000,\"updateBy\":1,\"updateTime\":1579576621619,\"id\":7,\"roleNames\":\"测试角色,管理员角色\",\"loginCount\":0,\"username\":\"test5\",\"status\":0}]', '2020-01-21 11:17:02');
INSERT INTO `sys_operate_log` VALUES (121, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"系统默认参数\",\"paramName\":\"sys_params\",\"paramId\":3,\"paramValue\":\"{\\\"copyright\\\":\\\"Copyright © 2020 晴天雨\\\",\\\"code\\\":\\\"<script type=\\\\\\\"text/javascript\\\\\\\" src=\\\\\\\"https://js.users.51.la/19400986.js\\\\\\\"><\\/script>\\\",\\\"headImgUrl\\\":\\\"http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r\\\",\\\"keywords\\\":\\\"长征CMS系统\\\",\\\"defaultUserRole\\\":\\\"1\\\",\\\"logo\\\":\\\"http://q35smspdq.bkt.clouddn.com/Fmn6EGo4ZelbTAJCuDxUbvS1P3gH\\\",\\\"description\\\":\\\"长征CMS系统，用户管理后台系统\\\",\\\"title\\\":\\\"长征CMS系统\\\",\\\"url\\\":\\\"http://www.longmarch.top\\\"}\"}]', '2020-01-21 11:17:38');
INSERT INTO `sys_operate_log` VALUES (122, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[7,1,2,3,8,9,13,14,18,19,23,24,25,26,27,28,29,33]}]', '2020-01-21 11:18:28');
INSERT INTO `sys_operate_log` VALUES (123, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,34,35,36,27,28,29,33]}]', '2020-01-21 11:18:36');
INSERT INTO `sys_operate_log` VALUES (124, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[7,1,2,3,8,9,13,14,18,19,23,24,25,26]}]', '2020-01-21 11:19:23');
INSERT INTO `sys_operate_log` VALUES (125, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928708000,\"updateBy\":1,\"roleName\":\"测试角色\",\"description\":\"测试专用角色\",\"updateTime\":1578900211000,\"id\":2,\"status\":0,\"checkedKeys\":[7,1,2,3,8,9,13,14,18,19,23,24,25,26,27,28,29,33]}]', '2020-01-21 11:19:27');
INSERT INTO `sys_operate_log` VALUES (126, 16, '331044508', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[2,6,9,14,19,23,24,25,26,36,27,28,29,33,7,1,8,13,18,34]}]', '2020-01-21 11:35:45');
INSERT INTO `sys_operate_log` VALUES (127, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"createBy\":1,\"createTime\":1578928705000,\"updateBy\":1,\"roleName\":\"管理员角色\",\"description\":\"上帝之子\",\"updateTime\":1578901920000,\"id\":1,\"status\":0,\"checkedKeys\":[7,1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,34,35,36,27,28,29,33]}]', '2020-01-22 17:31:01');
INSERT INTO `sys_operate_log` VALUES (128, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1\",\"phone\":\"15001924470\",\"createTime\":1578808583000,\"updateBy\":1,\"updateTime\":1579685569879,\"id\":8,\"roleNames\":\"管理员角色\",\"loginCount\":0,\"username\":\"test6\",\"status\":0}]', '2020-01-22 17:32:50');
INSERT INTO `sys_operate_log` VALUES (129, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"phone\":\"15001924470\",\"createTime\":1578808575000,\"updateBy\":1,\"updateTime\":1579685576088,\"id\":7,\"roleNames\":\"测试角色,管理员角色\",\"loginCount\":0,\"username\":\"test5\",\"status\":0}]', '2020-01-22 17:32:56');
INSERT INTO `sys_operate_log` VALUES (130, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"headImgUrl\":\"http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r\",\"phone\":\"15001924470\",\"createTime\":1578808575000,\"updateBy\":1,\"updateTime\":1579685616833,\"id\":7,\"roleNames\":\"测试角色,管理员角色\",\"loginCount\":0,\"username\":\"test5\",\"status\":0}]', '2020-01-22 17:33:37');
INSERT INTO `sys_operate_log` VALUES (131, 1, 'admin', '用户信息模块', '更新用户信息', '[{\"roleIds\":\"1,2\",\"headImgUrl\":\"http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r\",\"phone\":\"15001924470\",\"createTime\":1578808569000,\"updateBy\":1,\"updateTime\":1579685680440,\"id\":6,\"roleNames\":\"测试角色,管理员角色\",\"loginCount\":0,\"username\":\"test4\",\"status\":0}]', '2020-01-22 17:34:41');
INSERT INTO `sys_operate_log` VALUES (132, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:test:delete\",\"createTime\":1579448823000,\"updateBy\":1,\"description\":\"测试删除2\",\"updateTime\":1579693455945,\"id\":33,\"type\":2,\"parentId\":27,\"permissionName\":\"测试删除\",\"status\":0}]', '2020-01-22 19:44:16');
INSERT INTO `sys_operate_log` VALUES (133, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:test:delete\",\"createTime\":1579448823000,\"updateBy\":1,\"description\":\"测试删除\",\"updateTime\":1579693487858,\"id\":33,\"type\":2,\"parentId\":27,\"permissionName\":\"测试删除\",\"status\":0}]', '2020-01-22 19:44:48');

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter`  (
  `param_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数名称',
  `param_value` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '参数值',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数描述',
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '平台参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_parameter
-- ----------------------------
INSERT INTO `sys_parameter` VALUES (1, 'default_user_role', '{\"roleId\": 1, \"roleName\": \"超级管理员\"}', '新用户注册默认归属角色');
INSERT INTO `sys_parameter` VALUES (2, 'qiniu_upload', '{\"bucket\":\"longmarch123\",\"secretKey\":\"ZKj7MXTZUfOnPeuw4hEd23-MZEvXuRc62t02Vddu\",\"accessKey\":\"JP30SdrdnwHXrCO5v24JoEDmBM2mhIU3MndHVqlR\",\"fileMaxSize\":\"20971520\",\"name\":\"文件上传参数\",\"downloadUrl\":\"http://upload.longmarch.top\",\"expireSeconds\":\"1000000\"}', '七牛上传参数');
INSERT INTO `sys_parameter` VALUES (3, 'sys_params', '{\"copyright\":\"Copyright © 2020 晴天雨\",\"code\":\"<script type=\\\"text/javascript\\\" src=\\\"https://js.users.51.la/19400986.js\\\"></script>\",\"headImgUrl\":\"http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r\",\"keywords\":\"长征CMS系统\",\"defaultUserRole\":\"1\",\"logo\":\"http://q35smspdq.bkt.clouddn.com/Fmn6EGo4ZelbTAJCuDxUbvS1P3gH\",\"description\":\"长征CMS系统，用户管理后台系统\",\"title\":\"长征CMS系统\",\"url\":\"http://www.longmarch.top\"}', '系统默认参数');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父节点ID',
  `permission_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `permission_string` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限字符串',
  `type` tinyint(3) NOT NULL COMMENT '权限类型，菜单 1，按钮 2',
  `status` tinyint(2) UNSIGNED NULL DEFAULT 0 COMMENT '状态（1 停用， 0 启用， 默认 0）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 7, '用户管理', NULL, 'sys:user:manage', 1, 0, 1, '2020-01-12 17:26:43', NULL, NULL);
INSERT INTO `sys_permission` VALUES (2, 1, '查看用户', NULL, 'sys:user:show', 2, 0, 1, '2020-01-12 17:26:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (3, 1, '添加用户', NULL, 'sys:user:create', 2, 0, 1, '2020-01-12 17:26:49', NULL, NULL);
INSERT INTO `sys_permission` VALUES (4, 1, '修改用户', NULL, 'sys:user:update', 2, 0, 1, '2020-01-12 17:26:51', NULL, NULL);
INSERT INTO `sys_permission` VALUES (5, 1, '删除用户', NULL, 'sys:user:delete', 2, 0, 1, '2020-01-12 17:26:54', NULL, NULL);
INSERT INTO `sys_permission` VALUES (6, 1, '设置权限', NULL, 'sys:permission:setting', 2, 0, 1, '2020-01-12 17:26:56', NULL, NULL);
INSERT INTO `sys_permission` VALUES (7, 0, '系统管理', NULL, 'sys:manage', 1, 0, 1, '2020-01-12 17:26:58', NULL, NULL);
INSERT INTO `sys_permission` VALUES (8, 7, '角色管理', NULL, 'sys:role:manage', 1, 0, 1, '2020-01-13 23:19:57', NULL, NULL);
INSERT INTO `sys_permission` VALUES (9, 8, '查看角色', NULL, 'sys:role:show', 2, 0, 1, '2020-01-13 23:20:29', NULL, NULL);
INSERT INTO `sys_permission` VALUES (10, 8, '添加角色', NULL, 'sys:role:create', 2, 0, 1, '2020-01-13 23:20:54', NULL, NULL);
INSERT INTO `sys_permission` VALUES (11, 8, '修改角色', NULL, 'sys:role:update', 2, 0, 1, '2020-01-13 23:21:20', NULL, NULL);
INSERT INTO `sys_permission` VALUES (12, 8, '删除角色', NULL, 'sys:role:delete', 2, 0, 1, '2020-01-13 23:21:44', NULL, NULL);
INSERT INTO `sys_permission` VALUES (13, 7, '权限管理', NULL, 'sys:permission:manage', 1, 0, 1, '2020-01-13 23:53:14', NULL, NULL);
INSERT INTO `sys_permission` VALUES (14, 13, '查看权限', NULL, 'sys:permission:show', 2, 0, 1, '2020-01-13 23:53:39', NULL, NULL);
INSERT INTO `sys_permission` VALUES (15, 13, '添加权限', NULL, 'sys:permission:create', 2, 0, 1, '2020-01-13 23:54:06', NULL, NULL);
INSERT INTO `sys_permission` VALUES (16, 13, '修改权限', NULL, 'sys:permission:update', 2, 0, 1, '2020-01-13 23:54:32', NULL, NULL);
INSERT INTO `sys_permission` VALUES (17, 13, '删除权限', NULL, 'sys:permission:delete', 2, 0, 1, '2020-01-13 23:55:00', NULL, NULL);
INSERT INTO `sys_permission` VALUES (18, 7, '字典管理', NULL, 'sys:dictionary:manage', 1, 0, 1, '2020-01-13 23:53:14', NULL, NULL);
INSERT INTO `sys_permission` VALUES (19, 18, '查看字典', NULL, 'sys:dictionary:show', 2, 0, 1, '2020-01-14 00:06:29', NULL, NULL);
INSERT INTO `sys_permission` VALUES (20, 18, '添加字典', NULL, 'sys:dictionary:create', 2, 0, 1, '2020-01-14 00:06:52', NULL, NULL);
INSERT INTO `sys_permission` VALUES (21, 18, '修改字典', NULL, 'sys:dictionary:update', 2, 0, 1, '2020-01-14 00:07:14', NULL, NULL);
INSERT INTO `sys_permission` VALUES (22, 18, '删除字典', NULL, 'sys:dictionary:delete', 2, 0, 1, '2020-01-14 00:07:38', NULL, NULL);
INSERT INTO `sys_permission` VALUES (23, 7, '登录日志', NULL, 'sys:loginlog:manage', 1, 0, 1, '2020-01-14 23:08:57', NULL, NULL);
INSERT INTO `sys_permission` VALUES (24, 23, '查看日志', NULL, 'sys:loginlog:show', 2, 0, 1, '2020-01-14 23:09:35', NULL, NULL);
INSERT INTO `sys_permission` VALUES (25, 7, '操作日志', '操作日志模块', 'sys:operatelog:manage', 1, 0, 1, '2020-01-14 23:10:17', 1, '2020-01-17 21:14:57');
INSERT INTO `sys_permission` VALUES (26, 25, '查看日志', '测试', 'sys:operatelog:show', 2, 0, 1, '2020-01-14 23:10:46', 1, '2020-01-19 23:10:46');
INSERT INTO `sys_permission` VALUES (27, 0, '测试权限', '测试权限', 'sys:test:manage', 1, 0, 1, '2020-01-19 23:38:36', NULL, NULL);
INSERT INTO `sys_permission` VALUES (28, 27, '添加添加', '添加添加', 'sys:test:create', 2, 0, 1, '2020-01-19 23:39:46', 1, '2020-01-19 23:40:28');
INSERT INTO `sys_permission` VALUES (29, 27, '测试更新', '测试更新', 'sys:test:update', 2, 0, 1, '2020-01-19 23:41:50', 1, '2020-01-19 23:42:07');
INSERT INTO `sys_permission` VALUES (33, 27, '测试删除', '测试删除', 'sys:test:delete', 2, 0, 1, '2020-01-19 23:47:03', 1, '2020-01-22 19:44:48');
INSERT INTO `sys_permission` VALUES (34, 7, '参数管理', '参数管理', 'sys:parameter:manage', 1, 0, 1, '2020-01-20 22:19:49', NULL, NULL);
INSERT INTO `sys_permission` VALUES (35, 34, '修改参数', '修改参数', 'sys:parameter:update', 2, 0, 1, '2020-01-20 22:20:30', NULL, NULL);
INSERT INTO `sys_permission` VALUES (36, 34, '查看参数', '查看参数', 'sys:parameter:show', 2, 0, 1, '2020-01-20 23:23:58', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态（1 停用， 0 启用， 默认 0）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员角色', '上帝之子', 0, 1, '2020-01-13 23:18:25', 1, '2020-01-13 15:52:00');
INSERT INTO `sys_role` VALUES (2, '测试角色', '测试专用角色', 0, 1, '2020-01-13 23:18:28', 1, '2020-01-13 15:23:31');

-- ----------------------------
-- Table structure for sys_role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_rel`;
CREATE TABLE `sys_role_permission_rel`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  UNIQUE INDEX `index_unique_roleid_permissionid`(`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission_rel
-- ----------------------------
INSERT INTO `sys_role_permission_rel` VALUES (1, 1);
INSERT INTO `sys_role_permission_rel` VALUES (1, 2);
INSERT INTO `sys_role_permission_rel` VALUES (1, 3);
INSERT INTO `sys_role_permission_rel` VALUES (1, 4);
INSERT INTO `sys_role_permission_rel` VALUES (1, 5);
INSERT INTO `sys_role_permission_rel` VALUES (1, 6);
INSERT INTO `sys_role_permission_rel` VALUES (1, 7);
INSERT INTO `sys_role_permission_rel` VALUES (1, 8);
INSERT INTO `sys_role_permission_rel` VALUES (1, 9);
INSERT INTO `sys_role_permission_rel` VALUES (1, 10);
INSERT INTO `sys_role_permission_rel` VALUES (1, 11);
INSERT INTO `sys_role_permission_rel` VALUES (1, 12);
INSERT INTO `sys_role_permission_rel` VALUES (1, 13);
INSERT INTO `sys_role_permission_rel` VALUES (1, 14);
INSERT INTO `sys_role_permission_rel` VALUES (1, 15);
INSERT INTO `sys_role_permission_rel` VALUES (1, 16);
INSERT INTO `sys_role_permission_rel` VALUES (1, 17);
INSERT INTO `sys_role_permission_rel` VALUES (1, 18);
INSERT INTO `sys_role_permission_rel` VALUES (1, 19);
INSERT INTO `sys_role_permission_rel` VALUES (1, 20);
INSERT INTO `sys_role_permission_rel` VALUES (1, 21);
INSERT INTO `sys_role_permission_rel` VALUES (1, 22);
INSERT INTO `sys_role_permission_rel` VALUES (1, 23);
INSERT INTO `sys_role_permission_rel` VALUES (1, 24);
INSERT INTO `sys_role_permission_rel` VALUES (1, 25);
INSERT INTO `sys_role_permission_rel` VALUES (1, 26);
INSERT INTO `sys_role_permission_rel` VALUES (1, 27);
INSERT INTO `sys_role_permission_rel` VALUES (1, 28);
INSERT INTO `sys_role_permission_rel` VALUES (1, 29);
INSERT INTO `sys_role_permission_rel` VALUES (1, 33);
INSERT INTO `sys_role_permission_rel` VALUES (1, 34);
INSERT INTO `sys_role_permission_rel` VALUES (1, 35);
INSERT INTO `sys_role_permission_rel` VALUES (1, 36);
INSERT INTO `sys_role_permission_rel` VALUES (2, 1);
INSERT INTO `sys_role_permission_rel` VALUES (2, 2);
INSERT INTO `sys_role_permission_rel` VALUES (2, 3);
INSERT INTO `sys_role_permission_rel` VALUES (2, 7);
INSERT INTO `sys_role_permission_rel` VALUES (2, 8);
INSERT INTO `sys_role_permission_rel` VALUES (2, 9);
INSERT INTO `sys_role_permission_rel` VALUES (2, 13);
INSERT INTO `sys_role_permission_rel` VALUES (2, 14);
INSERT INTO `sys_role_permission_rel` VALUES (2, 18);
INSERT INTO `sys_role_permission_rel` VALUES (2, 19);
INSERT INTO `sys_role_permission_rel` VALUES (2, 23);
INSERT INTO `sys_role_permission_rel` VALUES (2, 24);
INSERT INTO `sys_role_permission_rel` VALUES (2, 25);
INSERT INTO `sys_role_permission_rel` VALUES (2, 26);
INSERT INTO `sys_role_permission_rel` VALUES (2, 27);
INSERT INTO `sys_role_permission_rel` VALUES (2, 28);
INSERT INTO `sys_role_permission_rel` VALUES (2, 29);
INSERT INTO `sys_role_permission_rel` VALUES (2, 33);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `head_img_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` tinyint(2) NULL DEFAULT 0 COMMENT '状态（1 停用， 0 启用， 默认 0）',
  `login_count` int(11) NULL DEFAULT 0 COMMENT '登录次数',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_unique_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'd89267ba6e888426c8f798a04f2fb874', 'http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r', '15001924470', 0, 60, '2020-01-22 20:57:05', 1, '2019-07-21 04:42:11', 1, '2020-01-22 20:57:05');
INSERT INTO `sys_user` VALUES (2, 'test', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 10, '2020-01-20 00:04:41', 1, '2019-07-21 04:42:11', 2, '2020-01-20 00:04:41');
INSERT INTO `sys_user` VALUES (5, 'test3', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:00', 1, '2020-01-21 11:15:49');
INSERT INTO `sys_user` VALUES (6, 'test4', 'd89267ba6e888426c8f798a04f2fb874', 'http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:09', 1, '2020-01-22 17:34:40');
INSERT INTO `sys_user` VALUES (7, 'test5', 'd89267ba6e888426c8f798a04f2fb874', 'http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:15', 1, '2020-01-22 17:33:37');
INSERT INTO `sys_user` VALUES (8, 'test6', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:23', 1, '2020-01-22 17:32:50');
INSERT INTO `sys_user` VALUES (9, 'test7', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:32', NULL, NULL);
INSERT INTO `sys_user` VALUES (10, 'test8', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:36', 1, '2020-01-18 16:23:01');
INSERT INTO `sys_user` VALUES (11, 'test9', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:46', NULL, NULL);
INSERT INTO `sys_user` VALUES (12, 'test10', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:53', 1, '2020-01-13 16:22:40');
INSERT INTO `sys_user` VALUES (13, 'test11', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 0, NULL, 1, '2020-01-12 13:57:03', NULL, NULL);
INSERT INTO `sys_user` VALUES (14, 'test12', 'd89267ba6e888426c8f798a04f2fb874', NULL, '15001924470', 0, 0, NULL, 1, '2020-01-14 14:24:30', NULL, NULL);
INSERT INTO `sys_user` VALUES (15, 'yuyue', '4280d89a5a03f812751f504cc10ee8a5', 'http://q35smspdq.bkt.clouddn.com/Fvh8o2WLEaviNBu0jLSxGCZseGgH', '15001924470', 0, 0, NULL, 0, '2020-01-20 22:06:09', NULL, NULL);
INSERT INTO `sys_user` VALUES (16, '331044508', '4280d89a5a03f812751f504cc10ee8a5', 'http://q35smspdq.bkt.clouddn.com/FqqwWxuKPK6VQ9PEtXlSVNcRSYuw', '15001924470', 0, 2, '2020-01-21 11:31:53', 0, '2020-01-21 08:37:17', 16, '2020-01-21 11:31:53');
INSERT INTO `sys_user` VALUES (17, 'yuyue5', 'd89267ba6e888426c8f798a04f2fb874', 'http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r', '15001924470', 0, 0, NULL, 0, '2020-01-21 11:37:50', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_rel`;
CREATE TABLE `sys_user_role_rel`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  UNIQUE INDEX `index_unique_userid_roleid`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_rel
-- ----------------------------
INSERT INTO `sys_user_role_rel` VALUES (1, 1);
INSERT INTO `sys_user_role_rel` VALUES (2, 2);
INSERT INTO `sys_user_role_rel` VALUES (5, 1);
INSERT INTO `sys_user_role_rel` VALUES (5, 2);
INSERT INTO `sys_user_role_rel` VALUES (6, 1);
INSERT INTO `sys_user_role_rel` VALUES (6, 2);
INSERT INTO `sys_user_role_rel` VALUES (7, 1);
INSERT INTO `sys_user_role_rel` VALUES (7, 2);
INSERT INTO `sys_user_role_rel` VALUES (8, 1);
INSERT INTO `sys_user_role_rel` VALUES (10, 2);
INSERT INTO `sys_user_role_rel` VALUES (14, 1);
INSERT INTO `sys_user_role_rel` VALUES (15, 1);
INSERT INTO `sys_user_role_rel` VALUES (16, 1);
INSERT INTO `sys_user_role_rel` VALUES (17, 1);

SET FOREIGN_KEY_CHECKS = 1;
