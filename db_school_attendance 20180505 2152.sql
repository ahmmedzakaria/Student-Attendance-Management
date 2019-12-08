-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema db_school_attendance
--

CREATE DATABASE IF NOT EXISTS db_school_attendance;
USE db_school_attendance;

--
-- Definition of table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `attendance_id` int(10) unsigned NOT NULL auto_increment,
  `student_id` int(10) unsigned NOT NULL,
  `attendance_status` int(10) unsigned NOT NULL,
  `attendance_date` varchar(45) NOT NULL,
  `class_id` int(10) unsigned NOT NULL,
  `month_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`attendance_id`),
  KEY `FK_attendance_student_class_class_id` (`class_id`),
  KEY `FK_attendance_student_student_id` (`student_id`),
  KEY `FK_attendance_month_month_id` (`month_id`),
  CONSTRAINT `FK_attendance_month_month_id` FOREIGN KEY (`month_id`) REFERENCES `month` (`month_id`),
  CONSTRAINT `FK_attendance_student_class_class_id` FOREIGN KEY (`class_id`) REFERENCES `student_class` (`class_id`),
  CONSTRAINT `FK_attendance_student_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` (`attendance_id`,`student_id`,`attendance_status`,`attendance_date`,`class_id`,`month_id`) VALUES 
 (2,14,1,'04-29-18',8,4),
 (3,14,0,'04-29-18',8,4),
 (8,14,1,'05-04-18',8,5),
 (9,16,0,'05-04-18',8,5),
 (10,26,1,'05-04-18',8,5),
 (11,20,1,'05-04-18',7,5),
 (12,22,1,'05-04-18',7,5),
 (13,23,0,'05-04-18',7,5),
 (14,17,1,'05-04-18',6,5),
 (15,18,1,'05-04-18',6,5),
 (16,19,0,'05-04-18',6,5),
 (17,21,0,'05-04-18',6,5),
 (18,24,1,'05-04-18',6,5),
 (19,25,0,'05-04-18',6,5),
 (20,17,0,'05-05-18',6,5),
 (21,18,1,'05-05-18',6,5),
 (22,19,1,'05-05-18',6,5),
 (23,21,1,'05-05-18',6,5),
 (24,24,1,'05-05-18',6,5),
 (25,25,0,'05-05-18',6,5);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;


--
-- Definition of trigger `tBeforeAttendance`
--

DROP TRIGGER /*!50030 IF EXISTS */ `tBeforeAttendance`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `tBeforeAttendance` BEFORE INSERT ON `attendance` FOR EACH ROW BEGIN
If NEW.attendance_status=1 THEN
UPDATE db_school_attendance.attendance_report SET count_presents=count_presents+1
where student_id=NEW.student_id AND class_id=NEW.class_id AND month_id=NEW.month_id;
ELSEIF NEW.attendance_status=0 THEN
UPDATE db_school_attendance.attendance_report SET count_absents=count_absents+1
where student_id=NEW.student_id AND class_id=NEW.class_id AND month_id=NEW.month_id;
END IF;
END $$

DELIMITER ;

--
-- Definition of table `attendance_report`
--

DROP TABLE IF EXISTS `attendance_report`;
CREATE TABLE `attendance_report` (
  `attendance_report_id` int(10) unsigned NOT NULL auto_increment,
  `student_id` int(10) unsigned NOT NULL,
  `class_id` int(10) unsigned NOT NULL,
  `count_presents` int(10) unsigned NOT NULL default '0',
  `month_id` int(10) unsigned NOT NULL,
  `count_absents` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`attendance_report_id`),
  KEY `FK_attendance_report_student_student_id` (`student_id`),
  KEY `FK_attendance_report_student_class_class_id` (`class_id`),
  KEY `FK_attendance_report_month_month_id` (`month_id`),
  CONSTRAINT `FK_attendance_report_month_month_id` FOREIGN KEY (`month_id`) REFERENCES `month` (`month_id`),
  CONSTRAINT `FK_attendance_report_student_class_class_id` FOREIGN KEY (`class_id`) REFERENCES `student_class` (`class_id`),
  CONSTRAINT `FK_attendance_report_student_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance_report`
--

/*!40000 ALTER TABLE `attendance_report` DISABLE KEYS */;
INSERT INTO `attendance_report` (`attendance_report_id`,`student_id`,`class_id`,`count_presents`,`month_id`,`count_absents`) VALUES 
 (1,14,8,1,4,1),
 (3,16,8,0,4,0),
 (4,14,8,1,5,0),
 (5,16,8,0,5,1),
 (6,17,6,1,5,1),
 (7,18,6,2,5,0),
 (8,19,6,1,5,1),
 (9,20,7,1,5,0),
 (10,21,6,1,5,1),
 (11,22,7,1,5,0),
 (12,23,7,0,5,1),
 (13,24,6,2,5,0),
 (14,25,6,0,5,2),
 (15,26,8,1,5,0);
/*!40000 ALTER TABLE `attendance_report` ENABLE KEYS */;


--
-- Definition of table `class_counter`
--

DROP TABLE IF EXISTS `class_counter`;
CREATE TABLE `class_counter` (
  `class_count_id` int(10) unsigned NOT NULL auto_increment,
  `class_id` int(10) unsigned NOT NULL,
  `class_count` int(10) unsigned NOT NULL,
  `month_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`class_count_id`),
  KEY `FK_class_counter_month_month_id` (`month_id`),
  KEY `FK_class_counter_student_class_coass_id` (`class_id`),
  CONSTRAINT `FK_class_counter_month_month_id` FOREIGN KEY (`month_id`) REFERENCES `month` (`month_id`),
  CONSTRAINT `FK_class_counter_student_class_coass_id` FOREIGN KEY (`class_id`) REFERENCES `student_class` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_counter`
--

/*!40000 ALTER TABLE `class_counter` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_counter` ENABLE KEYS */;


