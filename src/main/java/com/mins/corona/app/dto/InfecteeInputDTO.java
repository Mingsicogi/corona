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

    private String x;
    private String y;

    private Integer howManyPeopleMeet;
    private String detailInfo;

    @DateTimeFormat(pattern = "YYYYDDMM")
    private String issueOpenDate;
}
