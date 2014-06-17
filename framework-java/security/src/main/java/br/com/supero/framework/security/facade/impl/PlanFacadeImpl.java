package br.com.supero.framework.security.facade.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.supero.framework.base.facade.impl.CrudFacadeImpl;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.security.dao.PlanDAO;
import br.com.supero.framework.security.entity.Plan;
import br.com.supero.framework.security.entity.QPlan;
import br.com.supero.framework.security.facade.PlanFacade;
import br.com.supero.framework.security.filter.PlanFilterData;

@Named
public class PlanFacadeImpl extends CrudFacadeImpl<Long, Plan, QPlan> implements
		PlanFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	public PlanFacadeImpl(PlanDAO dao) {
		super(dao);
	}

	@Override
	public List<Plan> findAll() {
		return ((PlanDAO) super.getDAO()).findAll();
	}

	@Override
	public DataPage<Plan> findByFilter(PlanFilterData filter, Page page) {
		return ((PlanDAO) super.getDAO()).findByFilter(filter, page);
	}

}