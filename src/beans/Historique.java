package beans;

public class Historique {
	private Long id;
	private String emailJoueur1;
	private String emailJoueur2;
	private String usernameJoueur1;
	private String usernameJoueur2;
	private int score;
	private String mapName;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsernameJoueur1() {
		return usernameJoueur1;
	}
	public void setUsernameJoueur1(String usernameJoueur1) {
		this.usernameJoueur1 = usernameJoueur1;
	}
	public String getUsernameJoueur2() {
		return usernameJoueur2;
	}
	public void setUsernameJoueur2(String usernameJoueur2) {
		this.usernameJoueur2 = usernameJoueur2;
	}
	public String getEmailJoueur1() {
		return emailJoueur1;
	}
	public void setEmailJoueur1(String emailJoueur1) {
		this.emailJoueur1 = emailJoueur1;
	}
	public String getEmailJoueur2() {
		return emailJoueur2;
	}
	public void setEmailJoueur2(String emailJoueur2) {
		this.emailJoueur2 = emailJoueur2;
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
}
