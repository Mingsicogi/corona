package com.mins.corona.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Infectee {

	@Id
	@GeneratedValue
	private Long id;

	private Long infectOrder;

	@OneToMany(mappedBy = "infectee", cascade = CascadeType.ALL)
	@JsonManagedReference
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
