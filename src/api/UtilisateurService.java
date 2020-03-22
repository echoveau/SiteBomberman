package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Utilisateur;
import dao.DAOFactory;
import dao.UtilisateurDao;
import dao.UtilisateurDaoImpl;

@Path("/users")
public class UtilisateurService {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	
	@GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur getUser( @PathParam("email") String email ) {
		DAOFactory dao = new DAOFactory("jdbc:mysql://localhost:3306/bomberman_site", "root", "KeEt01051208+"); 
		
		UtilisateurDao userDao =  dao.getUtilisateurDao();
		Utilisateur user =  userDao.trouver(email);
		
        return user;
    } 
}
