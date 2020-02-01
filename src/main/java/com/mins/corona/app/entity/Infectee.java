package com.mins.corona.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Infectee {

	@Id
	@GeneratedValue
	private Long id;

	private int howManyPeopleMeet;
	private String x; // 위도
	private String y; // 경
	private String detailInfo;

	private Date issueOpenDate;

	private Date regYmdt;
}
