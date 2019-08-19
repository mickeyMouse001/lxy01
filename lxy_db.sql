/*
Navicat MySQL Data Transfer

Source Server         : 123.58.34.185
Source Server Version : 50505
Source Host           : 123.58.34.185:3306
Source Database       : lxy_db

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-08-19 15:27:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ad`
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad` (
  `oid` bigint(20) NOT NULL,
  `position_id` bigint(20) DEFAULT NULL COMMENT '广告位id',
  `ad_name` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '广告名称',
  `ad_cover` varchar(255) DEFAULT '',
  `ad_url` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '路径',
  `sort_val` tinyint(4) NOT NULL DEFAULT 99,
  `start_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '开启时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '截止时间',
  `del_tag` tinyint(4) DEFAULT 0 COMMENT '删除标志：1-是，0-否',
  `ins_time` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`oid`),
  KEY `position_id` (`position_id`),
  CONSTRAINT `ad_ibfk_1` FOREIGN KEY (`position_id`) REFERENCES `ad_position` (`oid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ad
-- ----------------------------
INSERT INTO `ad` VALUES ('1', '1', '我是广告一(轮播图广告)', 'https://pics3.baidu.com/feed/a5c27d1ed21b0ef4e887ffe6aa6849df80cb3e48.jpeg?token=768158a3f3e3c507a580c7fd6f10f39b&s=5A85A94445832CE559B2F519030050DB', 'https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9567341175660704884%22%7D&n_type=0&p_from=1', '99', '2019-07-08 10:54:25', '2019-07-31 10:40:19', '0', '2019-07-08 10:39:30');
INSERT INTO `ad` VALUES ('2', '2', '我是广告二（页面body广告）', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562563871263&di=b3873f235b53409c0537823a9e6087cd&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F337%2F24494.jpg', 'http://jtg.caidao.hexun.com/act/zg/sj88.html?dama27minchen&gp=000572&bd_vid=7341385945196818639', '99', '2019-07-08 10:54:24', '2019-07-31 10:45:10', '0', '2019-07-08 10:45:14');
INSERT INTO `ad` VALUES ('3', '1', '我是广告三（轮播广告）', 'https://pics5.baidu.com/feed/2cf5e0fe9925bc31f360db0aba4195b4ca137083.jpeg?token=6b9b5ec193c4758928cc40186ef1a543&s=9230622144D005D20610EF820300A085', 'https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9416088387650829079%22%7D&n_type=0&p_from=1', '99', '2019-07-08 11:20:28', '2019-07-31 11:19:04', '0', '2019-07-08 11:19:20');

-- ----------------------------
-- Table structure for `ad_position`
-- ----------------------------
DROP TABLE IF EXISTS `ad_position`;
CREATE TABLE `ad_position` (
  `oid` bigint(20) NOT NULL,
  `page_tag` char(10) CHARACTER SET utf8 DEFAULT '',
  `pos_location` char(10) CHARACTER SET utf8 DEFAULT '' COMMENT '广告位名称',
  `sort_val` tinyint(4) NOT NULL DEFAULT 99 COMMENT '广告位排序值',
  `pos_width` int(11) DEFAULT NULL COMMENT '建议宽度(pix)',
  `pos_height` int(11) DEFAULT NULL COMMENT '建议高度（pix）',
  `pos_state` tinyint(4) DEFAULT 0 COMMENT '状态：1-开启，0-关闭',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ad_position
-- ----------------------------
INSERT INTO `ad_position` VALUES ('1', 'home', 'head', '99', null, null, '1');
INSERT INTO `ad_position` VALUES ('2', 'home', 'body', '99', null, null, '1');

-- ----------------------------
-- Table structure for `attachment_store`
-- ----------------------------
DROP TABLE IF EXISTS `attachment_store`;
CREATE TABLE `attachment_store` (
  `oid` bigint(20) NOT NULL,
  `obj_id` bigint(20) DEFAULT NULL COMMENT '对象ID',
  `path` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `sort_val` tinyint(1) DEFAULT 99 COMMENT '排序值',
  `category` tinyint(1) DEFAULT 0 COMMENT '1-实名，2-分享晒图，3-图咖',
  `mod_time` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_16` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件库\r\n\r\n图咖，分享，实名图片';

-- ----------------------------
-- Records of attachment_store
-- ----------------------------
INSERT INTO `attachment_store` VALUES ('1', '1', 'http://www.muyingbao.net/lxy/img_cafe/201907/tu1.jpg', '99', '3', '2019-07-09 10:30:39');
INSERT INTO `attachment_store` VALUES ('2', '1', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562649648615&di=a2b5c2c10aee48ea9af183e8c3ef4a88&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fhb%2Fpics%2Fhv1%2F16%2F54%2F1990%2F129413536.jpg', '99', '2', '2019-07-09 10:32:59');

-- ----------------------------
-- Table structure for `comment_like`
-- ----------------------------
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like` (
  `oid` bigint(20) NOT NULL,
  `category` bit(1) DEFAULT NULL COMMENT '1-晒图，2-图咖',
  `opt_tag` bit(1) DEFAULT NULL COMMENT '1-评论，2-点赞',
  `content` varchar(255) CHARACTER SET latin1 DEFAULT NULL COMMENT '评论内容',
  `obj_id` bigint(20) DEFAULT NULL COMMENT '对象id',
  `rule_id` bigint(20) DEFAULT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `ins_time` datetime DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_18` (`obj_id`),
  KEY `FK_Reference_20` (`rule_id`),
  KEY `FK_Reference_35` (`uid`),
  CONSTRAINT `FK_Reference_17` FOREIGN KEY (`obj_id`) REFERENCES `sharing_img` (`oid`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`obj_id`) REFERENCES `img_cafe` (`oid`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`rule_id`) REFERENCES `jifen_award_rule` (`rule_id`),
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论/点赞';

-- ----------------------------
-- Records of comment_like
-- ----------------------------

-- ----------------------------
-- Table structure for `img_cafe`
-- ----------------------------
DROP TABLE IF EXISTS `img_cafe`;
CREATE TABLE `img_cafe` (
  `oid` bigint(20) NOT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `title` varchar(50) DEFAULT '',
  `describle` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `rule_id` bigint(20) DEFAULT NULL COMMENT '积分规则',
  `ins_time` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT 99 COMMENT '排序值，可人工干预排序',
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_13` (`uid`),
  KEY `FK_Reference_15` (`rule_id`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`rule_id`) REFERENCES `jifen_award_rule` (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图咖';

-- ----------------------------
-- Records of img_cafe
-- ----------------------------
INSERT INTO `img_cafe` VALUES ('1', '596627380316151808', '你想要的蓝天，我给你', null, '2', '2019-07-06 10:09:47', '99');

-- ----------------------------
-- Table structure for `img_carousel`
-- ----------------------------
DROP TABLE IF EXISTS `img_carousel`;
CREATE TABLE `img_carousel` (
  `oid` bigint(20) NOT NULL,
  `content_type` tinyint(4) DEFAULT NULL COMMENT '类型：1-图咖，2-分享',
  `obj_id` bigint(20) DEFAULT NULL COMMENT '对应的数据id',
  `sort_val` int(11) DEFAULT 99,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='轮播图';

-- ----------------------------
-- Records of img_carousel
-- ----------------------------
INSERT INTO `img_carousel` VALUES ('1', '1', '1', '99');
INSERT INTO `img_carousel` VALUES ('2', '2', '1', '99');
INSERT INTO `img_carousel` VALUES ('3', '3', '3', '99');

-- ----------------------------
-- Table structure for `jifen_award_rule`
-- ----------------------------
DROP TABLE IF EXISTS `jifen_award_rule`;
CREATE TABLE `jifen_award_rule` (
  `rule_id` bigint(20) NOT NULL,
  `category` int(1) DEFAULT 0 COMMENT '1-签到，2-图咖，3-评论',
  `jifen_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分奖励规则';

-- ----------------------------
-- Records of jifen_award_rule
-- ----------------------------
INSERT INTO `jifen_award_rule` VALUES ('1', '1', '10');
INSERT INTO `jifen_award_rule` VALUES ('2', '2', '20');
INSERT INTO `jifen_award_rule` VALUES ('3', '3', '10');

-- ----------------------------
-- Table structure for `jifen_record`
-- ----------------------------
DROP TABLE IF EXISTS `jifen_record`;
CREATE TABLE `jifen_record` (
  `uid` bigint(20) NOT NULL,
  `trade_type` bit(1) DEFAULT NULL COMMENT '1-支付，2-收入',
  `trade_count` int(11) DEFAULT NULL,
  `trade_desc` varchar(125) CHARACTER SET latin1 DEFAULT NULL,
  `ins_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jifen_record
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `oid` bigint(20) NOT NULL,
  `msg_body` varchar(255) CHARACTER SET utf8 NOT NULL,
  `push_tag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1-推送，0-不推送',
  `msg_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '1-公共消息，2-非公共消息',
  `ins_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `msg_state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '1-有效，0-无效',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '今晚8点整有流星雨', '0', '1', '2019-07-08 16:31:58', '1');
INSERT INTO `message` VALUES ('2', '每周六下午18点公布许愿结果，敬请关注', '0', '1', '2019-07-08 16:38:31', '1');

-- ----------------------------
-- Table structure for `obj_count`
-- ----------------------------
DROP TABLE IF EXISTS `obj_count`;
CREATE TABLE `obj_count` (
  `obj_id` bigint(20) NOT NULL,
  `category` bit(1) DEFAULT b'0' COMMENT '1-晒图，2-图咖,3-广告',
  `comment_count` bigint(20) DEFAULT NULL COMMENT '评论数',
  `like_count` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `browse_count` bigint(20) DEFAULT NULL COMMENT '浏览数',
  PRIMARY KEY (`obj_id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`obj_id`) REFERENCES `sharing_img` (`oid`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`obj_id`) REFERENCES `img_cafe` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='统计';

-- ----------------------------
-- Records of obj_count
-- ----------------------------

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `oid` bigint(20) NOT NULL,
  `p_code` char(20) CHARACTER SET utf8 NOT NULL,
  `p_model` char(20) CHARACTER SET utf8 NOT NULL,
  `p_name` char(20) CHARACTER SET utf8 NOT NULL,
  `p_price` int(11) NOT NULL COMMENT '价格',
  `category_id` bigint(20) NOT NULL COMMENT '产品分类ID',
  `p_cover` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '产品封面',
  `p_info_url` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '产品详情H5',
  `store_num` int(11) NOT NULL COMMENT '库存数量',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'fsddsdd', 'Iphone 6s', 'Iphone 6s', '500000', '1', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1184487066,3175236337&fm=26&gp=0.jpg', 'https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.1.777866adCGJuVT&id=579794586729&skuId=3856455121119&user_id=2616970884&cat_id=2&is_b=1&rn=d4d0a6780852026977916d87c5d83167', '100');
INSERT INTO `product` VALUES ('2', 'ERWEERR', 'Iwatch', 'Iwatch', '100000', '2', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2442984373,2417959653&fm=26&gp=0.jpg', 'https://item.jd.com/33477845521.html', '100');

-- ----------------------------
-- Table structure for `sharing_img`
-- ----------------------------
DROP TABLE IF EXISTS `sharing_img`;
CREATE TABLE `sharing_img` (
  `oid` bigint(20) NOT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `category` bit(1) DEFAULT NULL COMMENT '1-许愿成功图，2-其他',
  `obj_id` bigint(20) DEFAULT NULL,
  `title` varchar(50) DEFAULT '',
  `describle` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `ins_time` timestamp NULL DEFAULT current_timestamp(),
  `sort` int(11) DEFAULT 99 COMMENT '排序值，可人工干预排序',
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_11` (`uid`),
  KEY `FK_Reference_36` (`obj_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_Reference_36` FOREIGN KEY (`obj_id`) REFERENCES `user_wish` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='晒图';

-- ----------------------------
-- Records of sharing_img
-- ----------------------------
INSERT INTO `sharing_img` VALUES ('1', '596627380316151808', '', '1', '原来实现愿望就是这么简单呀', '', null, '99');

-- ----------------------------
-- Table structure for `sign_in`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in`;
CREATE TABLE `sign_in` (
  `oid` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `sign_date` date DEFAULT NULL COMMENT '签到日期',
  `days` bit(1) DEFAULT NULL COMMENT '连续天数',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '规则id',
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_2` (`rule_id`),
  KEY `FK_Reference_3` (`uid`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`rule_id`) REFERENCES `jifen_award_rule` (`rule_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='签到';

-- ----------------------------
-- Records of sign_in
-- ----------------------------
INSERT INTO `sign_in` VALUES ('1', '596627380316151808', '2019-07-07', '', null);

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `oid` bigint(20) NOT NULL,
  `title` varchar(50) DEFAULT '',
  `content` varchar(255) DEFAULT '',
  `positive_side_id` bigint(20) DEFAULT NULL COMMENT '正方id',
  `negative_side_id` bigint(20) DEFAULT NULL COMMENT '反方id',
  `positive_jifen_count` int(11) NOT NULL DEFAULT 0 COMMENT '正方积分数',
  `negative_jifen_count` int(11) NOT NULL DEFAULT 0 COMMENT '反方积分数',
  `victory_side` tinyint(1) DEFAULT NULL COMMENT '胜利方：0-反方，1-正方',
  `topic_state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '0-已关闭，1-进行中，2-pending（不可以投票，等待结果）,3-已结束',
  `today` date NOT NULL COMMENT '当天日期',
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_21` (`positive_side_id`),
  KEY `FK_Reference_22` (`negative_side_id`),
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`positive_side_id`) REFERENCES `topic_bet_name` (`oid`),
  CONSTRAINT `FK_Reference_22` FOREIGN KEY (`negative_side_id`) REFERENCES `topic_bet_name` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='今日话题';

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '中美贸易近期能否达成和解', '', '1', '2', '10', '5', '0', '1', '2019-07-09');

-- ----------------------------
-- Table structure for `topic_bet_name`
-- ----------------------------
DROP TABLE IF EXISTS `topic_bet_name`;
CREATE TABLE `topic_bet_name` (
  `oid` bigint(20) NOT NULL,
  `name` char(8) DEFAULT '' COMMENT '投注名称，例如：同意，不同意等',
  `type` bit(1) NOT NULL DEFAULT b'0' COMMENT '1-正方，0-反方',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic_bet_name
-- ----------------------------
INSERT INTO `topic_bet_name` VALUES ('1', '可以', '');
INSERT INTO `topic_bet_name` VALUES ('2', '不可以', '');

-- ----------------------------
-- Table structure for `topic_bet_record`
-- ----------------------------
DROP TABLE IF EXISTS `topic_bet_record`;
CREATE TABLE `topic_bet_record` (
  `oid` bigint(20) NOT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `jifen_count` int(11) DEFAULT NULL,
  `bet_name` bit(1) DEFAULT NULL COMMENT '投注方名称',
  `ins_time` datetime DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_23` (`uid`),
  KEY `FK_Reference_24` (`topic_id`),
  CONSTRAINT `FK_Reference_23` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_Reference_24` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='今日话题投注记录';

-- ----------------------------
-- Records of topic_bet_record
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL,
  `cid` varchar(100) DEFAULT '' COMMENT 'clientID,客户端id',
  `account` char(30) DEFAULT '' COMMENT '账号',
  `password` char(32) DEFAULT '' COMMENT '密码',
  `avatar` varchar(125) DEFAULT '' COMMENT '头像',
  `u_type` tinyint(1) DEFAULT 0 COMMENT '是否内部用户：0-否，1-是',
  `u_level` tinyint(1) DEFAULT 0 COMMENT '星级',
  `sex` tinyint(1) DEFAULT 0 COMMENT '性别：0-未知，1-男，2-女',
  `tel` char(12) DEFAULT '' COMMENT '手机号',
  `certification_flag` tinyint(1) DEFAULT 0 COMMENT '是否实名：1-是，0-否',
  `invitation_code` char(20) DEFAULT '' COMMENT '邀请码',
  `p_uid` bigint(20) DEFAULT NULL COMMENT '上线用户id',
  `nick_name` char(64) DEFAULT '',
  `u_state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '用户状态：0-不可用，1-可用',
  `salt` char(32) NOT NULL DEFAULT '' COMMENT '盐值，用于加密用户密码(md5(uid))',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, 'lisa', '123456', 'http://muyingbao.net/res/ico/app/user/original/201806/454651655552778240.jpg', '1', '0', '1', '13538584186', '0', '654321', null, '流星雨', '1', 'fsfdfdsfd');
INSERT INTO `user` VALUES ('596627380316151808', 'fsdf', 'mark', 'cbc12547056cdd559e87aacdc60e6889', 'http://www.muyingbao.net/lxy/default_header.png', '0', '0', '0', '13538584186', '0', 'PQAFNEAP3399', null, 'lxy-72031694', '1', 'e0452b30507895f927b8c9e9ba648af0');

-- ----------------------------
-- Table structure for `user_address`
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `oid` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `contact_name` varchar(8) CHARACTER SET latin1 DEFAULT NULL,
  `contact_phone` char(12) CHARACTER SET latin1 DEFAULT NULL,
  `address_detail` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `default_flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_7` (`uid`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_address
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` bigint(20) NOT NULL,
  `jifen_count` bigint(20) NOT NULL DEFAULT 0 COMMENT '积分数',
  `comment_count` int(11) NOT NULL DEFAULT 0 COMMENT '评论数',
  `publish_count` int(11) NOT NULL DEFAULT 0 COMMENT '发布数',
  `longitude` int(11) DEFAULT NULL COMMENT '经度',
  `latitude` int(11) DEFAULT NULL COMMENT '维度',
  `province_code` char(6) CHARACTER SET latin1 DEFAULT NULL,
  `city_code` char(6) CHARACTER SET latin1 DEFAULT NULL,
  `area_code` char(6) CHARACTER SET latin1 DEFAULT NULL,
  `last_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `login_ip` char(15) CHARACTER SET latin1 DEFAULT NULL,
  `os_type` int(11) DEFAULT NULL COMMENT '1-IOS;2-Android',
  `app_jx` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('596627380316151808', '0', '0', '0', '1234', '4321', 'p1', 'c1', 'a1', '2019-07-05 09:04:12', '127.0.0.1', '2', 'android123');

-- ----------------------------
-- Table structure for `user_label`
-- ----------------------------
DROP TABLE IF EXISTS `user_label`;
CREATE TABLE `user_label` (
  `label_id` bigint(20) NOT NULL,
  `name` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `category` bit(1) DEFAULT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_label
-- ----------------------------

-- ----------------------------
-- Table structure for `user_label_relation`
-- ----------------------------
DROP TABLE IF EXISTS `user_label_relation`;
CREATE TABLE `user_label_relation` (
  `label_id` bigint(20) DEFAULT NULL,
  `uid` bigint(20) DEFAULT NULL,
  KEY `FK_Reference_37` (`uid`),
  KEY `FK_Reference_5` (`label_id`),
  CONSTRAINT `FK_Reference_37` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`label_id`) REFERENCES `user_label` (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_label_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `user_realname`
-- ----------------------------
DROP TABLE IF EXISTS `user_realname`;
CREATE TABLE `user_realname` (
  `oid` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `certificate_category` bit(1) DEFAULT NULL COMMENT '证书类型',
  `certificate_code` char(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '证书名称',
  `wf_status` bit(1) DEFAULT NULL COMMENT '审核状态',
  `ins_time` datetime DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_8` (`uid`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户实名申请信息';

-- ----------------------------
-- Records of user_realname
-- ----------------------------

-- ----------------------------
-- Table structure for `user_third`
-- ----------------------------
DROP TABLE IF EXISTS `user_third`;
CREATE TABLE `user_third` (
  `oid` bigint(18) NOT NULL,
  `uid` bigint(18) NOT NULL COMMENT 'app_user.uid',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '用户昵称',
  `avatar` char(255) DEFAULT '' COMMENT '头像',
  `login_type` tinyint(2) NOT NULL COMMENT '登陆类型  0-账号密码，1-手机验证码，2-微信，3-qq',
  `openid` char(50) CHARACTER SET utf8mb4 NOT NULL,
  `unionid` char(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ins_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方登陆用户信息';

-- ----------------------------
-- Records of user_third
-- ----------------------------

-- ----------------------------
-- Table structure for `user_tool`
-- ----------------------------
DROP TABLE IF EXISTS `user_tool`;
CREATE TABLE `user_tool` (
  `uid` bigint(20) DEFAULT NULL,
  `tool_id` bigint(20) DEFAULT NULL,
  `store_num` int(11) DEFAULT NULL COMMENT '道具数量',
  KEY `FK_Reference_31` (`uid`),
  KEY `FK_Reference_32` (`tool_id`),
  CONSTRAINT `FK_Reference_31` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`tool_id`) REFERENCES `wish_help_tool` (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=sjis;

-- ----------------------------
-- Records of user_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `user_tool_trade_rec`
-- ----------------------------
DROP TABLE IF EXISTS `user_tool_trade_rec`;
CREATE TABLE `user_tool_trade_rec` (
  `uid` bigint(20) NOT NULL,
  `tool_id` bigint(20) NOT NULL,
  `trade_type` bit(1) DEFAULT NULL,
  `trade_count` int(11) DEFAULT NULL,
  `trade_desc` varchar(125) CHARACTER SET latin1 DEFAULT NULL,
  `ins_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tool_id`,`uid`),
  KEY `FK_Reference_34` (`uid`),
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`tool_id`) REFERENCES `wish_help_tool` (`tool_id`),
  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=sjis COMMENT='用户道具交易记录';

-- ----------------------------
-- Records of user_tool_trade_rec
-- ----------------------------

-- ----------------------------
-- Table structure for `user_wish`
-- ----------------------------
DROP TABLE IF EXISTS `user_wish`;
CREATE TABLE `user_wish` (
  `oid` bigint(20) NOT NULL COMMENT '许愿id',
  `wish_id` bigint(20) DEFAULT NULL COMMENT '愿望id',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `current_values` int(11) DEFAULT NULL COMMENT '当前愿力值',
  `status` bit(1) DEFAULT NULL COMMENT '许愿状态：0-成长中，1-成功，2-失败',
  `ins_time` datetime DEFAULT NULL COMMENT '许愿时间',
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_26` (`wish_id`),
  KEY `FK_Reference_27` (`uid`),
  CONSTRAINT `FK_Reference_26` FOREIGN KEY (`wish_id`) REFERENCES `wish` (`oid`),
  CONSTRAINT `FK_Reference_27` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=sjis COMMENT='用户许愿';

-- ----------------------------
-- Records of user_wish
-- ----------------------------
INSERT INTO `user_wish` VALUES ('1', '1', '596627380316151808', '11', '', null);

-- ----------------------------
-- Table structure for `wish`
-- ----------------------------
DROP TABLE IF EXISTS `wish`;
CREATE TABLE `wish` (
  `oid` bigint(20) NOT NULL COMMENT '愿望id',
  `wish_name` varchar(16) CHARACTER SET utf8 DEFAULT '' COMMENT '愿望名称',
  `wish_type` tinyint(1) DEFAULT 1 COMMENT '类型：0-普通，1-需排名竞争的愿望',
  `gift_id` bigint(20) DEFAULT NULL COMMENT '礼物id',
  `wish_state` tinyint(1) DEFAULT 0 COMMENT '0-未开启，1-进行中，2-已结束',
  `wish_num` int(11) NOT NULL DEFAULT 1 COMMENT '愿望数量',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `ins_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort_val` tinyint(1) DEFAULT 99 COMMENT '排序值',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_25` (`gift_id`),
  CONSTRAINT `FK_Reference_25` FOREIGN KEY (`gift_id`) REFERENCES `wish_gift` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=sjis COMMENT='愿望';

-- ----------------------------
-- Records of wish
-- ----------------------------
INSERT INTO `wish` VALUES ('1', '苹果手机', '1', '1', '1', '1', '2019-07-09 11:01:52', '2019-07-09 11:01:57', '1', '2019-07-10 11:02:14');
INSERT INTO `wish` VALUES ('2', '功能手表', '1', '2', '1', '1', '2019-07-09 11:53:36', '2019-07-09 11:53:41', '1', '2019-07-10 11:53:53');

-- ----------------------------
-- Table structure for `wish_gift`
-- ----------------------------
DROP TABLE IF EXISTS `wish_gift`;
CREATE TABLE `wish_gift` (
  `oid` bigint(20) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品id',
  `wish_value` int(11) DEFAULT NULL COMMENT '需要的愿力值',
  `name` char(8) CHARACTER SET latin1 DEFAULT NULL COMMENT '礼品名称',
  `type` bit(1) DEFAULT NULL COMMENT '1-电子消费类，2-实物类',
  `help_limit_rate` int(11) DEFAULT NULL COMMENT '助力上限比例',
  PRIMARY KEY (`oid`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `wish_gift_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`oid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=sjis COMMENT='礼品';

-- ----------------------------
-- Records of wish_gift
-- ----------------------------
INSERT INTO `wish_gift` VALUES ('1', '1', '5000', 'Iphone6S', '', '10');
INSERT INTO `wish_gift` VALUES ('2', '2', '1000', 'Iwatch', '', '10');

-- ----------------------------
-- Table structure for `wish_help_record`
-- ----------------------------
DROP TABLE IF EXISTS `wish_help_record`;
CREATE TABLE `wish_help_record` (
  `oid` bigint(20) NOT NULL,
  `help_uid` bigint(20) DEFAULT NULL COMMENT '助力用户id',
  `help_type` bit(1) DEFAULT NULL COMMENT '1-积分助力，2-道具助力',
  `make_wish_id` bigint(20) DEFAULT NULL COMMENT '许愿id',
  `tool_id` bigint(20) DEFAULT NULL,
  `wish_values` int(11) DEFAULT NULL COMMENT '愿力值',
  `ins_time` datetime DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_Reference_28` (`help_uid`),
  KEY `FK_Reference_29` (`make_wish_id`),
  KEY `FK_Reference_30` (`tool_id`),
  CONSTRAINT `FK_Reference_28` FOREIGN KEY (`help_uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`make_wish_id`) REFERENCES `user_wish` (`oid`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`tool_id`) REFERENCES `wish_help_tool` (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=sjis COMMENT='愿力值记录';

-- ----------------------------
-- Records of wish_help_record
-- ----------------------------

-- ----------------------------
-- Table structure for `wish_help_tool`
-- ----------------------------
DROP TABLE IF EXISTS `wish_help_tool`;
CREATE TABLE `wish_help_tool` (
  `tool_id` bigint(20) NOT NULL,
  `name` char(8) CHARACTER SET latin1 DEFAULT NULL COMMENT '道具名称',
  `wish_value` int(11) DEFAULT NULL COMMENT '愿力值',
  PRIMARY KEY (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=sjis COMMENT='助力道具';

-- ----------------------------
-- Records of wish_help_tool
-- ----------------------------

-- ----------------------------
-- Function structure for `pro_test`
-- ----------------------------
DROP FUNCTION IF EXISTS `pro_test`;
DELIMITER ;;
CREATE DEFINER=`lxy`@`125.93.73.90` FUNCTION `pro_test`(`p1` int) RETURNS int(11)
BEGIN
	#Routine body goes here...

	RETURN 1;
END
;;
DELIMITER ;
