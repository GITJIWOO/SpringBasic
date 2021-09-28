package org.ict.service;

import java.util.List;

import org.ict.domain.Criteria;
import org.ict.domain.SearchCriteria;
import org.ict.domain.BoardVO;
import org.ict.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//BoardServicelmpl은 BoardService 인터페이스를 구현합니다.
@Log4j // 로깅을 위한 어노테이션, X가 뜨면 pom.xml에서 추가 수정 // 자세한 사항은 pom.xml의 log4j 참조.
@Service // 의존성 등록을 위한 어노테이션
@AllArgsConstructor // 서비스 생성자 자동생성
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	// 등록작업시 BoardVO를 매개로 실행하기 때문에
	// 아래와 같이 BoardVO를 파라미터에 설정해둠.
	// BoardServiceTests에 VO를 생성해 테스트해주세요.
	@Override 
	public void register(BoardVO vo) {
		log.info("등록 작업 실행");
		mapper.insertSelectKey(vo);
	}
	
	// 전체 글을 다 가져오는게 아닌 특정 글 하나만 가져오는 로직을
	// 완성시켜주시고, 테스트코드고 작성해서 테스트해주세요.
	@Override
	public BoardVO get(long bno) {
		BoardVO get = mapper.select(bno);
		return get;
	}
	
	// 삭제 및 수정은 한꺼번에 진행해주세요.
	// 테스트코드까지 작성해주시면 됩니다.
	@Override
	public void modify(BoardVO vo) {
		mapper.update(vo);
	}

	@Override
	public void remove(long bno) {
		mapper.delete(bno);
	}
	
	// 글 전체 목록을 가져오는 로직을 작성해주세요
	// 해당 로직은 mapper 내부의 getList의 쿼리문을 먼저
	// 전체 글을 가져오는 로직으로 수정해 주신다음 service에
	// 등록해서 구현해주시면 됩니다.
	// 추가로 테스트로 진행해주세요.
	@Override
	public List<BoardVO> getList() {
		List<BoardVO> boardList = mapper.getList();
		return boardList;
	}

	@Override
	public List<BoardVO> getSearch(String keyword) {
		List<BoardVO> boardList = mapper.getSearch(keyword);
		return boardList;
	}
	
	@Override
	public List<BoardVO> getListPaging(SearchCriteria dto) {
		// dto정보(pageNum, amount)를 받아오면
		// 그걸 이용해서 mapper쪽의 getListPaging호출 후
		// 나온 결과물을 리턴해서 컨트롤러에서 쓸 수 있도록 처리
		List<BoardVO> boardList = mapper.getListPaging(dto);
		return boardList;
	}

	@Override
	public void insertSelectKey(BoardVO vo) {
	}

	@Override
	public int getAllPage(SearchCriteria cri) {
		int page = mapper.getAllPage(cri);
		return page;
	}
}
