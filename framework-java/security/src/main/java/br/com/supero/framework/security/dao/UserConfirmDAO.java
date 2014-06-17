package br.com.supero.framework.security.dao;

import java.util.List;

import br.com.supero.framework.base.dao.CrudDAO;
import br.com.supero.framework.security.entity.QUserConfirm;
import br.com.supero.framework.security.entity.UserConfirm;

public interface UserConfirmDAO extends CrudDAO<String, UserConfirm, QUserConfirm> {

	// Custom Methods
	public List<UserConfirm> findAll();
}
