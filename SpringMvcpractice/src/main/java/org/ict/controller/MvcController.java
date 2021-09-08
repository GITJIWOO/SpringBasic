package org.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// 빈 컨테이너에 넣어주세요.(등록된 컨트롤러만 실제로 작동됨)
@Controller
public class MvcController {
	
	// value = 기본주소(localhost:8181) 뒤에 /goA를 붙이면 goA()메서드 실행
	@RequestMapping(value="/goA")
	// return 타입이 String인 경우 결과 페이지를 지정할 수 있음
	public String goA() {
		System.out.println("goA 주소 접속 감지");
		// 결과 페이지는 views 폴더 아래에 A.jsp
		return "A";
	}
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB 주소 접속 감지");
		return "B";
	}
	
	// goC는 파라미터를 입력받을 수 있도록 해보겠습니다.
	@RequestMapping(value="/goC")
	// 주소 뒤 ?cNum=값 형태로 들어오는 값을 로직 내 cNum으로 처리합니다.
	// 들어온 파라미터를 .jsp파일로 전달하기 위해서는
	// Model model을 파라미터에 추가로 선언해줍니다.
	public String goC(int cNum, Model model) {
		System.out.println("주소로 전달받은 값 : " + cNum);
		
		// 전달받은 cNum을 C.jsp에 출력하는 로직을 작성해주세요.
		model.addAttribute("cNum", cNum);
		
		return "C";
	}
	
	// goD는 requestParam을 이용해 변수명과 받는 이름이 일치하지 않게 해보겠습니다.
	@RequestMapping(value="/goD")
	// @RequestParam("대체이름")은 변수 왼쪽에 선언합니다.
	// 이렇게 되면 적힌 변수명 대신 대체이름으로 치환해 받아옵니다.
	// 만약 @RequestParam에 값이 들어오지 않는다면 실행을 하지 않습니다.
	public String goD(@RequestParam("d") int dNum, Model model) {
		System.out.println("d 변수명으로 받은게 dNum에 저장 : " + dNum);
		model.addAttribute("dNum", dNum);
		return "D";
	}
	
	// 폼으로 연결하는 메서드도 만들겠습니다.
	// 폼과 결과페이지가같은 주소를 공유하게 하기 위해서 폼쪽을 겟방식 접근 허용
	@RequestMapping(value="/goctof", method=RequestMethod.GET)
	public String cToFForm() {
		// ctoffrom을 이용해 섭씨온도를 입력하고 제출버튼을 누르면
		// 결과값이 나오는 로직을 작성해주세요.
		// input 태그의 name 속성을 cNum으로 주시면 되고
		// action은 value에 적힌 주소값으로 넘겨주시면 됩니다.
		return "ctofform";
	}
	
	// cToF 메서드를 만들겠습니다.
	// 섭씨 온도를 입력받아 화씨 온도로 바꿔서 출력해주는 로직을 작성해주세요.
	// (화씨 - 32) / 1.8 = 섭씨
	@RequestMapping(value="/goctof", method=RequestMethod.POST)
	public String cToF(@RequestParam double cNum, Model model) {
		double fNum = cNum * 1.8 + 32;
		System.out.println("받은 섭씨 : " + cNum);
		System.out.println("바꾼 화씨 : " + fNum);
		model.addAttribute("cNum", cNum);
		model.addAttribute("fNum", fNum);
		return "ctof";
	}
	
	// 위와 같은 방식으로 bmi측정페이지를 만들어보겠습니다.
	// 폼과 결과페이지로 구성해주시면 되고
	// bmi 공식은 체중 / (키(m) ^ 2) 으로 나오는 결과입니다.
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String inputbmi() {
		return "inputbmi";
	}
	
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String bmi(double height, double weight, Model model) {
		double bmi = weight / ((height / 100) * (height / 100));
		bmi = (double)Math.round(bmi * 100) / 100;
		model.addAttribute("height", height);
		model.addAttribute("weight", weight);
		model.addAttribute("bmi", bmi);
		return "bmi";
	}
	
	// ParaVariable을 이용하면 url 패턴만으로도 특정 파라미터를 받아올 수 있습니다.
	// rest방식으로 url을 처리할 때 주로 사용하는 방식입니다.
	// /pathtest/숫자 중 숫자 위치에 온 것은 page라는 변수값으로 간주
	@RequestMapping(value="/pathtest/{page}")
	public String pathText(@PathVariable int page, Model model) {
		// 받아온 page변수를 path.jsp로 보내주세요
		// path.jsp에는 {page} 페이지 조회중입니다 라는 문장이 뜨게 해주세요.
		model.addAttribute("page", page);
		return "path";
	}
	
	// ctof 로직을 PathVariable을 적용해서 만들어주세요.
	// ctofpv.jsp 에 결과가 나오면 됩니다.
	// 섭씨 온도를 url의 일부로 받도록 처리
	@RequestMapping(value="/ctofpv/{cNum}")
	public String cToFPV(@PathVariable double cNum, Model model) {
		double fNum = cNum * 1.8 + 32;
		System.out.println("받은 섭씨 : " + cNum);
		System.out.println("바꾼 화씨 : " + fNum);
		model.addAttribute("cNum", cNum);
		model.addAttribute("fNum", fNum);
		return "ctofpv";
	}
	
	// void 타입 컨트롤러의 특징
	// void 타입은 리턴 구문을 사용할수 없는 만큼
	// view 파일의 이름을 그냥 메서드이름.jsp 로 자동 저장 해버립니다.
	@RequestMapping(value="/voidreturn")
	public void voidTest(int num, Model model) {
		System.out.println("void 컨트롤러는 리턴구문이 필요없습니다.");
		// 1. 파라미터를 아무거나 받아오게 임의로 설정해주세요.
		// 2. 현 메서드의 맞는 view파일을 생성해주세요.
		// 3. 1에서 얻어온 파라미터를 2에 출력되도록 설정해주세요.
		model.addAttribute("num", num);
	}
}