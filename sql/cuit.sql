/*
MySQL Data Transfer
Source Host: localhost
Source Database: cuit
Target Host: localhost
Target Database: cuit
Date: 2017/8/1 0:16:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(50) DEFAULT NULL,
  `course_time` char(30) DEFAULT NULL,
  `week_time` char(10) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `location` char(50) DEFAULT NULL,
  `teacher_name` char(50) DEFAULT NULL,
  `description` char(254) DEFAULT NULL,
  `teacher_photo` char(254) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_data
-- ----------------------------
DROP TABLE IF EXISTS `course_data`;
CREATE TABLE `course_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `data_name` char(254) DEFAULT NULL,
  `data_path` char(200) DEFAULT NULL,
  `upload_time` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for home_work
-- ----------------------------
DROP TABLE IF EXISTS `home_work`;
CREATE TABLE `home_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `work_content` char(254) DEFAULT NULL,
  `start_time` char(100) DEFAULT NULL,
  `end_time` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `num` char(10) DEFAULT NULL,
  `reason` char(100) DEFAULT NULL,
  `score_time` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(50) DEFAULT NULL,
  `number` char(20) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `code` char(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student_course_info
-- ----------------------------
DROP TABLE IF EXISTS `student_course_info`;
CREATE TABLE `student_course_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(50) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `code` char(30) DEFAULT NULL,
  `photo` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `course` VALUES ('1', '高数', '1-20:3:1', 'both', '1', '2107', '李艳', '高等数学下', 'photo path');
INSERT INTO `course` VALUES ('2', '物理', '1-14:2:3', 'single', '1', '2017', '李艳', '大学物理下', 'wuli teacher photo path');
INSERT INTO `home_work` VALUES ('1', '1', '第一章：2,3,4', '2017.7.30', '2017.8.5');
INSERT INTO `score` VALUES ('1', '1', '1', '5', '答题', '2017/8/1');
INSERT INTO `student` VALUES ('1', 'link', '123', '13005728152', '456');
INSERT INTO `student_course_info` VALUES ('1', '1', '1');
INSERT INTO `student_course_info` VALUES ('2', '1', '2');
INSERT INTO `teacher` VALUES ('1', '李艳', '133', '123', 'liyan photo');
INSERT INTO `teacher` VALUES ('4', 'zhangsan', '138123456', '12345', null);