--
-- Definition of table `month`
--

DROP TABLE IF EXISTS `month`;
CREATE TABLE `month` (
  `month_id` int(10) unsigned NOT NULL auto_increment,
  `month_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`month_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `month`
--

/*!40000 ALTER TABLE `month` DISABLE KEYS */;
INSERT INTO `month` (`month_id`,`month_name`) VALUES 
 (1,'January'),
 (2,'February'),
 (3,'March'),
 (4,'April'),
 (5,'May'),
 (6,'June'),
 (7,'July'),
 (8,'August'),
 (9,'September'),
 (10,'October'),
 (11,'November'),
 (12,'December');
/*!40000 ALTER TABLE `month` ENABLE KEYS */;


--
-- Definition of table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(10) unsigned NOT NULL auto_increment,
  `class_id` int(10) unsigned NOT NULL,
  `student_name` varchar(45) NOT NULL,
  `roll` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`student_id`),
  KEY `FK_student_student_class_class_id` (`class_id`),
  CONSTRAINT `FK_student_student_class_class_id` FOREIGN KEY (`class_id`) REFERENCES `student_class` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`student_id`,`class_id`,`student_name`,`roll`) VALUES 
 (14,8,'Mahfuze',1),
 (16,8,'iourowqioe',2),
 (17,6,'Rofique',1),
 (18,6,'Akash',2),
 (19,6,'Shorif',3),
 (20,7,'Bijoy',1),
 (21,6,'Rafsan',4),
 (22,7,'Mukta',2),
 (23,7,'Jolil',3),
 (24,6,'Lokman',5),
 (25,6,'Baten',6),
 (26,8,'Kamrul',3);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


--
-- Definition of table `student_class`
--

DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class` (
  `class_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY  (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_class`
--

/*!40000 ALTER TABLE `student_class` DISABLE KEYS */;
INSERT INTO `student_class` (`class_id`,`name`) VALUES 
 (6,'Six'),
 (7,'Seven'),
 (8,'Eight'),
 (9,'Nine'),
 (10,'Ten');
/*!40000 ALTER TABLE `student_class` ENABLE KEYS */;


--
-- Definition of table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY  (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`teacher_id`,`name`,`user_name`,`password`,`email`) VALUES 
 (1,'Abdul Bari','bari','123','bari@gmail.com');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
