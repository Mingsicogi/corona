package com.mins.corona.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InfecteeInputDTO {
    private Long infectOrder;

    private Integer age;
    private String country;

    private String x;
    private String y;
    @DateTimeFormat(pattern = "YYYYDDMM")
    private String arriveYmdt;

    private String markingColor; // 감염자 표시 색
    private String virusSourceAreaVisitYn; // 우한시 방문여
    private String whichHospital; // 입원 병원

    private Integer howManyPeopleMeet;
    private String detailInfo;

    @DateTimeFormat(pattern = "YYYYDDMM")
    private String issueOpenDate;
}
