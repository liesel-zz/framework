package br.com.loghaus.quatroestacoes.facade.impl;

import br.com.supero.framework.base.facade.impl.CrudFacadeImpl;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.loghaus.quatroestacoes.dao.TagDAO;
import br.com.loghaus.quatroestacoes.entity.QTag;
import br.com.loghaus.quatroestacoes.entity.Tag;
import br.com.loghaus.quatroestacoes.facade.TagFacade;
import br.com.loghaus.quatroestacoes.filter.TagFilterData;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class TagFacadeImpl extends CrudFacadeImpl<Long, Tag, QTag>
		implements TagFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	public TagFacadeImpl(TagDAO dao) {
		super(dao);
	}

	@Override
	public DataPage<Tag> findByFilter(TagFilterData filter, Page page) {
		return ((TagDAO) super.getDAO()).findByFilterQ(filter, page);
	}

	@Override
	public List<Tag> findAll() {
		return ((TagDAO) super.getDAO()).findAll();
	}

	public Tag findByFilter(TagFilterData filter) {
		return ((TagDAO) super.getDAO()).findByFilterQ(filter);
	}

	@Override
	public Tag findById(Long id) {
		TagFilterData filter = new TagFilterData();
		filter.setId(id);

        // FIXME TIRAR LAZYS
		return ((TagDAO) super.getDAO()).findByFilterQ(filter);
	}

	public DataPage<Tag> zoom(TagFilterData filter, Page page) {
		return ((TagDAO) super.getDAO()).zoomQ(filter, page);
	}
}
