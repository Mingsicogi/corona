package com.mins.corona.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfectedPersonInfoOfRegionDTO {
    private String regionName;
    private Integer todayInfectedPersonCnt;
    private Integer totalInfectPersonCnt;
    private Integer isolationPersonCnt;
    private Integer deathPersonCnt;
    private Integer releasePersonCnt;

    private Integer percentOfCountry;

    public InfectedPersonInfoOfRegionDTO(String regionName, Integer todayInfectedPersonCnt, Integer totalInfectPersonCnt, Integer isolationPersonCnt, Integer deathPersonCnt, Integer releasePersonCnt) {
        this.regionName = regionName;
        this.todayInfectedPersonCnt = todayInfectedPersonCnt;
        this.totalInfectPersonCnt = totalInfectPersonCnt;
        this.isolationPersonCnt = isolationPersonCnt;
        this.deathPersonCnt = deathPersonCnt;
        this.releasePersonCnt = releasePersonCnt;
    }

    public boolean isRegion() {
        if(StringUtils.isNotBlank(regionName)) {
            try {
                RegionCd.valueOf(regionName);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }
}
