package br.com.supero.framework.security.facade.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.supero.framework.base.facade.impl.CrudFacadeImpl;
import br.com.supero.framework.security.dao.UserConfirmDAO;
import br.com.supero.framework.security.entity.QUserConfirm;
import br.com.supero.framework.security.entity.UserConfirm;
import br.com.supero.framework.security.facade.UserConfirmFacade;

@Named
public class UserConfirmFacadeImpl extends CrudFacadeImpl<String, UserConfirm, QUserConfirm> implements
		UserConfirmFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	public UserConfirmFacadeImpl(UserConfirmDAO dao) {
		super(dao);
	}

	@Override
	public List<UserConfirm> findAll() {
		return ((UserConfirmDAO) super.getDAO()).findAll();
	}
}