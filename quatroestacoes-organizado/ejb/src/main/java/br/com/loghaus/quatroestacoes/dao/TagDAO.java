package br.com.loghaus.quatroestacoes.dao;

import br.com.supero.framework.base.dao.CrudDAO;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.loghaus.quatroestacoes.entity.QTag;
import br.com.loghaus.quatroestacoes.entity.Tag;
import br.com.loghaus.quatroestacoes.filter.TagFilterData;

import java.util.List;

public interface TagDAO extends CrudDAO<Long, Tag, QTag> {

	public List<Tag> findAll();
	
	public Tag findByFilterQ(TagFilterData filter);
	
	public DataPage<Tag> findByFilterQ(TagFilterData filter, Page page);
	
	public DataPage<Tag> zoomQ(TagFilterData filter, Page page);
}
