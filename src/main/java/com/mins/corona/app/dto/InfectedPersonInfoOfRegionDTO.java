package com.mins.corona.app.dto;

import com.mins.corona.app.cd.RegionCd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfectedPersonInfoOfRegionDTO implements Serializable {
    private String regionName;
    private String regionEngName;
    private String regionId;
    private Integer todayInfectedPersonCnt;
    private Integer totalInfectPersonCnt;
    private Integer isolationPersonCnt;
    private Integer deathPersonCnt;
    private Integer releasePersonCnt;

    private String percentOfCountry;

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

    public String getRegionEngName() {
        return RegionCd.valueOf(regionName).getEng();
    }

    public String getRegionId() {
        return RegionCd.valueOf(regionName).getRegionId();
    }
}
