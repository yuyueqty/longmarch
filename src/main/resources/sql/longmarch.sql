/*
 Navicat Premium Data Transfer

 Source Server         : 42.192.14.85(longmarch)
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 42.192.14.85:6356
 Source Schema         : longmarch

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 10/01/2021 21:44:32
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
  `FIRED_TIME` bigint(20) NOT NULL,
  `SCHED_TIME` bigint(20) NOT NULL,
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
  `LAST_CHECKIN_TIME` bigint(20) NOT NULL,
  `CHECKIN_INTERVAL` bigint(20) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('RenrenScheduler', 'c184f539f91e1610282435406', 1610286265445, 15000);

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(20) NOT NULL,
  `REPEAT_INTERVAL` bigint(20) NOT NULL,
  `TIMES_TRIGGERED` bigint(20) NOT NULL,
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
  `NEXT_FIRE_TIME` bigint(20) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(20) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(20) NOT NULL,
  `END_TIME` bigint(20) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(6) NULL DEFAULT NULL,
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
  `publish_status` tinyint(4) NULL DEFAULT 1 COMMENT '发布状态（1 未发布， 2 审核中，3 已发布，默认 1）',
  `auto_publish_status` tinyint(4) NULL DEFAULT 0 COMMENT '是否自动发布（0 否，1 是，默认 0）',
  `clicks` int(11) NULL DEFAULT 0 COMMENT '点击次数（默认0）',
  `likes` int(11) NULL DEFAULT 0 COMMENT '点赞次数（默认0）',
  `sort` int(11) NULL DEFAULT 0 COMMENT '文章排序（由大到小排序，默认0）',
  `recommend` tinyint(4) NULL DEFAULT 0 COMMENT '是否推荐（0 否， 1 是，默认 0）',
  `tags` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章标签',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_article_tag_rel
-- ----------------------------
DROP TABLE IF EXISTS `cms_article_tag_rel`;
CREATE TABLE `cms_article_tag_rel`  (
  `tag_id` bigint(20) NOT NULL COMMENT 'tag_id',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章关联标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父分类ID',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `redirect_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转地址',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '类目排序',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类详情',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unique_category_name_tenant_id_channel_id`(`category_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章类目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_tag
-- ----------------------------
DROP TABLE IF EXISTS `cms_tag`;
CREATE TABLE `cms_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `num` int(11) NULL DEFAULT NULL COMMENT '标签文章数量',
  `hot` int(11) NULL DEFAULT NULL COMMENT '标签热度',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章标签' ROW_FORMAT = Dynamic;

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
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态（0 停用， 1 启用， 默认 0）',
  `count` int(11) NOT NULL COMMENT '失败尝试次数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1, 'longMarchJobTask', 'batchPublishArticles', '', '1 * * * * ?', 0, 3, '定时发布文章', 1, '2020-02-10 18:20:47', 1, '2021-01-10 21:43:06');

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
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `execute_time` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '任务开始执行时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '任务执行结束时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级ID',
  `parent_ids` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父极IDS',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据值',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `sort` int(11) NULL DEFAULT NULL COMMENT '字典排序',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` bigint(20) NULL DEFAULT 1 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_type_index`(`code`, `value`) USING BTREE,
  INDEX `index_type`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典信息' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_dictionary` VALUES (21, 'hidden_dict', '1', '隐藏', 1, 1, '菜单隐藏', 1, '2021-01-09 18:13:42');
INSERT INTO `sys_dictionary` VALUES (22, 'hidden_dict', '0', '不隐藏', 2, 1, '菜单隐藏', 1, '2021-01-09 18:14:03');
INSERT INTO `sys_dictionary` VALUES (23, 'cache_dict', '1', '缓存', 1, 1, '菜单缓存', 1, '2021-01-09 18:15:03');
INSERT INTO `sys_dictionary` VALUES (24, 'cache_dict', '0', '不缓存', 2, 1, '菜单缓存', 1, '2021-01-09 18:15:19');
INSERT INTO `sys_dictionary` VALUES (25, 'perm_dict', '3', '页面', 3, 1, '权限类型', 1, '2021-01-09 18:16:56');
INSERT INTO `sys_dictionary` VALUES (26, 'fw_field_dict', '1', '交友', 1, 1, '分维行业', 1, '2021-01-10 21:15:47');
INSERT INTO `sys_dictionary` VALUES (27, 'account_type_dict', '1', '订阅号', 1, 1, '账号类型', 1, '2021-01-10 21:16:10');
INSERT INTO `sys_dictionary` VALUES (28, 'account_type_dict', '2', '服务号', 2, 1, '账号类型', 1, '2021-01-10 21:16:33');
INSERT INTO `sys_dictionary` VALUES (29, 'auth_status_dict', '1', '已认证', 1, 1, '认证状态', 1, '2021-01-10 21:17:21');
INSERT INTO `sys_dictionary` VALUES (30, 'auth_status_dict', '0', '未认证', 2, 1, '认证状态', 1, '2021-01-10 21:17:38');
INSERT INTO `sys_dictionary` VALUES (31, 'default_status_dict', '1', '默认', 1, 1, '是否默认', 1, '2021-01-10 21:33:21');
INSERT INTO `sys_dictionary` VALUES (32, 'default_status_dict', '0', '非默认', 2, 1, '是否默认', 1, '2021-01-10 21:33:46');

-- ----------------------------
-- Table structure for sys_generator
-- ----------------------------
DROP TABLE IF EXISTS `sys_generator`;
CREATE TABLE `sys_generator`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `column_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `column_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段类型',
  `property_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `not_null` tinyblob NULL COMMENT '不为空',
  `list_show` tinyblob NULL COMMENT '列表显示',
  `form_show` tinyblob NULL COMMENT '表单显示',
  `form_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单类型（input, textarea, radio, checkbox, date）',
  `query_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询类型（eq, date）',
  `order_by` tinyblob NULL COMMENT '是否排序',
  `parameter` tinyblob NULL COMMENT '是否参数',
  `default_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认值',
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典code',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '生成工具实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_image
-- ----------------------------
DROP TABLE IF EXISTS `sys_image`;
CREATE TABLE `sys_image`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '图片大小',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '图片管理' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 483 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (441, 1, 'admin', '127.0.0.1', '2021-01-08 17:54:17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (442, 1, 'admin', '127.0.0.1', '2021-01-08 17:54:53', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (443, 1, 'admin', '127.0.0.1', '2021-01-08 17:55:05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (444, 1, 'admin', '127.0.0.1', '2021-01-08 17:55:51', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (445, 1, 'admin', '127.0.0.1', '2021-01-08 18:02:49', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (446, 1, 'admin', '127.0.0.1', '2021-01-08 18:02:54', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (447, 1, 'admin', '127.0.0.1', '2021-01-08 18:03:21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (448, 1, 'admin', '127.0.0.1', '2021-01-08 18:03:50', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (449, 1, 'admin', '127.0.0.1', '2021-01-08 18:14:30', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (450, 1, 'admin', '127.0.0.1', '2021-01-08 18:35:06', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (451, 1, 'admin', '127.0.0.1', '2021-01-08 18:35:17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (452, 1, 'admin', '127.0.0.1', '2021-01-08 19:01:10', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (453, 1, 'admin', '127.0.0.1', '2021-01-08 22:33:52', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (454, 1, 'admin', '127.0.0.1', '2021-01-09 12:00:32', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (455, 1, 'admin', '127.0.0.1', '2021-01-09 12:00:42', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (456, 1, 'admin', '127.0.0.1', '2021-01-09 12:03:38', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (457, 1, 'admin', '127.0.0.1', '2021-01-09 12:06:52', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (458, 1, 'admin', '127.0.0.1', '2021-01-09 12:07:35', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (459, 1, 'admin', '127.0.0.1', '2021-01-09 12:35:28', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (460, 1, 'admin', '127.0.0.1', '2021-01-09 12:35:30', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (461, 1, 'admin', '127.0.0.1', '2021-01-09 12:35:44', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (462, 1, 'admin', '127.0.0.1', '2021-01-09 12:35:47', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (463, 1, 'admin', '127.0.0.1', '2021-01-09 12:38:00', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (464, 1, 'admin', '127.0.0.1', '2021-01-09 12:38:01', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (465, 1, 'admin', '127.0.0.1', '2021-01-09 12:38:02', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (466, 1, 'admin', '127.0.0.1', '2021-01-09 13:14:05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (467, 1, 'admin', '127.0.0.1', '2021-01-09 15:21:16', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (468, 1, 'admin', '127.0.0.1', '2021-01-09 15:21:32', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (469, 1, 'admin', '127.0.0.1', '2021-01-09 15:22:01', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (470, 1, 'admin', '127.0.0.1', '2021-01-09 15:24:10', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (471, 1, 'admin', '127.0.0.1', '2021-01-09 15:28:32', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (472, 1, 'admin', '127.0.0.1', '2021-01-09 15:28:38', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (473, 1, 'admin', '127.0.0.1', '2021-01-09 15:28:58', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (474, 1, 'admin', '127.0.0.1', '2021-01-09 15:30:35', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (475, 1, 'admin', '127.0.0.1', '2021-01-09 15:32:53', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (476, 1, 'admin', '127.0.0.1', '2021-01-09 15:34:12', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (477, 1, 'admin', '127.0.0.1', '2021-01-09 15:34:23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (478, 1, 'admin', '127.0.0.1', '2021-01-09 15:48:59', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (479, 1, 'admin', '127.0.0.1', '2021-01-09 16:01:14', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (480, 1, 'admin', '172.17.0.1', '2021-01-10 20:36:35', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (481, 1, 'admin', '172.17.0.1', '2021-01-10 20:41:58', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (482, 1, 'admin', '172.17.0.1', '2021-01-10 21:37:22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36');

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
) ENGINE = InnoDB AUTO_INCREMENT = 843 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
INSERT INTO `sys_operate_log` VALUES (782, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:parameter:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"1\",\"icon\":\"parameter\",\"description\":\"参数管理\",\"sort\":1,\"type\":1,\"title\":\"parameterManage\",\"parentId\":1,\"path\":\"parameter\",\"component\":\"parameter/index\",\"name\":\"ParameterManage\",\"id\":38,\"permissionName\":\"参数管理\",\"status\":1}]', '2021-01-08 19:30:19');
INSERT INTO `sys_operate_log` VALUES (783, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:parameter:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"1\",\"icon\":\"parameter\",\"description\":\"参数管理\",\"sort\":100,\"type\":1,\"title\":\"parameterManage\",\"parentId\":1,\"path\":\"parameter\",\"component\":\"parameter/index\",\"name\":\"ParameterManage\",\"id\":38,\"permissionName\":\"参数管理\",\"status\":1}]', '2021-01-08 19:32:31');
INSERT INTO `sys_operate_log` VALUES (784, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:loginlog:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"1\",\"icon\":\"login-log\",\"description\":\"登录日志\",\"sort\":90,\"type\":1,\"title\":\"loginLogManage\",\"parentId\":1,\"path\":\"loginlog\",\"component\":\"log/loginLog\",\"name\":\"LoginLogManage\",\"id\":41,\"permissionName\":\"登录日志\",\"status\":1}]', '2021-01-08 19:32:57');
INSERT INTO `sys_operate_log` VALUES (785, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:schedule:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"1\",\"icon\":\"schedule\",\"description\":\"任务管理\",\"sort\":80,\"type\":1,\"title\":\"scheduleManage\",\"parentId\":1,\"path\":\"schedule\",\"component\":\"job/schedule\",\"name\":\"ScheduleManage\",\"id\":28,\"permissionName\":\"任务管理\",\"status\":1}]', '2021-01-08 19:33:42');
INSERT INTO `sys_operate_log` VALUES (786, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:operatelog:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"1\",\"icon\":\"log\",\"description\":\"操作日志\",\"sort\":70,\"type\":1,\"title\":\"operateLogManage\",\"parentId\":1,\"path\":\"operatelog\",\"component\":\"log/operateLog\",\"name\":\"OperateLogManage\",\"id\":43,\"permissionName\":\"操作日志\",\"status\":1}]', '2021-01-08 19:33:58');
INSERT INTO `sys_operate_log` VALUES (787, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:dictionary:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"1\",\"icon\":\"dictionary\",\"description\":\"字典管理\",\"sort\":60,\"type\":1,\"title\":\"dictionaryManage\",\"parentId\":1,\"path\":\"dictionary\",\"component\":\"dictionary/index\",\"name\":\"DictionaryManage\",\"id\":23,\"permissionName\":\"字典管理\",\"status\":1}]', '2021-01-08 19:34:14');
INSERT INTO `sys_operate_log` VALUES (788, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"sys:department:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"1\",\"icon\":\"tree\",\"description\":\"部门管理\",\"sort\":50,\"type\":1,\"title\":\"departmentManage\",\"parentId\":1,\"path\":\"department\",\"component\":\"department/index\",\"name\":\"DepartmentManage\",\"id\":18,\"permissionName\":\"部门管理\",\"status\":1}]', '2021-01-08 19:35:30');
INSERT INTO `sys_operate_log` VALUES (789, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"/system/user\",\"permissionString\":\"sys:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"NaN\",\"icon\":\"component\",\"description\":\"系统管理\",\"sort\":1,\"type\":1,\"title\":\"system\",\"path\":\"/system\",\"component\":\"Layout\",\"name\":\"System\",\"id\":1,\"permissionName\":\"系统管理\",\"status\":1}]', '2021-01-08 19:36:46');
INSERT INTO `sys_operate_log` VALUES (790, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"/cms/article\",\"permissionString\":\"cms:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"NaN\",\"icon\":\"cms\",\"description\":\"内容管理\",\"sort\":10,\"type\":1,\"title\":\"cmsManage\",\"path\":\"/cms\",\"component\":\"Layout\",\"name\":\"CmsManage\",\"id\":45,\"permissionName\":\"内容管理\",\"status\":1}]', '2021-01-08 19:37:04');
INSERT INTO `sys_operate_log` VALUES (791, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"cms:article:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"45\",\"icon\":\"article\",\"description\":\"文章管理\",\"sort\":1,\"type\":1,\"title\":\"articleManage\",\"parentId\":45,\"path\":\"article\",\"component\":\"cms/article\",\"name\":\"ArticleManage\",\"id\":46,\"permissionName\":\"文章管理\",\"status\":1}]', '2021-01-08 22:37:55');
INSERT INTO `sys_operate_log` VALUES (792, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"permissionString\":\"cms:category:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"45\",\"icon\":\"category\",\"description\":\"分类管理\",\"sort\":10,\"type\":1,\"title\":\"categoryManage\",\"parentId\":45,\"path\":\"category\",\"component\":\"cms/category\",\"name\":\"CategoryManage\",\"id\":51,\"permissionName\":\"分类管理\",\"status\":1}]', '2021-01-08 22:38:11');
INSERT INTO `sys_operate_log` VALUES (793, 1, 'admin', '平台参数表模块', '更新平台参数表', '[{\"remark\":\"新用户注册默认归属角色\",\"paramName\":\"default_user_role\",\"paramId\":3,\"paramValue\":\"{\\\"roleId\\\":1}\"}]', '2021-01-08 22:39:11');
INSERT INTO `sys_operate_log` VALUES (794, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,56]}]', '2021-01-09 16:00:35');
INSERT INTO `sys_operate_log` VALUES (795, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65]}]', '2021-01-09 16:11:52');
INSERT INTO `sys_operate_log` VALUES (796, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"tool:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"NaN\",\"icon\":\"schedule\",\"description\":\"系统工具\",\"sort\":300,\"type\":1,\"title\":\"systemTool\",\"path\":\"/tool\",\"component\":\"Layout\",\"name\":\"SystemTool\",\"id\":56,\"permissionName\":\"系统工具\",\"status\":1}]', '2021-01-09 16:14:01');
INSERT INTO `sys_operate_log` VALUES (797, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67]}]', '2021-01-09 16:24:29');
INSERT INTO `sys_operate_log` VALUES (798, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68]}]', '2021-01-09 16:26:58');
INSERT INTO `sys_operate_log` VALUES (799, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72]}]', '2021-01-09 16:39:20');
INSERT INTO `sys_operate_log` VALUES (800, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78]}]', '2021-01-09 16:49:45');
INSERT INTO `sys_operate_log` VALUES (801, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83]}]', '2021-01-09 16:54:29');
INSERT INTO `sys_operate_log` VALUES (802, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88]}]', '2021-01-09 16:57:24');
INSERT INTO `sys_operate_log` VALUES (803, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94]}]', '2021-01-09 17:06:56');
INSERT INTO `sys_operate_log` VALUES (804, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,68,66,67,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95]}]', '2021-01-09 17:17:24');
INSERT INTO `sys_operate_log` VALUES (805, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,68,66,67,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100]}]', '2021-01-09 17:20:44');
INSERT INTO `sys_operate_log` VALUES (806, 1, 'admin', '角色信息模块', '更新角色信息', '[{\"roleName\":\"管理员角色\",\"description\":\"管理员角色\",\"dataPerm\":3,\"id\":1,\"dataPermIds\":\"\",\"status\":1,\"checkedKeys\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,68,66,67,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103]}]', '2021-01-09 17:25:33');
INSERT INTO `sys_operate_log` VALUES (807, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"/system/user\",\"permissionString\":\"sys:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"NaN\",\"icon\":\"setting\",\"description\":\"系统管理\",\"sort\":100,\"type\":1,\"title\":\"system\",\"path\":\"/system\",\"component\":\"Layout\",\"name\":\"System\",\"id\":1,\"permissionName\":\"系统管理\",\"status\":1}]', '2021-01-09 17:49:23');
INSERT INTO `sys_operate_log` VALUES (808, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"/monitoring/onlineuser\",\"permissionString\":\"sys:monitoring:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"NaN\",\"icon\":\"monitor\",\"description\":\"系统监控\",\"sort\":200,\"type\":1,\"title\":\"sysMonitor\",\"path\":\"/monitoring\",\"component\":\"Layout\",\"name\":\"SysMonitor\",\"id\":39,\"permissionName\":\"系统监控\",\"status\":1}]', '2021-01-09 17:49:50');
INSERT INTO `sys_operate_log` VALUES (809, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"sys:monitoring:onlineuser:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"39\",\"icon\":\"onlineuser\",\"description\":\"在线管理\",\"sort\":210,\"type\":1,\"title\":\"onlineUserManage\",\"parentId\":39,\"path\":\"onlineuser\",\"component\":\"user/onlineUser\",\"name\":\"OnlineUserManage\",\"id\":40,\"permissionName\":\"在线管理\",\"status\":1}]', '2021-01-09 17:50:34');
INSERT INTO `sys_operate_log` VALUES (810, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"monitoring:sql:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"39\",\"icon\":\"sql\",\"description\":\"SQL监控\",\"sort\":210,\"type\":1,\"title\":\"sqlMonitor\",\"parentId\":39,\"path\":\"onlineuser\",\"component\":\"druid/login\",\"name\":\"SqlMonitor\",\"id\":54,\"permissionName\":\"SQL监控\",\"status\":1}]', '2021-01-09 17:51:21');
INSERT INTO `sys_operate_log` VALUES (811, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"monitoring:api:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"39\",\"icon\":\"api\",\"description\":\"API接口\",\"sort\":210,\"type\":1,\"title\":\"swaggerApi\",\"parentId\":39,\"path\":\"api\",\"component\":\"druid/login\",\"name\":\"SwaggerApi\",\"id\":55,\"permissionName\":\"API接口\",\"status\":1}]', '2021-01-09 17:52:05');
INSERT INTO `sys_operate_log` VALUES (812, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"sys:operatelog:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"39\",\"icon\":\"log\",\"description\":\"操作日志\",\"sort\":220,\"type\":1,\"title\":\"operateLogManage\",\"parentId\":39,\"path\":\"operatelog\",\"component\":\"log/loginLog\",\"name\":\"OperateLogManage\",\"id\":42,\"permissionName\":\"操作日志\",\"status\":1}]', '2021-01-09 17:52:27');
INSERT INTO `sys_operate_log` VALUES (813, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"sys:loginlog:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"39\",\"icon\":\"login-log\",\"description\":\"登录日志\",\"sort\":230,\"type\":1,\"title\":\"loginLogManage\",\"parentId\":39,\"path\":\"loginlog\",\"component\":\"log/loginLog\",\"name\":\"LoginLogManage\",\"id\":48,\"permissionName\":\"登录日志\",\"status\":1}]', '2021-01-09 17:52:39');
INSERT INTO `sys_operate_log` VALUES (814, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"tool:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"NaN\",\"icon\":\"tool\",\"description\":\"系统工具\",\"sort\":300,\"type\":1,\"title\":\"systemTool\",\"path\":\"/tool\",\"component\":\"Layout\",\"name\":\"SystemTool\",\"id\":56,\"permissionName\":\"系统工具\",\"status\":1}]', '2021-01-09 17:53:55');
INSERT INTO `sys_operate_log` VALUES (815, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"tool:job:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"56\",\"icon\":\"schedule\",\"description\":\"任务管理\",\"sort\":310,\"type\":1,\"title\":\"scheduleManage\",\"parentId\":56,\"path\":\"schedule\",\"component\":\"job/schedule\",\"name\":\"ScheduleManage\",\"id\":57,\"permissionName\":\"任务管理\",\"status\":1}]', '2021-01-09 17:54:32');
INSERT INTO `sys_operate_log` VALUES (816, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"tool:job:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"56\",\"icon\":\"generator\",\"description\":\"代码生成\",\"sort\":320,\"type\":1,\"title\":\"generator\",\"parentId\":56,\"path\":\"generator\",\"component\":\"generator/table\",\"name\":\"Generator\",\"id\":66,\"permissionName\":\"代码生成\",\"status\":1}]', '2021-01-09 17:54:59');
INSERT INTO `sys_operate_log` VALUES (817, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"components:icons:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"69\",\"icon\":\"icon\",\"description\":\"图标\",\"sort\":410,\"type\":1,\"title\":\"icons\",\"parentId\":69,\"path\":\"icons\",\"component\":\"icons/index\",\"name\":\"Icons\",\"id\":70,\"permissionName\":\"图标\",\"status\":1}]', '2021-01-09 17:55:27');
INSERT INTO `sys_operate_log` VALUES (818, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"components:tinymce:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"69\",\"icon\":\"fwb\",\"description\":\"富文本编辑\",\"sort\":420,\"type\":1,\"title\":\"tinymce\",\"parentId\":69,\"path\":\"tinymce\",\"component\":\"components-demo/tinymce\",\"name\":\"Tinymce\",\"id\":71,\"permissionName\":\"富文本编辑\",\"status\":1}]', '2021-01-09 17:55:44');
INSERT INTO `sys_operate_log` VALUES (819, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"components:markdown:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"69\",\"icon\":\"markdown\",\"description\":\"Markdown\",\"sort\":430,\"type\":1,\"title\":\"markdown\",\"parentId\":69,\"path\":\"markdown\",\"component\":\"components-demo/markdown\",\"name\":\"Markdown\",\"id\":72,\"permissionName\":\"Markdown\",\"status\":1}]', '2021-01-09 17:55:59');
INSERT INTO `sys_operate_log` VALUES (820, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"cms:article:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"73\",\"icon\":\"article\",\"description\":\"文章管理\",\"sort\":510,\"type\":1,\"title\":\"articleManage\",\"parentId\":73,\"path\":\"article\",\"component\":\"cms/article\",\"name\":\"ArticleManage\",\"id\":74,\"permissionName\":\"文章管理\",\"status\":1}]', '2021-01-09 17:56:12');
INSERT INTO `sys_operate_log` VALUES (821, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"cms:category:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"73\",\"icon\":\"category\",\"description\":\"文章分类\",\"sort\":520,\"type\":1,\"title\":\"categoryManage\",\"parentId\":73,\"path\":\"category\",\"component\":\"cms/category\",\"name\":\"CategoryManage\",\"id\":79,\"permissionName\":\"文章分类\",\"status\":1}]', '2021-01-09 17:56:22');
INSERT INTO `sys_operate_log` VALUES (822, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"cms:tag:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"73\",\"icon\":\"fwtag\",\"description\":\"文章标签\",\"sort\":530,\"type\":1,\"title\":\"articleTagManage\",\"parentId\":73,\"path\":\"tag\",\"component\":\"cms/tag\",\"name\":\"ArticleTagManage\",\"id\":84,\"permissionName\":\"文章标签\",\"status\":1}]', '2021-01-09 17:57:13');
INSERT INTO `sys_operate_log` VALUES (823, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"wx:gzhAccount:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"89\",\"icon\":\"gzh\",\"description\":\"公众号管理\",\"sort\":610,\"type\":1,\"title\":\"gzhAccountManage\",\"parentId\":89,\"path\":\"gzhaccount\",\"component\":\"wx/wxgzh\",\"name\":\"GzhAccountManage\",\"id\":90,\"permissionName\":\"公众号管理\",\"status\":1}]', '2021-01-09 17:57:27');
INSERT INTO `sys_operate_log` VALUES (824, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"wx:gzhuser:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"89\",\"icon\":\"fensi\",\"description\":\"粉丝管理\",\"sort\":620,\"type\":1,\"title\":\"gzhUserManage\",\"parentId\":89,\"path\":\"gzhuser\",\"component\":\"wx/wxuser\",\"name\":\"GzhUserManage\",\"id\":95,\"permissionName\":\"粉丝管理\",\"status\":1}]', '2021-01-09 17:57:36');
INSERT INTO `sys_operate_log` VALUES (825, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"wx:tagrule:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"89\",\"icon\":\"guide\",\"description\":\"标签规则\",\"sort\":630,\"type\":1,\"title\":\"gzhTagRuleManage\",\"parentId\":89,\"path\":\"tagrule\",\"component\":\"wx/tagrule\",\"name\":\"GzhTagRuleManage\",\"id\":101,\"permissionName\":\"标签规则\",\"status\":1}]', '2021-01-09 17:58:08');
INSERT INTO `sys_operate_log` VALUES (826, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"/wx/wxgzh\",\"permissionString\":\"wx:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"NaN\",\"icon\":\"wechat\",\"description\":\"微信管理\",\"sort\":600,\"type\":1,\"title\":\"wx_manage\",\"path\":\"/wx\",\"component\":\"Layout\",\"name\":\"Wx_manage\",\"id\":89,\"permissionName\":\"微信管理\",\"status\":1}]', '2021-01-09 17:58:29');
INSERT INTO `sys_operate_log` VALUES (827, 1, 'admin', '权限信息模块', '更新权限信息', '[{\"redirect\":\"\",\"permissionString\":\"cms:tag:manage\",\"cache\":1,\"hidden\":0,\"parentIds\":\"73\",\"icon\":\"eye-open\",\"description\":\"文章标签\",\"sort\":530,\"type\":1,\"title\":\"articleTagManage\",\"parentId\":73,\"path\":\"tag\",\"component\":\"cms/tag\",\"name\":\"ArticleTagManage\",\"id\":84,\"permissionName\":\"文章标签\",\"status\":1}]', '2021-01-09 18:00:45');
INSERT INTO `sys_operate_log` VALUES (828, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"hidden_dict\",\"createTime\":1610187222357,\"description\":\"菜单隐藏\",\"id\":21,\"label\":\"隐藏\",\"sort\":1,\"value\":\"1\",\"status\":1}]', '2021-01-09 18:13:42');
INSERT INTO `sys_operate_log` VALUES (829, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"hidden_dict\",\"createTime\":1610187243155,\"description\":\"菜单隐藏\",\"id\":22,\"label\":\"不隐藏\",\"sort\":2,\"value\":\"0\",\"status\":1}]', '2021-01-09 18:14:03');
INSERT INTO `sys_operate_log` VALUES (830, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"cache_dict\",\"createTime\":1610187303051,\"description\":\"菜单缓存\",\"id\":23,\"label\":\"缓存\",\"sort\":1,\"value\":\"1\",\"status\":1}]', '2021-01-09 18:15:03');
INSERT INTO `sys_operate_log` VALUES (831, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"cache_dict\",\"createTime\":1610187319385,\"description\":\"菜单缓存\",\"id\":24,\"label\":\"不缓存\",\"sort\":2,\"value\":\"0\",\"status\":1}]', '2021-01-09 18:15:19');
INSERT INTO `sys_operate_log` VALUES (832, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"perm_dict\",\"createTime\":1610187416005,\"description\":\"权限类型\",\"id\":25,\"label\":\"页面\",\"sort\":3,\"value\":\"3\",\"status\":1}]', '2021-01-09 18:16:56');
INSERT INTO `sys_operate_log` VALUES (833, 1, 'admin', '系统公众号表模块', '创建系统公众号表', '[{\"createBy\":1,\"createTime\":1610284298706,\"accountType\":2,\"authStatus\":1,\"jwid\":\"分维\",\"weixinAppid\":\"wxaeeab59bae7d0deb\",\"id\":1,\"fwAppsecret\":\"5a85862f532c43d0b089c81b4c296a07\",\"fwField\":4,\"fwAppid\":\"619125947727085568\"}]', '2021-01-10 21:11:39');
INSERT INTO `sys_operate_log` VALUES (834, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"fw_field_dict\",\"createTime\":1610284546599,\"description\":\"分维行业\",\"id\":26,\"label\":\"交友\",\"sort\":1,\"value\":\"1\",\"status\":1}]', '2021-01-10 21:15:47');
INSERT INTO `sys_operate_log` VALUES (835, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"account_type_dict\",\"createTime\":1610284570408,\"description\":\"账号类型\",\"id\":27,\"label\":\"订阅号\",\"sort\":1,\"value\":\"1\",\"status\":1}]', '2021-01-10 21:16:10');
INSERT INTO `sys_operate_log` VALUES (836, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"account_type_dict\",\"createTime\":1610284593000,\"description\":\"账号类型\",\"id\":28,\"label\":\"服务号\",\"sort\":2,\"value\":\"2\",\"status\":1}]', '2021-01-10 21:16:33');
INSERT INTO `sys_operate_log` VALUES (837, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"auth_status_dict\",\"createTime\":1610284641495,\"description\":\"认证状态\",\"id\":29,\"label\":\"已认证\",\"sort\":1,\"value\":\"1\",\"status\":1}]', '2021-01-10 21:17:22');
INSERT INTO `sys_operate_log` VALUES (838, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"auth_status_dict\",\"createTime\":1610284657823,\"description\":\"认证状态\",\"id\":30,\"label\":\"未认证\",\"sort\":2,\"value\":\"0\",\"status\":1}]', '2021-01-10 21:17:38');
INSERT INTO `sys_operate_log` VALUES (839, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"default_status_dict\",\"createTime\":1610285600724,\"description\":\"是否默认\",\"id\":31,\"label\":\"默认\",\"sort\":1,\"value\":\"1\",\"status\":1}]', '2021-01-10 21:33:21');
INSERT INTO `sys_operate_log` VALUES (840, 1, 'admin', '字典信息模块', '创建字典信息', '[{\"createBy\":1,\"code\":\"default_status_dict\",\"createTime\":1610285625891,\"description\":\"是否默认\",\"id\":32,\"label\":\"非默认\",\"sort\":2,\"value\":\"0\",\"status\":1}]', '2021-01-10 21:33:46');
INSERT INTO `sys_operate_log` VALUES (841, 1, 'admin', '系统公众号表模块', '设置默认公众号', '[1]', '2021-01-10 21:37:35');
INSERT INTO `sys_operate_log` VALUES (842, 1, 'admin', '定时任务', '修改任务', '[{\"cronExpression\":\"1 * * * * ?\",\"createBy\":1,\"createTime\":1581330047000,\"updateBy\":1,\"count\":3,\"methodName\":\"batchPublishArticles\",\"beanName\":\"longMarchJobTask\",\"remark\":\"定时发布文章\",\"updateTime\":1610286185991,\"id\":1,\"params\":\"\",\"status\":false}]', '2021-01-10 21:43:06');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '平台参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_parameter
-- ----------------------------
INSERT INTO `sys_parameter` VALUES (1, 'sys_params', '{\"copyright\":\"Copyright © 2020 晴天雨\",\"code\":\"<script type=\\\"text/javascript\\\" src=\\\"https://js.users.51.la/19400986.js\\\"></script>\",\"headImgUrl\":\"http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r\",\"keywords\":\"长征CMS系统\",\"defaultUserRole\":\"1\",\"logo\":\"http://q35smspdq.bkt.clouddn.com/Fmn6EGo4ZelbTAJCuDxUbvS1P3gH\",\"description\":\"长征CMS系统，用户管理后台系统\",\"title\":\"长征CMS系统\",\"url\":\"http://www.longmarch.top\"}', '系统默认参数');
INSERT INTO `sys_parameter` VALUES (2, 'qiniu_upload', '{\"bucket\":\"longmarch123\",\"secretKey\":\"ZKj7MXTZUfOnPeuw4hEd23-MZEvXuRc62t02Vddu\",\"accessKey\":\"JP30SdrdnwHXrCO5v24JoEDmBM2mhIU3MndHVqlR\",\"fileMaxSize\":\"20971520\",\"name\":\"文件上传参数\",\"downloadUrl\":\"http://upload.longmarch.top\",\"expireSeconds\":\"1000000\"}', '七牛上传参数');
INSERT INTO `sys_parameter` VALUES (3, 'default_user_role', '{\"roleId\":1}', '新用户注册默认归属角色');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父节点ID',
  `parent_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点IDS',
  `permission_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `permission_string` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限字符串',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '权限类型，菜单 1，按钮 2',
  `status` tinyint(2) UNSIGNED NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `component` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `redirect` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重定向',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由名字',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由标题',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `hidden` tinyint(2) NULL DEFAULT NULL COMMENT '是否隐藏',
  `cache` tinyint(2) NULL DEFAULT NULL COMMENT '是否缓存',
  `sort` int(100) NULL DEFAULT NULL COMMENT '排序',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, 'NaN', '系统管理', '系统管理', 'sys:manage', 1, 1, '/system', 'Layout', '/system/user', 'System', 'system', 'setting', 0, 1, 100, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (2, 1, '1', '用户管理', '用户管理', 'sys:user:manage', 1, 1, 'user', 'user/index', '', 'UserManage', 'userManage', 'user', 0, 1, 110, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (3, 2, '1,2', '查看用户', '查看用户', 'sys:user:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (4, 2, '1,2', '添加用户', '添加用户', 'sys:user:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (5, 2, '1,2', '修改用户', '修改用户', 'sys:user:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (6, 2, '1,2', '删除用户', '删除用户', 'sys:user:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (7, 2, '1,2', '修改密码', '修改密码', 'sys:user:change:password', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (8, 2, '1,2', '导出用户', '导出用户', 'sys:user:export', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 6, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (9, 1, '1', '角色管理', '角色管理', 'sys:role:manage', 1, 1, 'role', 'role/index', '', 'RoleManage', 'roleManage', 'peoples', 0, 1, 120, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (10, 9, '1,9', '查看角色', '查看角色', 'sys:role:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 300, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (11, 9, '1,9', '添加角色', '添加角色', 'sys:role:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (12, 9, '1,9', '修改角色', '修改角色', 'sys:role:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (13, 9, '1,9', '删除角色', '删除角色', 'sys:role:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (14, 9, '1,9', '导出角色', '导出角色', 'sys:role:export', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (15, 1, '1', '部门管理', '部门管理', 'sys:department:manage', 1, 1, 'department', 'department/index', '', 'DepartmentManage', 'departmentManage', 'tree', 0, 1, 130, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (16, 15, '1,15', '查看部门', '查看部门', 'sys:department:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:10', NULL, NULL);
INSERT INTO `sys_permission` VALUES (17, 15, '1,15', '添加部门', '添加部门', 'sys:department:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (18, 15, '1,15', '修改部门', '修改部门', 'sys:department:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (19, 15, '1,15', '删除部门', '删除部门', 'sys:department:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (20, 15, '1,15', '导出部门', '导出部门', 'sys:department:export', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (21, 1, '1', '权限管理', '权限管理', 'sys:permission:manage', 1, 1, 'permission', 'permission/index', '', 'PermissionManage', 'permissionManage', 'lock', 0, 1, 140, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (22, 21, '1,21', '查看权限', '查看权限', 'sys:permission:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (23, 21, '1,21', '添加权限', '添加权限', 'sys:permission:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (24, 21, '1,21', '修改权限', '修改权限', 'sys:permission:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (25, 21, '1,21', '删除权限', '删除权限', 'sys:permission:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (26, 21, '1,21', '导出权限', '导出权限', 'sys:permission:export', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (27, 1, '1', '参数管理', '参数管理', 'sys:parameter:manage', 1, 1, 'parameter', 'parameter/index', '', 'ParameterManage', 'parameterManage', 'parameter', 0, 1, 150, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (28, 27, '1,27', '查看参数', '查看参数', 'sys:parameter:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (29, 27, '1,27', '添加参数', '添加参数', 'sys:parameter:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (30, 27, '1,27', '修改参数', '修改参数', 'sys:parameter:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (31, 27, '1,27', '删除参数', '删除参数', 'sys:parameter:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (32, 27, '1,27', '导出参数', '导出参数', 'sys:parameter:export', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (33, 1, '1', '字典管理', '字典管理', 'sys:dictionary:manage', 1, 1, 'dictionary', 'dictionary/index', '', 'DictionaryManage', 'dictionaryManage', 'dictionary', 0, 1, 160, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (34, 33, '1,33', '查看字典', '查看字典', 'sys:dictionary:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (35, 33, '1,33', '添加字典', '添加字典', 'sys:dictionary:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (36, 33, '1,33', '修改字典', '修改字典', 'sys:dictionary:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (37, 33, '1,33', '删除字典', '删除字典', 'sys:dictionary:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (38, 33, '1,33', '导出字典', '导出字典', 'sys:dictionary:export', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (39, 0, 'NaN', '系统监控', '系统监控', 'sys:monitoring:manage', 1, 1, '/monitoring', 'Layout', '/monitoring/onlineuser', 'SysMonitor', 'sysMonitor', 'monitor', 0, 1, 200, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (40, 39, '39', '在线管理', '在线管理', 'sys:monitoring:onlineuser:manage', 1, 1, 'onlineuser', 'user/onlineUser', '', 'OnlineUserManage', 'onlineUserManage', 'onlineuser', 0, 1, 210, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (41, 40, '39,40', '踢出用户', '踢出用户', 'sys:kickout:onlineuser', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (42, 39, '39', '操作日志', '操作日志', 'sys:operatelog:manage', 1, 1, 'operatelog', 'log/loginLog', '', 'OperateLogManage', 'operateLogManage', 'log', 0, 1, 220, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (43, 42, '1,42', '查看日志', '查看日志', 'sys:operateLog:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (44, 42, '1,42', '添加日志', '添加日志', 'sys:operateLog:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (45, 42, '1,42', '修改日志', '修改日志', 'sys:operateLog:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (46, 42, '1,42', '删除日志', '删除日志', 'sys:operateLog:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (47, 42, '1,42', '导出日志', '导出日志', 'sys:operateLog:export', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (48, 39, '39', '登录日志', '登录日志', 'sys:loginlog:manage', 1, 1, 'loginlog', 'log/loginLog', '', 'LoginLogManage', 'loginLogManage', 'login-log', 0, 1, 230, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (49, 48, '1,48', '查看日志', '查看日志', 'sys:loginLog:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (50, 48, '1,48', '添加日志', '添加日志', 'sys:loginLog:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (51, 48, '1,48', '修改日志', '修改日志', 'sys:loginLog:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (52, 48, '1,48', '删除日志', '删除日志', 'sys:loginLog:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (53, 48, '1,48', '导出日志', '导出日志', 'sys:loginLog:export', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (54, 39, '39', 'SQL监控', 'SQL监控', 'monitoring:sql:manage', 1, 1, 'onlineuser', 'druid/login', '', 'SqlMonitor', 'sqlMonitor', 'sql', 0, 1, 210, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (55, 39, '39', 'API接口', 'API接口', 'monitoring:api:manage', 1, 1, 'api', 'druid/login', '', 'SwaggerApi', 'swaggerApi', 'api', 0, 1, 210, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (56, 0, 'NaN', '系统工具', '系统工具', 'tool:manage', 1, 1, '/tool', 'Layout', '', 'SystemTool', 'systemTool', 'tool', 0, 1, 300, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (57, 56, '56', '任务管理', '任务管理', 'tool:job:manage', 1, 1, 'schedule', 'job/schedule', '', 'ScheduleManage', 'scheduleManage', 'schedule', 0, 1, 310, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (58, 57, '56,57', '查看任务', '查看任务', 'job:schedule:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (59, 57, '56,57', '创建任务', '创建任务', 'job:schedule:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (60, 57, '56,57', '修改任务', '修改任务', 'job:schedule:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (61, 57, '56,57', '删除任务', '删除任务', 'job:schedule:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (62, 57, '56,57', '立即任务', '立即任务', 'job:schedule:run', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (63, 57, '56,57', '暂停任务', '暂停任务', 'job:schedule:pause', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 6, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (64, 57, '56,57', '恢复任务', '恢复任务', 'job:schedule:resume', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 7, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (65, 57, '56,57', '重置任务', '重置任务', 'job:schedule:reset', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 8, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (66, 56, '56', '代码生成', '代码生成', 'sys:generator:manage', 1, 1, 'generator', 'generator/table', '', 'Generator', 'generator', 'generator', 0, 1, 320, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (67, 66, '56,66', '配置', '配置', 'sys:generator:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (68, 57, '56,57', '下载', '下载', 'sys:generator:download', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (69, 0, 'NaN', '组件管理', '组件管理', 'components:manage', 1, 1, '/components', 'Layout', '', 'Components', 'components', 'component', 0, 1, 400, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (70, 69, '69', '图标', '图标', 'components:icons:manage', 1, 1, 'icons', 'icons/index', '', 'Icons', 'icons', 'icon', 0, 1, 410, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (71, 69, '69', '富文本编辑', '富文本编辑', 'components:tinymce:manage', 1, 1, 'tinymce', 'components-demo/tinymce', '', 'Tinymce', 'tinymce', 'fwb', 0, 1, 420, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (72, 69, '69', 'Markdown', 'Markdown', 'components:markdown:manage', 1, 1, 'markdown', 'components-demo/markdown', '', 'Markdown', 'markdown', 'markdown', 0, 1, 430, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (73, 0, 'NaN', '内容管理', '内容管理', 'cms:manage', 1, 1, '/cms', 'Layout', 'cms/article', 'CmsManage', 'cmsManage', 'cms', 0, 1, 500, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (74, 73, '73', '文章管理', '文章管理', 'cms:article:manage', 1, 1, 'article', 'cms/article', '', 'ArticleManage', 'articleManage', 'article', 0, 1, 510, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (75, 74, '73,74', '查看文章', '查看文章', 'cms:article:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (76, 74, '73,74', '创建文章', '创建文章', 'cms:article:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (77, 74, '73,74', '修改文章', '修改文章', 'cms:article:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (78, 74, '73,74', '删除文章', '删除文章', 'cms:article:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (79, 73, '73', '文章分类', '文章分类', 'cms:category:manage', 1, 1, 'category', 'cms/category', '', 'CategoryManage', 'categoryManage', 'category', 0, 1, 520, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (80, 79, '73,79', '查看分类', '查看分类', 'cms:category:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (81, 79, '73,79', '创建分类', '创建分类', 'cms:category:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (82, 79, '73,79', '修改分类', '修改分类', 'cms:category:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (83, 79, '73,79', '删除分类', '删除分类', 'cms:category:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (84, 73, '73', '文章标签', '文章标签', 'cms:tag:manage', 1, 1, 'tag', 'cms/tag', '', 'ArticleTagManage', 'articleTagManage', 'eye-open', 0, 1, 530, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (85, 84, '73,84', '查看标签', '查看标签', 'cms:tag:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (86, 84, '73,84', '创建标签', '创建标签', 'cms:tag:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (87, 84, '73,84', '修改标签', '修改标签', 'cms:tag:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:11', NULL, NULL);
INSERT INTO `sys_permission` VALUES (88, 84, '73,84', '删除标签', '删除标签', 'cms:tag:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (89, 0, 'NaN', '微信管理', '微信管理', 'wx:manage', 1, 1, '/wx', 'Layout', '/wx/wxgzh', 'Wx_manage', 'wx_manage', 'wechat', 0, 1, 600, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (90, 89, '89', '公众号管理', '公众号管理', 'wx:gzhAccount:manage', 1, 1, 'gzhaccount', 'wx/wxgzh', '', 'GzhAccountManage', 'gzhAccountManage', 'gzh', 0, 1, 610, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (91, 90, '89,90', '查看公众号', '查看公众号', 'wx:gzhAccount:show', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (92, 90, '89,90', '创建公众号', '创建公众号', 'wx:gzhAccount:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (93, 90, '89,90', '修改公众号', '修改公众号', 'wx:gzhAccount:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (94, 90, '89,90', '删除公众号', '删除公众号', 'wx:gzhAccount:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (95, 90, '89,90', '设置公众号', '设置公众号', 'wx:gzhAccount:setting', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 5, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (96, 89, '89', '粉丝管理', '粉丝管理', 'wx:gzhuser:manage', 1, 1, 'gzhuser', 'wx/wxuser', '', 'GzhUserManage', 'gzhUserManage', 'fensi', 0, 1, 620, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (97, 96, '89,96', '同步微信用户', '同步微信用户', 'wx:gzhuser:sync:all', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (98, 96, '89,96', '解析分维标签', '解析分维标签', 'wx:gzhuser:analyse:all', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (99, 96, '89,96', '解析营销标签', '解析营销标签', 'wx:gzhuser:analysis', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (100, 96, '89,96', '取消用户标签', '取消用户标签', 'wx:gzhuser:remove', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (101, 96, '89,96', '下载用户标签', '下载用户标签', 'wx:gzhuser:download', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 4, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (102, 89, '89', '标签规则', '标签规则', 'wx:tagrule:manage', 1, 1, 'tagrule', 'wx/tagrule', '', 'GzhTagRuleManage', 'gzhTagRuleManage', 'guide', 0, 1, 630, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (103, 102, '89,102', '创建规则', '创建规则', 'wx:gzhTag:create', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (104, 102, '89,102', '更新规则', '更新规则', 'wx:gzhTag:update', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 2, 1, '2021-01-10 13:26:12', NULL, NULL);
INSERT INTO `sys_permission` VALUES (105, 102, '89,102', '删除规则', '删除规则', 'wx:gzhTag:delete', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 3, 1, '2021-01-10 13:26:12', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `data_perm` int(11) NULL DEFAULT 3 COMMENT '数据权限（1 用户ID， 2 部门ID， 3 全部）',
  `data_perm_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限ID集合',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员角色', '管理员角色', 1, 3, '', 1, '2020-02-10 18:20:46', 1, '2021-01-09 17:25:33');

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
INSERT INTO `sys_role_permission_rel` VALUES (1, 56);
INSERT INTO `sys_role_permission_rel` VALUES (1, 57);
INSERT INTO `sys_role_permission_rel` VALUES (1, 58);
INSERT INTO `sys_role_permission_rel` VALUES (1, 59);
INSERT INTO `sys_role_permission_rel` VALUES (1, 60);
INSERT INTO `sys_role_permission_rel` VALUES (1, 61);
INSERT INTO `sys_role_permission_rel` VALUES (1, 62);
INSERT INTO `sys_role_permission_rel` VALUES (1, 63);
INSERT INTO `sys_role_permission_rel` VALUES (1, 64);
INSERT INTO `sys_role_permission_rel` VALUES (1, 65);
INSERT INTO `sys_role_permission_rel` VALUES (1, 66);
INSERT INTO `sys_role_permission_rel` VALUES (1, 67);
INSERT INTO `sys_role_permission_rel` VALUES (1, 68);
INSERT INTO `sys_role_permission_rel` VALUES (1, 69);
INSERT INTO `sys_role_permission_rel` VALUES (1, 70);
INSERT INTO `sys_role_permission_rel` VALUES (1, 71);
INSERT INTO `sys_role_permission_rel` VALUES (1, 72);
INSERT INTO `sys_role_permission_rel` VALUES (1, 73);
INSERT INTO `sys_role_permission_rel` VALUES (1, 74);
INSERT INTO `sys_role_permission_rel` VALUES (1, 75);
INSERT INTO `sys_role_permission_rel` VALUES (1, 76);
INSERT INTO `sys_role_permission_rel` VALUES (1, 77);
INSERT INTO `sys_role_permission_rel` VALUES (1, 78);
INSERT INTO `sys_role_permission_rel` VALUES (1, 79);
INSERT INTO `sys_role_permission_rel` VALUES (1, 80);
INSERT INTO `sys_role_permission_rel` VALUES (1, 81);
INSERT INTO `sys_role_permission_rel` VALUES (1, 82);
INSERT INTO `sys_role_permission_rel` VALUES (1, 83);
INSERT INTO `sys_role_permission_rel` VALUES (1, 84);
INSERT INTO `sys_role_permission_rel` VALUES (1, 85);
INSERT INTO `sys_role_permission_rel` VALUES (1, 86);
INSERT INTO `sys_role_permission_rel` VALUES (1, 87);
INSERT INTO `sys_role_permission_rel` VALUES (1, 88);
INSERT INTO `sys_role_permission_rel` VALUES (1, 89);
INSERT INTO `sys_role_permission_rel` VALUES (1, 90);
INSERT INTO `sys_role_permission_rel` VALUES (1, 91);
INSERT INTO `sys_role_permission_rel` VALUES (1, 92);
INSERT INTO `sys_role_permission_rel` VALUES (1, 93);
INSERT INTO `sys_role_permission_rel` VALUES (1, 94);
INSERT INTO `sys_role_permission_rel` VALUES (1, 95);
INSERT INTO `sys_role_permission_rel` VALUES (1, 96);
INSERT INTO `sys_role_permission_rel` VALUES (1, 97);
INSERT INTO `sys_role_permission_rel` VALUES (1, 98);
INSERT INTO `sys_role_permission_rel` VALUES (1, 99);
INSERT INTO `sys_role_permission_rel` VALUES (1, 100);
INSERT INTO `sys_role_permission_rel` VALUES (1, 101);
INSERT INTO `sys_role_permission_rel` VALUES (1, 102);
INSERT INTO `sys_role_permission_rel` VALUES (1, 103);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `head_img_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `user_type` tinyint(1) NULL DEFAULT NULL COMMENT '用户类型（1 后台管理用户， 2 注册会员）',
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'd89267ba6e888426c8f798a04f2fb874', NULL, 'http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r', '18888888888', 1, NULL, 42, '2021-01-10 13:37:22', NULL, NULL, 1, '2020-02-10 18:20:46', NULL, NULL);

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

-- ----------------------------
-- Table structure for wx_fwtag_rule
-- ----------------------------
DROP TABLE IF EXISTS `wx_fwtag_rule`;
CREATE TABLE `wx_fwtag_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则ID',
  `score` int(11) NULL DEFAULT NULL COMMENT '分数',
  `compute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计算（大于或小于）',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签内容',
  `new_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新标签',
  `gzh_id` int(11) NULL DEFAULT NULL COMMENT '公众号ID',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分维标签规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_gzh_account
-- ----------------------------
DROP TABLE IF EXISTS `wx_gzh_account`;
CREATE TABLE `wx_gzh_account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `jwid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公众号',
  `wx_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公众号登录账号',
  `login_passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公众号登录密码',
  `application_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用类型',
  `qrcodeimg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信二维码图片',
  `weixin_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `weixin_appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信AppId',
  `weixin_appsecret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信AppSecret',
  `fw_appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分维AppId',
  `fw_appsecret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分维AppSecret',
  `fee_type` int(11) NULL DEFAULT NULL COMMENT '计费类型',
  `fw_field` int(11) NULL DEFAULT NULL COMMENT '分维解析行业',
  `account_type` int(11) NULL DEFAULT NULL COMMENT '公众号类型',
  `auth_status` int(11) NULL DEFAULT NULL COMMENT '是否认证',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `token_gettime` datetime(0) NULL DEFAULT NULL,
  `apiticket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `apiticket_gettime` datetime(0) NULL DEFAULT NULL,
  `jsapiticket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jsapiticket_gettime` datetime(0) NULL DEFAULT NULL,
  `auth_type` tinyblob NULL,
  `business_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `func_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `headimgurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorization_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorizer_refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorization_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_deleted` int(11) NULL DEFAULT NULL,
  `is_default_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tenant_id` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统公众号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_gzh_account
-- ----------------------------
INSERT INTO `wx_gzh_account` VALUES (1, '分维', '', NULL, NULL, NULL, NULL, NULL, 'wxaeeab59bae7d0deb', NULL, '619125947727085568', '5a85862f532c43d0b089c81b4c296a07', 1, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, '1', NULL, 1, '2021-01-10 21:11:39', NULL, NULL);

-- ----------------------------
-- Table structure for wx_gzh_fenwei_tag
-- ----------------------------
DROP TABLE IF EXISTS `wx_gzh_fenwei_tag`;
CREATE TABLE `wx_gzh_fenwei_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gzh_id` bigint(20) NULL DEFAULT NULL COMMENT '公众号ID',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信OpenID',
  `field_id` int(11) NULL DEFAULT NULL COMMENT '行业ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签内容',
  `rank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可信度',
  `score` int(11) NULL DEFAULT NULL COMMENT '阈值',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公众号粉丝分维解析标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_gzh_tag
-- ----------------------------
DROP TABLE IF EXISTS `wx_gzh_tag`;
CREATE TABLE `wx_gzh_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gzh_id` bigint(20) NULL DEFAULT NULL COMMENT '公众号ID',
  `wx_tag_id` bigint(20) NULL DEFAULT NULL COMMENT '微信公众号标签ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新标签名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信公众号标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_gzh_tag_rule
-- ----------------------------
DROP TABLE IF EXISTS `wx_gzh_tag_rule`;
CREATE TABLE `wx_gzh_tag_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则ID',
  `score` int(11) NULL DEFAULT NULL COMMENT '分数',
  `compute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计算（大于或小于）',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签内容',
  `tag_id` bigint(20) NULL DEFAULT NULL COMMENT '新标签ID',
  `gzh_id` bigint(20) NULL DEFAULT NULL COMMENT '公众号ID',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_gzh_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_gzh_user`;
CREATE TABLE `wx_gzh_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gzh_id` bigint(20) NULL DEFAULT NULL COMMENT '公众号ID',
  `open_id` int(11) NULL DEFAULT NULL COMMENT 'openid',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `nickname_txt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '过滤后昵称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注名称',
  `head_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sex_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别：\'1\':男性；\'2\':女性；\'0\':未知',
  `subscribe` int(11) NULL DEFAULT NULL COMMENT '是否关注:\'0\':未关注；\'1\':关注',
  `subscribe_time` int(11) NULL DEFAULT NULL COMMENT '关注时间',
  `subscribe_scene` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户关注渠道来源',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `bind_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '绑定状态：\'N\':未绑定；\'Y\':已绑定；\'V\':绑定中',
  `bind_time` datetime(0) NULL DEFAULT NULL COMMENT '绑定时间',
  `tag_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签id',
  `fen_wei_tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分维标签',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区',
  `qr_scene` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二维码扫码场景',
  `qr_scene_str` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二维码扫码常见描述',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '用户所在分组id',
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `union_id` int(11) NULL DEFAULT NULL COMMENT 'union_id',
  `jwid` int(11) NULL DEFAULT NULL COMMENT '公众号原始id',
  `is_deleted` tinyblob NULL COMMENT '是否删除（1 已删除，0 未删除，默 0）',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '粉丝表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
