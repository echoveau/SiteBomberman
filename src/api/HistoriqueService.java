package api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Historique;
import dao.DAOConfigurationException;
import dao.DAOFactory;
import dao.HistoriqueDao;


@Path("/historique")
public class HistoriqueService {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String FICHIER_PROPERTIES       = "/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";
	
	@POST
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Historique postUser( Historique histo ) {
		
		Properties properties = new Properties();
        String url;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
            
            DAOFactory dao = new DAOFactory(url, nomUtilisateur, motDePasse);
            HistoriqueDao histoDao =  dao.getHistoriqueDao();
    		histoDao.creer(histo);
    		
    		return histo;
    		
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }
        
    }
}
