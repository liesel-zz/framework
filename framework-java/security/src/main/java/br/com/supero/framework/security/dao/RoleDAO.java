package br.com.supero.framework.security.dao;

import java.util.List;

import br.com.supero.framework.base.dao.CrudDAO;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.security.entity.QRole;
import br.com.supero.framework.security.entity.Role;
import br.com.supero.framework.security.filter.RoleFilterData;

public interface RoleDAO extends CrudDAO<String, Role, QRole> {

	// Custom Methods
	public List<Role> findAll();

	public Role findByRole(String role);
	
	public DataPage<Role> findByFilter(RoleFilterData filter, Page page);

}