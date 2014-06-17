package br.com.supero.framework.security.facade;

import java.util.List;

import br.com.supero.framework.base.facade.CrudFacade;
import br.com.supero.framework.security.entity.QUserConfirm;
import br.com.supero.framework.security.entity.UserConfirm;

public interface UserConfirmFacade extends CrudFacade<String, UserConfirm, QUserConfirm> {
	
	// Custom Methods
	public List<UserConfirm> findAll();

}