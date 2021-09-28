package org.ict.persistence;

import org.ict.domain.Criteria;
import org.ict.domain.BoardVO;
import org.ict.mapper.BoardMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// 테스트코드 기본세팅(RunWith, ContextConfiguration, Log4j)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	// 이 테스트코드 내에서는 Mapper테스트를 담당합니다.
	// 따라서 BoardMapper 내부의 메서드를 실행할 예정이고
	// BoardMapper 타입의 변수가 필요하니
	// 선언해주시고 자동 주입으로 넣어주세요.
	@Autowired
	private BoardMapper mapper;
	
	// 테스트용 메서드의 이름은 testGetList입니다.
	// 테스트 코드가 실행될 수 있도록 만들어주세요.
	// @Test
	public void testGetList() {
		// mapper 내부의 getList 메서드를 호출하려면?
		log.info(mapper.getList());
	}
	
	// insert를 실행할 테스트코드를 하단에 작성해보겠습니다.
	// @Test
	public void testInsert() {
		// 글 입력을 위해서 BoardVO타입을 매개로 사용함
		// 따라서 BoardVO를 만들어 놓고
		// setter로 글제목,글본문, 글쓴이, 만 저장해둔 채로
		// mapper.insert(vo);를 호출해서 실행여부를 확인한 뒤,
		// 위 설명을 토대로 아래 vo에 6번글에 대한 제목 본문 글쓴이를 넣고
		// 호출해주신 다음 실제로 DB에 글이 들어갔는지 확인해주세요.
		BoardVO vo = new BoardVO();
		vo.setTitle("글제목");
		vo.setContent("글내용");
		vo.setWriter("글쓴이");
		mapper.insert(vo);
	}
	
	// @Test
	public void testSelect() {
		mapper.select(5L);
	}
	
	// @Test
	public void testDelete() {
		mapper.delete(4L);
	}
	
	// @Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(1L);
		vo.setTitle("글제목수정");
		vo.setContent("글내용수정");
		vo.setWriter("글쓴이수정");
//		log.info(vo);
		mapper.update(vo);
	}
	
	// @Test
	public void testgetPaging() {
		// 페이징 코드를 이용해서 원하는 번호의 페이지가 잘 출력되는지
		// 확인해주세요.
		Criteria dto = new Criteria(4000, 10);
		mapper.getListPaging(dto);
	}
	
	@Test
	public void testgetAllPage() {
		mapper.getAllPage();
	}
}
