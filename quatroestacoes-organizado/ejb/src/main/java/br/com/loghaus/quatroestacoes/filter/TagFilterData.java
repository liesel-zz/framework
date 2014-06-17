package br.com.loghaus.quatroestacoes.filter;

import br.com.supero.framework.base.filter.FilterData;
import br.com.loghaus.quatroestacoes.entity.QTag;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Expression;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagFilterData implements FilterData<QTag> {

	private Long userId;

	private Long id;

	private String nome;

    private Integer orderBy;

	public static final int BY_ID_ASC = 1;
	public static final int BY_ID_DESC = 2;
	public static final int BY_NOME_ASC = 3;
	public static final int BY_NOME_DESC = 4;

	public TagFilterData(){
	}

	public TagFilterData(Long id){
		setId(id);
	}

	public TagFilterData(String nome){
		setNome(nome);
	}

	public TagFilterData(Long id, String nome){
		setId(id);
		setNome(nome);
	}
	
	@Override
	public Map<String, Object> getFilterData() {
		Map<String, Object> filter = new HashMap<String, Object>();
		if (getId() != null)
			filter.put("id", getId());
		if(getNome() != null)
			filter.put("nome", "%" + getNome() + "%");

		return filter;
	}

	@Override
	public Predicate getParamsQ(QTag tag){
		BooleanBuilder builder = new BooleanBuilder();
		
		if(id != null)
			builder.and(tag.id.eq(id));
		if(nome != null)
			builder.and(tag.nome.containsIgnoreCase(nome));

		return builder;
		
	}
	
	@Override
	public List<Expression<?>> getJoinFetchQ(QTag tag){
		
		List<Expression<?>> fetchLst = new ArrayList<Expression<?>>();



		return fetchLst;
	}
	
	@Override
	public OrderSpecifier<?> getOrderByQ(QTag tag){
		if(orderBy == null)
			return null;
			
		switch (orderBy) {
			case BY_ID_ASC:
				return tag.id.asc();
			case BY_ID_DESC:
				return tag.id.desc();
			case BY_NOME_ASC:
				return tag.nome.asc();
			case BY_NOME_DESC:
            return tag.nome.asc();
			default:
				return null;
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

}
