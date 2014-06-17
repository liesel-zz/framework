package br.com.supero.framework.security.facade.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.supero.framework.base.facade.impl.CrudFacadeImpl;
import br.com.supero.framework.security.dao.PasswordResetDAO;
import br.com.supero.framework.security.entity.PasswordReset;
import br.com.supero.framework.security.entity.QPasswordReset;
import br.com.supero.framework.security.facade.PasswordResetFacade;

@Named
public class PasswordResetFacadeImpl extends CrudFacadeImpl<String, PasswordReset, QPasswordReset> implements
		PasswordResetFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	public PasswordResetFacadeImpl(PasswordResetDAO dao) {
		super(dao);
	}

	@Override
	public List<PasswordReset> findAll() {
		return ((PasswordResetDAO) super.getDAO()).findAll();
	}
}