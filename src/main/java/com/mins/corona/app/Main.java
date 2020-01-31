package com.mins.corona.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main {

	@RequestMapping("/")
	public String defaultPage() {
		return "redirect:" + "/whoareyou";
	}

	@RequestMapping("/main")
	public String main() {
		return "index";
	}
}
