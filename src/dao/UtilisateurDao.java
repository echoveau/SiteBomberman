package dao;

import beans.Utilisateur;

public interface UtilisateurDao {

    void creer( Utilisateur utilisateur ) throws DAOException;

    boolean trouver( String email, Utilisateur utilisateur ) throws DAOException;

}