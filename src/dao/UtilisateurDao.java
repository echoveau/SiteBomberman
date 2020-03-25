package dao;

import beans.Utilisateur;

public interface UtilisateurDao {
	boolean supprimer (String email);
    void creer( Utilisateur utilisateur ) throws DAOException;
    Utilisateur trouver ( String email ) throws DAOException;
	boolean modifier(boolean samePassword, String oldEmail, Utilisateur utilisateur);
}