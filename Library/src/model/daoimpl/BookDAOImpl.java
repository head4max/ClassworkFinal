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
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import model.dao.BookDAO;
import model.entity4dao.Book;
import model.entity4dao.ExtendedUser;
import model.service.SQLPoolConnection;

/**
 * @author user
 *
 */
public class BookDAOImpl implements BookDAO {

	private static String createPreparedStatement;
	private static String addPreparedStatement;
	private static String updatePreparedStatement;
	private static String getAllPreparedStatement;
	
	static{
		ResourceBundle rbBook = ResourceBundle.getBundle("model.sql_bundle.sql_prepared_statement");
		
		createPreparedStatement = rbBook.getString("create_book");
		addPreparedStatement = rbBook.getString("add_book");
		updatePreparedStatement = rbBook.getString("update_book");
		getAllPreparedStatement = rbBook.getString("update_bookgetAll_book");
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
	public boolean add(Book entity) {
		
		PreparedStatement psCreate = null;
		Connection con = null;
		int res = 0;
		
		try {
			con = SQLPoolConnection.getInstance().getConnection();
			psCreate = con.prepareStatement(addPreparedStatement);
		
			psCreate.setString(1, entity.getName());
			psCreate.setString(2, entity.getText());
			psCreate.setString(3, entity.getAuthor());
			psCreate.setDate(4, entity.getDate());
			psCreate.setInt(5, entity.getPrice());
			psCreate.setString(6, entity.getCreator());
			switch(entity.getCategory()){
				case FANTASTIC:
					psCreate.setInt(3, 1);
					break;
				case DETECTIVE:
					psCreate.setInt(3, 2);
					break;
				default:
					psCreate.setInt(3, 3);
					break;
			}
			
			res = psCreate.executeUpdate();
		} catch (SQLException e) {
		} catch (IOException e) {
		} catch (PropertyVetoException e) {
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
	 * @see model.dao.BookDAO#update(model.entity4dao.Book)
	 */
	@Override
	public boolean update(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> getAll() {
		
		PreparedStatement psCreate = null;
		Connection con = null;
		List<Book> userList= new ArrayList<Book>();
		
		try {
			con = SQLPoolConnection.getInstance().getConnection();
			psCreate = con.prepareStatement(getAllPreparedStatement);
		
			ResultSet rsUserById = psCreate.executeQuery();
			
			
			while(rsUserById.next()){
				String name = rsUserById.getString(1);
				String text = rsUserById.getString(2);
				String author = rsUserById.getString(3);
				Date datePublication = rsUserById.getDate(4);
				int price = rsUserById.getInt(5);
				String creator = rsUserById.getString(6);
				int category = rsUserById.getInt(7);
				
				userList.add(new Book(name, text, author, datePublication, price, creator, category));
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
		if(userList.size() > 0){
			return userList;
		} else {
			return null;
		}
	}
}
