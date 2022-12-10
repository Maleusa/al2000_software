package fc.user;

public class UserInformations {
	
	private int id;
	private String password;
	
	public UserInformations(int id, String password) {
		this.id=id;
		this.password=password;
	}
	
	public boolean checkIdentity(String password) {
		return password.equals(this.password);
	}
}
