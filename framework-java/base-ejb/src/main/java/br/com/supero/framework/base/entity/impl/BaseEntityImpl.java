package br.com.supero.framework.base.entity.impl;

import java.io.Serializable;

import br.com.supero.framework.base.entity.BaseEntity;


/**
 * 
 * */
@SuppressWarnings("serial")
public abstract class BaseEntityImpl<ID extends Serializable> implements
		BaseEntity<ID> {

	@Override
	public boolean entityIsNew() {
		return getId() == null;
	}

}
