package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Utilisateur;
import dao.UtilisateurDao;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";
    private UtilisateurDao utilisateurDao;
    

    private static final String ALGO_CHIFFREMENT = "SHA-256";
    
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public ConnexionForm(UtilisateurDao utilisateurDao) {
    	this.utilisateurDao = utilisateurDao ;
	}

	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Utilisateur verifierUtilisateur( HttpServletRequest request ) {
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();
        
        if(!utilisateurDao.existe(email)) {
        	setErreur( CHAMP_EMAIL, "Votre adresse n'est pas valide" );
        }
        else {
            utilisateur = utilisateurDao.trouver(email);
            System.out.println(email+"   ------    "+utilisateur.getEmail());

            try {
                validationEmail( email, utilisateur.getEmail() );
            } catch ( Exception e ) {
                setErreur( CHAMP_EMAIL, e.getMessage() );
            }
            utilisateur.setEmail( email );

            try {
                validationMotsDePasse( motDePasse, utilisateur.getPassWord() );
            } catch ( Exception e ) {
                setErreur( CHAMP_PASS, e.getMessage() );
            }
            utilisateur.setPassWord( motDePasse );
        }
        
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return utilisateur;
    }
    
    private void validationEmail( String email, String emailDB ) throws Exception {
        if ( email != null ) {
            if ( email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                if(!email.equals(emailDB)) {
                	throw new Exception( "Votre adresse n'est pas valide" );
                }
            }
            else {
            	throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
    }

    private void validationMotsDePasse( String motDePasse, String motDePasseDB) throws Exception {
       if ( !(motDePasse.length() < 3) ) {
           //Verification du mot de passe
           ConfigurablePasswordEncryptor passwordCheck = new ConfigurablePasswordEncryptor();
           passwordCheck.setAlgorithm( ALGO_CHIFFREMENT );
           passwordCheck.setPlainDigest( false );
           
    	   if(!passwordCheck.checkPassword(motDePasse, motDePasseDB))
               throw new Exception( "Mot de passe invalide." );
            
       } else {
            throw new Exception( "Le mot de passe doit avoir plus de 3 caractères." );
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