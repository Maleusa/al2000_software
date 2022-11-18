package fc.user;

public class TechnicalUser extends User {
	private String login;
	private String password;
	
	public TechnicalUser(String login, String password) {
		this.login=login;
		this.password=password;
	}
	
	
	public boolean checkIdentity(String login, String password) {
		boolean id = this.login.equalsIgnoreCase(login);
		id = id && this.password.equals(password);
		return id;
	}
	
	public void setLogin(String login) {
		this.login=login;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
}
