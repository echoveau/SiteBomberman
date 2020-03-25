package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import dao.DAOFactory;
import dao.HistoriqueDao;
import dao.UtilisateurDao;
import forms.ConnexionForm;
import forms.ModificationForm;

/**
 * Servlet implementation class changeAccount
 */
@WebServlet("/changeAccount")
public class ChangeAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
	public static final String CONF_DAO_FACTORY = "daofactory";
    private UtilisateurDao utilisateurDao;
    private HistoriqueDao historiqueDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
        this.historiqueDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getHistoriqueDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/changeAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ModificationForm form = new ModificationForm(utilisateurDao,historiqueDao);
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Utilisateur newUser = form.modifierUtilisateur( request );
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        
		if(form.getErreurs().isEmpty()) {		
	        //PASSAGE EN SESSION de l'utilisateur
			HttpSession session = request.getSession();
			session.setAttribute(ATT_USER, newUser);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(request, response);
		}
		else
			this.getServletContext().getRequestDispatcher("/WEB-INF/changeAccount.jsp").forward(request, response);
	}

}
