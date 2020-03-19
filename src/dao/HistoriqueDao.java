package dao;

import java.util.ArrayList;

import beans.Historique;

public interface HistoriqueDao {
    //void creer( Historique historique ) throws DAOException;
    ArrayList<Historique> trouver( String email ) throws DAOException;
}
