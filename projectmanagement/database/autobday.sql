-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 09. Dez 2011 um 12:00
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
-- Tabellenstruktur für Tabelle `abdaccount`
--

CREATE TABLE IF NOT EXISTS `abdaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abduser` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `passwort` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `abduser` (`abduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abdcontact`
--

CREATE TABLE IF NOT EXISTS `abdcontact` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `mail` varchar(255) NOT NULL,
  `bday` date NOT NULL,
  `hashid` varchar(255) NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abdgroup`
--

CREATE TABLE IF NOT EXISTS `abdgroup` (
  `id` varchar(255) NOT NULL,
  `account` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `template` text NOT NULL,
  `active` tinyint(1) NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abdgrouptocontact`
--

CREATE TABLE IF NOT EXISTS `abdgrouptocontact` (
  `group1` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`group1`,`contact`),
  KEY `contact` (`contact`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abduser`
--

CREATE TABLE IF NOT EXISTS `abduser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `passwort` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `abdaccount`
--
ALTER TABLE `abdaccount`
  ADD CONSTRAINT `abdaccount_ibfk_1` FOREIGN KEY (`abduser`) REFERENCES `abduser` (`id`);

--
-- Constraints der Tabelle `abdgroup`
--
ALTER TABLE `abdgroup`
  ADD CONSTRAINT `abdgroup_ibfk_1` FOREIGN KEY (`account`) REFERENCES `abdaccount` (`id`);

--
-- Constraints der Tabelle `abdgrouptocontact`
--
ALTER TABLE `abdgrouptocontact`
  ADD CONSTRAINT `abdgrouptocontact_ibfk_2` FOREIGN KEY (`contact`) REFERENCES `abdcontact` (`id`),
  ADD CONSTRAINT `abdgrouptocontact_ibfk_1` FOREIGN KEY (`group1`) REFERENCES `abdgroup` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;