package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Historique;
public class HistoriqueDaoImpl implements HistoriqueDao {
	private DAOFactory          daoFactory;
	private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, emailJoueur1,usernameJoueur1,emailJoueur2,usernameJoueur2,score,mapName FROM Historique WHERE (emailJoueur1 = ?) OR (emailJoueur2 = ?)";
	//private static final String SQL_INSERT = "INSERT INTO Historique (emailJoueur1,usernameJoueur1,emailJoueur2,usernameJoueur2,score,mapName) VALUES (?,?,?,?,?,?)";
	
	
	public HistoriqueDaoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
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
	        preparedStatement.setInt(2, 1);
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
		historique.setEmailJoueur1( resultSet.getString( "emailJoueur1" ) );
		historique.setUsernameJoueur1( resultSet.getString( "usernameJoueur1" ) );
		historique.setEmailJoueur2( resultSet.getString( "emailJoueur2" ) );
		historique.setUsernameJoueur2( resultSet.getString( "usernameJoueur2" ) );
		historique.setScore( resultSet.getInt( "score" ) );
		historique.setMapName( resultSet.getString( "mapName" ) );
	    return historique;
	}

}
