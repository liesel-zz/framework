package br.com.loghaus.quatroestacoes.facade;

import br.com.supero.framework.base.facade.CrudFacade;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.loghaus.quatroestacoes.entity.Video;
import br.com.loghaus.quatroestacoes.entity.QVideo;
import br.com.loghaus.quatroestacoes.filter.VideoFilterData;

import java.util.List;

public interface VideoFacade extends CrudFacade<Long, Video, QVideo> {
	
	public List<Video> findAll();
	
	public DataPage<Video> findByFilter(VideoFilterData filter, Page page);
	
	public Video findByFilter(VideoFilterData filter);
	
	public Video findById(Long id);
	
	public DataPage<Video> zoom(VideoFilterData filter, Page page);
	
}
