/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2017-04-15 08:52:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ps_booked
-- ----------------------------
DROP TABLE IF EXISTS `ps_booked`;
CREATE TABLE `ps_booked` (
  `bookId` int(11) NOT NULL,
  `bookState` int(255) DEFAULT '0' COMMENT '0未预约，1预约中，2预约成功，3预约失败',
  `bookUserId` int(11) DEFAULT NULL,
  `bebookedUserId` int(11) DEFAULT NULL,
  `bookTime` datetime DEFAULT NULL,
  `bookReason` varchar(255) DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  `submitTime` datetime DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_booked
-- ----------------------------

-- ----------------------------
-- Table structure for ps_college
-- ----------------------------
DROP TABLE IF EXISTS `ps_college`;
CREATE TABLE `ps_college` (
  `collegeId` int(11) NOT NULL AUTO_INCREMENT,
  `collegeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`collegeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_college
-- ----------------------------
INSERT INTO `ps_college` VALUES ('1', '电信学院');
INSERT INTO `ps_college` VALUES ('2', '理学院');
INSERT INTO `ps_college` VALUES ('3', '机电学院');
INSERT INTO `ps_college` VALUES ('4', '经管学院');
INSERT INTO `ps_college` VALUES ('6', '设计学院');

-- ----------------------------
-- Table structure for ps_major
-- ----------------------------
DROP TABLE IF EXISTS `ps_major`;
CREATE TABLE `ps_major` (
  `majorId` int(11) NOT NULL AUTO_INCREMENT,
  `majorName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`majorId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_major
-- ----------------------------
INSERT INTO `ps_major` VALUES ('1', '计算机');
INSERT INTO `ps_major` VALUES ('2', '电子信息');
INSERT INTO `ps_major` VALUES ('3', '光电信息');
INSERT INTO `ps_major` VALUES ('4', '材料化学');
INSERT INTO `ps_major` VALUES ('5', '电气工程');
INSERT INTO `ps_major` VALUES ('7', '心理应用');
INSERT INTO `ps_major` VALUES ('8', '教育科技');
INSERT INTO `ps_major` VALUES ('9', '汉语言文学');
INSERT INTO `ps_major` VALUES ('10', '英语师范');
INSERT INTO `ps_major` VALUES ('11', '经济管理');
INSERT INTO `ps_major` VALUES ('12', '商务管理');

-- ----------------------------
-- Table structure for ps_manager
-- ----------------------------
DROP TABLE IF EXISTS `ps_manager`;
CREATE TABLE `ps_manager` (
  `managerId` int(11) NOT NULL AUTO_INCREMENT,
  `managerName` varchar(255) DEFAULT NULL,
  `mg_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`managerId`),
  KEY `mg_userId` (`mg_userId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_manager
-- ----------------------------
INSERT INTO `ps_manager` VALUES ('1', '管理员1', '60');
INSERT INTO `ps_manager` VALUES ('5', 'chenou管理员', '67');
INSERT INTO `ps_manager` VALUES ('7', '李四', '73');
INSERT INTO `ps_manager` VALUES ('8', '初级管理员', '76');

-- ----------------------------
-- Table structure for ps_manager_relation
-- ----------------------------
DROP TABLE IF EXISTS `ps_manager_relation`;
CREATE TABLE `ps_manager_relation` (
  `rmId` int(11) NOT NULL AUTO_INCREMENT,
  `managerId` int(11) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`rmId`),
  KEY `managerId` (`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_manager_relation
-- ----------------------------

-- ----------------------------
-- Table structure for ps_notice
-- ----------------------------
DROP TABLE IF EXISTS `ps_notice`;
CREATE TABLE `ps_notice` (
  `noticeId` int(11) NOT NULL AUTO_INCREMENT,
  `noticeTitle` varchar(255) DEFAULT NULL,
  `noticeContent` varchar(255) DEFAULT NULL,
  `noticeImage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_notice
-- ----------------------------
INSERT INTO `ps_notice` VALUES ('3', '小林', '测试修改', '/WEB-INF/upload/1490585674858.jpg');
INSERT INTO `ps_notice` VALUES ('5', '', '康娜最可爱了lelele', '/WEB-INF/upload/1490682319049.jpg');
INSERT INTO `ps_notice` VALUES ('6', 'xiaolin', 'tuoer', '/WEB-INF/upload/1490682615076.jpg');

-- ----------------------------
-- Table structure for ps_student
-- ----------------------------
DROP TABLE IF EXISTS `ps_student`;
CREATE TABLE `ps_student` (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `studentName` varchar(255) DEFAULT NULL,
  `studentNumber` varchar(255) DEFAULT NULL,
  `stu_userId` int(11) DEFAULT NULL,
  `collegeId` int(11) DEFAULT NULL,
  `majorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`studentId`),
  UNIQUE KEY `studentNumber` (`studentNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_student
-- ----------------------------
INSERT INTO `ps_student` VALUES ('15', '这不是学生', '66763737', '52', '2', '6');
INSERT INTO `ps_student` VALUES ('17', '刘欣', '235546576765', '58', '1', '1');

-- ----------------------------
-- Table structure for ps_teacher
-- ----------------------------
DROP TABLE IF EXISTS `ps_teacher`;
CREATE TABLE `ps_teacher` (
  `teacherId` int(11) NOT NULL AUTO_INCREMENT,
  `teacherName` varchar(255) DEFAULT NULL,
  `tc_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`teacherId`),
  KEY `tc_userId` (`tc_userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_teacher
-- ----------------------------
INSERT INTO `ps_teacher` VALUES ('3', 'lishi132', '72');
INSERT INTO `ps_teacher` VALUES ('4', '张给', '71');
INSERT INTO `ps_teacher` VALUES ('5', '王教师', '78');
INSERT INTO `ps_teacher` VALUES ('6', 'liulaoshi ', '79');

-- ----------------------------
-- Table structure for ps_testlink
-- ----------------------------
DROP TABLE IF EXISTS `ps_testlink`;
CREATE TABLE `ps_testlink` (
  `testlinkId` int(11) NOT NULL AUTO_INCREMENT,
  `linkTitle` varchar(255) DEFAULT NULL,
  `linkPath` varchar(255) DEFAULT NULL,
  `linkImg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`testlinkId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_testlink
-- ----------------------------
INSERT INTO `ps_testlink` VALUES ('1', 'MBIT', 'http://mbit01.sinaapp.com/', '/WEB-INF/upload/1491454669540.jpg');
INSERT INTO `ps_testlink` VALUES ('2', '百度', 'www.baidu.com', '/WEB-INF/upload/1491455173340.jpg');

-- ----------------------------
-- Table structure for ps_user
-- ----------------------------
DROP TABLE IF EXISTS `ps_user`;
CREATE TABLE `ps_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `mobilePhone` varchar(11) DEFAULT '',
  `userEmail` varchar(255) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `modifyTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `state` bit(1) DEFAULT NULL,
  `role` int(2) DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_user
-- ----------------------------
INSERT INTO `ps_user` VALUES ('21', '123@qq.com', '123', null, null, null, null, '2017-03-23 23:35:40', null, '1');
INSERT INTO `ps_user` VALUES ('22', '666', '123sdfdsaf', null, '25334', '13435@qq.com', null, '2017-03-23 23:35:39', null, '2');
INSERT INTO `ps_user` VALUES ('23', 'get', '123', null, null, null, null, '2017-03-27 20:39:47', null, '0');
INSERT INTO `ps_user` VALUES ('25', 'get', '123', null, null, null, null, '2017-03-23 23:35:35', null, '2');
INSERT INTO `ps_user` VALUES ('26', '666', '123sdfdsaf', null, '25334', '13435@qq.com', null, '2017-03-23 23:35:34', null, '2');
INSERT INTO `ps_user` VALUES ('27', 'sie_suguihao', '12345678', null, 'sdf@qq.com', '12@qq.com', null, '2017-03-23 23:35:43', null, '1');
INSERT INTO `ps_user` VALUES ('52', '这不是学生', '56580d84be5507769b35139f27442f61f43b70989885a170', null, '127397894', '555@qq.com', null, '2017-03-27 18:01:07', null, '2');
INSERT INTO `ps_user` VALUES ('54', 'suguihao1', '56580d84be5507769b35139f27442f61f43b70989885a170', '56580d84be5507769b35139f27442f61f43b70989885a170', '', '123@qq.com', null, '2017-03-23 02:07:33', null, '3');
INSERT INTO `ps_user` VALUES ('56', 'testsie', 'e1dc1753922e347e6658532a359e31800072e47964231b43', null, '1234566', '1428305195@qq.com', null, '2017-03-22 16:12:24', null, '3');
INSERT INTO `ps_user` VALUES ('57', 'jsfdhdf123', 'f83b1c034808438665402a1f027b77e15c3a05f171b91795', null, '12442354', '987@qq.com', null, '2017-03-23 23:36:06', null, '2');
INSERT INTO `ps_user` VALUES ('58', '刘欣', 'd48a7982931b66096658514bd9ab7a11a211e08862480575', null, null, '235546576765@ps.com', null, '2017-03-23 23:36:09', null, '2');
INSERT INTO `ps_user` VALUES ('60', '管理员1', '486a7d01ed36d9ca7a144550231a5830589b225c7f79733e', null, '', 'guanliyuan@qq.com', null, '2017-03-23 23:36:00', null, '3');
INSERT INTO `ps_user` VALUES ('63', 'guanliyuan3', '09a54193116af45f5e87d67df2fc56f2843a62887c91e906', null, '', 'guanliyuan3@qq.com', null, '2017-03-27 20:39:48', null, '0');
INSERT INTO `ps_user` VALUES ('64', '呀呀呀', '167661b4603bc4152f381b01977173c72d38a0505443a376', null, '', '13249692@ps.com', null, null, null, '0');
INSERT INTO `ps_user` VALUES ('65', '呀呀呀87', '07f200c5ba4494d54400340c035b7ed9478fb49644627168', null, '', '13249692@ps.com', null, null, null, '1');
INSERT INTO `ps_user` VALUES ('67', 'chenou', '72203833764649a66b546b05b9a38a634f6eb47187e18869', null, '1493895', 'chenou@qq.com', null, '2017-03-27 21:07:17', null, '3');
INSERT INTO `ps_user` VALUES ('69', '张', '12345678', null, '90827434', 'zhang@ps.com', null, null, null, '3');
INSERT INTO `ps_user` VALUES ('70', '张三', '12345678', null, '90827434', 'zhangsan@ps.com', null, null, null, '2');
INSERT INTO `ps_user` VALUES ('71', '张给', '12345678', null, '23599', 'zhanggei@qq.com', null, '2017-03-27 22:21:27', null, '0');
INSERT INTO `ps_user` VALUES ('72', 'lishi132', '12345678', null, '90827434', 'zhangsan@ps.com', null, null, null, '2');
INSERT INTO `ps_user` VALUES ('73', '李四', '12345678', null, '90827434', 'Lisi@ps.com', null, '2017-03-27 22:02:37', null, '3');
INSERT INTO `ps_user` VALUES ('75', 'lishi132xuesheng', '12345678', null, '90827434', 'uesheng@ps.com', null, null, null, '1');
INSERT INTO `ps_user` VALUES ('76', '初级管理员', '12345678', null, '12345', 'chuji@ps.com', null, null, null, '3');
INSERT INTO `ps_user` VALUES ('78', '王教师', '93b57494459a65010615881d79991558bf4bc4cd3368b799', null, '88889', 'WANG@qq.com', null, '2017-03-28 16:33:26', null, '2');
INSERT INTO `ps_user` VALUES ('79', 'liulaoshi ', '790257836889f31a8b84c37ae3d002272124750379114620', null, null, 'liulai@qq.com', null, null, null, '2');

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `relationid` int(11) NOT NULL AUTO_INCREMENT,
  `tagid` int(11) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`relationid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relation
-- ----------------------------
INSERT INTO `relation` VALUES ('1', '1', null);
INSERT INTO `relation` VALUES ('2', '2', '1');
INSERT INTO `relation` VALUES ('3', '3', null);
INSERT INTO `relation` VALUES ('4', '4', '2');
INSERT INTO `relation` VALUES ('5', '5', '7');
INSERT INTO `relation` VALUES ('6', '6', '2');
INSERT INTO `relation` VALUES ('7', '7', null);
INSERT INTO `relation` VALUES ('8', '8', '7');
INSERT INTO `relation` VALUES ('9', '9', null);
INSERT INTO `relation` VALUES ('10', '10', '9');
INSERT INTO `relation` VALUES ('11', '11', '9');
INSERT INTO `relation` VALUES ('12', '12', '9');
INSERT INTO `relation` VALUES ('13', '13', '15');
INSERT INTO `relation` VALUES ('14', '14', '14');
INSERT INTO `relation` VALUES ('15', '15', null);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '1');
INSERT INTO `tag` VALUES ('2', 'ooooo');
INSERT INTO `tag` VALUES ('3', '2');
INSERT INTO `tag` VALUES ('4', 'biubiub');
INSERT INTO `tag` VALUES ('5', '是的');
INSERT INTO `tag` VALUES ('6', '哇哦');
INSERT INTO `tag` VALUES ('7', '喵喵喵');
INSERT INTO `tag` VALUES ('8', '？？？');
INSERT INTO `tag` VALUES ('9', '简史');
INSERT INTO `tag` VALUES ('10', '未来简史');
INSERT INTO `tag` VALUES ('11', '人类简史');
INSERT INTO `tag` VALUES ('12', '时间简史');
INSERT INTO `tag` VALUES ('13', '培训');
INSERT INTO `tag` VALUES ('14', 'EMP');
INSERT INTO `tag` VALUES ('15', '赛意');
