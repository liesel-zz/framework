package br.com.supero.framework.security.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.supero.framework.base.dao.impl.CrudDAOImpl;
import br.com.supero.framework.security.dao.PasswordResetDAO;
import br.com.supero.framework.security.entity.PasswordReset;
import br.com.supero.framework.security.entity.QPasswordReset;

@Stateless
public class PasswordResetDAOImpl extends CrudDAOImpl<String, PasswordReset, QPasswordReset>
		implements PasswordResetDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<PasswordReset> findAll() {
		return findNamed("PASSWORDRESET.FIND_ALL");
	}

}