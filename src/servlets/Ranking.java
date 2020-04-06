package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Historique;
import beans.Utilisateur;
import dao.DAOFactory;
import dao.HistoriqueDao;
import dao.UtilisateurDao;

/**
 * Servlet implementation class Ranking
 */
@WebServlet("/ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "utilisateurs";
	public static final String ATT_RECORDS = "historiques";

	private HistoriqueDao historiqueDao;
	private UtilisateurDao utilisateurDao;

    public Ranking() {
        super();
    }
    
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO historique */
        this.historiqueDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getHistoriqueDao();
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Historique> historiques = new ArrayList<Historique>();
		historiques = historiqueDao.trouverTous();
		
		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		utilisateurs = utilisateurDao.trouverTous();
		
        request.setAttribute( ATT_RECORDS, historiques );
        request.setAttribute( ATT_USER, utilisateurs );
		this.getServletContext().getRequestDispatcher("/WEB-INF/ranking.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
