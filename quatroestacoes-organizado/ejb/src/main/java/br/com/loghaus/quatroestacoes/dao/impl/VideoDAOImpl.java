package br.com.loghaus.quatroestacoes.dao.impl;

import br.com.supero.framework.base.dao.impl.CrudDAOImpl;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.base.security.Log;
import br.com.loghaus.quatroestacoes.dao.VideoDAO;
import br.com.loghaus.quatroestacoes.entity.Video;
import br.com.loghaus.quatroestacoes.entity.QVideo;
import br.com.loghaus.quatroestacoes.filter.VideoFilterData;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Log
public class VideoDAOImpl extends CrudDAOImpl<Long, Video, QVideo> implements VideoDAO {

	private static final long serialVersionUID = 1L;
	
	QVideo qVideo = QVideo.video;

	@Override
	public DataPage<Video> findByFilterQ(VideoFilterData filter, Page page) {
		return super.findQ(qVideo, filter, page);
	}
	
	public Video findByFilterQ(VideoFilterData filter){
		return super.findSingleQ(qVideo, filter);
	}
	
	@Override
	public List<Video> findAll() {
		return from(qVideo).list(qVideo);
	}
	
	public DataPage<Video> zoomQ(VideoFilterData filter, Page page){
		return super.findQ(qVideo, filter, page);
	}
	
}
