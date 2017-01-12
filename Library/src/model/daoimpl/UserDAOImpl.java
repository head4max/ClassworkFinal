/**
 * 
 */
package model.daoimpl;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.entity4dao.ExtendedUser;
import model.service.SQLPoolConnection;
import model.dao.UserDAO;
import model.entity4dao.User;

/**
 * @author user
 *
 */
public class UserDAOImpl implements UserDAO {

	private static String createPreparedStatement;
	private static String addPreparedStatement;
	private static String authPreparedStatement;
	
	static{
		ResourceBundle rbBook = ResourceBundle.getBundle("model.sql_bundle.sql_prepared_statement");
		
		createPreparedStatement = rbBook.getString("create_user");
		addPreparedStatement = rbBook.getString("add_user");
		authPreparedStatement = rbBook.getString("auth_user");
	}
	
	/* (non-Javadoc)
	 * @see model.dao.AbstractDAO#create()
	 */
	@Override
	public void create() {
		
		PreparedStatement psCreate = null;
		Connection con = null;
		
		try {
			con = SQLPoolConnection.getInstance().getConnection();

			psCreate = con.prepareStatement(createPreparedStatement);
			psCreate.executeUpdate();
		} catch (IOException e) {
		} catch (PropertyVetoException e) {
		} catch (SQLException e) {
		} finally {
			if(psCreate != null){
				try {
					psCreate.close();
				} catch (SQLException e) {
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see model.dao.AbstractDAO#add(model.entity4dao.Entity)
	 */
	@Override
	public boolean add(User entity) {
		
		PreparedStatement psCreate = null;
		Connection con = null;
		int res = 0;
		
		model.entity4dao.ExtendedUser eu = (ExtendedUser)entity;
		
		try {
			con = SQLPoolConnection.getInstance().getConnection();
			psCreate = con.prepareStatement(addPreparedStatement);
		
			psCreate.setString(1, eu.getName());
			psCreate.setString(2, eu.getLastName());
			psCreate.setString(3, eu.getMail());
			psCreate.setString(4, eu.getPassword());
			
			res = psCreate.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (PropertyVetoException e) {
			System.out.println(e);
		} finally {
			if(psCreate != null){
				try {
					psCreate.close();
				} catch (SQLException e) {
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return res == 1 ? true : false;
	}

	/* (non-Javadoc)
	 * @see model.dao.UserDAO#auth(java.lang.String, java.lang.String)
	 */
	@Override
	public User auth(String login, String password) {
		
		PreparedStatement psCreate = null;
		Connection con = null;
		User tempUser = null;
		
		try {
			con = SQLPoolConnection.getInstance().getConnection();
			psCreate = con.prepareStatement(authPreparedStatement);
		
			psCreate.setString(1, login);
			psCreate.setString(2, password);
			ResultSet rsUserById = psCreate.executeQuery();
			if(rsUserById.next()){
				tempUser = new User(rsUserById.getString(1), rsUserById.getString(2), rsUserById.getString(3));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (PropertyVetoException e) {
			System.out.println(e);
		} finally {
			if(psCreate != null){
				try {
					psCreate.close();
				} catch (SQLException e) {
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return tempUser;
	}

}
