package com.mins.corona.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 감염자 관련 컨트롤러
 *
 */
@Controller
@RequestMapping("/infectee")
public class Infectee {

	@RequestMapping("/inputPage")
	public String inputPage(){

		return "";
	}

	@RequestMapping("/input")
	@ResponseBody
	public ResponseEntity<String> input() {
		return ResponseEntity.ok("Success");
	}
}
