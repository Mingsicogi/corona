package com.mins.corona.app.controller;

import com.mins.corona.app.dto.InfecteeInputDTO;
import com.mins.corona.app.dto.SearchAddrDTO;
import com.mins.corona.app.entity.Infectee;
import com.mins.corona.app.entity.InfecteeMoveLocation;
import com.mins.corona.app.repository.InfecteeRepository;
import com.mins.corona.app.service.NaverMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * 감염자 관련 컨트롤러
 *
 */
@Controller
@RequestMapping("/corona/infectee")
public class InfecteeManage {

	@Autowired
	private NaverMapService naverCloudService;

	@Autowired
	private InfecteeRepository infecteeRepository;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

	@RequestMapping("/input")
	public String inputPage(){
		return "infecteeInput";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		List<Infectee> infecteeList = infecteeRepository.findAll();
		model.addAttribute("infecteeInfoList", infecteeList);
		model.addAttribute("totalCnt", infecteeList.size());
		return "infecteeList";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseEntity<String> input(InfecteeInputDTO param) {

		Infectee infectee;
		try {
			InfecteeMoveLocation location = new InfecteeMoveLocation();
			location.setX(param.getX());
			location.setY(param.getY());
			location.setArriveYmdt(SDF.parse(param.getArriveYmdt()));

			infectee = new Infectee();
			infectee.setMarkingColor(param.getMarkingColor());
			infectee.setVirusSourceAreaVisitYn(param.getVirusSourceAreaVisitYn());
			infectee.setWhichHospital(param.getWhichHospital());
			infectee.setHowManyPeopleMeet(param.getHowManyPeopleMeet());
			infectee.setDetailInfo(param.getDetailInfo());
			infectee.setInfectOrder(param.getInfectOrder());
			infectee.setIssueOpenDate(SDF.parse(param.getIssueOpenDate()));
			infectee.addLocation(location);

			infectee.setRegYmdt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

		} catch (ParseException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("확진자 발생일 포맷을 yyyyMMdd(년월일) 형태로 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		infecteeRepository.save(infectee);

		return ResponseEntity.ok("Success");
	}

	@RequestMapping("/search/location")
	@ResponseBody
	public ResponseEntity<SearchAddrDTO.Location> getLocationInfo(SearchAddrDTO.Req param) {
		SearchAddrDTO.Res detailAddrInfo = naverCloudService.getAddrDetailInfoByAddr(param);
		return ResponseEntity.ok(new SearchAddrDTO.Location(detailAddrInfo.getAddresses().get(0).getX(), detailAddrInfo.getAddresses().get(0).getY()));
	}
}
