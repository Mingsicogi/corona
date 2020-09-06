package com.mins.corona.app.service;

import com.mins.corona.app.dto.InfectedPersonInfoOfRegionDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 중앙대책방역 본부 데이터를 가져옴
 *
 * @author minssogi
 */
@Service
@Slf4j
public class GovernmentDataService {

    private static final String getDataUrl = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";

    public Document getData() {
        try {
            return Jsoup.connect(getDataUrl).get();
        } catch (IOException e) {
            log.error("error : {}", e.getMessage(), e);
            return null;
        }
    }

//    @Cacheable(cacheNames = "commonCache", key = "#cacheKey")
    public List<InfectedPersonInfoOfRegionDTO> getInfectedPersonList(String cacheKey) {
        Elements trs = this.getData().getElementsByTag("tr");

        List<InfectedPersonInfoOfRegionDTO> dataList = new ArrayList<>(18);
        for (Element tr : trs) {
            Elements th = tr.getElementsByTag("th");

            if (th.size() == 1) { //의미 있는 row
                String region = th.text();

                Elements elementsByAttributeValueStarting = tr.getElementsByAttributeValueStarting("headers", "status_level");
                Integer todayTotalCnt = Integer.valueOf(elementsByAttributeValueStarting.get(0).text().replace(",", ""));

                Elements infectedPersonInfo = tr.getElementsByAttributeValueStarting("headers", "status_con");
                Integer totalCntOfRegion = Integer.valueOf(infectedPersonInfo.get(0).text().replace(",", ""));
                Integer isolationCnt = Integer.valueOf(infectedPersonInfo.get(1).text().replace(",", ""));
                Integer releaseCnt = Integer.valueOf(infectedPersonInfo.get(2).text().replace(",", ""));
                Integer deathPersonCnt = Integer.valueOf(infectedPersonInfo.get(3).text().replace(",", ""));

                dataList.add(new InfectedPersonInfoOfRegionDTO(region, todayTotalCnt, totalCntOfRegion, isolationCnt, deathPersonCnt, releaseCnt));
            }
        }

        return dataList;
    }

//    @Cacheable(cacheNames = "commonCache", key = "#cacheKey")
    public List<InfectedPersonInfoOfRegionDTO> getInfectedPersonListOrderByTotalCnt(List<InfectedPersonInfoOfRegionDTO> infectedPersonList, String cacheKey) {
        return infectedPersonList.stream().filter(InfectedPersonInfoOfRegionDTO::isRegion).sorted((o1, o2) -> {
            if(o1.getTotalInfectPersonCnt() == o2.getTotalInfectPersonCnt()) {
                return 0;
            }

            return o1.getTotalInfectPersonCnt() < o2.getTotalInfectPersonCnt() ? 1 : -1;
        }).collect(Collectors.toList());
    }
}
