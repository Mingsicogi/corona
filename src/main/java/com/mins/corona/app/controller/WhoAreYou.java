package com.mins.corona.app.controller;

import com.mins.corona.app.service.NaverMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/corona/whoareyou")
public class WhoAreYou {

	@Autowired
	private NaverMapService naverMapService;

	@RequestMapping("")
	public String getPage(Model model) {

		model.addAttribute("infecteeInfoList", naverMapService.getAllInfecteeList("infecteeInfoList"));

		return "whoAreYou";
	}
}
