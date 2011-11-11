-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 10. Nov 2011 um 22:13
-- Server Version: 5.5.16
-- PHP-Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `autobday`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abdgroup`
--

CREATE TABLE IF NOT EXISTS `abdgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL,
  `name` varchar(56) NOT NULL,
  `template` text NOT NULL,/*DEFAULT NULL???*/
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abduser`
--

CREATE TABLE IF NOT EXISTS `abduser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(56) NOT NULL,
  `passwort` text NOT NULL,
  `salt` text NOT NULL,
  `name` text NOT NULL,
  `firstname` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `accountdata`
--

CREATE TABLE IF NOT EXISTS `accountdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abduser` int(11) NOT NULL,
  `username` varchar(56) NOT NULL,
  `passwort` text NOT NULL,
  `type` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `abduser` (`abduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `contact`
--

CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abdgroup` int(11) NOT NULL,
  `name` varchar(56) DEFAULT NULL,
  `firstname` varchar(56) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `mail` text NOT NULL,
  `bday` date NOT NULL,
  `active` tinyint(1) NOT NULL,
  `hashid` varchar(20) NOT NULL,
  `urlid` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `abdgroup` (`abdgroup`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `abdgroup`
--
ALTER TABLE `abdgroup`
  ADD CONSTRAINT `abdgroup_ibfk_1` FOREIGN KEY (`account`) REFERENCES `accountdata` (`id`);

--
-- Constraints der Tabelle `accountdata`
--
ALTER TABLE `accountdata`
  ADD CONSTRAINT `accountdata_ibfk_1` FOREIGN KEY (`abduser`) REFERENCES `abduser` (`id`);

--
-- Constraints der Tabelle `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`abdgroup`) REFERENCES `abdgroup` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
