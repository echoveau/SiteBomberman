-- Fichier de mise en place de la base de donnée pour le Site Bomberman

--GRANT ALL ON bomberman_site.* TO 'java'@'localhost' IDENTIFIED BY 'KeEt01051208';
--CREATE DATABASE bomberman_site DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- use bomebrman_site

-- On drop les tables déjà existante 
DROP TABLE bomberman_site.Utilisateur;
DROP TABLE bomberman_site.Historique;

-- Création de la tables Utilisateur :
CREATE TABLE  bomberman_site.Utilisateur (
 id INT( 11 ) NOT NULL AUTO_INCREMENT ,
 email VARCHAR( 60 ) NOT NULL ,
 mot_de_passe CHAR( 56 ) NOT NULL ,
 nom VARCHAR( 20 ) NOT NULL , 
 nb_parties_gagnees INT( 11 ) NOT NULL,
 nb_parties_perdues INT( 11 ) NOT NULL,
 PRIMARY KEY ( id ),
 UNIQUE ( email )
) ENGINE = INNODB;

-- Création de la table Historique :
CREATE TABLE  bomberman_site.Historique (
 id INT( 11 ) NOT NULL AUTO_INCREMENT ,
 datePartie TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 emailJoueur VARCHAR( 60 ) NOT NULL ,
 usernameJoueur VARCHAR( 60 ) NOT NULL ,
 victoire VARCHAR( 60 ) NOT NULL ,
 modeJeu VARCHAR( 60 ),
 nbJoueur INT(11),
 score INT(11) NOT NULL,
 mapName VARCHAR( 60 ) NOT NULL,
 PRIMARY KEY ( id )
) ENGINE = INNODB;
