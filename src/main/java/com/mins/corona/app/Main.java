package com.mins.corona.app;

import com.mins.corona.app.dto.InfectedPersonInfoOfRegionDTO;
import com.mins.corona.app.service.GovernmentDataService;
import com.mins.corona.app.service.NaverMapService;
import com.mins.corona.utils.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class Main {

	private final GovernmentDataService governmentDataService;

	@GetMapping(value = {"/", "/main", "", "/index"})
	public String getPage(Model model) {

		List<InfectedPersonInfoOfRegionDTO> infectList = governmentDataService.getInfectedPersonList("infectList");

		InfectedPersonInfoOfRegionDTO totalInfectedPersonInfoOfCountry = infectList.get(0);

		List<InfectedPersonInfoOfRegionDTO> infectRankList = governmentDataService.getInfectedPersonListOrderByTotalCnt(infectList, "infectRankList");
		infectRankList.forEach(person -> person.setPercentOfCountry(person.getTotalInfectPersonCnt() * 100 / totalInfectedPersonInfoOfCountry.getTotalInfectPersonCnt()));

		model.addAttribute("rankList", infectRankList);
		model.addAttribute("today", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		return "main";
	}
}
