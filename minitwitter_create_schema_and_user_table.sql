
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';



-- -----------------------------------------------------
-- Schema twitterdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `twitterdb` DEFAULT CHARACTER SET utf8 ;

-- -----------------------------------------------------
-- Table `twitterdb`.`user`
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `twitterdb`;

CREATE DATABASE `twitterdb`;
CREATE TABLE IF NOT EXISTS twitterdb.user (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `birthdate` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `questionNo` int(11) NOT NULL,
  `answer` varchar(45) NOT NULL,
  `profilePicture` varchar(200) DEFAULT NULL,
  `LastLogin` DATETIME,
  `dateCreated` DATETIME,
  `salt` int NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS twitterdb.tweets (
  `tweetID` INT NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `tweet` VARCHAR(5000) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `dateCreated` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tweetID`));

CREATE TABLE IF NOT EXISTS twitterdb.mentions (
  `mentionedID` INT NOT NULL,
  `tweetID` INT NOT NULL,
  `dateCreated` DATETIME
  );

CREATE TABLE IF NOT EXISTS twitterdb.hashtag(
  `hashtagID` INT NOT NULL AUTO_INCREMENT,
  `hashtagText` VARCHAR(140) NOT NULL UNIQUE,
  `hashtagCount` int(11) NOT NULL,  
  PRIMARY KEY (`hashtagID`));

CREATE TABLE IF NOT EXISTS twitterdb.tweetHashtag(
  `tweetID` INT NOT NULL,
  `hashtagID` INT NOT NULL
);

DROP TABLE IF EXISTS twitterdb.follows;
CREATE TABLE twitterdb.follows(
  `followId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `followedEmail` varchar(45) DEFAULT NULL,
  `followedDate` VARCHAR(45),
  PRIMARY KEY (`followId`)
);
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
