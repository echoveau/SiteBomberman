package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Utilisateur;
import dao.HistoriqueDao;
import dao.UtilisateurDao;

public final class ModificationForm {
	private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";
    private static final String CHAMP_OLDPASS   = "ancienMotdepasse";
    private static final String CHAMP_CONF   = "confirmation";
    private static final String CHAMP_NOM    = "nom";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_RECORDS = "historique";
    

    private static final String ALGO_CHIFFREMENT = "SHA-256";
    
    private UtilisateurDao      utilisateurDao;
    private HistoriqueDao 		historiqueDao;
    private String              resultat;
    private boolean             samePassword;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public ModificationForm(UtilisateurDao utilisateurDao, HistoriqueDao historiqueDao) {
    	this.utilisateurDao = utilisateurDao ;
    	this.historiqueDao = historiqueDao;
    	this.samePassword = false;
    }
    
    public Utilisateur modifierUtilisateur( HttpServletRequest request ) {
        String newEmail = getValeurChamp( request, CHAMP_EMAIL );
        String newMotDePasse = getValeurChamp( request, CHAMP_PASS );
        String newConfirmation = getValeurChamp( request, CHAMP_CONF );
        String newNom = getValeurChamp( request, CHAMP_NOM );
        String oldMotDePasse = getValeurChamp( request, CHAMP_OLDPASS );
        

        Utilisateur utilisateur = new Utilisateur( (Utilisateur) request.getSession().getAttribute(ATT_USER));
        
        String oldEmail = utilisateur.getEmail();

        
        try {
            validationEmail( newEmail, utilisateur);
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }

        try {
            validationMotsDePasse( oldMotDePasse, utilisateur.getPassWord() );
        } catch ( Exception e ) {
            setErreur( CHAMP_OLDPASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
        
        try {
            validationMotsDePasse( newMotDePasse, newConfirmation, utilisateur );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }

        try {
            validationNom( newNom, utilisateur );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }

        if ( erreurs.isEmpty() ) {
        	if(utilisateurDao.modifier( samePassword, oldEmail , utilisateur ) && historiqueDao.modifier( oldEmail , utilisateur )) {
                resultat = "Succès de l'inscription.";
        	}
        	else {
                resultat = "Échec de l'inscription.";
        	}
        } else {
            resultat = "Échec de l'inscription.";
        }

        return utilisateur;
    }
    
    @SuppressWarnings("unused")
	private void validationEmail( String email, Utilisateur utilisateur  ) throws Exception {
    	Utilisateur user = utilisateurDao.trouver(email);
    	
    	if(user==null) {
	        if ( email != null ) {
	            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	                throw new Exception( "Merci de saisir une adresse mail valide." );
	            }

	            utilisateur.setEmail( email );
	        }
	        else {
	        	//On garde la même adresse mail
	        }
    	}else {
    		if(!email.equals(utilisateur.getEmail()))
    			throw new Exception( "L'adresse mail renseignée dispose déjà d'un compte." );
    	}
    	
    	
    	
        
    }

    private void validationMotsDePasse( String motDePasse, String confirmation, Utilisateur utilisateur ) throws Exception {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( motDePasse.length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
            else {
                ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
                passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
                passwordEncryptor.setPlainDigest( false );
                String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );

                utilisateur.setPassWord( motDePasseChiffre );
            }
        } else {
        	if ( motDePasse == null && confirmation == null ) {
        		//On garde le même mot de passe
        		samePassword = true;        		
        	}
        	else
        		throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
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
    
    private void validationNom( String nom, Utilisateur utilisateur ) throws Exception {
    	if(nom!=null) {
            if ( nom.length() < 3 ) {
                throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
            }else {
                utilisateur.setUserName( nom );
            }
    	}
    	else {
    		//On garde le même nom
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
