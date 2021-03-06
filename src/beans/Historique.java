package beans;

import java.text.DateFormat;
import java.util.Date;

public class Historique {
    DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
	
    private Long id;
	private Date datePartie;
	private String emailJoueur;
	private String usernameJoueur;
	private String victoire;
	private String modeJeu;
	private int nbJoueur;
	private int score;
	private String mapName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmailJoueur() {
		return emailJoueur;
	}
	public void setEmailJoueur(String emailJoueur) {
		this.emailJoueur = emailJoueur;
	}
	public String getUsernameJoueur() {
		return usernameJoueur;
	}
	public void setUsernameJoueur(String usernameJoueur) {
		this.usernameJoueur = usernameJoueur;
	}
	public String isVictoire() {
		return victoire;
	}
	public void setVictoire(String victoire) {
		this.victoire = victoire;
	}
	public String getModeJeu() {
		return modeJeu;
	}
	public void setModeJeu(String modeJeu) {
		this.modeJeu = modeJeu;
	}
	public int getNbJoueur() {
		return nbJoueur;
	}
	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public Date getDatePartie() {
		return datePartie;
	}
	public String getDatePartieFormate() {
		return shortDateFormat.format(datePartie);
	}
	public void setDatePartie(Date datePartie) {
		this.datePartie = datePartie;
	}
	
	
}
