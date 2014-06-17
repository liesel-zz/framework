package br.com.supero.framework.base.test.impl;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;

import br.com.supero.framework.base.dao.exception.DaoException;
import br.com.supero.framework.base.entity.impl.BaseEntityImpl;
import br.com.supero.framework.base.facade.CrudFacade;
import br.com.supero.framework.base.test.CrudTest;

import com.mysema.query.types.path.EntityPathBase;


/**
 * 
 * @author Fernando <fernando@mksdev.com>
 * @version 0.0.1-SNAPSHOT
 * */
public abstract class CrudTestImpl<ID extends Serializable, T extends BaseEntityImpl<ID>, Q extends EntityPathBase<T>>
		implements CrudTest<ID, T, Q> {

	// Inject Logger
	@Inject
	Logger log;
	
	T currentEntity;

	/**
	 * 
	 * */
	protected abstract T getEntity();

	private CrudFacade<ID, T, Q> facade;

	/**
	 * 
	 * */
	public void setFacade(CrudFacade<ID, T, Q> facade) {
		this.facade = facade;
	}

	/**
	 * Return facade
	 * 
	 * @return CrudFacade<ID, T>
	 * 
	 * */
	public CrudFacade<ID, T, Q> getFacade() {
		return this.facade;
	}
	
	@Test
	@InSequence(1)
	public void testEntity() {
		currentEntity = getEntity();
		
		Assert.assertNotNull(currentEntity);
	}
	

	// Tests
	@Test
	@InSequence(2)
	public void save() throws DaoException {
		currentEntity = getEntity();
		currentEntity = facade.persist(currentEntity);

		Assert.assertNotNull(currentEntity.getId());

		log.info(currentEntity + " was persisted with id "
				+ currentEntity.getId());
	}

	/**
	 * Test remove Entity
	 * 
	 * */
	@Test
	@InSequence(3)
	public void remove() throws DaoException {
		currentEntity = getEntity();
		currentEntity = facade.findById(currentEntity.getId());
		
		facade.remove(currentEntity);

		Assert.assertNotNull(currentEntity.getId());

		log.info(currentEntity.getClass() + " was deleted with id "
				+ currentEntity.getId());
	}

}
