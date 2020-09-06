package com.mins.corona.app;

import com.mins.corona.app.service.NaverMapService;
import com.mins.corona.utils.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class Main {

	private final NaverMapService naverMapService;

	@GetMapping(value = {"/", "/main", "", "/index"})
	public String getPage(Model model) {
		Optional<String> infecteeInfoListAsJsonString = CommonUtil.objectToString(naverMapService.getAllInfecteeList("infecteeInfoList"));
		model.addAttribute("infecteeInfoJsonStr", infecteeInfoListAsJsonString.orElse("noData"));

		return "main";
	}
}
