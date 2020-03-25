package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static dao.DAOUtilitaire.*;

import beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
	
	private DAOFactory          daoFactory;
	private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, email, nom, mot_de_passe FROM Utilisateur WHERE email = ?";
	private static final String SQL_INSERT = "INSERT INTO Utilisateur (email, mot_de_passe, nom) VALUES (?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Utilisateur WHERE email = ?";
	private static final String SQL_UPDATE_SAME_PASSWORD = "UPDATE Utilisateur SET email=?,nom=? WHERE email = ?";
	private static final String SQL_UPDATE = "UPDATE Utilisateur SET email=?,mot_de_passe=?,nom=? WHERE email = ?";
	
    public UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
    /* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */

	@Override
	public boolean modifier(boolean samePassword,String oldEmail, Utilisateur utilisateur) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        if(samePassword)
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_SAME_PASSWORD, true, utilisateur.getEmail(),utilisateur.getUserName(),oldEmail );
	        else
	        	preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true, utilisateur.getEmail(),utilisateur.getPassWord(),utilisateur.getUserName(),oldEmail );
	        
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
    
	
	@Override
	public boolean supprimer(String email) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, email );
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
    
	@Override
	public void creer(Utilisateur utilisateur) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, utilisateur.getEmail(), utilisateur.getPassWord(), utilisateur.getUserName() );
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	        }
	        /* Récupération de l'id auto-généré par la requête d'insertion */
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
	            utilisateur.setId( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
		
	}

	@Override
	public Utilisateur trouver(String email) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Utilisateur utilisateur = new Utilisateur();

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, email );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	            utilisateur = map( resultSet );
	            return utilisateur;
	        }
	        else return null;
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

	}
	
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static Utilisateur map( ResultSet resultSet ) throws SQLException {
	    Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setId( resultSet.getLong( "id" ) );
	    utilisateur.setEmail( resultSet.getString( "email" ) );
	    utilisateur.setPassWord( resultSet.getString( "mot_de_passe" ) );
	    utilisateur.setUserName( resultSet.getString( "nom" ) );
	    return utilisateur;
	}
	
}
