package br.com.supero.framework.base.paging;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private int startRow;

	private int pageSize;

	/**
	 * Construtor vazio.
	 */
	public Page() {
	}

	/**
	 * Construtor.
	 * 
	 * @param startRow
	 *            linha inicial
	 * @param pageSize
	 */
	public Page(int startRow, int pageSize) {
		this.startRow = startRow;
		this.pageSize = pageSize;
	}

	/**
	 * Retorna a linha inicial do total de registros. Inicia em zero.
	 * 
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.pageSize);
		result.append("/");
		result.append(this.startRow);
		return result.toString();
	}

	/**
	 * maiores que zero.
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isValid(Page p) {
		return p != null && p.getStartRow() >= 0 && p.getPageSize() > 0;
	}

	/**
	 * Seta a linha inicial.
	 * 
	 * @param startRow
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}