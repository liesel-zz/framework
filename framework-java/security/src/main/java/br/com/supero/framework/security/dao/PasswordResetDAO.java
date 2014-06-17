package br.com.supero.framework.security.dao;

import java.util.List;

import br.com.supero.framework.base.dao.CrudDAO;
import br.com.supero.framework.security.entity.PasswordReset;
import br.com.supero.framework.security.entity.QPasswordReset;

public interface PasswordResetDAO extends CrudDAO<String, PasswordReset, QPasswordReset> {

	// Custom Methods
	public List<PasswordReset> findAll();
}