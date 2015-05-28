delimiter $$

CREATE TABLE `Approval_List` (
  `idApproval_List` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `EMAIL` varchar(256) NOT NULL,
  `Pwd` varchar(45) NOT NULL,
  `CODE` varchar(45) NOT NULL,
  `Timestmp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idApproval_List`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `Artikel` (
  `idArtikel` int(11) NOT NULL AUTO_INCREMENT,
  `Bezeichnung` varchar(140) NOT NULL,
  `Beschreibung` varchar(1024) DEFAULT NULL,
  `Gewicht` float DEFAULT NULL,
  `Anzahl` float NOT NULL,
  `MwSt` float NOT NULL,
  `Preis_Brutto` float DEFAULT '0',
  `Preis_Netto` float DEFAULT '0',
  `AblaufDatum` date DEFAULT NULL,
  `idNutzer` int(11) DEFAULT NULL,
  `Zustand` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`idArtikel`),
  KEY `idNutzer` (`idNutzer`),
  CONSTRAINT `Artikel_ibfk_1` FOREIGN KEY (`idNutzer`) REFERENCES `Nutzer` (`idNutzer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `Bankverbindung` (
  `idBankverbindung` int(11) NOT NULL AUTO_INCREMENT,
  `Konto_nr` char(10) DEFAULT NULL,
  `BLZ` char(8) DEFAULT NULL,
  `Bank` varchar(45) DEFAULT NULL,
  `idNutzer` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBankverbindung`),
  KEY `idNutzer` (`idNutzer`),
  CONSTRAINT `Bankverbindung_ibfk_1` FOREIGN KEY (`idNutzer`) REFERENCES `Nutzer` (`idNutzer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `Nutzer` (
  `idNutzer` int(11) NOT NULL AUTO_INCREMENT,
  `Nachname` varchar(45) DEFAULT NULL,
  `Vorname` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(256) NOT NULL,
  `Strasse` varchar(45) DEFAULT NULL,
  `Nr` varchar(8) DEFAULT NULL,
  `PLZ` varchar(7) DEFAULT NULL,
  `Ort` varchar(45) DEFAULT NULL,
  `Land` varchar(45) DEFAULT NULL,
  `Zustand` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idNutzer`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `SEPA` (
  `idSEPA` int(11) NOT NULL AUTO_INCREMENT,
  `IBAN` char(22) DEFAULT NULL,
  `BIC_SWIFT` char(11) DEFAULT NULL,
  `idNutzer` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSEPA`),
  UNIQUE KEY `IBAN` (`IBAN`),
  KEY `idNutzer` (`idNutzer`),
  CONSTRAINT `SEPA_ibfk_1` FOREIGN KEY (`idNutzer`) REFERENCES `Nutzer` (`idNutzer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `Transaktion` (
  `idTransaktion` int(11) NOT NULL AUTO_INCREMENT,
  `idVerkaeufer` int(11) DEFAULT NULL,
  `idKaeufer` int(11) DEFAULT NULL,
  `idArtikel` int(11) DEFAULT NULL,
  `Menge` float DEFAULT NULL,
  `Preis_Bruto` float DEFAULT NULL,
  `MwSt` float DEFAULT NULL,
  `Zeitstempel` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idTransaktion`),
  KEY `idVerkaeufer` (`idVerkaeufer`),
  KEY `idKaeufer` (`idKaeufer`),
  KEY `idArtikel` (`idArtikel`),
  CONSTRAINT `Transaktion_ibfk_1` FOREIGN KEY (`idVerkaeufer`) REFERENCES `Nutzer` (`idNutzer`),
  CONSTRAINT `Transaktion_ibfk_2` FOREIGN KEY (`idKaeufer`) REFERENCES `Nutzer` (`idNutzer`),
  CONSTRAINT `Transaktion_ibfk_3` FOREIGN KEY (`idArtikel`) REFERENCES `Artikel` (`idArtikel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `User_cred` (
  `userID` int(11) NOT NULL,
  `EMAIL` varchar(256) NOT NULL,
  `Pwd` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

