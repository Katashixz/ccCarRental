/*
Navicat MySQL Data Transfer

Source Server         : planA
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : cccar

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2021-09-13 10:53:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_worker
-- ----------------------------
DROP TABLE IF EXISTS `admin_worker`;
CREATE TABLE `admin_worker` (
  `worker_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `point_num` bigint(20) DEFAULT NULL,
  `worker_name` varchar(20) DEFAULT NULL,
  `worker_password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`worker_num`),
  KEY `FK_workplace` (`point_num`),
  CONSTRAINT `FK_workplace` FOREIGN KEY (`point_num`) REFERENCES `car_point` (`point_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_worker
-- ----------------------------
INSERT INTO `admin_worker` VALUES ('1001', '1001', 'worker', '202CB962AC59075B964B07152D234B70');
INSERT INTO `admin_worker` VALUES ('1006', null, 'admin', '202CB962AC59075B964B07152D234B70');
INSERT INTO `admin_worker` VALUES ('1007', null, 'admin2', '202CB962AC59075B964B07152D234B70');
INSERT INTO `admin_worker` VALUES ('1008', '1003', 'worker2', '202CB962AC59075B964B07152D234B70');
INSERT INTO `admin_worker` VALUES ('1009', '1002', 'worker3', '698D51A19D8A121CE581499D7B701668');

-- ----------------------------
-- Table structure for car_broken
-- ----------------------------
DROP TABLE IF EXISTS `car_broken`;
CREATE TABLE `car_broken` (
  `broken_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `worker_num` bigint(20) DEFAULT NULL,
  `carinfo_num` bigint(20) DEFAULT NULL,
  `broken_time` datetime DEFAULT NULL,
  `broken_describe` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`broken_num`),
  KEY `FK_brokencar` (`carinfo_num`),
  KEY `FK_work` (`worker_num`),
  CONSTRAINT `FK_brokencar` FOREIGN KEY (`carinfo_num`) REFERENCES `car_info` (`carinfo_num`),
  CONSTRAINT `FK_work` FOREIGN KEY (`worker_num`) REFERENCES `admin_worker` (`worker_num`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_broken
-- ----------------------------
INSERT INTO `car_broken` VALUES ('1', '1001', '1005', '2021-09-11 00:00:00', '损坏');

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info` (
  `carinfo_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `point_num` bigint(20) DEFAULT NULL,
  `model_num` bigint(20) DEFAULT NULL,
  `carinfo_license` varchar(20) DEFAULT NULL,
  `carinfo_state` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`carinfo_num`),
  KEY `FK_car_belong_model` (`model_num`),
  KEY `FK_car_belong_point` (`point_num`),
  CONSTRAINT `FK_car_belong_model` FOREIGN KEY (`model_num`) REFERENCES `car_model` (`model_num`),
  CONSTRAINT `FK_car_belong_point` FOREIGN KEY (`point_num`) REFERENCES `car_point` (`point_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_info
-- ----------------------------
INSERT INTO `car_info` VALUES ('1001', '1001', '1002', '浙A88888', '在途');
INSERT INTO `car_info` VALUES ('1002', '1001', '1001', '浙B77777', '空闲');
INSERT INTO `car_info` VALUES ('1003', '1003', '1004', '浙C66666', '在途');
INSERT INTO `car_info` VALUES ('1004', '1001', '1003', '沪A55555', '空闲');
INSERT INTO `car_info` VALUES ('1005', '1001', '1005', '浙D44444', '报废');
INSERT INTO `car_info` VALUES ('1006', '1001', '1006', '浙A11111', '空闲');
INSERT INTO `car_info` VALUES ('1007', '1002', '1001', '浙A123456', '空闲');

-- ----------------------------
-- Table structure for car_model
-- ----------------------------
DROP TABLE IF EXISTS `car_model`;
CREATE TABLE `car_model` (
  `model_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_num` bigint(20) DEFAULT NULL,
  `model_name` varchar(20) DEFAULT NULL,
  `model_brand` varchar(20) DEFAULT NULL,
  `model_displacement` varchar(20) DEFAULT NULL,
  `model_gears` varchar(20) DEFAULT NULL,
  `model_seats` bigint(20) DEFAULT NULL,
  `model_price` double DEFAULT NULL,
  `model_image` longblob,
  PRIMARY KEY (`model_num`),
  KEY `FK_belong` (`type_num`),
  CONSTRAINT `FK_belong` FOREIGN KEY (`type_num`) REFERENCES `car_type` (`type_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_model
-- ----------------------------
INSERT INTO `car_model` VALUES ('1001', '1001', '朗逸', '大众', '1.4', '自动挡', '5', '1200', null);
INSERT INTO `car_model` VALUES ('1002', '1003', '昂科拉', '别克', '1.4', '自动挡', '5', '1500', null);
INSERT INTO `car_model` VALUES ('1003', '1004', '卡宴', '保时捷', '4', '自动挡', '4', '2500', null);
INSERT INTO `car_model` VALUES ('1004', '1002', '五菱宏光', '五菱', '1.5', '手动挡', '8', '800', null);
INSERT INTO `car_model` VALUES ('1005', '1001', '朗逸', '大众', '1.5', '手动挡', '5', '1000', null);
INSERT INTO `car_model` VALUES ('1006', '1004', '骷髅马', '骷髅', '4', '自动挡', '4', '2000', null);

-- ----------------------------
-- Table structure for car_point
-- ----------------------------
DROP TABLE IF EXISTS `car_point`;
CREATE TABLE `car_point` (
  `point_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `point_name` varchar(20) DEFAULT NULL,
  `point_city` varchar(50) DEFAULT NULL,
  `point_address` varchar(100) DEFAULT NULL,
  `point_phonenum` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`point_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_point
-- ----------------------------
INSERT INTO `car_point` VALUES ('1001', '杭州一号店', '杭州', '拱墅区cc租车自提点', '88889999');
INSERT INTO `car_point` VALUES ('1002', '杭州二号店', '杭州', '西湖区cc租车自提点', '88887777');
INSERT INTO `car_point` VALUES ('1003', '上海一号店', '上海', '黄浦区cc租车自提点', '89898989');
INSERT INTO `car_point` VALUES ('1004', '温州一号店', '温州', '鹿城区cc租车自提点', '88886666');

-- ----------------------------
-- Table structure for car_transfer
-- ----------------------------
DROP TABLE IF EXISTS `car_transfer`;
CREATE TABLE `car_transfer` (
  `trans_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `carinfo_num` bigint(20) DEFAULT NULL,
  `trans_time` datetime DEFAULT NULL,
  `trans_out_num` bigint(20) DEFAULT NULL,
  `trans_in_num` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`trans_num`),
  KEY `FK_car_transfer` (`carinfo_num`),
  CONSTRAINT `FK_car_transfer` FOREIGN KEY (`carinfo_num`) REFERENCES `car_info` (`carinfo_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_transfer
-- ----------------------------
INSERT INTO `car_transfer` VALUES ('1001', '1006', '2021-09-09 00:00:00', '1002', '1003');
INSERT INTO `car_transfer` VALUES ('1002', '1006', '2021-09-11 00:00:00', '1001', '1003');
INSERT INTO `car_transfer` VALUES ('1003', '1003', '2021-09-12 00:00:00', '1001', '1003');
INSERT INTO `car_transfer` VALUES ('1004', '1004', '2021-09-12 00:00:00', '1004', '1001');

-- ----------------------------
-- Table structure for car_type
-- ----------------------------
DROP TABLE IF EXISTS `car_type`;
CREATE TABLE `car_type` (
  `type_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL,
  `type_discribe` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`type_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_type
-- ----------------------------
INSERT INTO `car_type` VALUES ('1001', '经济型', '经济实惠');
INSERT INTO `car_type` VALUES ('1002', '商务型', '高端大气');
INSERT INTO `car_type` VALUES ('1003', 'SUV', '上层人士的选择');
INSERT INTO `car_type` VALUES ('1004', '豪华型', '迎娶白富美，走上人生巅峰');

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `coupon_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `coupon_describe` varchar(200) DEFAULT NULL,
  `coupon_exemption` double DEFAULT NULL,
  `coupon_start` datetime DEFAULT NULL,
  `coupon_over` datetime DEFAULT NULL,
  `coupon_point` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`coupon_num`),
  KEY `point_num` (`coupon_point`),
  CONSTRAINT `point_num` FOREIGN KEY (`coupon_point`) REFERENCES `car_point` (`point_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1001;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('1002', '满1000-300', '300', '2021-09-09 10:14:49', '2021-09-11 10:14:51', null);
INSERT INTO `coupon` VALUES ('1003', '满800-399', '399', '2021-09-12 00:00:00', '2021-09-14 00:00:00', '1001');
INSERT INTO `coupon` VALUES ('1007', '满500-300', '300', '2021-09-13 00:00:00', '2021-09-20 00:00:00', '1001');
INSERT INTO `coupon` VALUES ('1008', '满899-299', '299', '2021-09-13 00:00:00', '2021-09-17 00:00:00', '1001');

-- ----------------------------
-- Table structure for discount
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `discount_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `discount_point` bigint(20) DEFAULT NULL,
  `discount_cartype` bigint(20) DEFAULT NULL,
  `discount_dis` double(10,2) DEFAULT NULL,
  `discount_amount` double DEFAULT NULL,
  `discount_start` datetime DEFAULT NULL,
  `discount_over` datetime DEFAULT NULL,
  PRIMARY KEY (`discount_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discount
-- ----------------------------
INSERT INTO `discount` VALUES ('1001', '1001', '1004', '0.80', '100', '2021-09-24 14:36:14', '2021-09-30 14:36:16');
INSERT INTO `discount` VALUES ('1002', '1002', '1006', '0.85', '20', '2021-09-09 14:38:30', '2021-09-10 14:38:32');
INSERT INTO `discount` VALUES ('1003', '1001', '1003', '0.70', '30', '2021-09-03 16:09:58', '2021-09-22 16:10:02');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `userinfo_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `userinfo_name` varchar(50) DEFAULT NULL,
  `userinfo_sex` varchar(5) DEFAULT NULL,
  `userinfo_password` varchar(100) DEFAULT NULL,
  `userinfo_phonenum` varchar(20) DEFAULT NULL,
  `userinfo_email` varchar(100) DEFAULT NULL,
  `userinfo_city` varchar(50) DEFAULT NULL,
  `userinfo_registertime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userinfo_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1001', 'test', '男', '202CB962AC59075B964B07152D234B70', '110', '1114', '上海', '2021-09-03 11:15:01');
INSERT INTO `user_info` VALUES ('1009', '阿三', '男', '202CB962AC59075B964B07152D234B70', '', '', '温州', '2021-09-03 14:57:35');
INSERT INTO `user_info` VALUES ('1013', 'test2', '男', '202CB962AC59075B964B07152D234B70', '1', '1', '上海', '2021-09-12 14:42:43');
INSERT INTO `user_info` VALUES ('1014', 'test3', '男', '202CB962AC59075B964B07152D234B70', '145671461324', '123456789@qq.com', '上海', '2021-09-12 18:09:06');

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order` (
  `order_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `userinfo_num` bigint(20) DEFAULT NULL,
  `coupon_num` bigint(20) DEFAULT NULL,
  `discount_num` bigint(20) DEFAULT NULL,
  `carinfo_num` bigint(20) DEFAULT NULL,
  `order_get_point` bigint(20) DEFAULT NULL,
  `order_get_time` date DEFAULT NULL,
  `order_return_point` bigint(20) DEFAULT NULL,
  `order_return_time` date DEFAULT NULL,
  `order_total_time` bigint(11) DEFAULT NULL,
  `order_origin_money` double DEFAULT NULL,
  `order_total_money` double DEFAULT NULL,
  `order_state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`order_num`),
  KEY `userinfoNum` (`userinfo_num`),
  KEY `couponNum` (`coupon_num`),
  KEY `discountNum` (`discount_num`),
  KEY `carinfoNum` (`carinfo_num`),
  CONSTRAINT `carinfoNum` FOREIGN KEY (`carinfo_num`) REFERENCES `car_info` (`carinfo_num`),
  CONSTRAINT `couponNum` FOREIGN KEY (`coupon_num`) REFERENCES `coupon` (`coupon_num`),
  CONSTRAINT `discountNum` FOREIGN KEY (`discount_num`) REFERENCES `discount` (`discount_num`),
  CONSTRAINT `userinfoNum` FOREIGN KEY (`userinfo_num`) REFERENCES `user_info` (`userinfo_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1022 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES ('1001', '1009', null, null, '1004', '1001', '2021-09-09', '1001', '2021-09-15', '2', null, '5000', '进行中');
INSERT INTO `user_order` VALUES ('1012', '1001', null, null, '1005', '1002', '2021-09-09', '1001', '2021-09-11', '2', '1000', '2000', '已完成');
INSERT INTO `user_order` VALUES ('1013', '1001', '1002', null, '1004', '1001', '2021-09-09', '1004', '2021-09-10', '1', '2500', '2200', '已完成');
INSERT INTO `user_order` VALUES ('1014', '1001', '1003', null, '1003', '1001', '2021-09-11', '1001', '2021-09-11', '1', '800', '401', '已完成');
INSERT INTO `user_order` VALUES ('1015', '1001', null, null, '1005', '1001', '2021-09-09', '1001', '2021-09-09', '1', '1000', '1000', '已完成');
INSERT INTO `user_order` VALUES ('1016', '1001', null, null, '1006', '1003', '2021-09-11', '1001', '2021-09-11', '1', '2000', '2000', '已完成');
INSERT INTO `user_order` VALUES ('1017', '1001', null, null, '1006', '1003', '2021-09-13', '1001', '2021-09-13', '1', '2000', '2000', '已完成');
INSERT INTO `user_order` VALUES ('1018', '1001', null, null, '1001', '1001', '2021-09-11', null, null, null, '1500', null, '进行中');
INSERT INTO `user_order` VALUES ('1019', '1001', null, null, '1002', '1001', '2021-09-12', '1001', '2021-09-12', '1', '1200', '1200', '已完成');
INSERT INTO `user_order` VALUES ('1020', '1001', null, null, '1003', '1003', '2021-09-14', null, null, null, '800', null, '进行中');
INSERT INTO `user_order` VALUES ('1021', '1001', '1003', '1003', '1004', '1001', '2021-09-14', '1001', '2021-09-14', '1', '2500', '1750', '已完成');
