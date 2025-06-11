package com.example.project.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class webuser {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int webuser_id;
	
	public String name;
public 	String email;
public String password;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy="user" ,orphanRemoval = true)
	List<projectgroup> grp = new  ArrayList<>();
	
	
    @OneToMany(cascade=CascadeType.ALL,mappedBy="user_t")
    List<task> t=new ArrayList<>();
    

	
	
	@OneToMany(mappedBy = "user")
	List<teammember> memberships;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	List<notifications> n = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	List<chat> chat = new ArrayList<>();
	
	public int getWebuser_id() {
		return webuser_id;
	}
	public void setWebuser_id(int webuser_id) {
		this.webuser_id = webuser_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public webuser(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	
	}
	public webuser(String name, String email,String password,List<projectgroup> grp,List<teammember> memberships,List<task> t,List<notifications> n,List<chat> chat) {
		super();
		this.password=password;
		this.name = name;
		this.email = email;
		this.grp = grp;
		this.memberships=memberships;
		this.t=t;
		this.n=n;
		this.chat=chat;
	}
	
	public webuser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<projectgroup> getGrp() {
		return grp;
	}
	public void setGrp(List<projectgroup> grp) {
		this.grp = grp;
	}

	public List<teammember> getMemberships() {
		return memberships;
	}
	public void setMemberships(List<teammember> memberships) {
		this.memberships = memberships;
	}
	public List<task> getT() {
		return t;
	}
	public void setT(List<task> t) {
		this.t = t;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
