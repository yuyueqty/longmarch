/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.1.115
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 192.168.1.115:3306
 Source Schema         : long_march

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 18/01/2020 23:29:39
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
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

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
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('RenrenScheduler', 'DESKTOP-833SC1J1577499618545', 1577508578074, 15000);

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
  `publish_status` tinyint(2) NULL DEFAULT 0 COMMENT '发布状态（0 未发布， 1 审核中，2 已发布，默认 0）',
  `clicks` int(11) NULL DEFAULT 0 COMMENT '点击次数（默认0）',
  `likes` int(11) NULL DEFAULT 0 COMMENT '点赞次数（默认0）',
  `sort` int(11) NULL DEFAULT 0 COMMENT '文章排序（由大到小排序，默认0）',
  `is_top` tinyint(1) NULL DEFAULT 0 COMMENT '是否置顶（0 否， 1 是，默认 0）',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除（1 已删除，0 未删除，默 0）',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES (1, 6, '百度1', '健康中国，看习近平的体育情缘', '<div class=\"cnt_bd\">\n<p data-spm-anchor-id=\"0.0.0.i1\">有一位&ldquo;资深的体育迷&rdquo;，ddd</p>\n<p>他的工作&ldquo;忙碌指数&rdquo;屡屡刷新纪录，</p>\n<p>他同时也是全民健身的倡导者、践行者。</p>\n<p>他，就是习近平。</p>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<p><strong>他是这些运动的爱好者</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p><span id=\"flash_0\"><img src=\"http://t.live.cntv.cn/cntvwebplay/cntvplayer/images/plug-in_bg.gif\" width=\"540\" height=\"400\" /></span></p>\n<div align=\"center\">\n<div class=\"flash_install\"><a href=\"http://www.adobe.com/go/getflashplayer_cn\"><img src=\"http://player.cntv.cn/flashplayer/logo/get_adobe_flash_player.png\" /></a>\n<p>请点此安装最新Flash</p>\n</div>\n</div>\n<p>&nbsp;</p>\n<p>&ldquo;说到体育活动，我喜欢游泳、爬山等运动，游泳我四五岁就学会了。我还喜欢足球、排球、篮球、网球、武术等运动。&rdquo;</p>\n<p>&mdash;&mdash;2014年2月7日，习近平接受俄罗斯电视台专访时的讲话</p>\n<section>\n<section>\n<section>\n<section><img class=\"raw-image\" src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230612499_939.gif\" data-ratio=\"0.2988506\" data-src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230612499_939.gif\" data-type=\"gif\" data-w=\"435\" />&nbsp;</section>\n&nbsp;\n<section>\n<section>\n<p><strong>游泳</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>游泳是习近平喜欢的运动之一。2014年2月，习近平接受俄罗斯电视台专访时曾说，&ldquo;<strong>现在还是抽出时间来游泳</strong>&rdquo;。2013年6月，在美国加利福尼亚州安纳伯格庄园，习近平同时任美国总统奥巴马进行会晤前，奥巴马问习近平，&ldquo;平常是不是经常锻炼？&rdquo;习近平笑着回答，&ldquo;平常主要是游泳、散步，每天至少游1000米&rdquo;。</p>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section></section>\n</section>\n&nbsp;\n<section>\n<section><strong><img class=\"raw-image\" src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230613015_415.gif\" data-ratio=\"0.2988506\" data-src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230613015_415.gif\" data-type=\"gif\" data-w=\"435\" /></strong>&nbsp;</section>\n&nbsp;\n<section>\n<section>\n<p><strong>爬山</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>习近平喜欢的体育运动，还有一项就是爬山。福建登山协会创始人朱韶明回忆，时任福建省省长的习近平，平均两三周就要来登一次鼓山。</p>\n<p>&nbsp;</p>\n<p>朱韶明说，当时登山协会提出让习近平当名誉会长，他就爽快答应了：&ldquo;<strong>其他名誉会长我从来不当，登山协会名誉会长我要当。</strong><strong>我当名誉会长，可以推动全民登山运动，运动可以给百姓带来幸福。</strong>&rdquo;</p>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section></section>\n</section>\n&nbsp;\n<section>\n<section><img class=\"raw-image\" src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230613489_712.gif\" data-ratio=\"0.2988506\" data-src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230613489_712.gif\" data-type=\"gif\" data-w=\"435\" />&nbsp;</section>\n&nbsp;\n<section>\n<section>\n<p><strong>足球</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>2013年6月5日，习近平在墨西哥城发表演讲时说：&ldquo;<strong>我是一个足球迷</strong>。&rdquo;</p>\n<p>&nbsp;</p>\n<p>习近平的&ldquo;足球缘&rdquo;由来已久。北京八一中学是个足球氛围浓厚的学校，少年时代的习近平在此就读时喜爱上了足球。2016年9月，习近平重回母校，看到小足球队员正在进行带球过人训练，他笑着对大家说，&ldquo;<strong>50多年前我就在这个地方踢过球，那时候还是土场子</strong>&rdquo;。他带着自豪回忆说，&ldquo;<strong>我们的球队在北京市比赛中拿过冠军，在全国比赛得了第四名</strong>&rdquo;。</p>\n<p>&nbsp;</p>\n<p>习近平在正定工作期间，他的同事张银耀回忆说，&ldquo;下班以后，他有空的时候，一听说有足球、篮球比赛什么的，就会抽空看看。他特别喜欢足球&rdquo;。</p>\n<p>&nbsp;</p>\n<p>习近平这份&ldquo;球迷&rdquo;的热情并没有因为走上国家领导人岗位而消失：2012年2月访问爱尔兰期间，在参观都柏林一个体育运动协会时，习近平走上球场草坪，饶有兴趣地展示了自己的脚法。</p>\n<p>&nbsp;</p>\n<p>2014年7月，在阿根廷布宜诺斯艾利斯，时任阿根廷副总统兼参议长布杜向习近平主席赠送了阿根廷国家足球队10号球衣。</p>\n<p>&nbsp;</p>\n<p><img class=\"rich_pages\" src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230614477_179.jpeg\" data-copyright=\"0\" data-ratio=\"0.6097560975609756\" data-s=\"300,640\" data-src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230614477_179.jpeg\" data-type=\"jpeg\" data-w=\"1066\" /></p>\n<p>&nbsp;</p>\n<p>2015年10月，习近平在英国参观访问曼彻斯特城市足球学院，观摩俱乐部一线球员训练比赛。习近平表示，英国是现代足球发源地和世界足球强国，英国足球很多方面值得我们借鉴。</p>\n<p>&nbsp;</p>\n<p>2014年出席中荷经贸合作论坛开幕式，习近平称赞荷兰足球队是世界足球的&ldquo;无冕之王&rdquo;；出席中法建交50周年纪念大会，习近平为当时成为中国男足主教练的法国人佩兰送上祝福；在德国，习近平与夫人彭丽媛一同看望在柏林训练的中国少年足球运动员。</p>\n<p>&nbsp;</p>\n<p>习近平喜爱足球、关注足球，已不仅是个人的兴趣爱好，而是传播友谊的桥梁。他在国际舞台上展现出的外交风采，更是给世界留下了深刻的印象。</p>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section><img class=\"raw-image\" src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230615004_829.gif\" data-ratio=\"0.2988506\" data-src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230615004_829.gif\" data-type=\"gif\" data-w=\"435\" />&nbsp;</section>\n&nbsp;\n<section>\n<section>\n<p><strong>篮球</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>习近平对篮球也非常感兴趣。在正定工作期间，工作之余他会和同事们打篮球。</p>\n<p>&nbsp;</p>\n<p>2012年2月，时任中国国家副主席习近平在结束访美之前，在洛杉矶观看NBA篮球比赛。比赛在洛杉矶湖人队和菲尼克斯太阳队之间进行。习近平说，NBA在中国很受欢迎，特别是深受年轻人的喜爱。湖人队出过很多球星，像过去的奥尼尔、现在的科比，在中国都很有名。他勉励湖人队为增进中美两国人民友谊作出不懈努力。</p>\n<p>&nbsp;</p>\n<p><img class=\"rich_pages\" src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230615464_4.gif\" data-backh=\"275\" data-backw=\"350\" data-before-oversubscription-url=\"https://mmbiz.qpic.cn/mmbiz_gif/DTobibL9LCHKY7ZfLjLUuV9FsRPwQ1TY5eoZwsjX87lhpZZhNnnsSsHVia7Imgkx6S6kBUznXJln1qbAHRvRwpFA/0?wx_fmt=gif\" data-copyright=\"0\" data-ratio=\"0.7857142857142857\" data-src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230615464_4.gif\" data-type=\"gif\" data-w=\"350\" /></p>\n<p>前湖人队著名球星约翰逊（右一）向习近平赠送印有习近平姓名拼音的洛杉矶湖人队纪念球衣</p>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section><img class=\"raw-image\" src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230616522_381.gif\" data-ratio=\"0.2988506\" data-src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230616522_381.gif\" data-type=\"gif\" data-w=\"435\" />&nbsp;</section>\n&nbsp;\n<section>\n<section>\n<p><strong>拳击</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>作为资深体育爱好者，习近平一直关心和重视中国体育事业发展。每遇到重大体育赛事，习近平时常到场看望运动健儿，为他们鼓劲加油。</p>\n<p>&nbsp;</p>\n<p>2014年8月，习近平到南京看望青奥会中国体育代表团 。在青奥村训练馆里，拳击手吕平、常园正在训练。习近平边看边点评说：&ldquo;<strong>你们的勾拳打得好，也很擅长直拳、摆拳。</strong><strong>我年轻时也练过拳击。</strong><strong>练拳击，抗击打能力、体能和场上控制力非常重要。</strong>&rdquo;</p>\n<p><span id=\"flash_1\"><img src=\"http://t.live.cntv.cn/cntvwebplay/cntvplayer/images/plug-in_bg.gif\" width=\"540\" height=\"400\" /></span></p>\n<div align=\"center\">\n<div class=\"flash_install\"><a href=\"http://www.adobe.com/go/getflashplayer_cn\"><img src=\"http://player.cntv.cn/flashplayer/logo/get_adobe_flash_player.png\" /></a>\n<p>请点此安装最新Flash</p>\n</div>\n</div>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section><strong>他还是这些运动的&ldquo;粉丝&rdquo;</strong>&nbsp;&nbsp; &nbsp; &nbsp;</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<p>&nbsp;</p>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n<section>\n<p>习近平喜爱的体育运动十分广泛，他说：&ldquo;这可以令我们精力充沛，并帮助我们更好地工作。&rdquo;</p>\n<p>&nbsp;</p>\n</section>\n</section>\n</section>\n</section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<p><strong>冰雪运动</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>习近平在接受俄罗斯电视台专访时表示，&ldquo;<strong>冰雪项目中，我爱看冰球、速滑、花样滑冰、雪地技巧。</strong><strong>特别是冰球，这项运动不仅需要个人力量和技巧，也需要团队配合和协作，是很好的运动</strong>&rdquo;。</p>\n<p>&nbsp;</p>\n<p>2017年4月，习近平对芬兰进行国事访问。在欢迎宴会开始前，习近平同芬兰总统尼尼斯托会见了刚刚参加完2017年世界花样滑冰锦标赛的中芬两国冰雪运动员代表，双方运动员向两国元首夫妇赠送了运动衣。也正是在那次访问中，两国元首商定将2019年确立为&ldquo;中芬冬季运动年&rdquo;。</p>\n<p><img src=\"https://p1.img.cctvpic.com/cportal/img/2019/8/8/1565231210404_397.gif\" /></p>\n<p><img class=\"rich_pages\" data-backh=\"260\" data-backw=\"350\" data-before-oversubscription-url=\"https://mmbiz.qpic.cn/mmbiz_gif/DTobibL9LCHI2ID06tNnrwVGfnhPSibqVgE4JP7eaBNspTDWV9Q3yBTEkuRcZd99OibGMSpdV2JUOkXDiblMF7IyTQ/0?wx_fmt=gif\" data-copyright=\"0\" data-ratio=\"0.7428571428571429\" data-src=\"https://mmbiz.qpic.cn/mmbiz_gif/DTobibL9LCHI2ID06tNnrwVGfnhPSibqVgE4JP7eaBNspTDWV9Q3yBTEkuRcZd99OibGMSpdV2JUOkXDiblMF7IyTQ/640?wx_fmt=gif\" data-type=\"gif\" data-w=\"350\" /></p>\n<p>2018年6月，习近平与俄罗斯总统普京在天津共同观看中俄青少年冰球友谊赛。习近平和普京在冰场同两国小队员逐一击拳问候。小球员们还分别向习近平和普京赠送了俄、中两队球衣。</p>\n<p>&nbsp;</p>\n<p><img class=\"\" src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230617305_369.jpeg\" data-src=\"http://p1.img.cctvpic.com/cportal/img/2019/8/8/1565230617305_369.jpeg\" data-type=\"jpeg\" data-ratio=\"0.67\" data-w=\"800\" /></p>\n<p>&nbsp;</p>\n<p>2017年2月，习近平在首都体育馆同刚刚结束亚冬会比赛回国的短道速滑队队员进行交流。习近平说，通过电视观看了大家的比赛，很精彩，祝贺你们取得好成绩。<strong>&ldquo;好风凭借力，送我上青云。</strong><strong>&rdquo;少年强则中国强，体育强则中国强。</strong></p>\n<p>&nbsp;</p>\n<p><img src=\"https://p1.img.cctvpic.com/cportal/img/2019/8/8/1565231264434_772.gif\" /></p>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section>\n<section>\n<p><strong>乒乓球、橄榄球、跳水&hellip;&hellip;</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n<section>\n<section>习近平在巴布亚新几内亚出访时，见证了两国文化交流的感人一幕。中国乒乓球学院巴布亚新几内亚训练中心的中国教练员、前世界冠军施之皓和奥运冠军张怡宁正在训练巴新运动员。习近平鼓励他们争创佳绩，做两国人民友好使者。&nbsp;\n<section></section>\n</section>\n</section>\n<p><span id=\"flash_2\"><img src=\"http://t.live.cntv.cn/cntvwebplay/cntvplayer/images/plug-in_bg.gif\" width=\"540\" height=\"400\" /></span></p>\n<div align=\"center\">\n<div class=\"flash_install\"><a href=\"http://www.adobe.com/go/getflashplayer_cn\"><img src=\"http://player.cntv.cn/flashplayer/logo/get_adobe_flash_player.png\" /></a>\n<p>请点此安装最新Flash</p>\n</div>\n</div>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section>\n<section>\n<p>&nbsp;</p>\n<p>出访新西兰期间，中新两国领导人共见记者后，新西兰英式橄榄球球星玛阿&middot;诺努（Ma\'a Nonu）赠送习近平一件球衣。习近平高兴地说，欢迎你到中国交流、教学、比赛，推动英式橄榄球在中国的发展。诺努兴奋地说：&ldquo;我现在就可以去！&rdquo;</p>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>&nbsp;</p>\n<p>在墨西哥演讲时，习近平表示：&ldquo;两年前，在中国教练指导下，墨西哥&lsquo;跳水公主&rsquo;埃斯皮诺萨和队友们包揽了2011年泛美运动会跳水项目全部8块金牌。墨西哥朋友们尝到了包揽金牌的滋味。祝愿中墨两国合作夺得更多&lsquo;金牌&rsquo;！&rdquo;</p>\n<p>&nbsp;</p>\n<p>习近平身体力行借体育传递友谊，助推国之交、民相亲，同时不遗余力促进奥林匹克运动发展。</p>\n<p>&nbsp;</p>\n<p>2017年1月，瑞士洛桑的国际奥林匹克博物馆，正在馆内参观的习近平受邀登上博物馆陈列的奥运冠军领奖台，站在上面举着双臂向大家致意。国际奥委会主席巴赫说，&ldquo;就推动奥林匹克运动而言，习近平主席是当之无愧的冠军&rdquo;。</p>\n<p>&nbsp;</p>\n<p><span id=\"flash_3\"><img src=\"http://t.live.cntv.cn/cntvwebplay/cntvplayer/images/plug-in_bg.gif\" width=\"540\" height=\"400\" /></span></p>\n<div align=\"center\">\n<div class=\"flash_install\"><a href=\"http://www.adobe.com/go/getflashplayer_cn\"><img src=\"http://player.cntv.cn/flashplayer/logo/get_adobe_flash_player.png\" /></a>\n<p>请点此安装最新Flash</p>\n</div>\n</div>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<p><strong>推动全民健身，他这样说&hellip;&hellip;</strong></p>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n<p>&nbsp;</p>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<section>\n<p>&nbsp;</p>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n<section>\n<p>&nbsp;</p>\n<p>全民健身运动的普及和参与国际体育合作的程度，也是一个国家现代化程度的重要标志。</p>\n<p>&mdash;&mdash;2019年1月31日，习近平会见国际奥委会主席巴赫时表示</p>\n</section>\n<section>\n<section></section>\n</section>\n<section>\n<p>&nbsp;</p>\n<p>体育承载着国家强盛、民族振兴的梦想。体育强则中国强，国运兴则体育兴。</p>\n<p>&mdash;&mdash;2017年8月27日，习近平会见全国群众体育先进单位、先进个人代表和全国体育系统先进集体、先进工作者代表以及在天津全运会群众比赛项目中获奖的运动员代表时的讲话</p>\n</section>\n<section>\n<section></section>\n</section>\n<section>\n<p>&nbsp;</p>\n<p>我们将以北京冬奥会为契机，推动群众体育和竞技体育全面平衡发展，推进全民健身事业，不断提升人民健康水平。</p>\n<p>&mdash;&mdash;2017年8月27日，习近平会见国际奥委会主席巴赫时表示</p>\n</section>\n<section>\n<section></section>\n</section>\n<section>\n<p>&nbsp;</p>\n<p>&ldquo;发展体育运动，增强人民体质&rdquo;是我国体育工作的根本任务。希望同志们充分认识体育对提高人民健康水平的积极意义，落实全民健身国家战略，普及全民健身运动，促进健康中国建设。</p>\n<p>&mdash;&mdash;2016年8月25日，习近平会见第31届奥林匹克运动会中国体育代表团全体成员时的讲话</p>\n</section>\n<section>\n<section></section>\n</section>\n<section>\n<p>&nbsp;</p>\n<p>北京举办冬奥会，将带动中国3亿多人参与冰雪运动。</p>\n<p>&mdash;&mdash;2015年1月14日，习近平会见国际奥协主席、亚奥理事会主席艾哈迈德亲王时表示</p>\n<section>\n<section>\n<section></section>\n</section>\n</section>\n<p>&nbsp;</p>\n<p data-spm-anchor-id=\"0.0.0.i3\">全民健身是全体人民增强体魄、健康生活的基础和保障，人民身体健康是全面建成小康社会的重要内涵，是每一个人成长和实现幸福生活的重要基础。</p>\n<p>&mdash;&mdash;2013年8月31日，习近平会见参加全国群众体育先进单位和先进个人表彰会、全国体育系统先进集体和先进工作者表彰会代表的讲话</p>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</section>\n</div>\n<div class=\"col_w320_fl\">\n<div class=\"model\">\n<div class=\"mbd\">\n<div class=\"relevance\" data-spm-anchor-id=\"0.0.0.i2\">编辑：高佳鑫</div>\n</div>\n</div>\n</div>', 'http://www.baidu.com/', '', '自由者', '2019-08-28 02:00:00', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-09 21:49:05', NULL, NULL);
