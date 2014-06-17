package br.com.supero.framework.security.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.supero.framework.base.dao.impl.CrudDAOImpl;
import br.com.supero.framework.security.dao.UserConfirmDAO;
import br.com.supero.framework.security.entity.QUserConfirm;
import br.com.supero.framework.security.entity.UserConfirm;

@Stateless
public class UserConfirmDAOImpl extends CrudDAOImpl<String, UserConfirm, QUserConfirm>
		implements UserConfirmDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<UserConfirm> findAll() {
		return findNamed("USERCONFIRM.FIND_ALL");
	}

}