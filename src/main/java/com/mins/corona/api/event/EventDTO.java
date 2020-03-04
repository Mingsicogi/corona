package com.mins.corona.api.event;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class EventDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private LocalDateTime beginEnrollmentDateTime;
    @NotNull
    private LocalDateTime closeEnrollmentDateTime;
    @NotNull
    private LocalDateTime beginEventDateTime;
    @NotNull
    private LocalDateTime endEventDateTime;
    @NotEmpty
    private String location; // (optional) 이게 없으면 온라인 모임 ​
    @Min(0)
    private int basePrice; // (optional) 참가비 기본
    @Min(0)
    private int maxPrice; // (optional) 참가비 최대값
    @Min(0)
    private int limitOfEnrollment; // 등록에 참가하는 인원수 제한
}
