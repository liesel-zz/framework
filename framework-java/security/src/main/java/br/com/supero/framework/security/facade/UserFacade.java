package br.com.supero.framework.security.facade;

import java.util.List;

import br.com.supero.framework.base.facade.CrudFacade;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.security.entity.QUser;
import br.com.supero.framework.security.entity.User;
import br.com.supero.framework.security.filter.UserFilterData;


public interface UserFacade extends CrudFacade<Long, User, QUser> {

	// Custom Methods
	public List<User> findAll();

	public User findByLogin(String login);
	
	public DataPage<User> findByFilter(UserFilterData filter, Page page);
	
	public User register(User user) throws Exception;
	
	public User confirmRegister(String uuid) throws Exception;
	
	public boolean lostPassword(String login) throws Exception;
	
	public boolean resetPassword(String uuid, String newPassword) throws Exception;

}