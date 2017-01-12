/**
 * 
 */
package model.dao;

import model.entity4dao.User;

/**
 * interface with User unique method
 * @author user
 *
 */
public interface UserDAO extends AbstractDAO<User> {

	/**
	 * check User for authorize
	 * @param login - user's mail
	 * @param password - user's password
	 * @return {@link User} if authorize successful, null - another way
	 */
	User auth(String login, String password);
}
