package br.com.supero.framework.security.dao;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import br.com.supero.framework.base.dao.CrudDAO;
import br.com.supero.framework.base.dao.exception.DaoException;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.security.entity.QUser;
import br.com.supero.framework.security.entity.User;
import br.com.supero.framework.security.filter.UserFilterData;

public interface UserDAO extends CrudDAO<Long, User, QUser> {

	// Custom Methods
	public List<User> findAll();
	
	public User findById(Long id);

	public User findByLogin(String login);

	public DataPage<User> findByFilter(UserFilterData filter, Page page);
	
	public User merge(User obj) throws ValidationException,
			ConstraintViolationException, DaoException;

}