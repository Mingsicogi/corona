package com.mins.corona.app.service;

import com.mins.corona.app.dto.InfectedPersonInfoOfRegionDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class GovernmentDataServiceTest {

    @Autowired
    GovernmentDataService governmentDataService;

    @Test
    void getData() {
        Elements trs = governmentDataService.getData().getElementsByTag("tr");

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

            log.info("bbb {}", th);
        }
        log.info("aaa {}", trs);
    }
}