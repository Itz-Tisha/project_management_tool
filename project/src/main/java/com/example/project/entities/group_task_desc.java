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
public class group_task_desc {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	@ManyToOne
	@JoinColumn(name="grp_assign_task")
	projectgroup grp_assign_task;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="grptask_t")
	List<task>t = new ArrayList<>();

	public group_task_desc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public group_task_desc(projectgroup grp,String name,List<task>t) {
		super();
		this.name=name;
		this.grp_assign_task = grp;
		this.t=t;
	}

	public projectgroup getGrp_assign_task() {
		return grp_assign_task;
	}

	public void setGrp_assign_task(projectgroup grp_assign_task) {
		this.grp_assign_task = grp_assign_task;
	}

	public List<task> getT() {
		return t;
	}

	public void setT(List<task> t) {
		this.t = t;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public projectgroup getGrp() {
		return grp_assign_task;
	}

	public void setGrp(projectgroup grp) {
		this.grp_assign_task = grp;
	}
	
	
	
}
