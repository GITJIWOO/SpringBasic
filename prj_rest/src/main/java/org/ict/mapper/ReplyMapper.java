package org.ict.mapper;

import java.util.List;

import org.ict.domain.ReplyVO;

public interface ReplyMapper {
	
	public List<ReplyVO> getList(long bno);
	
	public void create(ReplyVO vo);
	
	public void update(ReplyVO vo);
	
	public void delete(long r_no);
}
