package br.com.supero.framework.security.facade;

import java.util.List;

import br.com.supero.framework.base.facade.CrudFacade;
import br.com.supero.framework.security.entity.PasswordReset;
import br.com.supero.framework.security.entity.QPasswordReset;

public interface PasswordResetFacade extends CrudFacade<String, PasswordReset, QPasswordReset> {
	
	// Custom Methods
	public List<PasswordReset> findAll();

}