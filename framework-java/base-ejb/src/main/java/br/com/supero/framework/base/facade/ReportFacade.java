package br.com.supero.framework.base.facade;

import java.io.Serializable;
import java.util.List;

import br.com.supero.framework.base.filter.FilterData;


/**
 * Façade padrão para telas de relatório.
 */
public interface ReportFacade<V, K extends FilterData>
		extends Serializable {

	/**
	 * Realiza busca no banco de dados de acordo com o filtro especificado.
	 */
	public List<V> getReportData(K filter);
}