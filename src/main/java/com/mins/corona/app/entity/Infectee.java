package com.mins.corona.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Infectee {

	@Id
	@GeneratedValue
	private Long id;

	private Long infectOrder;

	@OneToMany(mappedBy = "infectee", cascade = CascadeType.ALL)
	private Set<InfecteeMoveLocation> location = new HashSet<>();

	private int howManyPeopleMeet;
	private String detailInfo;

	private Date issueOpenDate;

	private Date regYmdt;

	public void addLocation(InfecteeMoveLocation location) {
		this.location.add(location);
		location.setInfectee(this);
	}
}
