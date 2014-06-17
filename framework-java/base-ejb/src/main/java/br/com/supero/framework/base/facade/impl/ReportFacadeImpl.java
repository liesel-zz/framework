package br.com.supero.framework.base.facade.impl;

import java.util.List;

import br.com.supero.framework.base.dao.ReportDAO;
import br.com.supero.framework.base.facade.ReportFacade;
import br.com.supero.framework.base.filter.FilterData;


public class ReportFacadeImpl<V, K extends FilterData> implements
		ReportFacade<V, K> {
	private static final long serialVersionUID = 1L;
	
	private ReportDAO<V, K> dao;
	
	public ReportFacadeImpl(ReportDAO<V, K> dao) {
		this.dao = dao;
	}

	public ReportDAO<V, K> getDAO() {
		return this.dao;
	}
	
	@Override
	public List<V> getReportData(K filter) {
		return dao.getReportData(filter);
	}
}