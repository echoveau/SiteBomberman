package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class SuppressionForm {
    private static final String CHAMP_OLDPASS   = "ancienMotdepasse";
	public static final String ATT_USER = "utilisateur";
    private UtilisateurDao utilisateurDao;
    

    private static final String ALGO_CHIFFREMENT = "SHA-256";
    
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public SuppressionForm(UtilisateurDao utilisateurDao) {
    	this.utilisateurDao = utilisateurDao ;
	}

	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public boolean supprimerUtilisateur( HttpServletRequest request ) {
        String motDePasse = getValeurChamp( request, CHAMP_OLDPASS );

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_USER);
       
        try {
        	validationMotsDePasse( motDePasse, utilisateur.getPassWord() );
        } catch ( Exception e ) {
            setErreur( CHAMP_OLDPASS, e.getMessage() );
        }
        
        System.out.println(erreurs.size());
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
            if(utilisateurDao.supprimer(utilisateur.getEmail()))
            	return true;
        } else {
            resultat = "Échec de la connexion.";
        }
        
		return false;
    }


    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new Exception( "Mot de passe incorrecte." );
            } else if ( motDePasse.length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
        	throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }

}
