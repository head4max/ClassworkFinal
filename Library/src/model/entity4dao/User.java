/**
 * 
 */
package model.entity4dao;

/**
 * @author user
 *
 */
public class User extends Entity {

	private String lastName;
	private String mail;
	/**
	 * @param name
	 * @param lastName
	 * @param mail
	 */
	public User(String name, String lastName, String mail) {
		super(name);
		this.lastName = lastName;
		this.mail = mail;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMail() {
		return mail;
	}
}
