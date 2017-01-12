package model.entity4dao;

public class ExtendedUser extends User {

	private String password;
	
	public ExtendedUser(String name, String lastName, String mail, String password){
		super(name,lastName,mail);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
