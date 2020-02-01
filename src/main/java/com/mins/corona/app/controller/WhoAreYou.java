package com.mins.corona.app.controller;

import com.mins.corona.app.service.NaverMapService;
import com.mins.corona.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/corona/whoareyou")
public class WhoAreYou {

	@Autowired
	private NaverMapService naverMapService;

	@RequestMapping("")
	public String getPage(Model model) {
		Optional<String> infecteeInfoListAsJsonString = CommonUtil.objectToString(naverMapService.getAllInfecteeList("infecteeInfoList"));
		model.addAttribute("infecteeInfoJsonStr", infecteeInfoListAsJsonString.orElse("noData"));

		return "whoAreYou";
	}
}
