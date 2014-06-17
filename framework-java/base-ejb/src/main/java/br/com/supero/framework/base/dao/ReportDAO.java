package br.com.supero.framework.base.dao;

import java.io.Serializable;
import java.util.List;

import br.com.supero.framework.base.entity.RowMapper;
import br.com.supero.framework.base.filter.FilterData;


/**
 * DAO padrão para telas de relatório.
 */
public interface ReportDAO<V, K extends FilterData>
		extends Serializable {

	/**
	 * Realiza busca no banco de dados de acordo com o filtro especificado.
	 */
	public List<V> getReportData(K filter);
	
	/**
	 * Obtém a query principal do relatório.
	 */
	public String getQuery(K filter);
	
	/**
	 * Obtém o rowMapper utilizado para mapear o resultSet em DTOs.
	 */
	public RowMapper<V> getRowMapper();
	
}