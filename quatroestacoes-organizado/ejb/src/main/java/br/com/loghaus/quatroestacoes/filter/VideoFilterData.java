package br.com.loghaus.quatroestacoes.filter;

import br.com.supero.framework.base.filter.FilterData;
import br.com.loghaus.quatroestacoes.entity.QVideo;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Expression;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoFilterData implements FilterData<QVideo> {

	private Long userId;

	private Long id;

	private String nome;

	private String url;

    private Boolean fetchTag = false;

    private Boolean fetchType = false;

	private Integer orderBy;

	public static final int BY_ID_ASC = 1;
	public static final int BY_ID_DESC = 2;
	public static final int BY_NOME_ASC = 3;
	public static final int BY_NOME_DESC = 4;

	public VideoFilterData(){
	}

	public VideoFilterData(Long id){
		setId(id);
	}

	public VideoFilterData(String nome){
		setNome(nome);
	}

	public VideoFilterData(String nome, String D){
		setNome(nome);
		setUrl(url);
	}

	public VideoFilterData(Long id, String nome, String url){
		setId(id);
		setNome(nome);
        setUrl(url);
	}
	
	@Override
	public Map<String, Object> getFilterData() {
		Map<String, Object> filter = new HashMap<String, Object>();
		if (getId() != null)
			filter.put("id", getId());
		if(getNome() != null)
			filter.put("nome", "%" + getNome() + "%");
		if(getUrl() != null)
			filter.put("url", "%" + getUrl() + "%");

		return filter;
	}

	@Override
	public Predicate getParamsQ(QVideo video){
		BooleanBuilder builder = new BooleanBuilder();
		
		if(id != null)
			builder.and(video.id.eq(id));
		if(nome != null)
			builder.and(video.nome.containsIgnoreCase(nome));
		if(url != null)
			builder.and(video.url.containsIgnoreCase(url));
		
		return builder;
		
	}
	
	@Override
	public List<Expression<?>> getJoinFetchQ(QVideo video){
		
		List<Expression<?>> fetchLst = new ArrayList<Expression<?>>();
        if(fetchTag)
            fetchLst.add(video.listTags);

        if(fetchType)
            fetchLst.add(video.videoType);

		return fetchLst;
	}
	
	@Override
	public OrderSpecifier<?> getOrderByQ(QVideo video){
		if(orderBy == null)
			return null;
			
		switch (orderBy) {
			case BY_ID_ASC:
				return video.id.asc();
			case BY_ID_DESC:
				return video.id.desc();
			case BY_NOME_ASC:
				return video.nome.asc();
			case BY_NOME_DESC:
            return video.nome.asc();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getFetchTag() {return fetchTag ;}

    public void setFetchTag(Boolean fetchTag) {this.fetchTag = fetchTag ;}

    public Boolean getFetchType() {return fetchType ;}

    public void setFetchType(Boolean fetchType) {this.fetchType = fetchType ;}

    public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

}
