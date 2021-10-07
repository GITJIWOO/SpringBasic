package org.ict.service;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.mapper.BoardMapper;
import org.ict.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements ReplyService {

	// 서비스는 매퍼를 호출하기때문에 매퍼 생성
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void addReply(ReplyVO vo) {
		mapper.create(vo);
		Long bno = mapper.getBno(vo.getRno());
		boardMapper.updateReplyCount(bno, +1);
	}

	@Override
	public List<ReplyVO> listReply(Long bno) {
		return mapper.getList(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
	}

	@Override
	@Transactional
	public void removeReply(Long rno) {
		mapper.delete(rno);
		Long bno = mapper.getBno(rno);
		boardMapper.updateReplyCount(bno, -1);
	}
}
