drop database if exists myedu;
create database myedu;
use myedu;

CREATE TABLE `User` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Role` (
  `roleId` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Subject` (
  `subjectId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`subjectId`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Time` (
  `timeId` int NOT NULL AUTO_INCREMENT,
  `weekDay` varchar(45) NOT NULL,
  PRIMARY KEY (`timeId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Room` (
  `roomId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `capacity` int NOT NULL,
  PRIMARY KEY (`roomId`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `UserRole` (
  `userRoleId` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `roleId` int NOT NULL,
  PRIMARY KEY (`userRoleId`),
  UNIQUE KEY `userId_roleId_UNIQUE` (`userId`,`roleId`),
  KEY `fk_UserRole_2` (`roleId`),
  CONSTRAINT `fk_UserRole_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`),
  CONSTRAINT `fk_UserRole_2` FOREIGN KEY (`roleId`) REFERENCES `Role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Class` (
  `classId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `teacherUserId` int NOT NULL,
  `subjectId` int NOT NULL,
  `roomId` int NOT NULL,
  `timeId` int NOT NULL,
  PRIMARY KEY (`classId`),
  UNIQUE KEY `teacherUserId_subjectId_roomId_timeId_UNIQUE` (`teacherUserId`,`subjectId`,`roomId`,`timeId`),
  KEY `fk_Class_2` (`subjectId`),
  KEY `fk_Class_3` (`roomId`),
  KEY `fk_Class_4` (`timeId`),
  CONSTRAINT `fk_Class_1` FOREIGN KEY (`teacherUserId`) REFERENCES `User` (`userId`),
  CONSTRAINT `fk_Class_2` FOREIGN KEY (`subjectId`) REFERENCES `Subject` (`subjectId`),
  CONSTRAINT `fk_Class_3` FOREIGN KEY (`roomId`) REFERENCES `Room` (`roomId`),
  CONSTRAINT `fk_Class_4` FOREIGN KEY (`timeId`) REFERENCES `Time` (`timeId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Enrollment` (
  `enrollmentId` int NOT NULL AUTO_INCREMENT,
  `classId` int NOT NULL,
  `studentUserId` int NOT NULL,
  PRIMARY KEY (`enrollmentId`),
  UNIQUE KEY `classId_studentUserId_UNIQUE` (`classId`,`studentUserId`),
  KEY `fk_Enrollment_2` (`studentUserId`),
  CONSTRAINT `fk_Enrollment_1` FOREIGN KEY (`classId`) REFERENCES `Class` (`classId`),
  CONSTRAINT `fk_Enrollment_2` FOREIGN KEY (`studentUserId`) REFERENCES `User` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `myedu`.`Role` VALUES (1, "ADMIN");
INSERT INTO `myedu`.`Role` VALUES (2, "TEACHER");
INSERT INTO `myedu`.`Role`  VALUES (3, "STUDENT");

INSERT INTO `myedu`.`Subject` VALUES (1, "toan", "day la mon toan");
INSERT INTO `myedu`.`Subject` VALUES (2, "ly", "day la mon ly");
INSERT INTO `myedu`.`Subject` VALUES (3, "hoa", "day la mon hoa");
INSERT INTO `myedu`.`Subject` VALUES (4, "van", "day la mon van");
INSERT INTO `myedu`.`Subject` VALUES (5, "anh", "day la mon anh");

INSERT INTO `myedu`.`Time` VALUES (1, "T2");
INSERT INTO `myedu`.`Time` VALUES (2, "T3");
INSERT INTO `myedu`.`Time` VALUES (3, "T4");
INSERT INTO `myedu`.`Time` VALUES (4, "T5");
INSERT INTO `myedu`.`Time` VALUES (5, "T6");
INSERT INTO `myedu`.`Time` VALUES (6, "T7");

INSERT INTO `myedu`.`Room` VALUES (1, "Phong 1", 10);
INSERT INTO `myedu`.`Room` VALUES (2, "Phong 2", 20);
INSERT INTO `myedu`.`Room` VALUES (3, "Phong 3", 15);
INSERT INTO `myedu`.`Room` VALUES (4, "Phong 4", 25);
INSERT INTO `myedu`.`Room` VALUES (5, "Phong 5", 5);

INSERT INTO `myedu`.`User` VALUES (1, "admin", "admin", "Quan tri vien");
INSERT INTO `myedu`.`User` VALUES (2, "gv1", "gv1", "Giao vien 1");
INSERT INTO `myedu`.`User` VALUES (3, "gv2", "gv2", "Giao vien 2");
INSERT INTO `myedu`.`User` VALUES (4, "hs1", "hs1", "Hoc sinh 1");
INSERT INTO `myedu`.`User` VALUES (5, "hs2", "hs2", "Hoc sinh 2");
INSERT INTO `myedu`.`User` VALUES (6, "hs3", "hs3", "Hoc sinh 3");
INSERT INTO `myedu`.`User` VALUES (7, "hs4", "hs4", "Hoc sinh 4");

INSERT INTO `myedu`.`UserRole` VALUES (1, 1, 1);
INSERT INTO `myedu`.`UserRole` VALUES (2, 2, 2);
INSERT INTO `myedu`.`UserRole` VALUES (3, 3, 2);
INSERT INTO `myedu`.`UserRole` VALUES (4, 4, 3);
INSERT INTO `myedu`.`UserRole` VALUES (5, 5, 3);
INSERT INTO `myedu`.`UserRole` VALUES (6, 6, 3);
INSERT INTO `myedu`.`UserRole` VALUES (7, 7, 3);

INSERT INTO `myedu`.`Class` VALUES (1, "Lop toan thay 1", 2, 1, 1, 1);
INSERT INTO `myedu`.`Class` VALUES (2, "Lop ly thay 2", 3, 2, 2, 2);
    
INSERT INTO `myedu`.`Enrollment` VALUES (1, 1, 4);
INSERT INTO `myedu`.`Enrollment` VALUES (2, 1, 5);
INSERT INTO `myedu`.`Enrollment` VALUES (3, 1, 6);
INSERT INTO `myedu`.`Enrollment` VALUES (4, 2, 7);

