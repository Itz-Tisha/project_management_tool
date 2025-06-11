package com.example.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class notifications {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@ManyToOne
	@JoinColumn(name="user_notify")
	webuser user;
	
	@ManyToOne
	@JoinColumn(name="grp_notify")
	public projectgroup grp;
	
	public String msg;

	public notifications() {
		super();
		// TODO Auto-generated constructor stub
	}

	public notifications(webuser user, projectgroup grp, String msg) {
		super();
		this.user = user;
		this.grp = grp;
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public webuser getUser() {
		return user;
	}

	public void setUser(webuser user) {
		this.user = user;
	}

	public projectgroup getGrp() {
		return grp;
	}

	public void setGrp(projectgroup grp) {
		this.grp = grp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
