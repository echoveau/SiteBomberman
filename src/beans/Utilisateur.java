package beans;

public class Utilisateur {
	private Long id;
	private String passWord;
	private String userName;
	private String email;
	private int nbWonGames;
	private int nbLostGames;
	private float ratio;
	
	
	public Utilisateur() {
		super();
	}
	public Utilisateur(Utilisateur utilisateur) {
		super();
		this.id = utilisateur.getId();
		this.passWord = utilisateur.getPassWord();
		this.userName = utilisateur.getUserName();
		this.email = utilisateur.getEmail();
		this.nbWonGames = utilisateur.getNbWonGames();
		this.nbLostGames = utilisateur.getNbLostGames();
		this.ratio = utilisateur.getRatio();
		
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userNAme) {
		this.userName = userNAme;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNbWonGames() {
		return nbWonGames;
	}
	public void setNbWonGames(int nbWonGames) {
		this.nbWonGames = nbWonGames;
		if(nbLostGames!=0)
			this.ratio = (float)nbWonGames/(float)nbLostGames;
		else
			this.ratio = nbWonGames;
	}
	public int getNbLostGames() {
		return nbLostGames;
	}
	public void setNbLostGames(int nbLostGames) {
		this.nbLostGames = nbLostGames;
		if(nbLostGames!=0)
			this.ratio = (float)nbWonGames/(float)nbLostGames;
		else
			this.ratio = nbWonGames;
	}
	public float getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
}
