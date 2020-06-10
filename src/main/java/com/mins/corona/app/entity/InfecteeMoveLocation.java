package com.mins.corona.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class InfecteeMoveLocation {

    @Id
    @GeneratedValue
    private Long id;

    private String x; // 위도
    private String y; // 경도
    private Date arriveYmdt; //

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infectee_id")
    @JsonBackReference
    private Infectee infectee;
}
