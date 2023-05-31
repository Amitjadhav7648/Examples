package com.cg.mts.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicUpdate
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	private String username;
	private String password;
	private String role;
	@OneToOne
	private Customer customer;
	User() {

	}
	public User(String username, String password, String role, Customer customer) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.customer = customer;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
