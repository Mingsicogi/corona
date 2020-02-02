package com.mins.corona.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main {

	@RequestMapping(value = {"/corona", "/corona/main"})
	public String defaultPage() {
		return "redirect:" + "/corona/whoareyou";
	}
}
