package com.example.project.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class projectgroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int grp_id;
	
	public String name;
	
	public String projectdesc;
	
	@ManyToOne
	@JoinColumn(name="user_grp")
	webuser user;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy="grp_t")
	List<task> t = new ArrayList<>();

	
	@OneToMany(mappedBy = "group")
	List<teammember> members;

	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="grp_assign_task")
	List<group_task_desc>grp_task =new ArrayList<>();
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="grp")
	List<notifications> n = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="grp")
	List<chat> chat = new ArrayList<>();
	
	public projectgroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public projectgroup(String name, String projectdesc) {
		super();
		this.name = name;
		this.projectdesc = projectdesc;
	}


	public projectgroup(String name, String projectdesc ,webuser user,List<group_task_desc>grp_task ,List<teammember> members,List<task> t,List<notifications> n,List<chat> chat ) {
		super();
		this.name = name;
		this.projectdesc = projectdesc;
		this.user = user;
		this.t = t;
		this.grp_task = grp_task;
		this.members=members;
		this.n=n;
		this.chat=chat;
	}



	public List<task> getT() {
		return t;
	}

	public void setT(List<task> t) {
		this.t = t;
	}

	public String getProjectdesc() {
		return projectdesc;
	}

	public void setProjectdesc(String projectdesc) {
		this.projectdesc = projectdesc;
	}

	public List<teammember> getMembers() {
		return members;
	}

	public void setMembers(List<teammember> members) {
		this.members = members;
	}

	public int getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(int grp_id) {
		this.grp_id = grp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return projectdesc;
	}

	public void setDesc(String desc) {
		this.projectdesc = desc;
	}


	public webuser getUser() {
		return user;
	}



	public void setUser(webuser user) {
		this.user = user;
	}



	public List<group_task_desc> getGrp_task() {
		return grp_task;
	}



	public void setGrp_task(List<group_task_desc>grp_task) {
		this.grp_task = grp_task;
	}

	public List<notifications> getN() {
		return n;
	}

	public void setN(List<notifications> n) {
		this.n = n;
	}

	public List<chat> getChat() {
		return chat;
	}

	public void setChat(List<chat> chat) {
		this.chat = chat;
	}
	
	
}
