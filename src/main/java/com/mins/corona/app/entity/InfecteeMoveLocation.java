package com.mins.corona.app.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class InfecteeMoveLocation {

    @Id
    @GeneratedValue
    private Long id;

    private String x; // 위도
    private String y; // 경도

    @ManyToOne(fetch = FetchType.LAZY)
    private Infectee infectee;
}
