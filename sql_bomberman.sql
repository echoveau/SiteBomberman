-- Fichier de mise en place de la base de donnée pour le Site Bomberman

--GRANT ALL ON bomberman_site.* TO 'java'@'localhost' IDENTIFIED BY 'KeEt01051208';
--CREATE DATABASE bomberman_site DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- use bomebrman_site

-- On drop les tables déjà existante 
DROP TABLE bomberman_site.Utilisateur;

-- Création de la tables Utilisateur :
CREATE TABLE  bomberman_site.Utilisateur (
 id INT( 11 ) NOT NULL AUTO_INCREMENT ,
 email VARCHAR( 60 ) NOT NULL ,
 mot_de_passe CHAR( 56 ) NOT NULL ,
 nom VARCHAR( 20 ) NOT NULL ,
 PRIMARY KEY ( id ),
 UNIQUE ( email )
) ENGINE = INNODB;

-- Création de la table Historique :
CREATE TABLE  bomberman_site.Historique (
 id INT( 11 ) NOT NULL AUTO_INCREMENT ,
 emailJoueur1 VARCHAR( 60 ) NOT NULL ,
 usernameJoueur1 VARCHAR( 60 ) NOT NULL ,
 emailJoueur2 VARCHAR( 60 ),
 usernameJoueur2 VARCHAR( 60 ),
 score INT(11) NOT NULL,
 mapName VARCHAR( 60 ) NOT NULL,
 PRIMARY KEY ( id )
) ENGINE = INNODB;