package com.mins.corona.app.cd;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegionCd {
    서울("Seoul", "11"), 부산("Busan", "26"), 대구("Daegu", "27"), 인천("Incheon", "28"), 광주("Gwangju", "29"),
    대전("Daejeon", "30"), 울산("Ulsan", "31"), 세종("Sejong", "36"), 경기("Gyeonggi", "41"), 강원("Gangwon", "42"),
    충북("Chungbuk", "43"), 충남("Chungnam", "44"), 전북("Jeonbuk", "45"), 전남("Jeonnam", "46"), 경북("Gyeongbuk", "47"),
    경남("Gyeongnam", "48"), 제주("Jeju", "50"),
    ;

    private String eng;
    private String regionId;
}
