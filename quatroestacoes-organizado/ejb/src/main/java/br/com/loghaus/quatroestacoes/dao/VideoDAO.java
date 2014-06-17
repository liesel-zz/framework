package br.com.loghaus.quatroestacoes.dao;

import br.com.supero.framework.base.dao.CrudDAO;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.loghaus.quatroestacoes.entity.QVideo;
import br.com.loghaus.quatroestacoes.entity.Video;
import br.com.loghaus.quatroestacoes.filter.VideoFilterData;

import java.util.List;

public interface VideoDAO extends CrudDAO<Long, Video, QVideo> {

	public List<Video> findAll();
	
	public Video findByFilterQ(VideoFilterData filter);
	
	public DataPage<Video> findByFilterQ(VideoFilterData filter, Page page);
	
	public DataPage<Video> zoomQ(VideoFilterData filter, Page page);
}
