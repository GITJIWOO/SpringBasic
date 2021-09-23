package org.ict.controller;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 의존성, 로깅능을 추가해주세요.
@Controller // 컨트롤러이므로 컨트롤러로 빈컨테이너에 컴포넌트 스캔
@Log4j // 로깅기능 추가
@RequestMapping("/board/*") // 클래스 위에 작성시
// 이 클래스를 사용하는 모든 메서드의 연결주소 앞에 /board/ 추가
@AllArgsConstructor // 의존성 주입 설정을 위해 생성자만 생성
public class BoardController {
	
	// 컨트롤러는 서비스를 호출합니다. 서비스는 mapper를 호출합니다.
	@Autowired
	private BoardService service;
	
	@GetMapping("/list") // Get방식으로만 주소 연결
	public void list(String keyword, Model model) {
		if(keyword == null) {
			// 전체 글 정보를 얻어와서
			List<BoardVO> boardList = service.getList();
			// view파일에 list라는 이름으로 넘겨주기
			model.addAttribute("list", boardList);
			// keyword를 수정
			keyword = "";
		} else {
			List<BoardVO> searchList = service.getSearch(keyword);
			model.addAttribute("list", searchList);
		}
		
		
		log.info("list로직 접속");
		model.addAttribute("keyword", keyword);
		
		// 1. views 하위에 경로에 맞게 폴더 및 .jsp 파일 생성
		// 2. 부트스트랩을 적용해 게시글 목록을 화면에 표시.
	}
	
	// 아래 주소로 데이터를 보내줄 수 있는 form을 작성해주세요.
	// register.jsp 파일명으로 작성해주시면 되고
	// @GetMapping으로 register.jsp에 접근할 수 있는
	// 컨트롤러 메서드도 아래에 작성해주세요.	
	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	@PostMapping("/register") // Post방식으로만 접속 허용
	public String register(BoardVO vo, RedirectAttributes rttr, Model model) {
		
		// 글을 썻으면 상세페이지나 혹은 글 목록으로 이동시켜야 합니다.
		// 1. 글쓰는 로직 실행 후
		service.register(vo);
		// 2. list 주소로 강제로 이동을 시킵니다.
		// 이동을 시킬 때 몇 번 글을 썻는지 안내해주는 로직을 추가합니다.
		// addFlashAtttibute는 redirect시에 컨트롤러에서
		// .jsp파일로 데이터를 보내줄 때 사용합니다.
		// model.addAttribute()를 쓴다면
		// 일반 이동이 아닌 redirect 이동시에는 데이터가 소실됩니다.
		// 이를 막이 위해 rttr.addFlashAttribute로 대체합니다.
		rttr.addFlashAttribute("vo", vo.getBno());
		rttr.addFlashAttribute("result", "register");
		
		// views 폴더 하위 board 폴더의 list.jsp 출력
		// redirect로 이동시킬 때는 "redirect:파일명:"
		return "redirect:/board/list";
	}
	
	// 상세 페이지 조회는 Long bno에 적힌 글번호를 이용해서 합니다.
	// /get  을 주소로 getmapping을 사용하는 메서드 get을 만들어주세요.
	// service에서 get() 을 호출해 가져온 글 하나의 정보를
	// get.jsp로 보내줍니다.
	// get.jsp에는 글 본문을 포함한 정보를 조회할 수 있도록 만들어주세요.
	@GetMapping("/get/{bno}")
	public String get(@PathVariable Long bno, Model model) {
		
		// BoardVO 안에 service.get으로 받아온 값을 넣어줌
		BoardVO board = service.get(bno);
		
		// .jsp파일로 보내기 위해 model.addAttribute
		model.addAttribute("board", board);
		
		// .jsp 파일로 리턴
		return "/board/get";
	}
	
	// get방식으로 삭제를 허용하면 매크로 등을 이용해서
	// 마음대로 글삭제를 하는 경우가 생길 수 있으므로
	// 무조건 삭제 버튼을 클릭해서 삭제할 수 있도록
	// post방식 접근만 허용
	// bno를 받아서 삭제하고, 삭제 후에는 "success"라는 문자열을
	// .jsp로 보내줍니다.
	// 삭제가 완료되면 redirect 기능을 이용해 list페이지로 넘어가게
	// 코드를 내부에 작성해주세요.
	@PostMapping("/remove/{bno}")
	public String remove(@PathVariable long bno, RedirectAttributes rttr) {
		
		service.remove(bno);
		
		rttr.addFlashAttribute("result", "success");
		// XX번 글이 삭제되었습니다 라고 메세지를 띄우도록
		// bno 정보를 list.jsp에 같이 넘겨주시고 메세지도 수정해주세요.
		rttr.addFlashAttribute("bno", bno);
		
		return "redirect:/board/list";
	}
	
	// 수정로직도 post방식으로 진행해야 합니다.
	// /modify를 조소로 하고, 사용자가 수정할 수 있는 요소들을
	// BoardVO로 받아서 수정한 다음 수정한 글의 디테일페이지로 넘어오면 됩니다.
	// 수정 후는 디테일페이지로 redirect 해주세요.
	
	// 글을 수정할 때는 modify.jsp를 이용해 수정을 해야합니다.
	// PostMapping을 이용해서 /boardmodify로 접속시 수정용으로 접근시켜주세요.
	// 수정 폼은 register.jsp와 비슷한 양식으로 작성되어있지만
	// 해당 글이 몇 번인지에 대한 정보도 화면에 표출시켜야 하고
	// 글쓴이는 readonly를 걸어서 수정 불가하게 만들어주세요.
	// 아래 메서드는 수정 폼으로 접근하도록 만들어주시고
	// 수정 폼에는 내가 수정하고자 하는 글의 정보를 먼저 받아온 다음
	// model.addAttribute로 정보를 .jsp로 보내서 폼을 채워두시면 됩니다.
	@PostMapping("/boardmodify/{bno}")
	public String modify(@PathVariable long bno, Model model) {
		BoardVO board = service.get(bno);
		
		model.addAttribute("board", board);
		
		// board폴더 하위의 modify.jsp로 연결
		return "/board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(RedirectAttributes rttr, BoardVO board) {
		service.modify(board);
		
		rttr.addFlashAttribute("modifysuccess", "modifysuccess");
		rttr.addFlashAttribute("bno", board.getBno());
		
		// 상세 페이지는 bno가 파라미터로 주어져야 하기 때문에
		// 아래와 같이 리턴구문을 작성해야 합니다.
		return "redirect:/board/get/" + board.getBno();
	}
	
}
