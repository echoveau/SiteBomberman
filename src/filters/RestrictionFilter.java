package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RestrictionFilter
 */
@WebFilter({"/records"})
public class RestrictionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RestrictionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	    /* Cast des objets request et response */
	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	
	    /* Récupération de la session depuis la requête */
	    HttpSession session = request.getSession();
	
	    /**
	     * Si l'objet utilisateur n'existe pas dans la session en cours, alors
	     * l'utilisateur n'est pas connecté.
	     */
	    if ( session.getAttribute( "utilisateur" ) == null ) {
	        /* Redirection vers la page publique */
	    	request.getRequestDispatcher( "/connexion" ).forward( request, response );
	    } else {
	        /* Affichage de la page restreinte */
	        chain.doFilter( request, response );
	    }
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
