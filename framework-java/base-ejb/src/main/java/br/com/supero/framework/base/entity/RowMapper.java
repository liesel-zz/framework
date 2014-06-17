package br.com.supero.framework.base.entity;

import java.util.Collection;
import java.util.List;

/**
 * Interface mapeadora de resultSets de banco.
 * O método mapRows recebe uma lista crua do banco e a converte
 * numa coleção com os objetos desejados.
 * 
 * @author André Cardoso
 *
 * @param <T> Tipo de objeto que deve ser retornado pela query.
 */
public interface RowMapper<T> {
	public Collection<T> mapRows(List<Object[]> resultSet);
}