package br.com.supero.framework.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import br.com.supero.framework.base.dao.impl.CrudDAOImpl;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.security.dao.PlanDAO;
import br.com.supero.framework.security.entity.Plan;
import br.com.supero.framework.security.entity.QPlan;
import br.com.supero.framework.security.filter.PlanFilterData;

@Stateless
public class PlanDAOImpl extends CrudDAOImpl<Long, Plan, QPlan> implements PlanDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Plan> findAll() {
		return findNamed("PLAN.FIND_ALL");
	}

	@Override
	public DataPage<Plan> findByFilter(PlanFilterData filter, Page page) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "%" + filter.getName() + "%");
		
		return super.findListNamed("PLAN.LIKE_BY_NAME", "PLAN.COUNT_LIKE_BY_NAME", page, params);
	}

}