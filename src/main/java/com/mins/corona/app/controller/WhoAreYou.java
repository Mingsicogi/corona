package com.mins.corona.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/whoareyou")
public class WhoAreYou {

	@RequestMapping("")
	public String getPage(Model model) {

		return "whoAreYou";
	}
}
