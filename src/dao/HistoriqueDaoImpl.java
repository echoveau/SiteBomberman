package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Historique;
import beans.Utilisateur;
public class HistoriqueDaoImpl implements HistoriqueDao {
	private DAOFactory          daoFactory;
	private static final String SQL_SELECT_ALL = "SELECT * from Historique ORDER BY score desc, usernameJoueur";
	private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, datePartie, emailJoueur, usernameJoueur, victoire, modeJeu, nbJoueur, score, mapName FROM Historique WHERE (emailJoueur = ?)";
	private static final String SQL_INSERT = "INSERT INTO Historique (emailJoueur, usernameJoueur, victoire, modeJeu, nbJoueur, score, mapName) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE Historique SET emailJoueur=?,usernameJoueur=? WHERE emailJoueur = ?";
	
	
	public HistoriqueDaoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	
	@Override
	public boolean modifier(String oldEmail, Utilisateur utilisateur) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true, utilisateur.getEmail(),utilisateur.getUserName(),oldEmail );
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) 
	        	return false;
	        else
	        	return true;
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
	}
	
	
	/**
	 * Methode de création d'élément dans l'historique
	 */
	@Override
	public void creer(Historique historique) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, historique.getEmailJoueur(), historique.getUsernameJoueur(), historique.isVictoire(), historique.getModeJeu(), historique.getNbJoueur(), historique.getScore(), historique.getMapName());
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la création d'une valeur dans l'historique, aucune ligne ajoutée dans la table." );
	        }
	        /* Récupération de l'id auto-généré par la requête d'insertion */
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
	        	historique.setId( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Échec de la création de l'historique en base, aucun ID auto-généré retourné." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
		
	}

	
	@Override
	public ArrayList<Historique> trouverTous() throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    ArrayList<Historique> historiques= new ArrayList<Historique>();

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false );
	        resultSet = preparedStatement.executeQuery();	       
	        
	        while ( resultSet.next() ) {
	        	historiques.add(map(resultSet));
	        }
	        return historiques;
	    } 
	    catch ( SQLException e ) {throw new DAOException( e );} 
	    finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }
	}	
	
	
	
	
	@Override
	public ArrayList<Historique> trouver(String email) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    ArrayList<Historique> historiques= new ArrayList<Historique>();

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, email );
	        resultSet = preparedStatement.executeQuery();	       
	        
	        
	        while ( resultSet.next() ) {
	        	historiques.add(map(resultSet));
	        }
	        return historiques;
	    } 
	    catch ( SQLException e ) {throw new DAOException( e );} 
	    finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }
	}
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table Historique (un
	 * ResultSet) et le bean Historique.
	 */
	private static Historique map( ResultSet resultSet ) throws SQLException {
		Historique historique = new Historique();
		historique.setId( resultSet.getLong( "id" ) );
		historique.setDatePartie(resultSet.getTimestamp("datePartie"));
		historique.setEmailJoueur( resultSet.getString( "emailJoueur" ) );
		historique.setUsernameJoueur( resultSet.getString( "usernameJoueur" ) );
		historique.setVictoire( resultSet.getString( "victoire" ) );
		historique.setModeJeu( resultSet.getString( "modeJeu" ) );
		historique.setNbJoueur( resultSet.getInt( "nbJoueur" ) );
		historique.setScore( resultSet.getInt( "score" ) );
		historique.setMapName( resultSet.getString( "mapName" ) );
	    return historique;
	}

}
