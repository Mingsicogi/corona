package com.mins.corona.app.controller;

import com.mins.corona.app.dto.SearchAddrDTO;
import com.mins.corona.app.service.NaverMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 감염자 관련 컨트롤러
 *
 */
@Controller
@RequestMapping("/corona/infectee")
public class Infectee {

	@Autowired
	private NaverMapService naverCloudService;

	@RequestMapping("/inputPage")
	public String inputPage(){
		return "infecteeInput";
	}

	@RequestMapping("/input")
	@ResponseBody
	public ResponseEntity<String> input(Infectee infectee) {
		return ResponseEntity.ok("Success");
	}

	@RequestMapping("/search/location")
	@ResponseBody
	public ResponseEntity<SearchAddrDTO.Location> getLocationInfo(SearchAddrDTO.Req param) {
		SearchAddrDTO.Res detailAddrInfo = naverCloudService.getAddrDetailInfoByAddr(param);
		return ResponseEntity.ok(new SearchAddrDTO.Location(detailAddrInfo.getAddresses().get(0).getX(), detailAddrInfo.getAddresses().get(0).getY()));
	}
}
