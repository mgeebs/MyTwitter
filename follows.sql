USE `twitterdb` ;

DROP TABLE IF EXISTS `follows`;
CREATE TABLE `follows` (
  `followId` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `emailFollowing` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`followId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/