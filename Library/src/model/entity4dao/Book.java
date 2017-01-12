/**
 * 
 */
package model.entity4dao;

import java.sql.Date;

/**
 * class-expert contain info about one book
 * @author user
 *
 */
public class Book extends Entity {

//	equals genre of the book 
	public static enum Genre{FANTASTIC,DETECTIVE,HISTORY};
//	describe of the book
	private String text;
//	author of the book
	private String author;
//	purchasable date
	private Date date;
//	price of the book
	private int price;
//	creator of book info in online-library
	private String creator;
//	genre of the book
	private Genre category;
	/**
	 * @param text
	 * @param autor
	 * @param date
	 * @param price
	 * @param creator
	 */
	public Book(String name, String text, String autor, Date date, int price, String creator, int category) {
		super(name);
		this.text = text;
		this.author = autor;
		this.date = date;
		this.price = price;
		this.creator = creator;
		switch(category){
		case 1:
			this.category = Genre.DETECTIVE;
			break;
		case 2:
			this.category = Genre.FANTASTIC;
			break;
		default:
			this.category = Genre.HISTORY;
		}
	}
	@Override
	public String toString() {
		return "Book [" + "name="+ name + ", text=" + text + ", autor=" + author + ", date=" + date
				+ ", price=" + price + ", creator=" + creator + ", category=" + category + "]";
	}
	public String getText() {
		return text;
	}
	public String getAuthor() {
		return author;
	}
	public Date getDate() {
		return date;
	}
	public int getPrice() {
		return price;
	}
	public String getCreator() {
		return creator;
	}
	public Genre getCategory() {
		return category;
	}
	
	public int getCategoryInt() {
		switch(this.category){
		case DETECTIVE:
			return 1;
		case FANTASTIC:
			return 2;
		default:
			return 3;
		}
	}
}
