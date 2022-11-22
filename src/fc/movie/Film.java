package fc.movie;

public abstract class Film {
	protected String titre;
	protected int prix;
	
	public String getTitre() {
		return titre;
	}
	
	public int getPrix() {
		return prix;
	}
	
	public void setPrix(int prix) {
		this.prix=prix;
	}
}
