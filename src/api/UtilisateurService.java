package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Utilisateur;

@Path("/users")
public class UtilisateurService {
	@GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser( @PathParam("email") String email ) {
//		UtilisateurDAO userDao = new
//		Utilisateur user = ;
        return email;
    } 
}
