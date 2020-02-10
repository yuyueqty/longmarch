/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.1.115
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 192.168.1.115:3306
 Source Schema         : longmarch

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 10/02/2020 18:25:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) NOT NULL COMMENT '类目ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '摘要',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `source_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源地址',
  `image_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主图片地址',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章作者',
  `publish_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `publish_status` tinyint(2) NULL DEFAULT 1 COMMENT '发布状态（1 未发布， 2 审核中，3 已发布，默认 1）',
  `auto_publish_status` tinyint(2) NULL DEFAULT 0 COMMENT '是否自动发布（0 否，1 是，默认 0）',
  `clicks` int(11) NULL DEFAULT 0 COMMENT '点击次数（默认0）',
  `likes` int(11) NULL DEFAULT 0 COMMENT '点赞次数（默认0）',
  `sort` int(11) NULL DEFAULT 0 COMMENT '文章排序（由大到小排序，默认0）',
  `recommend` tinyint(2) NULL DEFAULT 0 COMMENT '是否推荐（0 否， 1 是，默认 0）',
  `label` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章标签',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父分类ID',
  `redirect_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转地址',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '类目排序',
  `status` tinyint(2) NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类详情',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unique_category_name_tenant_id_channel_id`(`category_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章类目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(2) NULL DEFAULT 0 COMMENT '状态（0 停用， 1 启用， 默认 0）',
  `count` int(10) NOT NULL COMMENT '失败尝试次数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1, 'longMarchJobTask', 'batchPublishArticles', '', '1 * * * * ?', 0, 3, '定时发布文章', 1, '2020-02-10 18:20:47', NULL, NULL);

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(2) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `execute_time` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '任务开始执行时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '任务执行结束时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21149 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级ID',
  `parent_ids` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父极IDS',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `sort` int(10) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门信息' ROW_FORMAT = Dynamic;

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
  `status` tinyint(2) NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` bigint(20) NULL DEFAULT 1 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_type_index`(`code`, `value`) USING BTREE,
  INDEX `index_type`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 242 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES (1, 'status_dict', '1', '启用', 0, 1, '数据状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (2, 'status_dict', '0', '停用', 0, 1, '数据状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (3, 'style_dict', '0', 'info', 0, 1, '按钮样式', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (4, 'style_dict', '1', 'success', 0, 1, '按钮样式', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (5, 'style_dict', '2', 'primary', 0, 1, '按钮样式', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (6, 'style_dict', '3', 'warning', 0, 1, '按钮样式', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (7, 'style_dict', '4', 'danger', 0, 1, '按钮样式', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (8, 'perm_dict', '1', '菜单', 0, 1, '权限类型', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (9, 'perm_dict', '2', '按钮', 0, 1, '权限类型', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (10, 'job_status_dict', '1', '任务运行中', 0, 1, '任务启动状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (11, 'job_status_dict', '0', '任务暂停中', 0, 1, '任务启动状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (12, 'job_result_status_dict', '1', '执行成功', 0, 1, '任务执行状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (13, 'job_result_status_dict', '0', '执行失败', 0, 1, '任务执行状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (14, 'publish_status_dict', '1', '草稿', 0, 1, '文章发布状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (15, 'publish_status_dict', '2', '待审核', 0, 1, '文章发布状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (16, 'publish_status_dict', '3', '已发布', 0, 1, '文章发布状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (17, 'recommend_dict', '1', '已推荐', 0, 1, '文章推荐状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (18, 'recommend_dict', '0', '未推荐', 0, 1, '文章推荐状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (19, 'auto_publish_status_dict', '1', '是', 0, 1, '文章自动发布状态', 1, '2020-02-10 18:20:47');
INSERT INTO `sys_dictionary` VALUES (20, 'auto_publish_status_dict', '0', '否', 0, 1, '文章自动发布状态', 1, '2020-02-10 18:20:47');

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
) ENGINE = InnoDB AUTO_INCREMENT = 441 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 782 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_parameter` VALUES (1, 'sys_params', '{\"copyright\":\"Copyright © 2020 晴天雨\",\"code\":\"<script type=\\\"text/javascript\\\" src=\\\"https://js.users.51.la/19400986.js\\\"></script>\",\"headImgUrl\":\"http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r\",\"keywords\":\"长征CMS系统\",\"defaultUserRole\":\"1\",\"logo\":\"http://q35smspdq.bkt.clouddn.com/Fmn6EGo4ZelbTAJCuDxUbvS1P3gH\",\"description\":\"长征CMS系统，用户管理后台系统\",\"title\":\"长征CMS系统\",\"url\":\"http://www.longmarch.top\"}', '系统默认参数');
INSERT INTO `sys_parameter` VALUES (2, 'qiniu_upload', '{\"bucket\":\"longmarch123\",\"secretKey\":\"ZKj7MXTZUfOnPeuw4hEd23-MZEvXuRc62t02Vddu\",\"accessKey\":\"JP30SdrdnwHXrCO5v24JoEDmBM2mhIU3MndHVqlR\",\"fileMaxSize\":\"20971520\",\"name\":\"文件上传参数\",\"downloadUrl\":\"http://upload.longmarch.top\",\"expireSeconds\":\"1000000\"}', '七牛上传参数');
INSERT INTO `sys_parameter` VALUES (3, 'default_user_role', '{\"roleId\":\"1\"}', '新用户注册默认归属角色');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父节点ID',
  `parent_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点IDS',
  `permission_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `permission_string` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限字符串',
  `type` tinyint(3) NOT NULL COMMENT '权限类型，菜单 1，按钮 2',
  `status` tinyint(2) UNSIGNED NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '', '系统管理', '系统管理', 'sys:manage', 1, 1, 1, '2020-02-10 18:20:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (2, 1, '1', '用户管理', '用户管理', 'sys:user:manage', 1, 1, 1, '2020-02-10 18:20:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (3, 2, '1,2', '查看用户', '查看用户', 'sys:user:show', 2, 1, 1, '2020-02-10 18:20:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (4, 2, '1,2', '添加用户', '查看用户', 'sys:user:create', 2, 1, 1, '2020-02-10 18:20:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (5, 2, '1,2', '修改用户', '修改用户', 'sys:user:update', 2, 1, 1, '2020-02-10 18:20:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (6, 2, '1,2', '删除用户', '删除用户', 'sys:user:delete', 2, 1, 1, '2020-02-10 18:20:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (7, 2, '1,2', '修改密码', '修改密码', 'sys:user:change:password', 2, 1, 1, '2020-02-10 18:20:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (8, 1, '1', '角色管理', '角色管理', 'sys:role:manage', 1, 1, 1, '2020-02-10 18:20:46', NULL, NULL);
INSERT INTO `sys_permission` VALUES (9, 8, '1,8', '查看角色', '查看角色', 'sys:role:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (10, 8, '1,8', '添加角色', '查看角色', 'sys:role:create', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (11, 8, '1,8', '修改角色', '修改角色', 'sys:role:update', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (12, 8, '1,8', '删除角色', '删除角色', 'sys:role:delete', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (13, 1, '1', '权限管理', '权限管理', 'sys:permission:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (14, 13, '1,13', '查看权限', '查看权限', 'sys:permission:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (15, 13, '1,13', '添加权限', '查看权限', 'sys:permission:create', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (16, 13, '1,13', '修改权限', '修改权限', 'sys:permission:update', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (17, 13, '1,13', '删除权限', '删除权限', 'sys:permission:delete', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (18, 1, '1', '部门管理', '部门管理', 'sys:department:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (19, 18, '1,18', '查看部门', '查看部门', 'sys:department:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (20, 18, '1,18', '添加部门', '查看部门', 'sys:department:create', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (21, 18, '1,18', '修改部门', '修改部门', 'sys:department:update', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (22, 18, '1,18', '删除部门', '删除部门', 'sys:department:delete', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (23, 1, '1', '字典管理', '字典管理', 'sys:dictionary:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (24, 23, '1,23', '查看字典', '查看字典', 'sys:dictionary:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (25, 23, '1,23', '添加字典', '查看字典', 'sys:dictionary:create', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (26, 23, '1,23', '修改字典', '修改字典', 'sys:dictionary:update', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (27, 23, '1,23', '删除字典', '删除字典', 'sys:dictionary:delete', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (28, 1, '1', '任务管理', '任务管理', 'sys:schedule:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (29, 28, '1,28', '查看任务', '查看任务', 'job:schedule:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (30, 28, '1,28', '添加任务', '查看任务', 'sys:schedule:create', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (31, 28, '1,28', '修改任务', '修改任务', 'sys:schedule:update', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (32, 28, '1,28', '删除任务', '删除任务', 'sys:schedule:delete', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (33, 28, '1,28', '执行任务', '执行任务', 'job:schedule:run', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (34, 28, '1,28', '暂停任务', '暂停任务', 'sys:schedule:pause', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (35, 28, '1,28', '恢复任务', '恢复任务', 'sys:schedule:resume', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (36, 28, '1,28', '重置任务', '重置任务', 'sys:schedule:reset', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (37, 28, '1,28', '任务日志', '任务日志', 'sys:schedule:log', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (38, 1, '1', '参数管理', '参数管理', 'sys:parameter:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (39, 38, '1,38', '查看参数', '查看参数', 'sys:parameter:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (40, 38, '1,38', '修改参数', '修改参数', 'sys:parameter:update', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (41, 1, '1', '登录日志', '登录日志', 'sys:loginlog:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (42, 41, '1,41', '查看日志', '查看日志', 'sys:loginlog:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (43, 1, '1', '操作日志', '操作日志', 'sys:operatelog:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (44, 43, '1,43', '查看日志', '查看日志', 'sys:operatelog:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (45, 0, '', '内容管理', '内容管理', 'cms:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (46, 45, '45', '文章管理', '文章管理', 'cms:article:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (47, 46, '45,46', '查看文章', '查看文章', 'cms:article:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (48, 46, '45,46', '添加文章', '查看文章', 'cms:article:create', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (49, 46, '45,46', '修改文章', '修改文章', 'cms:article:update', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (50, 46, '45,46', '删除文章', '删除文章', 'cms:article:delete', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (51, 45, '45', '分类管理', '分类管理', 'cms:category:manage', 1, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (52, 51, '45,51', '查看分类', '查看文章', 'cms:category:show', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (53, 51, '45,51', '添加分类', '添加分类', 'cms:category:create', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (54, 51, '45,51', '修改分类', '修改分类', 'cms:category:update', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES (55, 51, '45,51', '删除分类', '删除分类', 'cms:category:delete', 2, 1, 1, '2020-02-10 18:20:47', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `data_perm` int(11) NULL DEFAULT 3 COMMENT '数据权限（1 用户ID， 2 部门ID， 3 全部）',
  `data_perm_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限ID集合',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员角色', '管理员角色', 1, 3, NULL, 1, '2020-02-10 18:20:46', NULL, NULL);

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
INSERT INTO `sys_role_permission_rel` VALUES (1, 30);
INSERT INTO `sys_role_permission_rel` VALUES (1, 31);
INSERT INTO `sys_role_permission_rel` VALUES (1, 32);
INSERT INTO `sys_role_permission_rel` VALUES (1, 33);
INSERT INTO `sys_role_permission_rel` VALUES (1, 34);
INSERT INTO `sys_role_permission_rel` VALUES (1, 35);
INSERT INTO `sys_role_permission_rel` VALUES (1, 36);
INSERT INTO `sys_role_permission_rel` VALUES (1, 37);
INSERT INTO `sys_role_permission_rel` VALUES (1, 38);
INSERT INTO `sys_role_permission_rel` VALUES (1, 39);
INSERT INTO `sys_role_permission_rel` VALUES (1, 40);
INSERT INTO `sys_role_permission_rel` VALUES (1, 41);
INSERT INTO `sys_role_permission_rel` VALUES (1, 42);
INSERT INTO `sys_role_permission_rel` VALUES (1, 43);
INSERT INTO `sys_role_permission_rel` VALUES (1, 44);
INSERT INTO `sys_role_permission_rel` VALUES (1, 45);
INSERT INTO `sys_role_permission_rel` VALUES (1, 46);
INSERT INTO `sys_role_permission_rel` VALUES (1, 47);
INSERT INTO `sys_role_permission_rel` VALUES (1, 48);
INSERT INTO `sys_role_permission_rel` VALUES (1, 49);
INSERT INTO `sys_role_permission_rel` VALUES (1, 50);
INSERT INTO `sys_role_permission_rel` VALUES (1, 51);
INSERT INTO `sys_role_permission_rel` VALUES (1, 52);
INSERT INTO `sys_role_permission_rel` VALUES (1, 53);
INSERT INTO `sys_role_permission_rel` VALUES (1, 54);
INSERT INTO `sys_role_permission_rel` VALUES (1, 55);

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
  `status` tinyint(2) UNSIGNED NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `login_count` int(11) NULL DEFAULT 0 COMMENT '登录次数',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `dept_pids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门PIDS',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_unique_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'd89267ba6e888426c8f798a04f2fb874', 'http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r', '18888888888', 1, 0, NULL, NULL, NULL, 1, '2020-02-10 18:20:46', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_rel`;
CREATE TABLE `sys_user_role_rel`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  UNIQUE INDEX `index_unique_userid_roleid`(`user_id`, `role_id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE,
  INDEX `index_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_rel
-- ----------------------------
INSERT INTO `sys_user_role_rel` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
