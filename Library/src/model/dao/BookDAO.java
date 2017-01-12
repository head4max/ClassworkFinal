package model.dao;

import java.util.List;

import model.entity4dao.Book;

/**
 * interface with Book unique method
 * @author user
 *
 */
public interface BookDAO extends AbstractDAO<Book> {
	
	/**
	 * boolean update({@link Book})
	 * @param book - new info about book
	 * @return true if update was successful
	 */
	 boolean update(Book book);
	 
	 List<Book> getAll();
}
