package com.mins.corona.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Infectee {

	@Id
	@GeneratedValue
	private Long id;

	private int howManyPeopleMeet;
	private String issueOpenAddr;
	private String detailInfo;

	private Date issueOpenDate;

}
