package dao;

import java.util.ArrayList;

import beans.Historique;
import beans.Utilisateur;

public interface HistoriqueDao {
    void creer( Historique historique ) throws DAOException;
    ArrayList<Historique> trouver( String email ) throws DAOException;
    ArrayList<Historique> trouverTous() throws DAOException;
	boolean modifier(String oldEmail, Utilisateur utilisateur);
}
