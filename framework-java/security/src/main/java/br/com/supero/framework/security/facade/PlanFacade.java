package br.com.supero.framework.security.facade;

import java.util.List;

import br.com.supero.framework.base.facade.CrudFacade;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.security.entity.Plan;
import br.com.supero.framework.security.entity.QPlan;
import br.com.supero.framework.security.filter.PlanFilterData;

public interface PlanFacade extends CrudFacade<Long, Plan, QPlan> {

	// Custom Methods
	public List<Plan> findAll();
	
	public DataPage<Plan> findByFilter(PlanFilterData filter, Page page);

}