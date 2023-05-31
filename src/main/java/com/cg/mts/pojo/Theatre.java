package com.cg.mts.pojo;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "theatre")
public class Theatre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int theatreId;
	private String theatreName;
	private String theatreCity;
	private String managerName;
	private String managerContact;
	@OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
	private List<Screen> screen;
	@JsonIgnore
	@OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
	private List<Show> show;

	public Theatre() {

	}

	public Theatre(String theatreName, String theatreCity, String managerName, String managerContact,
			List<Screen> screen, List<Show> show) {
		super();
		this.theatreName = theatreName;
		this.theatreCity = theatreCity;
		this.managerName = managerName;
		this.managerContact = managerContact;
		this.screen = screen;
		this.show = show;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getTheatreCity() {
		return theatreCity;
	}

	public void setTheatreCity(String theatreCity) {
		this.theatreCity = theatreCity;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerContact() {
		return managerContact;
	}

	public void setManagerContact(String managerContact) {
		this.managerContact = managerContact;
	}

	public List<Screen> getScreen() {
		return screen;
	}

	public void setScreen(List<Screen> screen) {
		this.screen = screen;
	}

	public List<Show> getShow() {
		return show;
	}

	public void setShow(List<Show> show) {
		this.show = show;
	}
}
