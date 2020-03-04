package com.mins.corona.app.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // jpa를 사용하는 경우 상호 연관 관계를 가지는 필드는 hashcode 값으로 사용하면 overflow 발생 가능성이 있으니 주의.
public class Event {
    private Long id;
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
    private boolean offline;
    private boolean free;
    private EventStatus eventStatus;
}