package br.com.supero.framework.base.test;

import java.io.Serializable;

import br.com.supero.framework.base.dao.exception.DaoException;
import br.com.supero.framework.base.entity.BaseEntity;

import com.mysema.query.types.path.EntityPathBase;


/**
 * Crud Test Interface
 * 
 * @author Fernando <fernando@mksdev.com>
 * @version 0.0.1-SNAPSHOT
 * 
 * */
public interface CrudTest<ID extends Serializable,T extends BaseEntity<ID>, Q extends EntityPathBase<T>> {
	
	//FIXME validar mais metodos do CrudFacade por enquanto so testes basicos
	
	/**
	 * 
	 * */
	//public T find(ID id);

	/**
	 * 
	 * */
	//public T persist(T t) throws DaoException;

	/**
	 * 
	 */
	public void save() throws DaoException;
	
	/**
	 * 
	 * */
	public void remove() throws DaoException;

	/**
	 * 
	 * */
	//public T merge(T t) throws DaoException;
	
}
