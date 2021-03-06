package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.DAOUtilitaire.*;

import beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
	
	private DAOFactory          daoFactory;
	private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, email, nom, mot_de_passe,nb_parties_gagnees,nb_parties_perdues FROM Utilisateur WHERE email = ?";
	private static final String SQL_SELECT_ALL = "SELECT id, email, nom, mot_de_passe,nb_parties_gagnees,nb_parties_perdues FROM Utilisateur";
	private static final String SQL_INSERT = "INSERT INTO Utilisateur (email, mot_de_passe, nom,nb_parties_gagnees ,nb_parties_perdues) VALUES (?, ?, ?, 0, 0)";
	private static final String SQL_DELETE = "DELETE FROM Utilisateur WHERE email = ?";
	private static final String SQL_UPDATE_SAME_PASSWORD = "UPDATE Utilisateur SET email=?,nom=? WHERE email = ?";
	private static final String SQL_UPDATE = "UPDATE Utilisateur SET email=?,mot_de_passe=?,nom=? WHERE email = ?";
	private static final String SQL_UPDATE_RESULTAT_VIC = "UPDATE Utilisateur SET nb_parties_gagnees=nb_parties_gagnees+1 WHERE email = ?";
	private static final String SQL_UPDATE_RESULTAT_DEF = "UPDATE Utilisateur SET nb_parties_perdues=nb_parties_perdues+1 WHERE email = ?";
	
	private static final String DEFAITE = "defaite";
	private static final String VICTOIRE = "victoire";
	
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
	    utilisateur.setNbWonGames( resultSet.getInt( "nb_parties_gagnees" ) );
	    utilisateur.setNbLostGames( resultSet.getInt( "nb_parties_perdues" ) );
	    return utilisateur;
	}

	@Override
	public ArrayList<Utilisateur> trouverTous() {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    ArrayList<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false );
	        resultSet = preparedStatement.executeQuery();	       
	        
	        while ( resultSet.next() ) {
	        	utilisateurs.add(map(resultSet));
	        }
	        return utilisateurs;
	    } 
	    catch ( SQLException e ) {throw new DAOException( e );} 
	    finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }
	}

	@Override
	public boolean ajoutResultat(String email, String resultat) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        switch(resultat) {
	        	case VICTOIRE :
	        		preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_RESULTAT_VIC, true, email );
	        		break;
	        	case DEFAITE :
	        		preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_RESULTAT_DEF, true, email );
	        		break;
	        	
	        	default :
	        		break;
	        }
	        
	        
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
	
}
