package model.dao;

import model.entity4dao.Entity;

public interface AbstractDAO<T extends Entity> {

	void create();
	boolean add(T entity);
}
