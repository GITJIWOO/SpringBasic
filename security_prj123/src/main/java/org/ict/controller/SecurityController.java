package org.ict.controller;

import org.ict.domain.MemberVO;
import org.ict.security.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/secu/*")
public class SecurityController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람이 접속 가능한 all로직");
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/member")
	public void doMember() {
		log.info("회원들만 접속 가능한 member 로직");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("운영자만 접속 가능한 admin로직");
	}
	
	@GetMapping("/join")
	public String joinForm() {
		return "redirect:/secu/join";
	}
	
	@PostMapping("/join")
	public void join(MemberVO user) {
		service.join(user);
	}
	
}
