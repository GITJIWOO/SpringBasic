package org.ict.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restapi/*")
public class TestController {
	
	@RequestMapping(value="/success")
	public ResponseEntity<String> success(){
		ResponseEntity<String> entity = null;
		
		try {
			entity = new ResponseEntity<String>(
					"KimJiWooSUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(
					e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value="/fail")
	public ResponseEntity<String> fail(){
		ResponseEntity<String> entity = null;
		
		try {
			entity = new ResponseEntity<String>(
					"KimJiWooFAIL", HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(
					e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
