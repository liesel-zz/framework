package br.com.loghaus.quatroestacoes.facade.impl;

import br.com.supero.framework.base.facade.impl.CrudFacadeImpl;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.loghaus.quatroestacoes.dao.VideoDAO;
import br.com.loghaus.quatroestacoes.entity.Video;
import br.com.loghaus.quatroestacoes.entity.QVideo;
import br.com.loghaus.quatroestacoes.facade.VideoFacade;
import br.com.loghaus.quatroestacoes.filter.VideoFilterData;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class VideoFacadeImpl extends CrudFacadeImpl<Long, Video, QVideo>
		implements VideoFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	public VideoFacadeImpl(VideoDAO dao) {
		super(dao);
	}

	@Override
	public DataPage<Video> findByFilter(VideoFilterData filter, Page page) {
		return ((VideoDAO) super.getDAO()).findByFilterQ(filter, page);
	}

	@Override
	public List<Video> findAll() {
		return ((VideoDAO) super.getDAO()).findAll();
	}

	public Video findByFilter(VideoFilterData filter) {
		return ((VideoDAO) super.getDAO()).findByFilterQ(filter);
	}

	@Override
	public Video findById(Long id) {
		VideoFilterData filter = new VideoFilterData();
		filter.setId(id);
		return ((VideoDAO) super.getDAO()).findByFilterQ(filter);
	}

	public DataPage<Video> zoom(VideoFilterData filter, Page page) {
		return ((VideoDAO) super.getDAO()).zoomQ(filter, page);
	}
}
