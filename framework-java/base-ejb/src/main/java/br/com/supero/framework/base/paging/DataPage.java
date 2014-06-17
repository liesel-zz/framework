package br.com.supero.framework.base.paging;

import java.io.Serializable;
import java.util.List;

/**
 * Representa o resultado de uma pesquisa paginada.
 * 
 * @author alexandre
 * 
 * @param <T>
 *            DTO para qual foi realizada a pesquisa
 */
public class DataPage<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> data;

	private Number count;

	private Page page;

	/**
	 * Construtor.
	 * 
	 * @param data
	 * @param count
	 * @param page
	 */
	public DataPage(List<T> data, Number count, Page page) {
		this.data = data;
		this.count = count;
		this.page = page;
	}

	/**
	 * 
	 * @return
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * Quantidade total de registros da pesquisa.
	 * 
	 * @return
	 */
	public Number getCount() {
		return count;
	}

	/**
	 * 
	 * @return
	 */
	public List<T> getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("count=" + this.count);
		result.append(", page=[" + this.page + "]");
		result.append(", data=" + this.data);
		return result.toString();
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * Seta a quantidade total de registros da pesquisa.
	 * 
	 * @param count
	 */
	public void setCount(Number count) {
		this.count = count;
	}

	/**
	 * 
	 * @param page
	 */
	public void setPage(Page page) {
		this.page = page;
	}
}