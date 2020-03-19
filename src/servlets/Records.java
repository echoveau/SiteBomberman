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

@WebServlet("/records")
public class Records extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_RECORDS = "historiques";
	
	private HistoriqueDao historiqueDao;
   
    public Records() {
        super();
    }

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.historiqueDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getHistoriqueDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute(ATT_USER);
		
		ArrayList<Historique> historiques = new ArrayList<Historique>();
		historiques = historiqueDao.trouver(utilisateur.getEmail());
		
        request.setAttribute( ATT_RECORDS, historiques );
		this.getServletContext().getRequestDispatcher("/WEB-INF/records.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
