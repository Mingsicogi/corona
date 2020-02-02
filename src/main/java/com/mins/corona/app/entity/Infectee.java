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

	private Integer age;

	private String country;

	@OneToMany(mappedBy = "infectee", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<InfecteeMoveLocation> location = new HashSet<>();

	private Integer howManyPeopleMeet;

	private String markingColor; // 감염자 표시 색

	private String virusSourceAreaVisitYn; // 우한시 방문여부

	private String whichHospital; // 입원 병원

	private Date issueOpenDate;

	private String detailInfo;

	private Date regYmdt;

	public void addLocation(InfecteeMoveLocation location) {
		this.location.add(location);
		location.setInfectee(this);
	}
}
