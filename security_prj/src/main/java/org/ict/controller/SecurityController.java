package org.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/secu/*")
public class SecurityController {
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람이 접속 가능한 all로직");
	}
	
	@GetMapping("/member")
	public void doMember() {
		log.info("회원들만 접속 가능한 member 로직");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("운영자만 접속 가능한 admin로직");
	}
	
}
