package br.com.loghaus.quatroestacoes.facade;

import br.com.supero.framework.base.facade.CrudFacade;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.loghaus.quatroestacoes.entity.QTag;
import br.com.loghaus.quatroestacoes.entity.Tag;
import br.com.loghaus.quatroestacoes.filter.TagFilterData;

import java.util.List;

public interface TagFacade extends CrudFacade<Long, Tag, QTag> {
	
	public List<Tag> findAll();
	
	public DataPage<Tag> findByFilter(TagFilterData filter, Page page);
	
	public Tag findByFilter(TagFilterData filter);
	
	public Tag findById(Long id);
	
	public DataPage<Tag> zoom(TagFilterData filter, Page page);
	
}