INSERT INTO `cms_article` VALUES (2, 6, '百度2', '与商界共同努力走出经济困境', '<p data-spm-anchor-id=\"C73544894212.P9moqzeXHoOr.0.i2\"><strong>央视网消息</strong>（新闻联播）：香港特区行政长官林郑月娥今天（8月9日）下午主持召开跨界别会议，香港商会、零售、金融、银行等不同界别代表出席。</p>\n<p>　　会后，林郑月娥召开记者会表示，希望与商界继续交流，共同努力走出经济困境。她说，目前香港正处于内忧外患的境地。希望立即停止任何暴力行为，并呼吁各界放下分歧、减少对立，让香港社会尽快恢复正常秩序。</p>\n<p><img src=\"https://p1.img.cctvpic.com/photoworkspace/contentimg/2019/08/09/2019080921555413088.jpg\" alt=\" \" width=\"500\" /></p>\n<p data-spm-anchor-id=\"C73544894212.P9moqzeXHoOr.0.i3\">　　林郑月娥强调，特区政府会继续以真诚的态度聆听各界意见，希望将今后的工作做得更好。林郑月娥还宣布，将于8月13日提前恢复行政会议，并尽快制定措施以缓解民生和就业等问题。</p>', 'http://www.baidu.com/', '', '自由者', '2019-08-28 02:00:00', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-09 22:13:15', NULL, '2019-08-15 22:46:32');
INSERT INTO `cms_article` VALUES (3, 7, '百度3', 'ddd', '<p>ddddd</p>', 'http://www.baidu.com/', '', '自由者', '2019-08-28 02:00:00', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-11 23:00:12', NULL, '2019-08-12 11:59:47');
INSERT INTO `cms_article` VALUES (4, 7, '百度4', NULL, NULL, 'http://www.baidu.com/', NULL, '自由者', '2019-08-28 02:00:00', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-11 23:04:02', NULL, '2019-08-15 23:53:38');
INSERT INTO `cms_article` VALUES (5, 8, '百度5', NULL, NULL, 'http://www.baidu.com/', NULL, '自由者', '2019-08-28 18:52:08', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-11 23:18:26', NULL, '2019-08-12 11:59:47');
INSERT INTO `cms_article` VALUES (6, 8, '百度6', NULL, NULL, 'http://www.baidu.com/', NULL, '自由者', '2019-08-28 18:52:08', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-11 23:18:44', NULL, '2019-08-12 11:59:47');
INSERT INTO `cms_article` VALUES (7, 9, '百度7', NULL, NULL, 'http://www.baidu.com/', NULL, '自由者', '2019-08-28 18:52:08', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-11 23:25:20', NULL, '2019-08-20 23:34:56');
INSERT INTO `cms_article` VALUES (8, 9, '百度8', NULL, NULL, 'http://www.baidu.com/', NULL, '自由者', '2019-08-28 18:52:08', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-12 18:00:22', NULL, NULL);
INSERT INTO `cms_article` VALUES (9, 9, '百度9', NULL, NULL, 'http://www.baidu.com/', NULL, '自由者', '2019-08-28 18:52:08', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-12 18:00:31', NULL, NULL);
INSERT INTO `cms_article` VALUES (10, 10, '百度10', NULL, NULL, 'http://www.baidu.com/', NULL, '自由者', '2019-08-28 18:52:08', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-13 10:22:01', NULL, '2019-08-15 23:53:38');
INSERT INTO `cms_article` VALUES (11, 10, '百度11', '', '<p data-role=\"original-title\">原标题：一个理念，让这个小山村实现绿色跨越123</p>\n<p><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190815/a1d5d7f2637149e19fbdf6481b725a14.gif\" /></p>\n<p>在浙江省湖州市安吉县余村村口的一块石碑上，镌刻着&ldquo;绿水青山就是金山银山&rdquo;10个大字，这是所有外来游客都会参观的地方。</p>\n<p>所有余村人，都能讲出这个石刻背后的故事。</p>\n<p>上世纪90年代末，余村依靠炸山开矿和经营水泥厂，一度成为&ldquo;安吉首富村&rdquo;，村集体年收入达300万元。老百姓钱包鼓起来了，但生态环境却遭到了破坏。</p>\n<p>在村民汪苗青的记忆里，因为开矿，村里常年灰尘笼罩。村民不敢开窗，无法晾衣，就连百年银杏也结不出果。</p>\n<p>痛定思痛，余村决定关停污染企业，向绿色发展转型。但关了水泥厂和矿山，几乎断了村里的财路，很多村民失业，村集体年收入最少时降到21万元。</p>\n<p>回头走老路，还是坚定绿色发展，余村人站在了十字路口。一位特殊&ldquo;访客&rdquo;为彷徨中的余村人注入了&ldquo;强心剂&rdquo;，也为余村未来发展指明方向。</p>\n<p>那是2005年8月15日，时任浙江省委书记的习近平来到余村调研。根据行程安排，他将在村里停留20分钟，只听汇报，不作讲话。</p>\n<p>但听到时任村党支部书记鲍新民汇报说，余村关停了污染环境的矿山，开始搞生态旅游，打算让村民借景生财时，习近平十分高兴，肯定他们的做法，一说就是20分钟。</p>\n<p>习近平说：&ldquo;一定不要再想着走老路，还这样迷恋着过去的那种发展模式。所以，刚才你们讲了，下决心停掉一些矿山，这个都是高明之举。绿水青山就是金山银山。我们过去讲既要绿水青山，又要金山银山，实际上绿水青山就是金山银山。&rdquo;</p>\n<p>9天后，习近平以笔名&ldquo;哲欣&rdquo;在 《浙江日报》 的&ldquo;之江新语&rdquo;栏目发表了题为 《绿水青山也是金山银山》的评论。评论指出：&ldquo;如果能够把这些生态环境优势转化为生态农业、生态工业、生态旅游等生态经济的优势，那么绿水青山也就变成了金山银山。绿水青山可带来金山银山，但金山银山却买不到绿水青山。绿水青山与金山银山既会产生矛盾，又可辩证统一。&rdquo;</p>\n<p><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190815/897ca9efd9544c4e9580761d6700fb31.jpeg\" /></p>\n<p>俯瞰浙江省安吉县天荒坪镇余村</p>\n<p>&ldquo;是&lsquo;两山&rsquo;理念，让我们找到了重生的路径。&rdquo;现任村党支部书记潘文革说。10多年来，余村人点绿成&ldquo;金&rdquo;，让绿色成为发展中最耀眼、最动人的底色。余村也成为中国生态文明建设和绿色发展的一个缩影。</p>\n<p>不论在地方还是在中央工作，环境保护问题、生态文明建设从未远离过习近平的视野。</p>\n<p>在正定，他将&ldquo;宁肯不要钱，也不要污染，严格防止污染搬家、污染下乡&rdquo;列入《正定县经济、技术、社会发展总体规划》，亲自监督整改农村&ldquo;猪圈连茅厕&rdquo;，设立公共厕所，修建生活垃圾池。</p>\n<p>在福建，他五下长汀，走山村、访农户，摸实情、谋对策，支持长汀水土流失治理。</p>\n<p>在浙江，他大力推动&ldquo;生态省&rdquo;建设，提出&ldquo;既要GDP，又要绿色GDP&rdquo;，让&ldquo;千村示范、万村整治&rdquo;绽放之江。</p>\n<p>&hellip;&hellip;</p>\n<p>生态文明思想，一脉相承；生态文明建设实践，一以贯之。</p>\n<p>2012年12月，习近平担任总书记后首赴地方考察就谆谆告诫：&ldquo;我们在生态环境方面欠账太多了，如果不从现在起就把这项工作紧紧抓起来，将来会付出更大的代价。&rdquo;</p>\n<p>2014年1月，习近平来到内蒙古阿尔山市伊尔施镇看望生活在林业棚户区的群众。当听到阿尔山林区已全面停伐，正处在艰难转型期时，他深情地说：&ldquo;历史有它的阶段性，当时砍木头是为国家做贡献，现在种树看林子也是为国家做贡献。&rdquo;</p>\n<p><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190815/5a5e336409f748ca88a9e37cf08f4477.jpeg\" /></p>\n<p>云南省大理白族自治州大理市湾桥镇古生村与当地的库塘污水处理系统（2017年9月11日摄）。</p>\n<p>2015年1月，习近平来到云南大理洱海畔的湾桥镇古生村了解洱海生态保护情况。他走上木栈道，同当地干部合影后说：&ldquo;立此存照，过几年再来，希望水更干净清澈。&rdquo;他叮嘱，一定要把洱海保护好。</p>\n<p>伴随着习近平的足迹，&ldquo;绿水青山就是金山银山&rdquo;的理念在祖国大地生根发芽，枝繁叶茂。</p>\n<p>党的十八大以来，生态文明顶层设计和制度体系建设不断健全，污染治理加快推进，绿色发展成效明显，生态环境持续改善，森林覆盖面积和空气优良指数显著提高，人民获得感不断增强，一幅美丽中国新画卷正徐徐展开。</p>\n<p>&ldquo;让群众望得见山、看得见水、记得住乡愁，让自然生态美景永驻人间。&rdquo;</p>\n<p>这是习近平心中的愿景，也是对美丽中国的殷殷瞩望。</p>\n<p>一个小村庄，见证着一个重要思想的诞生，也辉映着美丽中国的未来。（中央广播电视总台央视网）</p>', 'http://www.baidu.com/', '', '自由者', '2019-09-18 18:17:58', 2, 0, 0, 0, 1, 0, 1, 1, '2019-08-15 23:36:26', NULL, '2019-08-17 08:07:27');
INSERT INTO `cms_article` VALUES (12, 10, '百度12', '', '<p>【环球网报道 记者 张丽媛】香港反对派示威者连续数周发起非法集会，期间更做出围攻警署、袭击警察、殴打内地旅客等暴行。昨晚(14日晚)，非法示威者又出新花样，前往深水埗警署外进行所谓的&ldquo;激光烧衣积阴德祈福晚会&rdquo;活动，警方警告多次仍不离去。对此，香港警方今天(15日)下午召开记者会说明情况。</p>\n<p>据香港《星岛日报》15日报道，深水埗警区指挥官何启轩在记者会上表示，昨晚有多名市民聚集在深水埗警署外，并以激光灯照向警署及警员。直至晚上9时许，已有约700人，道路阻塞。其后，有人在内街以弹珠及激光灯攻击警署及警员，并留意到附近有人手持砖头及水喉通，警方在警告无效后作出驱散。</p>\n<p>何启轩说，事后还有人在鸭寮街继续以弹珠攻击警员，警员因此再施放催泪弹，并在晚上22时30分完成驱散。他表示，昨晚是深水埗警署在近10日第4次被包围及袭击。</p>\n<p>香港警察公共关系科高级警司(媒体联络及传讯)江永祥表示，警方在昨日的行动中共拘捕17人，包括15男及2女，年龄介乎15至61岁，他们涉嫌非法集结、刑事毁坏、纵火等。</p>\n<p>此外，报道称，据香港警方透露，自6月9日示威活动开始，至昨日为止，警方共拘捕748人。</p>', 'http://www.baidu.com/', '', '自由者', '2019-09-18 18:17:54', 2, 0, 0, 0, 1, 0, 1, 1, '2019-08-15 23:40:06', NULL, '2019-08-15 23:53:38');
INSERT INTO `cms_article` VALUES (13, 10, '百度13', '', '<p data-role=\"original-title\">原标题：超强台风&ldquo;利奇马&rdquo;打破多项历史记录！</p>\n<p>今天，</p>\n<p>中国气象局针对</p>\n<p>超强台风&ldquo;利奇马&rdquo;的影响评估出炉！</p>\n<p>它是<strong>今年以来登陆我国最强的台风</strong>。</p>\n<p><strong>No.1</strong></p>\n<p><strong>登陆强度历史上全国排名第五、浙江第三</strong></p>\n<p>&ldquo;利奇马&rdquo;在1949年以来登陆我国大陆的台风中<strong>强度排名第五</strong>，在登陆浙江的台风中<strong>强度排名第三</strong>。</p>\n<p>福建、浙江、上海、江苏、安徽、山东及河北、天津、辽宁等地沿海地区均出现8级以上阵风，<strong>风速</strong>最大出现在浙江温岭三蒜岛61.4米/秒，为<strong>浙江历史第二位</strong>（第一位是2006年超强台风&ldquo;桑美&rdquo;，68米/秒）。</p>\n<p align=\"center\"><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190815/4dd29d33abdb46e1b0753647d8c0d1a6.jpeg\" /></p>\n<p><strong>1949年以来登陆我国大陆最强台风排名</strong></p>\n<p><strong>No.2</strong></p>\n<p><strong>降雨强度历史上山东排名第一、浙江第二。</strong></p>\n<p>受&ldquo;利奇马&rdquo;影响，浙江、山东、江苏等地均出现极端强降雨天气。</p>\n<p align=\"center\"><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190815/99491f44ea96430798818655f0438118.jpeg\" /></p>\n<p><strong>受台风影响，山东枣庄普降大到暴雨，城区内多处路段积水严重。图/新华网</strong></p>\n<p>浙江、山东等地共35个气象观测站日降雨量突破当地8月历史极值，其中19个站点突破当地日降雨量的历史极值。</p>\n<p>山东平均降雨量<strong>158毫米</strong>，超过了2018年台风&ldquo;温比亚&rdquo;带来的降雨（全省平均135.5毫米），为山东有记录以来的过程降雨量最大值。</p>\n<p>浙江全省平均降雨量<strong>165毫米</strong>，其中，临海括苍山过程雨量达831毫米，为登陆浙江台风第二位（第一位乐清砩头916毫米，0414号台风&ldquo;云娜&rdquo;所致）。</p>\n<p align=\"center\"><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190815/3c61d7f9995b41f08099e9d57c4be1e7.jpeg\" /></p>\n<p><strong>&ldquo;利奇马&rdquo;降雨量实况图（8月8日-14日）</strong></p>\n<p><strong>No.3</strong></p>\n<p><strong>北上影响范围广，陆地滞留时间历史上全国排名第六，浙江排名第一。</strong></p>\n<p>&ldquo;利奇马&rdquo;登陆后移动缓慢，在陆地滞留时间长达<strong>44个小时</strong>，为1949年以来<strong>全国第六位</strong>。</p>\n<p>其中，台风在浙江滞留达<strong>20个小时</strong>，为1949年以来滞留浙江时间最长的超强台风。台风先后影响了福建、浙江、上海、江苏、安徽、山东、河北、河南、天津、辽宁、吉林、黑龙江等12个省（市）。</p>\n<p align=\"center\"><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190815/d61703cd9d324b918790fc7a1192271b.jpeg\" /></p>\n<p><strong>受台风影响，浙江省台州市仙居县遭遇强降雨，多地出现洪涝灾害。图/中新网</strong></p>\n<p>台风暴雨100毫米以上覆盖的国土面积为<strong>36.1万平方公里</strong>，250毫米以上覆盖的国土面积为<strong>6.6万平方公里</strong>。</p>', 'http://www.baidu.com/', '', '自由者', '2019-08-28 18:52:08', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-15 23:42:54', NULL, '2019-08-20 23:32:20');
INSERT INTO `cms_article` VALUES (14, 10, '百度14', '', '<p><strong>中国外交部驻港特派员谢锋：香港面临回归以来最严峻局面 当务之急止暴制乱</strong></p>\n<p>中新社香港8月15日电 中国外交部驻香港特派员公署特派员谢锋15日表示，当前香港面临回归22年来最危险、最严峻的局面，当务之急和压倒一切的任务，就是止暴制乱、恢复秩序。</p>\n<p><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190815/3fd07cc176614f758c38735d8ab3fc82.jpeg\" /></p>\n<p>8月13日下午，大批示威者以机场手推车等堵塞香港国际机场1号客运大楼旅客登机行段及保安闸门，旅客未能经1号客运大楼前往离境大堂。机场管理局宣布，所有航班登记服务暂停。 中新社记者 麦尚旻 摄</p>\n<p>2019年国际法论坛当日在香港举行。谢锋发表主旨演讲时指出，当前香港事态的本质绝非所谓的人权、自由与民主问题，而是一些极端暴力分子裹挟不明真相者以反修例为幌子不断升级暴力犯罪活动，严重践踏法治和社会秩序、严重威胁香港市民安全、严重破坏香港繁荣稳定；是香港反对派和极端暴力分子企图以暴力等非法手段颠覆特区合法政府、挑战中央政府权威、动摇香港&ldquo;一国两制&rdquo;的宪制根基；是外国干预势力践踏国际法和国际关系基本准则，粗暴干涉香港事务和中国内政、破坏香港繁荣稳定、损害中国主权与安全，企图把香港作为一枚棋子，牵制和遏制中华民族伟大复兴。</p>\n<p>谢锋强调，中央政府坚定支持林郑月娥领导的特区政府依法施政，坚定支持香港警队和司法机构果断执法、严正司法，坚决支持绝大多数香港同胞反暴力、护法治、撑警队的正义之举。任何践踏香港法治、破坏香港繁荣稳定、冲击&ldquo;一国两制&rdquo;的暴力行径，必将遭到法律的严惩。</p>\n<p>他表示，任何外国政府、组织或个人干预香港事务的行径，必将遭到包括香港同胞在内全体中国人民的坚决回击。任何阻挠中华民族复兴的企图，注定将遭到可耻的失败。相信香港一定能够克服眼前的困难，拂去一时的阴霾，&ldquo;东方明珠&rdquo;必将闪耀更加璀璨的光芒。</p>\n<p>在演讲中，谢锋又从法理角度系统阐释对不干涉内政原则、《中英联合声明》以及&ldquo;一国两制&rdquo;原则的理解，正本清源，呼吁各方共同捍卫法治。</p>\n<p>谢锋表示，主权平等和不干涉内政是现代国际法基本原则和国际关系基本准则，为包括《联合国宪章》在内的多部国际法律文件所确认。外交和领事人员是派出国在接受国的官方代表，根据有关国际法有义务不干涉接受国内政。近期个别国家及其驻港领事人员插手香港事务、干涉中国内政的行为严重违反国际法。</p>\n<p>他说，个别外国拿《中英联合声明》说事、插手香港事务是完全错误的。《联合声明》是中英间关于中国收回香港及有关过渡期安排的重要文件，其中没有任何条款赋予英方干预回归后香港事务的权利，没有任何条款规定英方在香港回归后对香港承担任何责任。而且涉及英方的条款均已履行完毕，英方无权再根据《联合声明》对香港提出新的权利或者责任主张。</p>\n<p>他又说，全面准确理解&ldquo;一国两制&rdquo;，必须认清中国《宪法》是香港特区的&ldquo;根&rdquo;和&ldquo;源&rdquo;，香港《基本法》是&ldquo;一国两制&rdquo;的具体化和法制化，《宪法》和《基本法》共同构成香港特区的宪制基础。要把握好&ldquo;一国&rdquo;和&ldquo;两制&rdquo;的关系。&ldquo;一国&rdquo;是&ldquo;两制&rdquo;的基础与前提，如果&ldquo;一国&rdquo;原则受到冲击，&ldquo;两制&rdquo;就无从谈起。</p>\n<p>出席论坛的还包括香港特区行政长官林郑月娥、&ldquo;亚洲&mdash;非洲法律协商组织&rdquo;秘书长肯尼迪&middot;加斯顿、亚洲国际法律研究院主席梁定邦、中国国际法学会会长黄进等300余位嘉宾。(完)</p>', 'http://www.baidu.com/', '', '自由者', '2019-08-28 18:52:08', 0, 0, 0, 0, 0, 0, 1, 1, '2019-08-15 23:47:30', NULL, '2019-08-20 23:22:01');

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `channel_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '频道ID',
  `category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父分类ID',
  `parent_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点ID集合（多个ID以\',\'分割）',
  `depth` int(11) NULL DEFAULT 0 COMMENT '树形深度',
  `redirect_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转地址',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '类目排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '启用状态（0 未启用，1 已启用，默认 1）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类详情',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unique_category_name_tenant_id_channel_id`(`channel_id`, `category_name`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章类目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_category
-- ----------------------------
INSERT INTO `cms_category` VALUES (1, 1, '一级', 0, NULL, NULL, NULL, NULL, NULL, 1, '一级', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (2, 1, '二级', 1, '1', NULL, NULL, NULL, NULL, 1, '二级', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (3, 1, '三级', 2, '1,2', NULL, NULL, NULL, NULL, 1, '三级', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (4, 1, '二级-1', 0, '', NULL, NULL, NULL, NULL, 1, '二级-1', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (5, 1, '二级-2', 0, '', NULL, NULL, NULL, NULL, 1, '二级-2', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (6, 6, '便携生活', 0, NULL, NULL, NULL, NULL, NULL, 1, '便携生活', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (7, 6, '购 物', 0, NULL, NULL, NULL, NULL, NULL, 1, '购 物', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (8, 6, '直 播', 0, NULL, NULL, NULL, NULL, NULL, 1, '直 播', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (9, 6, '财经金融', 0, NULL, NULL, NULL, NULL, NULL, 1, '财经金融', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (10, 6, '社 区', 0, NULL, NULL, NULL, NULL, NULL, 1, '社 区', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (11, 6, '常用', 7, '7', NULL, NULL, NULL, NULL, 1, '常用', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (12, 6, '秒杀', 7, '7', NULL, NULL, NULL, NULL, 1, '秒杀', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (13, 6, '团购', 7, '7', NULL, NULL, NULL, NULL, 1, '团购', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (14, 6, '历史价格', 7, '7', NULL, NULL, NULL, NULL, 1, '历史价格', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (15, 7, '最新影视', 0, NULL, NULL, NULL, NULL, NULL, 1, '最新影视', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (16, 7, '高清蓝光', 0, NULL, NULL, NULL, NULL, NULL, 1, '高清蓝光', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (17, 7, '在线电影', 0, NULL, NULL, NULL, NULL, NULL, 1, '在线电影', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (18, 7, 'BT影视', 0, NULL, NULL, NULL, NULL, NULL, 1, 'BT影视', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (19, 7, '美 剧', 0, NULL, NULL, NULL, NULL, NULL, 1, '美 剧', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (20, 7, '日韩剧', 0, NULL, NULL, NULL, NULL, NULL, 1, '日韩剧', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (21, 7, '动 漫', 0, NULL, NULL, NULL, NULL, NULL, 1, '动 漫', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (22, 7, '下载', 19, '19', NULL, NULL, NULL, NULL, 1, '下载', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (23, 7, '视频网站', 19, '19', NULL, NULL, NULL, NULL, 1, '视频网站', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (24, 7, '字幕组', 19, '19', NULL, NULL, NULL, NULL, 1, '字幕组', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (25, 7, '韩剧', 20, '20', NULL, NULL, NULL, NULL, 1, '韩剧', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (26, 7, '日剧', 20, '20', NULL, NULL, NULL, NULL, 1, '日剧', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (27, 15, '影视', 0, NULL, NULL, NULL, NULL, NULL, 1, '影视', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (28, 15, '关于', 0, NULL, NULL, NULL, NULL, NULL, 1, '关于', 1, NULL, NULL);
INSERT INTO `cms_category` VALUES (29, 15, '留言', 1, '1', NULL, NULL, NULL, NULL, 0, '留言', 1, NULL, NULL);

-- ----------------------------
-- Table structure for cms_channel
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `channel_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '频道名称',
  `channel_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频道编号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频道图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '频道排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '启用状态（0 未启用，1 已启用，默认 1）',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_channel_name_tenant_id`(`channel_name`, `tenant_id`) USING BTREE,
  INDEX `key_channel_name`(`channel_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '频道' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_channel
-- ----------------------------
INSERT INTO `cms_channel` VALUES (6, '生活休闲', 'shenghuoxiuxian', '生活休闲', 'icon-star-empty', 6, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (7, '影视欣赏', 'yingshixinshang', '影视欣赏', 'icon-film', 2, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (8, '资源工具', 'ziyuangongju', '资源工具', 'icon-search', 3, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (9, ' 软件工具', ' ruanjiangongju', ' 软件工具', 'icon-save', 4, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (10, '模板素材', 'mobansucai', '模板素材', 'icon-money', 5, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (11, '读物', 'duwu', '读物', 'icon-book', 6, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (12, '音乐', 'yinle', '音乐', 'icon-music', 7, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (13, '学习天堂', 'xuexitiantang', '学习天堂', 'icon-pencil', 8, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (14, '导航先锋', 'daohangxianfeng', '导航先锋', 'icon-home', 9, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (15, '自定义导航', 'zidingyidaohang', '自定义导航', 'icon-home', 0, 1, 1, NULL, NULL);
INSERT INTO `cms_channel` VALUES (19, '测试频道', 'ceshipindao', '测试频道', NULL, 0, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(2, 0) NULL DEFAULT NULL,
  `amount` int(255) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for platform_account
-- ----------------------------
DROP TABLE IF EXISTS `platform_account`;
CREATE TABLE `platform_account`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台账户',
  `secret_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台密钥',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_account
-- ----------------------------
INSERT INTO `platform_account` VALUES (1, 'admin', '9f86433e539953bcfd99747c70648590');

-- ----------------------------
-- Table structure for platform_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `platform_dictionary`;
CREATE TABLE `platform_dictionary`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据值',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `sort` int(100) NULL DEFAULT NULL COMMENT '字典排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0 停用， 1 启用， 默认 1）',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` bigint(20) NULL DEFAULT 1 COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_type_index`(`code`, `value`) USING BTREE,
  INDEX `index_type`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_dictionary
-- ----------------------------
INSERT INTO `platform_dictionary` VALUES (1, 'DELETE', '1', '是', 1, 1, '是否删除', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (2, 'DELETE', '0', '否', 2, 1, '是否删除', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (3, 'SEX', '1', '男', 1, 1, '性别', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (4, 'SEX', '2', '女', 2, 1, '性别', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (5, 'SEX', '3', '保密', 3, 1, '性别', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (6, 'ACTIVATE', '1', '已启用', 2, 1, '是否激活', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (7, 'ACTIVATE', '0', '未启用', 1, 1, '是否激活', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (8, 'ADMIN', '1', '是', 1, 1, '是否超管', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (9, 'ADMIN', '0', '否', 2, 1, '是否超管', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (13, 'TEST', '1', '是', 1, 0, '测试', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (15, 'WORKING', '1', '已入职', 1, 1, '是否在职', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (16, 'WORKING', '0', '未入职', 2, 1, '是否在职', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (17, 'PERMTYPE', '2', '按钮', 2, 1, '权限类型', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (18, 'PERMTYPE', '1', '菜单', 1, 1, '权限类型', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (19, 'STATUS', '1', '已启用', 1, 1, '数据状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (20, 'STATUS', '0', '未启用', 2, 1, '数据状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (21, 'STYLE', '0', 'info', 1, 1, '信息按钮', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (22, 'STYLE', '1', 'success', 2, 1, '成功按钮', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (23, 'STYLE', '2', 'primary', 3, 1, '主要按钮', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (24, 'STYLE', '3', 'warning', 4, 1, '警告按钮', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (25, 'STYLE', '4', 'danger', 4, 1, '危险按钮', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (26, 'ARTICLE_STATUS', '0', '草稿', 1, 1, '文章状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (27, 'ARTICLE_STATUS', '1', '审核', 2, 1, '文章状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (28, 'ARTICLE_STATUS', '2', '发布', 3, 1, '文章状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (29, 'ARTICLE_TOP', '0', '下顶', 1, 1, '置顶状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (30, 'ARTICLE_TOP', '1', '置顶', 2, 1, '置顶状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (31, 'ACCOUNT_TYPE', '1', '订阅号', 1, 1, '公众号类型', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (32, 'ACCOUNT_TYPE', '2', '服务号', 2, 1, '公众号类型', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (33, 'AUTH_STATUS', '0', '未认证', 1, 1, '认证状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (34, 'AUTH_STATUS', '1', '已认证', 2, 1, '认证状态', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (35, 'AUTH_TYPE', '1', '手动授权', 1, 1, '授权方式', 1, NULL);
INSERT INTO `platform_dictionary` VALUES (36, 'AUTH_TYPE', '2', '扫描授权', 2, 1, '授权方式', 1, NULL);

-- ----------------------------
-- Table structure for platform_parameter
-- ----------------------------
DROP TABLE IF EXISTS `platform_parameter`;
CREATE TABLE `platform_parameter`  (
  `param_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数名称',
  `param_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数值',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数描述',
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '平台参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_parameter
-- ----------------------------
INSERT INTO `platform_parameter` VALUES (2, 'loginUrl', 'http://localhost:9528/login', NULL);

-- ----------------------------
-- Table structure for platform_permission
-- ----------------------------
DROP TABLE IF EXISTS `platform_permission`;
CREATE TABLE `platform_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父级节点ID',
  `p_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点ID集合',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源URL地址',
  `permission_str` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限字符串',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码（对应页面路由）',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限类型（枚举，菜单：MENU，方法：FUN）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '访问方式 GET POST PUT DELETE PATCH',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `disabled` tinyint(1) NULL DEFAULT NULL COMMENT '权限是否允许被选择',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用（枚举 已启用：1， 未启用：0，默认：1）',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_permission
-- ----------------------------
INSERT INTO `platform_permission` VALUES (1, '系统设置', 0, NULL, NULL, 'sys_setting', NULL, '1', '', 1, '', 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (2, '用户管理', 1, '1', NULL, 'user_manage', NULL, '1', '', 2, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (3, '添加用户', 2, '1,2', '/sys/user/create', 'user:create', NULL, '2', 'POST', 3, NULL, 0, NULL, 1, NULL, NULL, NULL, 1, '管理员', '2018-12-11 14:30:40');
INSERT INTO `platform_permission` VALUES (4, '修改用户', 2, '1,2', '/sys/user/update/*', 'user:update', NULL, '2', 'PUT', 4, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (5, '删除用户', 2, '1,2', '/sys/user/delete/*', 'user:delete', NULL, '2', 'DELETE', 5, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (6, '查看用户', 2, '1,2', '/sys/user/load/*', 'user:select', NULL, '2', 'GET', 6, NULL, 0, NULL, 1, NULL, NULL, NULL, 1, 'admin', '2019-03-18 15:50:56');
INSERT INTO `platform_permission` VALUES (7, '租户参数', 1, '1', '/sys/parameter', 'param_setting', NULL, '1', '', 7, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (8, '修改参数', 7, '1,7', '/sys/parameter/save', 'parameter:save', NULL, '2', '', 8, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (9, '查询参数', 7, '1,7', '/sys/parameter/list', 'parameter:list', NULL, '2', '', 9, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (13, '权限管理', 1, '1', NULL, 'permission_manage', NULL, '1', '', NULL, NULL, 1, NULL, 1, 1, 'string', '2018-11-17 14:03:24', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (14, '添加权限', 13, '1', '/sys/permission/create', 'permission:create', NULL, '2', '', NULL, NULL, 1, NULL, 1, 1, 'string', '2018-11-17 14:03:24', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (15, '修改权限', 13, '1,13', '/sys/permission/update/*', 'permission:update', NULL, '2', '', NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (16, '删除权限', 13, '1,13', '/sys/permission/delete/*', 'permission:delete', NULL, '2', '', NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (17, '查看权限', 13, '1,13', '/sys/permission/load/*', 'permission:select', NULL, '2', '', NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (62, '角色管理', 1, '1', '', 'role_manage', NULL, '1', '', NULL, NULL, 0, NULL, 1, 1, '管理员', '2018-12-13 11:56:23', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (63, '添加角色', 62, '1,62', '/sys/role/create', 'role:create', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, '管理员', '2018-12-13 11:57:53', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (64, '修改角色', 62, '1,62', '/sys/role/update/*', 'role:update', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, '管理员', '2018-12-13 11:58:41', 1, '管理员', '2018-12-13 11:58:54');
INSERT INTO `platform_permission` VALUES (65, '删除角色', 62, '1,62', '/sys/role/delete/*', 'role:delete', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, '管理员', '2018-12-13 11:59:39', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (66, '查看角色', 62, '1,62', '/sys/role/load/*', 'role:select', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, '管理员', '2018-12-13 11:59:39', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (71, '设置权限', 62, '1,62', '/sys/role-permission-rel/settingRolePerm', 'role:permission:setting', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, '管理员', '2018-12-13 11:59:39', 1, 'admin', '2019-01-27 21:14:35');
INSERT INTO `platform_permission` VALUES (72, '在线用户', 1, '1', '', 'onlineuser_manage', NULL, '1', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2018-12-18 13:40:38', 1, 'admin', '2018-12-18 13:44:04');
INSERT INTO `platform_permission` VALUES (73, '强制退出', 72, '1,72', '/sys/online/fogout', 'onlineuser:fogout', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2018-12-18 13:42:51', 1, 'admin', '2018-12-18 13:43:27');
INSERT INTO `platform_permission` VALUES (74, '树形表格', 1, '1', '', 'table_tree', NULL, '1', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2018-12-19 15:07:49', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (79, '导出用户', 2, '1,2', '/sys/user/export', 'user:export', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-01-25 15:56:02', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (80, '导入用户', 2, '1,2', '/sys/user/import', 'user:import', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-01-25 15:56:56', 1, 'admin', '2019-01-25 15:57:19');
INSERT INTO `platform_permission` VALUES (81, '字典管理', 1, '1', '', 'dict_manage', NULL, '1', '', NULL, NULL, 1, NULL, 1, 1, 'admin', '2019-03-18 15:49:50', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (82, '添加字典', 81, '1,81', '/sys/dictionary/create', 'dictionary:create', NULL, '2', '', NULL, NULL, 1, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (83, '修改字典', 81, '1,81', '/sys/dictionary/update/*', 'dictionary:update', NULL, '2', '', NULL, NULL, 1, NULL, 1, 1, 'admin', '2019-03-18 17:10:19', 1, 'admin', '2019-03-18 17:11:06');
INSERT INTO `platform_permission` VALUES (84, '删除字典', 81, '1,81', '/sys/dictionary/delete/*', 'dictionary:delete', NULL, '2', '', NULL, NULL, 1, NULL, 1, 1, 'admin', '2019-03-18 18:59:28', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (85, '任务管理', 1, '1', NULL, 'job_manage', NULL, '1', '', 2, NULL, 0, NULL, 1, 1, 'admin', '2019-06-18 16:11:01', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (86, '添加任务', 85, '1,85', '/tenant/schedule/create', 'job:create', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (87, '批量删除', 85, '1,85', '/tenant/schedule/delete/*', 'job:delete', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (88, '批量暂停', 85, '1,85', '/tenant/schedule/pause', 'job:pause', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (89, '批量恢复', 85, '1,85', '/tenant/schedule/resume', 'job:resume', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (90, '批量立即实行', 85, '1,85', '/tenant/schedule/run', 'job:run', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (91, '重置任务', 85, '1,85', '/tenant/schedule/reset', 'job:reset', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (92, '任务日志', 1, '1', NULL, 'joblog_manage', NULL, '1', '', 2, NULL, 0, NULL, 1, 1, 'admin', '2019-06-18 16:11:01', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (93, 'CMS系统', 0, '', NULL, 'cms_manage', NULL, '1', '', 2, NULL, 0, NULL, 1, 1, 'admin', '2019-06-18 16:11:01', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (94, '文章管理', 93, '93', NULL, 'article_manage', NULL, '1', '', 2, NULL, 0, NULL, 1, 1, 'admin', '2019-06-18 16:11:01', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (95, '添加文章', 94, '93,94', '/cms/article/create', 'article:create', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (96, '修改文章', 94, '93,94', '/cms/article/update/*', 'article:update', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (97, '删除文章', 94, '93,94', '/cms/article/delete/*', 'article:delete', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (98, '分类管理', 93, '93', NULL, 'category_manage', NULL, '1', '', 2, NULL, 0, NULL, 1, 1, 'admin', '2019-06-18 16:11:01', NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (99, '添加分类', 98, '93,98', '/cms/category/create', 'category:create', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (100, '修改文章', 98, '93,98', '/cms/category/update/*', 'category:update', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (101, '删除文章', 98, '93,98', '/cms/category/delete/*', 'category:delete', NULL, '2', '', NULL, NULL, 0, NULL, 1, 1, 'admin', '2019-03-18 17:08:37', 1, 'admin', '2019-03-18 17:10:48');
INSERT INTO `platform_permission` VALUES (102, '视频管理', 0, NULL, '', 'video_manage', NULL, '1', '', 3, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (103, '视频资源', 102, '102', '', 'zy_manage', NULL, '1', '', NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (104, '视频解析', 102, '102', '', 'jx_manage', NULL, '1', '', NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (105, '添加视频资源', 103, '102,103', '/zy/video-zy/create', 'video:zy:create', NULL, '2', '', NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (106, '修改视频资源', 103, '102,103', '/zy/video-zy/update', 'video:zy:update', NULL, '2', '', NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (107, '删除视频资源', 103, '102,103', '/zy/video-zy/delete/*', 'video:zy:delete', NULL, '2', '', NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (108, '平台参数', 1, '1', '', 'parameter_manage', NULL, '1', '', NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (109, '添加参数', 108, '108', '/platform/parameter/create', 'platform:parameter:create', NULL, '2', '', NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (110, '修改参数', 108, '108', '/platform/parameter/update/*/*', 'platform:parameter:update', NULL, '2', '', NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (111, '删除参数', 108, '108', '/platform/parameter/deleteBatch', '/platform:parameter:deleteBatch', NULL, '2', '', NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (112, '微信管理', 0, NULL, '', 'wx_manage', NULL, '1', '', 4, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (113, '公众号管理', 112, '112', '', 'gzh_manage', NULL, '1', '', 1, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (114, '粉丝管理', 112, '112', '', 'gzh_user_manage', NULL, '1', '', 1, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `platform_permission` VALUES (115, '标签管理', 112, '112', '', 'gzh_user_tag_manage', NULL, '1', '', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for platform_tenant
-- ----------------------------
DROP TABLE IF EXISTS `platform_tenant`;
CREATE TABLE `platform_tenant`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户编号',
  `tenant_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定制化访问地址',
  `tenant_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'logo',
  `secret_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密钥',
  `tenant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用（枚举 已启用：1， 未启用：0，默认：1）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_tenant_code`(`tenant_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台租户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_tenant
-- ----------------------------
INSERT INTO `platform_tenant` VALUES (1, 'GS0001', 'http://platform.aisys.top/api/to/gs0001', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', NULL, '长征集团', 1);
INSERT INTO `platform_tenant` VALUES (2, 'GS0002', 'http://platform.aisys.top/api/to/gs0002', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', NULL, '黑马集团', 1);
INSERT INTO `platform_tenant` VALUES (3, 'GS0003', 'http://platform.aisys.top/api/to/gs0003', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', NULL, '上汽集团', 1);
INSERT INTO `platform_tenant` VALUES (4, 'GS0004', 'http://platform.aisys.top/api/to/gs0004', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', NULL, '远大集团', 1);
INSERT INTO `platform_tenant` VALUES (5, 'GS0005', 'http://platform.aisys.top/api/to/gs0005', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', '849da534-f8a9-481a-a95c-91b9f1e7a743', '大宗集团', 1);
INSERT INTO `platform_tenant` VALUES (6, 'hfjy', 'http://platform.aisys.top/api/to/hfjy', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', '367d023c57294ee59a6c89f07135c7c9', '海风集团', 1);
INSERT INTO `platform_tenant` VALUES (7, 'lfdj', 'http://platform.aisys.top/api/to/lfdj', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', '4d2dac260dbd451e93fdb93303d72d1c', '刘锋大吉', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `operate_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '操作详情',
  `operate_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter`  (
  `param_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数名称',
  `param_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数值',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数描述',
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '平台参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_parameter
-- ----------------------------
INSERT INTO `sys_parameter` VALUES (1, 'default_user_role', '1', '新用户注册默认归属角色');

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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限信息' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_permission` VALUES (26, 25, '查看日志', NULL, 'sys:operatelog:show', 2, 0, 1, '2020-01-14 23:10:46', NULL, NULL);

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

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态（1 停用， 0 启用， 默认 0）',
  `login_count` int(11) NULL DEFAULT 0 COMMENT '登录次数',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_unique_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 36, '2020-01-18 23:22:29', 1, '2019-07-21 04:42:11', 1, '2020-01-18 23:22:29');
INSERT INTO `sys_user` VALUES (2, 'test', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 9, '2020-01-18 23:21:19', 1, '2019-07-21 04:42:11', 2, '2020-01-18 23:21:19');
INSERT INTO `sys_user` VALUES (5, 'test3', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:00', 1, '2020-01-18 16:24:45');
INSERT INTO `sys_user` VALUES (6, 'test4', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:09', 1, '2020-01-18 16:20:38');
INSERT INTO `sys_user` VALUES (7, 'test5', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:15', 1, '2020-01-18 16:25:21');
INSERT INTO `sys_user` VALUES (8, 'test6', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:23', NULL, NULL);
INSERT INTO `sys_user` VALUES (9, 'test7', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:32', NULL, NULL);
INSERT INTO `sys_user` VALUES (10, 'test8', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:36', 1, '2020-01-18 16:23:01');
INSERT INTO `sys_user` VALUES (11, 'test9', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:46', NULL, NULL);
INSERT INTO `sys_user` VALUES (12, 'test10', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:56:53', 1, '2020-01-13 16:22:40');
INSERT INTO `sys_user` VALUES (13, 'test11', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-12 13:57:03', NULL, NULL);
INSERT INTO `sys_user` VALUES (14, 'test12', 'd89267ba6e888426c8f798a04f2fb874', '15001924470', 0, 0, NULL, 1, '2020-01-14 14:24:30', NULL, NULL);

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
INSERT INTO `sys_user_role_rel` VALUES (10, 2);
INSERT INTO `sys_user_role_rel` VALUES (14, 1);

-- ----------------------------
-- Table structure for tenant_dept
-- ----------------------------
DROP TABLE IF EXISTS `tenant_dept`;
CREATE TABLE `tenant_dept`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级部门ID',
  `dept_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_dept
-- ----------------------------
INSERT INTO `tenant_dept` VALUES (1, 0, '长征集团', '公司名称', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tenant_dept` VALUES (2, 1, '销售部门', '销售部门', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tenant_dept` VALUES (3, 1, '技术部门', '技术部门', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tenant_dept` VALUES (4, 2, '销售一部', '销售一部', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tenant_dept` VALUES (5, 2, '销售二部', '销售二部', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tenant_dept` VALUES (6, 3, '研发部', '研发部', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tenant_dept` VALUES (7, 3, '测试部', '测试部', 1, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tenant_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tenant_login_log`;
CREATE TABLE `tenant_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `login_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆人ip',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登陆时间',
  `login_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆设备信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_login_log
-- ----------------------------
INSERT INTO `tenant_login_log` VALUES (1, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-17 16:46:45', NULL);
INSERT INTO `tenant_login_log` VALUES (2, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-17 16:47:24', NULL);
INSERT INTO `tenant_login_log` VALUES (3, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-17 16:47:52', NULL);
INSERT INTO `tenant_login_log` VALUES (4, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-17 16:48:12', NULL);
INSERT INTO `tenant_login_log` VALUES (5, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-17 16:48:57', NULL);
INSERT INTO `tenant_login_log` VALUES (6, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-17 16:49:12', NULL);
INSERT INTO `tenant_login_log` VALUES (7, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-17 16:51:58', NULL);
INSERT INTO `tenant_login_log` VALUES (8, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-17 16:52:09', NULL);
INSERT INTO `tenant_login_log` VALUES (9, 1, 1, 'admin', '10.200.187.28', '2019-09-17 17:29:13', NULL);
INSERT INTO `tenant_login_log` VALUES (10, 1, 1, 'admin', '10.200.187.28', '2019-09-17 18:23:01', 'PostmanRuntime/7.1.5');
INSERT INTO `tenant_login_log` VALUES (11, 1, 1, 'admin', '10.200.187.28', '2019-09-17 18:33:50', 'PostmanRuntime/7.1.5');
INSERT INTO `tenant_login_log` VALUES (12, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-18 14:12:51', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (13, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-18 14:17:45', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (14, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-18 14:45:39', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (15, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-18 14:55:26', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (16, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-18 15:21:09', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (17, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-18 15:44:07', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (18, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-19 10:36:08', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (19, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-19 10:36:32', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (20, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-19 10:38:51', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (21, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-19 10:45:57', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (22, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-19 10:59:13', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (23, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-09-19 11:08:59', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (24, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-09 13:52:04', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (25, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-11 15:34:26', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (26, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-11 17:03:13', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (27, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-11 17:07:46', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (28, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-11 18:39:24', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (29, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-12 10:45:10', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (30, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-12 11:40:44', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (31, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-12 13:57:30', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (32, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-12 17:24:53', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (33, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-12 18:31:32', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (34, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-12 19:02:05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (35, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-13 09:51:33', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (36, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-13 14:23:30', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (37, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-13 22:15:46', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (38, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-13 23:53:20', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (39, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-14 13:51:30', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (40, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-14 13:58:15', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (41, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-14 22:20:13', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');
INSERT INTO `tenant_login_log` VALUES (42, 1, 1, 'admin', '0:0:0:0:0:0:0:1', '2019-12-28 10:22:01', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36');

-- ----------------------------
-- Table structure for tenant_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `tenant_operate_log`;
CREATE TABLE `tenant_operate_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `bus_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '模块名称',
  `operate_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '操作类型',
  `operate_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '操作详情',
  `operate_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_operate_log
-- ----------------------------
INSERT INTO `tenant_operate_log` VALUES (1, 1, 1, '管理员', '用户', '修改用户', '修改用户：test9', '2019-09-18 17:20:05');
INSERT INTO `tenant_operate_log` VALUES (2, 1, 1, '管理员', '用户', '修改用户', '修改用户：test9', '2019-09-18 17:42:17');
INSERT INTO `tenant_operate_log` VALUES (3, 1, 1, '管理员', '用户', '修改用户', '修改用户：test9', '2019-09-18 17:43:06');
INSERT INTO `tenant_operate_log` VALUES (4, 1, 1, 'admin', '用户', '修改用户', '修改用户：test9', '2019-09-18 17:46:26');
INSERT INTO `tenant_operate_log` VALUES (5, 1, 1, 'admin', '角色权限', '创建/更新角色权限关系', '角色：1-权限：2,3,4,5,6,79,80,7,8,9,62,63,64,65,66,71,112,113,1', '2019-12-11 17:03:09');
INSERT INTO `tenant_operate_log` VALUES (6, 1, 1, 'admin', '用户', '修改用户', '修改用户：test11', '2019-12-28 10:22:21');
INSERT INTO `tenant_operate_log` VALUES (7, 1, 1, 'admin', '用户', '修改用户', '修改用户：test20', '2019-12-28 10:22:33');

-- ----------------------------
-- Table structure for tenant_parameter
-- ----------------------------
DROP TABLE IF EXISTS `tenant_parameter`;
CREATE TABLE `tenant_parameter`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `param_value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数值（JSON字符串）',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_param_name_tenant_id`(`param_name`, `tenant_id`) USING BTREE,
  INDEX `index_param_name`(`param_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_parameter
-- ----------------------------
INSERT INTO `tenant_parameter` VALUES (1, 'wx_pay', '{\"wx_authorization_url\":\"http://h5dev.aisys.top/comfirmGood\",\"mchId\":\"1519877341\",\"pay_notify\":\"http://h5dev.aisys.top/api/pay_notify\",\"appid\":\"wxb2e5a6cc24b6e2e2\",\"app_redirect_url\":\"http://h5dev.aisys.top/index\",\"wx_authentication_url\":\"http://h5dev.aisys.top/api/oauth\",\"secret\":\"0626347976a452eac301b1f5cd08fa2e\",\"paternerKey\":\"g16ehr4e4ge65f1a654gf6r4g65W465q\"}', 1);
INSERT INTO `tenant_parameter` VALUES (2, 'ali_pay', '{\"name\":\"支付宝参数\"}', 1);
INSERT INTO `tenant_parameter` VALUES (3, 'sms', '{\"name\":\"短信参数\"}', 1);
INSERT INTO `tenant_parameter` VALUES (4, 'qiniu_upload', '{\"bucket\":\"peibiao-public-001\",\"secretKey\":\"mpAWBrfKjP0VDEZ4JBmR1p_JgrettbBLROecKIZm\",\"accessKey\":\"X9XPViLd8_M-MqXcGA-6PEj7hL0kYEbs_Re7XoLX\",\"fileMaxSize\":\"20971520\",\"name\":\"文件上传参数\",\"downloadUrl\":\"http://upload.aisys.top\",\"expireSeconds\":\"1000000\"}', 1);
INSERT INTO `tenant_parameter` VALUES (5, 'email', '{\"name\":\"邮箱参数\"}', 1);
INSERT INTO `tenant_parameter` VALUES (6, 'ali_pay', '{\"name\":\"支付宝参数\"}', 2);
INSERT INTO `tenant_parameter` VALUES (7, 'sms', '{\"name\":\"dddd\"}', 2);
INSERT INTO `tenant_parameter` VALUES (8, 'qiniu_upload', '{\"name\":\"七牛参数\",\"expireSeconds\":\"1000000\",\"bucket\":\"peibiao-public-001\",\"secretKey\":\"mpAWBrfKjP0VDEZ4JBmR1p_JgrettbBLROecKIZm\",\"accessKey\":\"X9XPViLd8_M-MqXcGA-6PEj7hL0kYEbs_Re7XoLX\",\"downloadUrl\":\"http://upload.aisys.top\",\"fileMaxSize\":\"20971520\"}', 2);
INSERT INTO `tenant_parameter` VALUES (9, 'email', '{\"name\":\"163邮箱参数\"}', 2);
INSERT INTO `tenant_parameter` VALUES (10, 'wx_pay', '{\"name\":\"支付宝参数\",\"appid\":\"aaaaaaaaaaaaaaa\"}', 2);
INSERT INTO `tenant_parameter` VALUES (18, 'sys_params', '{\"copyright\":\"长征\",\"code\":\"统计代码\",\"keywords\":\"长征CMS,多租户系统,CMS系统\",\"description\":\"长征多租户CMS系统-服务于企业用户\",\"logo\":\"http://upload.aisys.top/Fmn6EGo4ZelbTAJCuDxUbvS1P3gH\",\"title\":\"长征多租户系统\",\"headPortrait\":\"http://upload.aisys.top/Fo5Bdt35geJoTuPKFb-Z3_R59ecL\",\"url\":\"http://www.longmarch.top\"}', 1);
INSERT INTO `tenant_parameter` VALUES (19, 'sys_params', '{\"title\":\"aaa\",\"headPortrait\":\"http://upload.aisys.top/Fo5Bdt35geJoTuPKFb-Z3_R59ecL\"}', 2);

-- ----------------------------
-- Table structure for tenant_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `tenant_permission_rel`;
CREATE TABLE `tenant_permission_rel`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户ID',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_tenant_id_permission_id`(`tenant_id`, `permission_id`) USING BTREE,
  INDEX `index_tenant_id`(`tenant_id`) USING BTREE,
  INDEX `index_permission_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '租户功能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_permission_rel
-- ----------------------------
INSERT INTO `tenant_permission_rel` VALUES (1, 1, 1);
INSERT INTO `tenant_permission_rel` VALUES (2, 1, 2);
INSERT INTO `tenant_permission_rel` VALUES (3, 1, 3);
INSERT INTO `tenant_permission_rel` VALUES (4, 1, 4);
INSERT INTO `tenant_permission_rel` VALUES (5, 1, 5);
INSERT INTO `tenant_permission_rel` VALUES (6, 1, 6);
INSERT INTO `tenant_permission_rel` VALUES (9, 1, 7);
INSERT INTO `tenant_permission_rel` VALUES (10, 1, 8);
INSERT INTO `tenant_permission_rel` VALUES (11, 1, 9);
INSERT INTO `tenant_permission_rel` VALUES (17, 1, 62);
INSERT INTO `tenant_permission_rel` VALUES (18, 1, 63);
INSERT INTO `tenant_permission_rel` VALUES (19, 1, 64);
INSERT INTO `tenant_permission_rel` VALUES (20, 1, 65);
INSERT INTO `tenant_permission_rel` VALUES (21, 1, 66);
INSERT INTO `tenant_permission_rel` VALUES (22, 1, 71);
INSERT INTO `tenant_permission_rel` VALUES (23, 1, 72);
INSERT INTO `tenant_permission_rel` VALUES (24, 1, 73);
INSERT INTO `tenant_permission_rel` VALUES (25, 1, 74);
INSERT INTO `tenant_permission_rel` VALUES (7, 1, 79);
INSERT INTO `tenant_permission_rel` VALUES (8, 1, 80);
INSERT INTO `tenant_permission_rel` VALUES (30, 1, 85);
INSERT INTO `tenant_permission_rel` VALUES (31, 1, 86);
INSERT INTO `tenant_permission_rel` VALUES (32, 1, 87);
INSERT INTO `tenant_permission_rel` VALUES (33, 1, 88);
INSERT INTO `tenant_permission_rel` VALUES (34, 1, 89);
INSERT INTO `tenant_permission_rel` VALUES (35, 1, 90);
INSERT INTO `tenant_permission_rel` VALUES (36, 1, 91);
INSERT INTO `tenant_permission_rel` VALUES (37, 1, 92);
INSERT INTO `tenant_permission_rel` VALUES (38, 1, 93);
INSERT INTO `tenant_permission_rel` VALUES (39, 1, 94);
INSERT INTO `tenant_permission_rel` VALUES (40, 1, 95);
INSERT INTO `tenant_permission_rel` VALUES (41, 1, 96);
INSERT INTO `tenant_permission_rel` VALUES (42, 1, 97);
INSERT INTO `tenant_permission_rel` VALUES (43, 1, 98);
INSERT INTO `tenant_permission_rel` VALUES (44, 1, 99);
INSERT INTO `tenant_permission_rel` VALUES (45, 1, 100);
INSERT INTO `tenant_permission_rel` VALUES (46, 1, 101);
INSERT INTO `tenant_permission_rel` VALUES (137, 1, 112);
INSERT INTO `tenant_permission_rel` VALUES (138, 1, 113);
INSERT INTO `tenant_permission_rel` VALUES (139, 1, 114);
INSERT INTO `tenant_permission_rel` VALUES (140, 1, 115);
INSERT INTO `tenant_permission_rel` VALUES (74, 2, 1);
INSERT INTO `tenant_permission_rel` VALUES (75, 2, 2);
INSERT INTO `tenant_permission_rel` VALUES (47, 2, 3);
INSERT INTO `tenant_permission_rel` VALUES (48, 2, 4);
INSERT INTO `tenant_permission_rel` VALUES (49, 2, 5);
INSERT INTO `tenant_permission_rel` VALUES (50, 2, 6);
INSERT INTO `tenant_permission_rel` VALUES (51, 2, 7);
INSERT INTO `tenant_permission_rel` VALUES (52, 2, 8);
INSERT INTO `tenant_permission_rel` VALUES (53, 2, 9);
INSERT INTO `tenant_permission_rel` VALUES (59, 2, 62);
INSERT INTO `tenant_permission_rel` VALUES (60, 2, 63);
INSERT INTO `tenant_permission_rel` VALUES (61, 2, 64);
INSERT INTO `tenant_permission_rel` VALUES (62, 2, 65);
INSERT INTO `tenant_permission_rel` VALUES (63, 2, 66);
INSERT INTO `tenant_permission_rel` VALUES (64, 2, 71);
INSERT INTO `tenant_permission_rel` VALUES (76, 2, 85);
INSERT INTO `tenant_permission_rel` VALUES (77, 2, 86);
INSERT INTO `tenant_permission_rel` VALUES (78, 2, 87);
INSERT INTO `tenant_permission_rel` VALUES (79, 2, 88);
INSERT INTO `tenant_permission_rel` VALUES (80, 2, 89);
INSERT INTO `tenant_permission_rel` VALUES (81, 2, 90);
INSERT INTO `tenant_permission_rel` VALUES (82, 2, 91);
INSERT INTO `tenant_permission_rel` VALUES (83, 2, 92);
INSERT INTO `tenant_permission_rel` VALUES (65, 2, 93);
INSERT INTO `tenant_permission_rel` VALUES (66, 2, 94);
INSERT INTO `tenant_permission_rel` VALUES (67, 2, 95);
INSERT INTO `tenant_permission_rel` VALUES (68, 2, 96);
INSERT INTO `tenant_permission_rel` VALUES (69, 2, 97);
INSERT INTO `tenant_permission_rel` VALUES (70, 2, 98);
INSERT INTO `tenant_permission_rel` VALUES (71, 2, 99);
INSERT INTO `tenant_permission_rel` VALUES (72, 2, 100);
INSERT INTO `tenant_permission_rel` VALUES (73, 2, 101);
INSERT INTO `tenant_permission_rel` VALUES (129, 7, 1);
INSERT INTO `tenant_permission_rel` VALUES (107, 7, 2);
INSERT INTO `tenant_permission_rel` VALUES (108, 7, 3);
INSERT INTO `tenant_permission_rel` VALUES (109, 7, 4);
INSERT INTO `tenant_permission_rel` VALUES (110, 7, 5);
INSERT INTO `tenant_permission_rel` VALUES (111, 7, 6);
INSERT INTO `tenant_permission_rel` VALUES (114, 7, 62);
INSERT INTO `tenant_permission_rel` VALUES (115, 7, 63);
INSERT INTO `tenant_permission_rel` VALUES (116, 7, 64);
INSERT INTO `tenant_permission_rel` VALUES (117, 7, 65);
INSERT INTO `tenant_permission_rel` VALUES (118, 7, 66);
INSERT INTO `tenant_permission_rel` VALUES (119, 7, 71);
INSERT INTO `tenant_permission_rel` VALUES (112, 7, 79);
INSERT INTO `tenant_permission_rel` VALUES (113, 7, 80);
INSERT INTO `tenant_permission_rel` VALUES (120, 7, 93);
INSERT INTO `tenant_permission_rel` VALUES (121, 7, 94);
INSERT INTO `tenant_permission_rel` VALUES (122, 7, 95);
INSERT INTO `tenant_permission_rel` VALUES (123, 7, 96);
INSERT INTO `tenant_permission_rel` VALUES (124, 7, 97);
INSERT INTO `tenant_permission_rel` VALUES (125, 7, 98);
INSERT INTO `tenant_permission_rel` VALUES (126, 7, 99);
INSERT INTO `tenant_permission_rel` VALUES (127, 7, 100);
INSERT INTO `tenant_permission_rel` VALUES (128, 7, 101);

-- ----------------------------
-- Table structure for tenant_role
-- ----------------------------
DROP TABLE IF EXISTS `tenant_role`;
CREATE TABLE `tenant_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `activate` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用（枚举 已启用：1， 未启用：0，默认：1）',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_role_name_tenant_id`(`role_name`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_role
-- ----------------------------
INSERT INTO `tenant_role` VALUES (1, '管理员组', '管理员组', 1, 1, 1, 'string', NULL, NULL, NULL, NULL);
INSERT INTO `tenant_role` VALUES (2, '工程部', '系统维护组', 1, 1, 1, 'string', '2018-11-17 13:55:26', 1, '管理员', '2018-12-13 12:00:53');
INSERT INTO `tenant_role` VALUES (4, '测试角色', '天生就是被删除的', 1, 1, 1, '管理员', '2018-11-29 10:56:50', 1, '管理员', '2018-12-10 16:21:00');
INSERT INTO `tenant_role` VALUES (5, '管理员组', '管理员组', 1, 2, 1, 'string', NULL, NULL, NULL, NULL);
INSERT INTO `tenant_role` VALUES (6, '测试组', '测试用户', 1, 2, 22, 'admin', '2019-01-26 22:01:05', 22, 'admin', '2019-03-26 14:00:56');
INSERT INTO `tenant_role` VALUES (7, '宣传部', '发布文章', 1, 7, 29, 'admin', '2019-07-27 17:53:18', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tenant_role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `tenant_role_permission_rel`;
CREATE TABLE `tenant_role_permission_rel`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_role_id_permission_id_tenant_id`(`role_id`, `permission_id`, `tenant_id`) USING BTREE,
  INDEX `index_role_id`(`role_id`) USING BTREE,
  INDEX `index_permission_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_role_permission_rel
-- ----------------------------
INSERT INTO `tenant_role_permission_rel` VALUES (14, 1, 1, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (1, 1, 2, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (18, 1, 3, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (19, 1, 4, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (20, 1, 5, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (5, 1, 6, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (15, 1, 7, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (16, 1, 8, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (17, 1, 9, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (8, 1, 62, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (9, 1, 63, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (10, 1, 64, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (11, 1, 65, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (12, 1, 66, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (13, 1, 71, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (6, 1, 79, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (7, 1, 80, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (39, 1, 112, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (40, 1, 113, 1);
INSERT INTO `tenant_role_permission_rel` VALUES (21, 6, 93, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (22, 6, 94, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (23, 6, 95, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (24, 6, 96, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (25, 6, 97, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (26, 6, 98, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (27, 6, 99, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (28, 6, 100, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (29, 6, 101, 2);
INSERT INTO `tenant_role_permission_rel` VALUES (30, 7, 93, 7);
INSERT INTO `tenant_role_permission_rel` VALUES (31, 7, 94, 7);
INSERT INTO `tenant_role_permission_rel` VALUES (32, 7, 95, 7);
INSERT INTO `tenant_role_permission_rel` VALUES (33, 7, 96, 7);
INSERT INTO `tenant_role_permission_rel` VALUES (34, 7, 97, 7);
INSERT INTO `tenant_role_permission_rel` VALUES (35, 7, 98, 7);
INSERT INTO `tenant_role_permission_rel` VALUES (36, 7, 99, 7);
INSERT INTO `tenant_role_permission_rel` VALUES (37, 7, 100, 7);
INSERT INTO `tenant_role_permission_rel` VALUES (38, 7, 101, 7);

-- ----------------------------
-- Table structure for tenant_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `tenant_schedule_job`;
CREATE TABLE `tenant_schedule_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `count` int(10) NOT NULL COMMENT '失败尝试次数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `tenant_schedule_job_log`;
CREATE TABLE `tenant_schedule_job_log`  (
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
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_user
-- ----------------------------
DROP TABLE IF EXISTS `tenant_user`;
CREATE TABLE `tenant_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号（用于系统登陆）',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `sex` int(1) NULL DEFAULT 1 COMMENT '性別（枚举 男：1， 女：2，保密：3）',
  `admin` tinyint(1) NULL DEFAULT 0 COMMENT '是否超管（枚举 是：1， 否：0，默认：0）',
  `working` tinyint(1) NULL DEFAULT 0 COMMENT '是否入职（枚举 已入职：1， 未入职：0，默认：0）',
  `activate` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用（枚举 已启用：1， 未启用：0，默认：1）',
  `head_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_account_tenant_id`(`account`, `tenant_id`) USING BTREE,
  UNIQUE INDEX `index_phone_tenant_id`(`phone`, `tenant_id`) USING BTREE,
  INDEX `index_account`(`account`) USING BTREE,
  INDEX `index_phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_user
-- ----------------------------
INSERT INTO `tenant_user` VALUES (1, 'admin', '9f86433e539953bcfd99747c70648590', '管理员', '15001924470', 3, 1, 1, 1, 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3523617831,1288544462&fm=27&gp=0.jpg', NULL, 1, 1, '管理员', '2018-10-30 19:55:20', 1, 'admin', '2019-01-17 10:57:42');
INSERT INTO `tenant_user` VALUES (2, 'yuyue', '9f86433e539953bcfd99747c70648590', '于跃', '15001924460', 1, 0, 1, 1, NULL, NULL, 1, 1, 'string', '2018-11-17 11:57:36', 1, 'admin', '2019-06-18 17:03:33');
INSERT INTO `tenant_user` VALUES (5, 'zhangsan', 'e699bd9b3b0684947f364327c47ab9f9', '张三', '15001924450', 1, 0, 1, 0, NULL, NULL, 1, 1, 'string', '2018-11-27 17:51:52', 1, '管理员', '2018-11-28 12:01:03');
INSERT INTO `tenant_user` VALUES (6, 'sdfsd', '9f86433e539953bcfd99747c70648590', 'sdfsd', 'sdfsdfsd123', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-11-28 12:40:26', NULL, NULL, NULL);
INSERT INTO `tenant_user` VALUES (7, 'sdfds', '9f86433e539953bcfd99747c70648590', 'sdfsdf', 'sdfsdf', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-11-28 12:54:11', 1, '管理员', '2018-11-28 14:50:05');
INSERT INTO `tenant_user` VALUES (8, 'sdfsdf', '9f86433e539953bcfd99747c70648590', 'sdfs', 'sdfsdfsd', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-11-28 14:52:01', 1, '管理员', '2018-12-12 18:30:24');
INSERT INTO `tenant_user` VALUES (9, 'test', '9f86433e539953bcfd99747c70648590', 'test', 'test', 1, 0, 1, 1, NULL, NULL, 1, 1, '管理员', '2018-12-12 18:35:21', 1, 'admin', '2019-01-26 23:04:47');
INSERT INTO `tenant_user` VALUES (10, 'test1', '9f86433e539953bcfd99747c70648590', 'test1', 'test1', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 08:57:09', 1, '管理员', '2018-12-13 09:12:50');
INSERT INTO `tenant_user` VALUES (11, 'test2', '9f86433e539953bcfd99747c70648590', 'test2', 'test2', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 09:13:04', 2, '于跃', '2018-12-14 20:17:41');
INSERT INTO `tenant_user` VALUES (12, 'test3', '9f86433e539953bcfd99747c70648590', 'test3', 'test3', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 09:17:16', NULL, NULL, NULL);
INSERT INTO `tenant_user` VALUES (13, 'test4', '9f86433e539953bcfd99747c70648590', 'test4', 'test4', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 09:17:39', NULL, NULL, NULL);
INSERT INTO `tenant_user` VALUES (14, 'test5', '9f86433e539953bcfd99747c70648590', 'test5', 'test5', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 09:19:42', 2, '于跃', '2018-12-17 10:36:50');
INSERT INTO `tenant_user` VALUES (15, 'test6', '9f86433e539953bcfd99747c70648590', 'test6', 'test6', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 09:21:33', NULL, NULL, NULL);
INSERT INTO `tenant_user` VALUES (16, 'test7', '9f86433e539953bcfd99747c70648590', 'test7', 'test7', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 09:21:58', 2, '于跃', '2018-12-17 10:36:32');
INSERT INTO `tenant_user` VALUES (17, 'test8', '9f86433e539953bcfd99747c70648590', 'test8', 'test8', 1, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 09:26:34', 2, '于跃', '2018-12-14 10:57:05');
INSERT INTO `tenant_user` VALUES (18, 'test9', '9f86433e539953bcfd99747c70648590', 'test9', 'test9', 3, 0, 0, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 10:02:55', 1, 'admin', '2019-09-18 17:46:26');
INSERT INTO `tenant_user` VALUES (19, 'test10', '9f86433e539953bcfd99747c70648590', 'test10', 'test10', 1, 0, 1, 1, NULL, NULL, 1, 1, '管理员', '2018-12-13 10:34:54', 1, 'admin', '2019-01-25 15:43:39');
INSERT INTO `tenant_user` VALUES (20, 'test11', '9f86433e539953bcfd99747c70648590', 'test11', 'test11', 1, 0, 0, 1, 'http://upload.aisys.top/FqqwWxuKPK6VQ9PEtXlSVNcRSYuw', NULL, 1, 1, '管理员', '2018-12-13 10:36:00', 1, 'admin', '2019-12-28 10:22:21');
INSERT INTO `tenant_user` VALUES (21, 'test20', '9f86433e539953bcfd99747c70648590', 'test20', 'test20', 1, 0, 0, 1, 'http://upload.aisys.top/FqqwWxuKPK6VQ9PEtXlSVNcRSYuw', NULL, 1, 1, 'admin', '2018-12-22 13:41:29', 1, 'admin', '2019-12-28 10:22:33');
INSERT INTO `tenant_user` VALUES (22, 'admin', '9f86433e539953bcfd99747c70648590', '管理员', '15001924470', 1, 1, 1, 1, 'http://upload.aisys.top/Fo5Bdt35geJoTuPKFb-Z3_R59ecL', NULL, 2, 1, 'admin', '2018-12-22 19:47:14', 22, 'admin', '2019-01-26 13:56:59');
INSERT INTO `tenant_user` VALUES (24, 'test', '9f86433e539953bcfd99747c70648590', '测试用户', '15001924471', 1, 0, 1, 1, NULL, NULL, 2, 22, 'admin', '2019-03-26 14:00:03', NULL, NULL, NULL);
INSERT INTO `tenant_user` VALUES (29, 'admin', '9f86433e539953bcfd99747c70648590', '上汽超级管理员', '15001202323', 1, 1, 1, 1, NULL, NULL, 7, NULL, NULL, NULL, 29, 'admin', '2019-07-27 18:05:57');
INSERT INTO `tenant_user` VALUES (30, 'shangqi01', '9f86433e539953bcfd99747c70648590', '上汽宣传01号', '15003040303', 1, 0, 1, 1, NULL, NULL, 7, 29, 'admin', '2019-07-27 18:07:06', 29, 'admin', '2019-07-27 18:08:11');

-- ----------------------------
-- Table structure for tenant_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `tenant_user_role_rel`;
CREATE TABLE `tenant_user_role_rel`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_id_role_id_tenant_id`(`user_id`, `role_id`, `tenant_id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE,
  INDEX `index_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_user_role_rel
-- ----------------------------
INSERT INTO `tenant_user_role_rel` VALUES (5, 1, 1, 1);
INSERT INTO `tenant_user_role_rel` VALUES (83, 2, 1, 1);
INSERT INTO `tenant_user_role_rel` VALUES (6, 8, 1, 1);
INSERT INTO `tenant_user_role_rel` VALUES (7, 8, 2, 1);
INSERT INTO `tenant_user_role_rel` VALUES (81, 9, 4, 1);
INSERT INTO `tenant_user_role_rel` VALUES (12, 10, 2, 1);
INSERT INTO `tenant_user_role_rel` VALUES (13, 10, 4, 1);
INSERT INTO `tenant_user_role_rel` VALUES (31, 11, 1, 1);
INSERT INTO `tenant_user_role_rel` VALUES (47, 14, 2, 1);
INSERT INTO `tenant_user_role_rel` VALUES (46, 16, 2, 1);
INSERT INTO `tenant_user_role_rel` VALUES (20, 17, 2, 1);
INSERT INTO `tenant_user_role_rel` VALUES (21, 18, 2, 1);
INSERT INTO `tenant_user_role_rel` VALUES (22, 18, 4, 1);
INSERT INTO `tenant_user_role_rel` VALUES (25, 19, 4, 1);
INSERT INTO `tenant_user_role_rel` VALUES (27, 20, 1, 1);
INSERT INTO `tenant_user_role_rel` VALUES (78, 21, 1, 1);
INSERT INTO `tenant_user_role_rel` VALUES (79, 22, 5, 2);
INSERT INTO `tenant_user_role_rel` VALUES (82, 24, 6, 2);
INSERT INTO `tenant_user_role_rel` VALUES (84, 30, 7, 7);

-- ----------------------------
-- Table structure for video_zy
-- ----------------------------
DROP TABLE IF EXISTS `video_zy`;
CREATE TABLE `video_zy`  (
  `zy_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源主键',
  `zy_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '资源名称',
  `zy_show` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示（1启用，0废弃，默认1）',
  `zy_apiurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '资源API接口',
  `zy_sort` int(11) NULL DEFAULT NULL COMMENT '资源排序',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`zy_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '视频资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video_zy
-- ----------------------------
INSERT INTO `video_zy` VALUES (1, '最大', 1, 'http://www.zdziyuan.com/inc/api.php', 1, 1, '2019-07-28 16:02:53', NULL);
INSERT INTO `video_zy` VALUES (3, '', 1, '', NULL, 1, '2019-09-19 11:17:14', NULL);

-- ----------------------------
-- Table structure for video_zyjx
-- ----------------------------
DROP TABLE IF EXISTS `video_zyjx`;
CREATE TABLE `video_zyjx`  (
  `jx_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `zy_id` bigint(20) NULL DEFAULT NULL COMMENT '资源ID',
  `jx_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '解析名称',
  `jx_alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '解析别名',
  `jx_show` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示（1显示，0废弃，默认1）',
  `jx_isjx` tinyint(1) NULL DEFAULT 1 COMMENT '是否需要解析（1需要，0不需要，默认1）',
  `jx_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '解析URL',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`jx_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '资源解析表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video_zyjx
-- ----------------------------
INSERT INTO `video_zyjx` VALUES (1, 1, 'zuidam3u8', '云播放', 1, 1, 'https://2wk.com/vip.php?url=', 1);
INSERT INTO `video_zyjx` VALUES (2, 1, 'zuidall', '在线播放', 1, 1, NULL, 1);
INSERT INTO `video_zyjx` VALUES (3, 3, '', '', 1, 1, NULL, 1);
INSERT INTO `video_zyjx` VALUES (4, 3, '', '', 1, 1, NULL, 1);
INSERT INTO `video_zyjx` VALUES (5, 3, '', '', 1, 1, NULL, 1);
INSERT INTO `video_zyjx` VALUES (6, 3, '', '', 1, 1, NULL, 1);

-- ----------------------------
-- Table structure for wx_gzh_account
-- ----------------------------
DROP TABLE IF EXISTS `wx_gzh_account`;
CREATE TABLE `wx_gzh_account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jwid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公众号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `application_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用类型',
  `qrcodeimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信二维码图片',
  `weixin_number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `weixin_appid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信AppId',
  `weixin_appsecret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信AppSecret',
  `account_type` tinyint(1) NULL DEFAULT NULL COMMENT '公众号类型',
  `auth_status` tinyint(1) NULL DEFAULT NULL COMMENT '是否认证',
  `access_token` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Access_Token',
  `token_gettime` datetime(0) NULL DEFAULT NULL COMMENT 'token获取的时间',
  `apiticket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'api凭证',
  `apiticket_gettime` datetime(0) NULL DEFAULT NULL COMMENT 'apiticket获取时间',
  `jsapiticket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'jsapiticket',
  `jsapiticket_gettime` datetime(0) NULL DEFAULT NULL COMMENT 'jsapiticket获取时间',
  `auth_type` tinyint(1) NULL DEFAULT NULL COMMENT '类型：1手动授权，2扫码授权',
  `business_info` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能的开通状况：1代表已开通',
  `func_info` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号授权给开发者的权限集',
  `nick_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权方昵称',
  `headimgurl` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权方头像',
  `authorization_info` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权信息',
  `authorizer_refresh_token` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '刷新token',
  `token` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '令牌',
  `authorization_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权状态（1正常，2已取消）',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除（1 已删除，0 未删除，默 0）',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_jwid`(`jwid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统公众号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_gzh_account
-- ----------------------------
INSERT INTO `wx_gzh_account` VALUES (1, 'gh_4c18fcfecc3f', '子航记录', NULL, 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 'tommyqty', 'wx61649ec50fe75470', 'e278ec217c0f6a0366b3c1cc39324bec', 2, 1, '28_KFZfFFskYciTFTAuAQ-Ah3a5Ab-lweWrGp12OOk1bBFbRsVCN9vgn-TNmhydAIxWxa3lzkOdvva8c-nXoVSbgDElUYFwxpBqBB3THrIylVWQUWtMlkG1bolg-jFc2J0ZciOx_9Sw-dOlYLhRVDUgADAIQH', '2019-12-10 13:10:00', '28_KFZfFFskYciTFTAuAQ-Ah3a5Ab-lweWrGp12OOk1bBFbRsVCN9vgn-TNmhydAIxWxa3lzkOdvva8c-nXoVSbgDElUYFwxpBqBB3THrIylVWQUWtMlkG1bolg-jFc2J0ZciOx_9Sw-kgt8ON7yVITDhtdwci0qeex8Xv0IVVMlGVXepUjZiOwfmkSb4uCCPQK0T1KSmBjUjMjE0DsuzyjyxfDJMJD6Kw', '2019-12-10 13:10:00', NULL, NULL, 2, '2019-12-10 13:10:00', '1', NULL, NULL, NULL, NULL, 'longmarch', NULL, 0, 1, 1, '2019-12-11 17:15:21', NULL, NULL);
INSERT INTO `wx_gzh_account` VALUES (2, 'gh_93eeb7320a9f', '分维', NULL, 'http://upload.aisys.top/FjYSGmjmPK5D6YDeXlxU2155uXE6', 'tommyqty', 'wxaeeab59bae7d0deb', '0c95e460f74fc1e3c4e56a825c8a385e', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 'http://upload.aisys.top/FqqwWxuKPK6VQ9PEtXlSVNcRSYuw', NULL, NULL, 'longmarch', NULL, 0, 1, 1, '2019-12-11 17:45:19', NULL, NULL);
INSERT INTO `wx_gzh_account` VALUES (3, 'gh_4c18fcfecc32', '测试公众号', NULL, '', 'fefsss', 'wx61649ec50fe75888', '', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, 0, 1, 1, '2019-12-11 18:52:30', NULL, NULL);

-- ----------------------------
-- Table structure for wx_gzh_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_gzh_user`;
CREATE TABLE `wx_gzh_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `open_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'openid',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `nickname_txt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过滤后昵称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注名称',
  `head_img_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sex_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别：\'1\':男性；\'2\':女性；\'0\':未知',
  `subscribe` tinyint(1) NULL DEFAULT NULL COMMENT '是否关注:\'0\':未关注；\'1\':关注',
  `subscribe_time` int(50) NULL DEFAULT NULL COMMENT '关注时间',
  `subscribe_scene` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户关注渠道来源',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `bind_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绑定状态：\'N\':未绑定；\'Y\':已绑定；\'V\':绑定中',
  `bind_time` datetime(0) NULL DEFAULT NULL COMMENT '绑定时间',
  `tag_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签id',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `qr_scene` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码扫码场景',
  `qr_scene_str` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码扫码常见描述',
  `group_id` int(64) NULL DEFAULT NULL COMMENT '用户所在分组id',
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `union_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jwid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号原始id',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除（1 已删除，0 未删除，默 0）',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_openid`(`open_id`) USING BTREE,
  INDEX `index_mobile`(`mobile`) USING BTREE,
  INDEX `index_qrScene`(`qr_scene`) USING BTREE,
  INDEX `index_jwid`(`jwid`) USING BTREE,
  INDEX `index_bindTime`(`bind_time`) USING BTREE,
  INDEX `index_tagidList`(`tag_ids`(255)) USING BTREE,
  INDEX `index_createTime`(`create_time`) USING BTREE,
  INDEX `index_subscribeTime`(`subscribe_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 351 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '粉丝表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_gzh_user
-- ----------------------------
INSERT INTO `wx_gzh_user` VALUES (1, 'oG-Lc1E8kMH--SxsdhJzzcfrT73M', '暗•🏂', NULL, '哈哈', 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuqUFZ1eHmRQ3EYgTM5uHlCcIwice8o41R4zsbyibf4mpcP5Eu3hwMQfNHf3AJdElp88Nou0MuP26SQ/132', '男', 1, 1, 1570788299, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '107,111,114,115,130,100,118,119,125,127,110,128', '', '', '美国', '0', '', 107, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (2, 'oG-Lc1E90uE-4FymeEGEp1Bjuihk', '王奕 Aviva', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauBPicodTwI8aZAibGbeCdc0zZYSJzlYyhpra8TDKoWZr5H89APK8JqLfXHrHGtmibIMicO1xxicgU5weOw/132', '女', 2, 1, 1570799345, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '107,111,114,115,118,100,125,127,128', '', '', '中国', '0', '', 107, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (3, 'oG-Lc1KZU3y3U5vurWDVCZ1WpHJA', '37.2℃', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM7EmBibH8nGwBc1E4y6OxqSopBRRl703hqr0B0ibjkSudOibvmA1LfmYjNPR9nzdvV57BvJtsuA5AGeg/132', '男', 1, 1, 1570793708, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '105,106,107,112,115,100,116,118,122,130,119,123,125,126,109,110,113,128,129', '', '', '安道尔', '0', '', 105, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (4, 'oG-Lc1C72LPl1MYJS5ym8YuhbIsY', 'Matt', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauBWlodI8XfGTszSsdZnxjark1qbY7tWp6RNUaYhibsdicsmjT2qVuiaNzWlhgic0TIXfNOuQ8ZSy4S8RA/132', '男', 1, 1, 1570792975, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '127,100,120,123,124,125,104,108,115,118,130', '上海', '浦东新区', '中国', '0', '', 127, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (5, 'oG-Lc1LFMOd9tuXxxmqjtVOWaWcU', '赵照之', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLA5CTcCr1VNiabcsBZ6erebb8DPlz53Dq5GYjEnR5kK2tZia3ywv4XxnIrK7picCPuIJOZcYBlLddXgw/132', '男', 1, 1, 1569845535, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '127,119,120,123,124,126,109,118,128,129,130,105,106,107,111,115', '上海', '徐汇', '中国', '0', '', 127, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (6, 'oG-Lc1IN4mhnQfw3w2sP-D1ZPGYw', '龚飞', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdCX3GxjddIBiaaQVVu86Z78WUJ8jNbMUPc3LetOTy8y4KicXGudbHrogo96HhD3FZdObNhS7zqY4ZwA/132', '男', 1, 1, 1572485533, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '107,111,113,114,115', '上海', '嘉定', '中国', '0', '', 107, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (7, 'oG-Lc1CRvAD48qJmyLM7EzxgQSC0', '俞天一', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM6ic1VHiax4TEl1Rtl8SYUKmHqTEicp78qBUaib7AmxAWIpWibmO9XfPd4ib5IicyCm0gicT716rxqLgS0sibg/132', '男', 1, 1, 1570791251, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '119,120,123,124,126,105,106,107,111,115,127', '上海', '浦东新区', '中国', '0', '', 119, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (8, 'oG-Lc1OpN8_XMqPUQyCDcEHzGyJo', 'Karen酱', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEIVsDRxwuenbZs4S6718fX2VPRkELP5icueWsNuVxuu76J4fTc2voBMQA2zp32vdbVcdjIqLeorBGw/132', '女', 2, 1, 1511942255, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '105,106,107,112,115,116,118,122,125,130,109,110,113,128,129,119,120,123,124,126', '江苏', '无锡', '中国', '0', '', 105, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (9, 'oG-Lc1Ou7sWLQe_qMV41fc54tinI', 'Mc', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLCb71KM4aQygVOoN1QTgvNpEvbVeib1B3C6ewJia051RQQ9sslkhEEPgbMUe793xFvUHapQF7Zg5RtQ/132', '男', 1, 1, 1570789559, 'ADD_SCENE_PROFILE_LINK', NULL, NULL, NULL, '100,118,120,123,125,104,108,113,115,130,119,124,127', '上海', '杨浦', '中国', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (10, 'oG-Lc1G1chTXs-ZO2aaRY81OigFM', '欣欣', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauBYXWlHZPxic2Q8icRRB1q94rpOHVAjBYq2soRjlqrfSia9DKZia3MQ0mGhz1ibia0MOlDiaAUmFNK0SiblsA/132', '女', 2, 1, 1571813370, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '117,121,124,127,109,120,123,129,130', '陕西', '渭南', '中国', '0', '', 117, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (11, 'oG-Lc1ChTB7tie3FYj7IbTKiu07c', 'Y  z', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEJjHMNliacHrxqnHAicDq6J56u3LFypeHckRH5PollA9yYuic4xorI0NpKf1NafztibpV14uSKTlicGzew/132', '男', 1, 1, 1574066617, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '104,108,111,114,115,127,100,103,113,118,125', '', '', '中国', '0', '', 104, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (12, 'oG-Lc1P-Nj3SqQiMDpPw2iR1Ta_4', 'Ashley', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM4ymdJEHzgnppicGY4D5dzzAgt7zRZORib46m4A4m1aVIrJP22muoNx86FpkIbcI5yYD2fqz8tRBlibg/132', '女', 2, 1, 1571671018, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '104,108,111,114,115,119,123,126,113,118,122,125,130', '上海', '黄浦', '中国', '0', '', 104, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (13, 'oG-Lc1ApVBUdWtr8ccWoalD6u_7o', '吴垚Frank', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEIDm4fl8WO8pcCbjySVUqCX0yzFfQSssJjrQ34yhZD99QWibjbn0WOwBKI8iaQFDKdR4oYR9sPib0icvg/132', '男', 1, 1, 1571824381, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '105,106,107,111,115,109,118,128,129,130,119,120,123,124,126', '上海', '黄浦', '中国', '0', '', 105, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (14, 'oG-Lc1OypiwXKoafbAwSSp9o29bs', '德昂 Jack', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxKyyFqzdWfW4aJZvHtvcpdNsEAsefT92z8w2Gp4IoJ9Ih6BDyeYE4E6uKw5ic3xwRsd4hCua9HSy9mRSbSmf5ic/132', '男', 1, 1, 1572495907, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '105,106,107,111,115,109,116,118,128,130,100,120,124,125,127', '北京', '朝阳', '中国', '0', '', 105, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (15, 'oG-Lc1KIEv0nGqV_bZPpD_oSDxiY', '魏峰', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM5qgKwCK4JM8f8I63Z5pbL9dQGVMHjgOMPOlyld6PjEy975PliaJ4mwdvJ3HOzMO2lVQtM0ML7ZbUg/132', '男', 1, 1, 1506071945, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '109,118,128,129,130,127,105,106,107,111,115,119,120,123,124,126', '上海', '浦东新区', '中国', '0', '', 109, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (16, 'oG-Lc1DOp9LOSQq-zp1ClFomMOac', '0xFF', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/04WrcqiaibGMw6VrGwogc6Vncd2WkERSa5XLkUK4beGagKzBglhnuTGJ5YiaAic5M6zMoIDib8xoSUT3PFcylpC8Br9pcEia3erOP3/132', '男', 1, 1, 1506085981, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '100,103,113,118,125,104,108,111,114,115,127', '奥克兰', '', '新西兰', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (17, 'oG-Lc1C9lFBMT4vQneBF9Y7RWDbA', '于跃', NULL, '我自己', 'http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBZH7CKKpuOSl4NkynKEmfEmKMeXQWYr9Ts3bicdvyfPRtehBzTIribMYSELFZnLjTVbGODm2tHRdoA/132', '男', 1, 1, 1574837042, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '119,120,123,124,126,105,106,107,111,115', '上海', '闵行', '中国', '100001', '', 119, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (18, 'oG-Lc1EzHMrLlygK6kHHC6CXIZRs', 'Clora', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxKyyFqzdWfYeN4rA3IIwia8dVqoHW6ocr2t6wubGnicGfG9k5eGSpPG4vkF0KcJEsibEl5htKyJnP62cZ5Za2Cial/132', '女', 2, 1, 1513913151, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '104,108,111,114,115,113,118,122,125,130,119,123,126', '广东', '深圳', '中国', '0', '', 104, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (19, 'oG-Lc1CTylN90-agnH7K1A5dLP9E', '虎贝贝', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM650c8H4Fc1MBvrM3BCU8MC0giams2gscYRnIBymMEUAQ7DNQ7icN49JBWYEWZiaicBUic2A43tF0dSIIw/132', '女', 2, 1, 1570792090, 'ADD_SCENE_PROFILE_LINK', NULL, NULL, NULL, '100,103,125,104,108,111,114,115', '', '', '冰岛', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (20, 'oG-Lc1FNNnKM1QKPlGeS-hIKaAWg', 'Serein.戴戴', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEJFGKjckTzW8ibCv6C1SHe26duhM1DGoyCSdWmm795ibnia4mblp7eXWtMgNoHw0MlRq4icrckFqk2SLA/132', '女', 2, 1, 1570788490, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '103,104,108,111,115,127,100,118,123,125,130', '', '', 'BA', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (21, 'oG-Lc1AQq0cgTPO9dVI6htszV71Q', '徐卫华', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLAawsTel5DIc0ib8shPbrj3WibBYBmALucjjzicbbsuzCF2SY5rRqjeQ56ibFONJibZ0kHgwZTqbb7PSGQ/132', '女', 2, 1, 1571108221, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '120,121,123,124,126,108,109,122,129,130,119', '上海', '黄浦', '中国', '0', '', 120, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (22, 'oG-Lc1PSizUBAvH8DDf4pSVzfJwY', 'Yan', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIXNbC9wffbYricSEPMyFYmLCFwfQjC7QouiaDXOFQg07ugPIqcVgib0jxa3fMf50YAwtdiazqJvbdBibdaBEK2TZrdpu/132', '未知', 0, 1, 1572151404, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '119', '', '', '', '0', '', 119, 'en', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (23, 'oG-Lc1Ljh7Vifkiij0_BsveSNM_4', '不吃香蕉的大象', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxKyyFqzdWfeeeN2NxTUhOYSSTbsns17tp7icZXhSkH5wgVlcVZHFqymgguKdB4I0AUB1ItGvukhlgBfHL4qlFo/132', '男', 1, 1, 1572061066, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '107,111,113,114,115,100,118,119,125', '北京', '', '中国', '0', '', 107, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (24, 'oG-Lc1LB7h-UNMcvKmEpqUwhfMY8', '瓶子', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEIEXZ5Hb8VKJkSkPnPIwrC1aqZj4K8IYIZcwh3H7Ujjc9LjVdr8WsksB95ysdrjslAYkD7KnT5qBA/132', '女', 2, 1, 1506071937, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '103,104,111,114,115,100,125', '香港', '', '中国', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (25, 'oG-Lc1D0ljj--0zIzi72_fMxgKmo', '韶华浅落', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM5icVF0IQYFMk2VFf5UoFcRKMd12vCpbaFUNANbdxH1yJ3rGyhux4bF8K4ek9svLyib5PNgLUfwIabfetEhNaGWtib3qqdjAgUOuw/132', '未知', 0, 1, 1571282614, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '100,125,103,104,111,114,115', '', '', '', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (26, 'oG-Lc1A1ffyDuvXAOxlB0YN8ZFn8', '冯绚', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLAHHvIZ9kXSKlpHSzlxLruCDsg7La59YCqsrdWw9BHzVL9nBenSG8EnXufMcmEFs3AsBOFibWHmS9w/132', '女', 2, 1, 1548841333, 'ADD_SCENE_OTHERS', NULL, NULL, NULL, '107,109,122,129,130,119,120,123,124,126', '上海', '杨浦', '中国', '0', '', 107, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (27, 'oG-Lc1FXfLHigc-qx4eTkD5cRyfw', '德昂Anne黎晓', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEIKO3rNLib1Ome8I5BATH8LqyEbEhA0vtxSjdzGUucweK6RXm70PgKJtWeiaalhUKUK685H7JAoh3LA/132', '女', 2, 1, 1572608859, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '119,123,124,126,127,105,106,107,111,115,109,113,128,129,130,100,116,118,120,125', '北京', '丰台', '中国', '0', '', 119, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (28, 'oG-Lc1Iz1PZyfMrO184ravWZGOls', '假装正经的强盗', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLB32JhL4ETWnDpFTg1SOXUEnL1W5wynZibhc30pDD20gVsSaHT9MgicujN7TILVuBsWccnRuel5CJO0Y7fsJaz9qt4iaoHomNGFqY/132', '男', 1, 1, 1572268056, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '100,118,119,125', '', '', '所罗门群岛', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (29, 'oG-Lc1PkeU3VG0tZ0Ssn0AAV80Rs', '阿炳', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxxrxib7x6wDicKmJJicBk1h6vcKc0ZbBsNs8AqML4yxmqGnYo7ib1T1G3a4ib7wI3j1iaZNXdkyG7UBgGLhJoiaicqgx1/132', '男', 1, 1, 1571814282, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '109,118,128,129,130,119,120,123,124,126,105,106,107,111,115,127', '陕西', '西安', '中国', '0', '', 109, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (30, 'oG-Lc1GQneIsgkOfhw71OP936kw0', 'Sky', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxxrxib7x6wD1RxjQlY4bPuDQ5Na5834qPPUiasLuymgfaeicTdpfJtEz0CdCrY79169KlgibGiavu4WeA8eA68pjsI/132', '男', 1, 1, 1569843564, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '119,120,123,124,127,104,108,113,114,115,109,118,122,125,130', '上海', '长宁', '中国', '0', '', 119, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (31, 'oG-Lc1ArHjTp7lvYuNWkIo_B5_WY', '梁小建', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxxrxib7x6wDzPWTO6D6ukZMkcI9CeECBKDzp3kblDHDVSo4Cbz2hVibicVer6YEN6Xv36OrbiceQWATzHkx5bFWr3/132', '男', 1, 1, 1572268078, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '105,106,107,111,115,127,109,118,128,129,130', '上海', '杨浦', '中国', '0', '', 105, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (32, 'oG-Lc1H1kDI-FsuD1YDHWa_w9bY8', '无情无情无', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIWG2CJaSuzpo209OE3wxDzQlwSQa6sVvZ3lOLaJgkibpKJ6c9tQxzJYVxicPcRF23uvFib0S5MtZlYM69wm1l49pZW/132', '男', 1, 1, 1547692685, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '119,127,105,106,107,111,115,120,123,124,125,126,100,109,118,128,130', '上海', '徐汇', '中国', '0', '', 119, 'en', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (33, 'oG-Lc1BNEXrUFl8tiL9W3md6KF80', 'JIM', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdCYh7SBSdxhKwaOoxWjQOLsDVUIq3POB99jQEiczCdn7NLfdXggGYnvTIdQsJJAbibKbS2WYEMKOLLM9rMEbgblr2/132', '男', 1, 1, 1571670043, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '127,100,103,113,118,125,104,108,111,114,115', '', '', '美国本土外小岛屿', '0', '', 127, 'en', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (34, 'oG-Lc1KEu-7NJ3iHdJy23cqjY8lM', '葛文文ᝰGladys', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxxrxib7x6wDzjiaw3ypEy2VZXqrqiaWia8Rs8VHCa0n668hOhb1vkGYroFwhYzLFiaIXfXqwAwtxuhbYWvYIib7xTAy/132', '女', 2, 1, 1572695940, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '104,108,111,114,115,127,100,103,118,123,125', '', '', '以色列', '0', '', 104, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (35, 'oG-Lc1K5IcYQRsnga_y4VsCxq828', 'Shirley Yu', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLD0puAOo1AraubhibTrC9d8e4YDOtMrGTlEsCq8qqn25OH11iaSZzCm1lZFRO6EWLeXicpIZIwiaQWicjXKpgwJhicIVHgkQIqVu6hW4/132', '女', 2, 1, 1572317176, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '104,108,111,114,115', '上海', '', '中国', '0', '', 104, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (36, 'oG-Lc1D0uEy8T6gGrfb059n1Dvqs', '--', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBcpWkl6KiblZQQahsaPLqlWzNQNs5SLRlkrkp6DO0DQLAa3dmZcmIGY297LudAnUfFHwTBAnALoiajqp0tjO57mK/132', '男', 1, 1, 1570788741, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '110,113,116,118,128,100,119,125,106,107,111,114,115', '', '', '格陵兰', '0', '', 110, 'en', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (37, 'oG-Lc1ARqN5Y-U4UkFj4wrXDC0VI', '伊杨🌈', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxxrxib7x6wD4Fn5XicYOVU6yQNwgGUPUDGDRASYvmDqFQj5GpIqcD2hUWb8ia0gLkw5vJODdYumX7jHSEod1kwFS/132', '女', 2, 1, 1572485540, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '103,117,120,121,124,127', '陕西', '西安', '中国', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (38, 'oG-Lc1OSG7CckEJkvzanSgXDAIE0', '余立里。', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM71t3K6LEwiaPpTIQbEyEHS7NDaTpG45AzBwHDyVUUiajwuudIEnNBq39eLZNDtwAvKTFBsCicta1Cq1wNtbhrtUKA6smJ2mXVfww/132', '女', 2, 1, 1571799600, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '103,104,111,117', '', '', '安道尔', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (39, 'oG-Lc1F167z8wQ7J6-RNh6XG3ESY', 'zhoudaye-', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBcpWkl6KiblZYVQ3xpYOn0o554rFLkNxuAt8WbCn72mFHx4TtSRdXjyWGpCBVZBIkEEONxicqZGkiaVqyGxrbicZ4U/132', '男', 1, 1, 1571828575, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '127,109,113,118,122,130,106,107,115,128,129,119,120,123,124,126', '上海', '长宁', '中国', '0', '', 127, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (40, 'oG-Lc1EjSCQjpbJOXyLP8d9ndPBM', 'Fiona～', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/jFaH1SN2mWCXU9ow9CmojPYcNtMUF7ibb66NK4T6FzLxSKJg5Et7UzDjfVRUKndulJYcYBgVyiaHLWKI6Yp8Un9Dia8navhRNem/132', '女', 2, 1, 1570788191, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '100,103,113,118,125,119,104,108,111,114,115', '上海', '', '中国', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (41, 'oG-Lc1PYI2bvYsd4bAkbB221uU40', '姜新龙', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIWG2CJaSuzpo8sWZBkiaP2JkzGknicwiaYuJqSCCq7LHkdibdmWoZQsdYj6gREoOOaXhtfiadUopucjM1Mu12KC1Rd5ic/132', '男', 1, 1, 1572486997, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '109,118,128,129,130,119,120,123,124,126,127,105,106,107,111,115', '新疆', '乌鲁木齐', '中国', '0', '', 109, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (42, 'oG-Lc1CMTNZYmhjL8hF8nOgKOOmU', '仲崇峻', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauAOnicgaoia8osmqBXx85FP4M94GUURy3OV3zvD0G2zt45JwrpicvvpcJz4W8XQs5zsxcOnbOfb5vR3ugKEdz7SVyq/132', '男', 1, 1, 1570789530, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '127,119,120,123,124,126,105,106,107,111,115,109,118,128,129,130', '', '', '中国', '0', '', 127, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (43, 'oG-Lc1He36idy-rTJWOLmOnJiBf4', 'cc', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIWG2CJaSuzpowiaykaicrAnKbP3UN54AAzq093ZVY15Du4hLTbbw6T6U5rN3dCUgibUfY5Z7W3vG2OSKJvpZKL5xUf/132', '女', 2, 1, 1570799946, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '117,121,124,127,109,120,123,129,130', '浙江', '杭州', '中国', '0', '', 117, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (44, 'oG-Lc1PRyvG1jvQC43iunzK7JOJM', '寻', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBhlWY6X7TLvulyK1P5aFINCKMu6Mn5sCFQ3QicjYdawUyE22n0bYKnOxM3Xj5zduhuZe6j1OC5Y4UQ4eteSWic37/132', '男', 1, 1, 1571282544, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '119,124,126,127,100,120,122,123,125,109,113,118,129,130,106,107,111,114,115', '上海', '嘉定', '中国', '0', '', 119, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (45, 'oG-Lc1N4ExLjwSZ4XG7nScaFypCc', 'yu', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdDDicVlZr4VOcK0thSMV58bdtZZ3BMbuwTdPP0WkqAYw25EHX0icGIuZxYnhRwibU5jZlx1tMc7qzxO6aJTn9oTsvT/132', '女', 2, 1, 1517892836, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '103,104,111,114,115,100,125', '', '', '中国', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (46, 'oG-Lc1IP2BVUvJWLx9RA5QdY-2Y8', '何慧敏', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBcpWkl6KiblZZzfCLib1bHKjLRgZtLz4wy0WdgPKQpZJlURzNtu9ryCq2OaQhEvyKvVuZdYBR9RacA7gYhEkUgDf/132', '女', 2, 1, 1572500161, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '103,104,111,114,115,100,125', '上海', '', '中国', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (47, 'oG-Lc1Jx2ECczaDwTwcK4zGiAQ1o', 'Angelina🍒', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauAOnicgaoia8osqQBibDEzvPFJFu7bCaCh8Aqkxk9RWdcE7x8ptcTAp6poJ4jGa2K4h0eqneawIBaGhOeT1Tx3ThQq/132', '女', 2, 1, 1570504544, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '100,118,120,125,130,104,108,111,114,115,119,123,124,127', '上海', '浦东新区', '中国', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (48, 'oG-Lc1Ib9bDOeudLgKw_vObIiW-0', '静悄悄地来', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIWG2CJaSuzpoz9lU3NNU2bg1TiaWSuKstgdpwvFZWQmKAico9KibjoajnSh8xRic8ibVv0YgCFHT2TrZZm3MHjJoELur/132', '男', 1, 1, 1572486611, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '109,118,128,129,130,100,120,123,124,125,119,126,127,105,106,107,111,115', '安徽', '合肥', '中国', '0', '', 109, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (49, 'oG-Lc1Do6OItxj-1yEZzvzS2_2lY', 'Boyang Wang', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIWG2CJaSuzpo0LAUwuht5yfcCZj6Xzy3kRugIbDFr54IvkDP7C6LbzgOUT8sXNPwqypRMdINWqaYp70dpE6UZwb/132', '男', 1, 1, 1573106624, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '100,118,123,125,130,127,103,104,108,111,115', '北京', '朝阳', '中国', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (50, 'oG-Lc1LcFudi0YfxKPq0j0FfH4RY', 'Cathy', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauAOnicgaoia8osrovojwtIPfEkWGe1FlkqUuNPCRJkGMmaSHOIqU8LDcxCMV8Fyyu2ibGl2nMaamU9b0alhQ5pKFWm/132', '女', 2, 1, 1572791704, 'ADD_SCENE_OTHERS', NULL, NULL, NULL, '100,103,113,118,125,119,104,108,111,114,115', '', '', '英国', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (51, 'oG-Lc1LeChrAA4v_PeFc7yvraHCc', '宣晓华 华院数据', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLD0puAOo1ArasjyP6O8j1gcf9zwx9SPs2WZJiaqn49E5jCIqmHkVIFCbUxvYfGx5TUYDbRFGGMLXABrRN2JmXN7Nco7L8qwQAoE/132', '男', 1, 1, 1570506215, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '100,109,118,125,130,120,124,126,127,105,107,111,115,128', '上海', '浦东新区', '中国', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (52, 'oG-Lc1G02DkbY1XsY-1Y4mrnJ2go', 'yongbin', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM4mPkUbroI5iaWYefSMQNfYb8Iww1yUEGe7G42x6UFucdFsAu9g1hs4UKjPvEabqXqOvCMPia6NkcfiaUqE1ObHNDpT391S36RCqM/132', '未知', 0, 1, 1572178792, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '104,108,111,114,115,100,103,125', '', '', '', '0', '', 104, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (53, 'oG-Lc1ICj-v8VVn3r9ivlT4Ilqts', '🍀莯奕小春', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIWG2CJaSuzpoicmu4vVpCFt6vLqiaKcnQPLftePMT9yreobEPRjWGLXO0aQ2ZZlt1ictqcorglbDQFo8w1iakJaQeLl/132', '女', 2, 1, 1570792323, 'ADD_SCENE_PROFILE_LINK', NULL, NULL, NULL, '103,104,111,117', '克莱尔', '', '爱尔兰', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (54, 'oG-Lc1BeN81CTHefSTSXQgsA2vP0', '樱子希薇', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBcpWkl6KiblZa1q3Tfg3JbDIfA7Ud3B8WGeia3EKHbENzR7HG6kkBzLhUS3nj5za38EurDX67nicq0JqdpNlgcJlF/132', '女', 2, 1, 1573037001, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '100,125,103,104,111,114,115', '', '', '中国', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (55, 'oG-Lc1J8RMTOSWCJnqHkpQhygDqE', '陈思如', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSuxxrxib7x6wDxDyicBc0Csial02yDXfutXRjSwQQ5ebqic5yeJ4IpCHQN7mjF2KtibvproWr8VE3pZVNfBeoTsQNUdn/132', '女', 2, 1, 1570797557, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '100,125,103,104,111,114,115', '', '', '安道尔', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (56, 'oG-Lc1AnRokDh6va-aInzXIEdAyA', '宝莲', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauAOnicgaoia8oslmj0qwibPXhXVHia8eqM7fS6oIay7h86sxhFUJht42YT4iavtsSKzULygHhx4X0cUcdoWaCibNOkf3ia/132', '女', 2, 1, 1562594103, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '100,125,103,104,111,114,115', '上海', '', '中国', '0', '', 100, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (57, 'oG-Lc1N0aQCOuoLsO21gAoPi2BOM', '周喜杰', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIWG2CJaSuzpo6OQftH2464EcFxsLTA4tMuJ7ms9Q0L7YSkpZFz7oX6On0hIvnqO4FPzXt7KicRibgfibfmydY9layd/132', '男', 1, 1, 1570798556, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '107,111,113,114,115,100,118,119,125', '', '', '列支敦士登', '0', '', 107, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (58, 'oG-Lc1MssO4HLLSB04rvegf69rFw', 'fk', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCSsYniaQ83s8Dlapev3N3BNcEOicAcicV0I57ibibKzshicEezS1dxutY1rXicCmMC3LCTtribOw80BfCibR7Pz8ZnQGYAWeY/132', '未知', 0, 1, 1572638086, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '103,104,111,114,115,100,125', '', '', '', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (59, 'oG-Lc1Luj6352sSmY1Hbdst3Hzh8', '大禹', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBcpWkl6KiblZZ7zsqOhOf0ovryvhO4LSbz15zibO1It3TK76vdlVh1K3oQeAWdJH4Mao1k2GGW1TIjQYD1XBsZey/132', '男', 1, 1, 1570793256, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '107,111,113,114,115,100,118,119,125', '', '', '中国', '0', '', 107, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (60, 'oG-Lc1KaOl4YiHuKhxVEPdDbJ7qs', 'King', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/gqcaAFAcCStm1FdT1icq5TXWUqJwBD6w21RSOSm4ibPEzJXLwX5mJQTHMHEibW7pgzZL81dl5IMRDn5gdHTMdBnLibxJyXxQibicmt/132', '男', 1, 1, 1570876078, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '119,120,123,124,126,104,108,114,115,129,109,113,118,122,130', '广东', '深圳', '中国', '0', '', 119, 'en', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (61, 'oG-Lc1PtdtLgow9SJa6n-FsAKavU', '安之若素', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIWR3onibBn3T6myLCaPnQgocmNqa0a8D1LFQ8U7Q8EgF3LsY7HnvFhu8Yg6IeLLd4yynnWH507d85W1NbzJGGOSia/132', '女', 2, 1, 1570876426, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '103,104,111,114,115,100,125', '上海', '', '中国', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (62, 'oG-Lc1K_XgAAnZkfjKEY2cx0dg54', 'Yellow虹', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdDkCM9wc1j1aA7TY40tmfWsGaJaOT3icRxzA94oMtGedXjaVdF0KNPWy5R5S3eibvD0z8sliaj3icGfgvKwrHUuMicVE/132', '女', 2, 1, 1571670212, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '105,106,107,111,115,109,113,128,129,130,100,116,118,120,125,119,123,124,126,127', '宾夕法尼亚', '费城', '美国', '0', '', 105, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (63, 'oG-Lc1ErWq2WYljfpJqjI3Jt7Dy4', '蛐蛐儿', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBcpWkl6KiblZQvJrcD10G3WYmiaD96MlB18MrQFvmAFOceOMPbjJia8IKT7iafq92cdffgSxlFkdiauT27uMJ9cotSm/132', '女', 2, 1, 1573032366, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '109,120,123,129,130,117,121,124,127', '上海', '浦东新区', '中国', '0', '', 109, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (64, 'oG-Lc1JIlmjK93EhUq1MPN1-WnLU', '文', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBcpWkl6KiblZXq4o9bY5icJUjJYF5o9YiaDdYd4kYsPEx2jyx4QuKjiazFiaMUduVZwpHjX64z5nEHQEnEEwMLSjMuW/132', '男', 1, 1, 1569845901, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '109,113,118,129,130,106,107,111,114,115,119,124,126,127,100,120,122,123,125', '上海', '宝山', '中国', '0', '', 109, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (65, 'oG-Lc1OzhMBoNam_eD35Veb_5I68', '围观', NULL, NULL, '', '未知', 0, 1, 1571282638, 'ADD_SCENE_SEARCH', NULL, NULL, NULL, '', '', '', '', '0', '', 0, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (66, 'oG-Lc1E3hlhqPFk6U8aVNTLT_D5k', 'maple', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdBcpWkl6KiblZfV7ibhyxC4CAFHbyb2x1ricPtBhNST7RU1bGRxslMhBxhJfHPnBuUcxmwW8AHWtjhib0lFzFEpF5Wb/132', '未知', 0, 1, 1569844854, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '103,104,111,114,115,100,125', '上海', '', '中国', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (67, 'oG-Lc1DSd4YfAfJ0QN8W8VBS7QEk', 'dt', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauAOnicgaoia8osjs9JCeweIxSQDhGicE63NnbozkvsmHOicJibeicxw9rPas46icwCePeBBQibO0hbBIFy5Nia2yeXUUaOicP/132', '男', 1, 1, 1506085883, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '105,106,107,111,115,127,109,118,128,129,130,119,120,123,124,126', '上海', '静安', '中国', '0', '', 105, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (68, 'oG-Lc1G2u4ssgzql7hz7Du_aUGMg', '飞', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/Z9iazku4kibdAWtk5MOMnZZnGFicH1EHEhmB7dsREGQiaWNkelLTXtFveibbbpqKEpvaEm68jiaZh8V2JPqnqPQAuARIkeZNjY8pria/132', '男', 1, 1, 1515484605, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '123,127,100,103,113,118,125,104,108,111,114,115', 'Umm al Qaiwain', '', 'AE', '0', '', 123, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (69, 'oG-Lc1EOKhQzuHEx2wdCXYwDJrt8', '邱', NULL, 'test5', 'http://thirdwx.qlogo.cn/mmopen/HgyG1UHNxIVCzZF4EA7rlaNSVnS2ibHBpCicOLicaibbN74Jo3VyItPgEH9KXhVfx3J2IYib46L8SjcuY3JicwbbjVUmznwORRz0TE/132', '男', 1, 1, 1574837176, 'ADD_SCENE_QR_CODE', NULL, NULL, NULL, '104,108,111,114,115', '上海', '', '中国', '100001', '', 104, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user` VALUES (70, 'oG-Lc1NFjwRiaKqsHWvZxyjEo0Bc', '栀子花🌸', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauBEJUTgVbFUo05pCBOG9H3KXP8e4rr9GhR1r8IhP2aV6swvffyQIVXMtcBCLxu5buTssgBicEn6M2cgib8ttKOXBD/132', '女', 2, 1, 1574066601, 'ADD_SCENE_PROFILE_CARD', NULL, NULL, NULL, '103,104,111,114,117', '', '', '中国', '0', '', 103, 'zh_CN', NULL, 'gh_93eeb7320a9f', 0, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wx_gzh_user_tag
-- ----------------------------
DROP TABLE IF EXISTS `wx_gzh_user_tag`;
CREATE TABLE `wx_gzh_user_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tagid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `jwid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签归属公众号原始id',
  `count` int(20) NULL DEFAULT 0 COMMENT '此标签下粉丝数',
  `synctime` datetime(0) NULL DEFAULT NULL COMMENT '同步时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除（1 已删除，0 未删除，默 0）',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户编号',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_index_tagid_name_jwid_tenant_id`(`tagid`, `name`, `jwid`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '粉丝标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_gzh_user_tag
-- ----------------------------
INSERT INTO `wx_gzh_user_tag` VALUES (1, '2', '星标组', 'gh_93eeb7320a9f', 0, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (2, '100', '冲动消费', 'gh_93eeb7320a9f', 40, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (3, '101', '喜欢甜食', 'gh_93eeb7320a9f', 0, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (4, '103', '不喜欢做决定', 'gh_93eeb7320a9f', 25, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (5, '104', '相信权威', 'gh_93eeb7320a9f', 33, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (6, '105', '选择困难', 'gh_93eeb7320a9f', 18, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (7, '106', '风险规避', 'gh_93eeb7320a9f', 21, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (8, '107', '担心健康', 'gh_93eeb7320a9f', 29, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (9, '108', '容易焦虑', 'gh_93eeb7320a9f', 21, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (10, '109', '关注理财', 'gh_93eeb7320a9f', 26, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (11, '110', '怕麻烦', 'gh_93eeb7320a9f', 4, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (12, '111', '喜欢比价', 'gh_93eeb7320a9f', 54, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (13, '112', '计划未来', 'gh_93eeb7320a9f', 2, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (14, '113', '关注养生', 'gh_93eeb7320a9f', 23, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (15, '114', '决策较慢', 'gh_93eeb7320a9f', 36, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (16, '115', '从众', 'gh_93eeb7320a9f', 58, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (17, '116', '消费水平高', 'gh_93eeb7320a9f', 6, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (18, '117', '消费目标：经济', 'gh_93eeb7320a9f', 7, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (19, '118', '花钱风格：超前消费', 'gh_93eeb7320a9f', 42, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (20, '119', '与配偶表达感情多', 'gh_93eeb7320a9f', 35, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (21, '120', '消费目标：体验', 'gh_93eeb7320a9f', 30, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (22, '121', '家庭满意度高', 'gh_93eeb7320a9f', 5, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (23, '122', '花钱风格：谨慎消费', 'gh_93eeb7320a9f', 11, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (24, '123', '消费目标：情感', 'gh_93eeb7320a9f', 34, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (25, '124', '消费目标：功能', 'gh_93eeb7320a9f', 30, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (26, '125', '品牌忠诚', 'gh_93eeb7320a9f', 44, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (27, '126', '奶爸/奶妈', 'gh_93eeb7320a9f', 24, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (28, '127', '工作忙', 'gh_93eeb7320a9f', 34, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (29, '128', '理性', 'gh_93eeb7320a9f', 20, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (30, '129', '爱运动健身', 'gh_93eeb7320a9f', 22, NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `wx_gzh_user_tag` VALUES (31, '130', '广告抗拒', 'gh_93eeb7320a9f', 34, NULL, 0, 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
