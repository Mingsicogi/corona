package com.mins.corona.api.event;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDTO {
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임 ​
    private int basePrice; // (optional) 참가비 기본
    private int maxPrice; // (optional) 참가비 최대값
    private int limitOfEnrollment; // 등록에 참가하는 인원수 제한
}
