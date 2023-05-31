package com.cg.mts.pojo;

import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Thejesh
 * @category ScreenPojo
 *
 */
@Entity
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int screenId;
	@JsonIgnore
	@ManyToOne
	private Theatre theatre;
	@OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
	private List<Show> show;
	private String screenName;
	@Column(name = "rowss")
	private int rows;
	@Column(name = "columnss")
	private int columns;

	public Screen() {

	}

	public Screen(Theatre theatre, List<Show> show, String screenName, int rows, int columns) {
		super();
		this.theatre = theatre;
		this.show = show;
		this.screenName = screenName;
		this.rows = rows;
		this.columns = columns;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public List<Show> getShow() {
		return show;
	}

	public void setShow(List<Show> show) {
		this.show = show;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
}
