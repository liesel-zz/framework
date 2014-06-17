package br.com.loghaus.quatroestacoes.dao.impl;

import br.com.supero.framework.base.dao.impl.CrudDAOImpl;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.base.security.Log;
import br.com.loghaus.quatroestacoes.dao.TagDAO;
import br.com.loghaus.quatroestacoes.entity.QTag;
import br.com.loghaus.quatroestacoes.entity.Tag;
import br.com.loghaus.quatroestacoes.filter.TagFilterData;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Log
public class TagDAOImpl extends CrudDAOImpl<Long, Tag, QTag> implements TagDAO {

	private static final long serialVersionUID = 1L;
	
	QTag qTag = QTag.tag;

	@Override
	public DataPage<Tag> findByFilterQ(TagFilterData filter, Page page) {
		return super.findQ(qTag, filter, page);
	}
	
	public Tag findByFilterQ(TagFilterData filter){
		return super.findSingleQ(qTag, filter);
	}
	
	@Override
	public List<Tag> findAll() {
		return from(qTag).list(qTag);
	}
	
	public DataPage<Tag> zoomQ(TagFilterData filter, Page page){
		return super.findQ(qTag, filter, page);
	}
	
}
