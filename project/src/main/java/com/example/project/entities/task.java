package com.example.project.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int task_id;
	
	@ManyToOne
	@JoinColumn(name="task_group")
	projectgroup grp_t;
	
	@ManyToOne
	@JoinColumn(name="task_user")
	webuser user_t;
	
	@ManyToOne
	@JoinColumn(name="task_grptask")
	group_task_desc grptask_t;

	public task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public task(projectgroup grp_t, webuser user_t, group_task_desc grptask_t) {
		super();
		this.grp_t = grp_t;
		this.user_t = user_t;
		this.grptask_t = grptask_t;
	}

	public int getId() {
		return task_id;
	}

	public void setId(int id) {
		this.task_id = id;
	}

	public projectgroup getGrp_t() {
		return grp_t;
	}

	public void setGrp_t(projectgroup grp_t) {
		this.grp_t = grp_t;
	}

	public webuser getUser_t() {
		return user_t;
	}

	public void setUser_t(webuser user_t) {
		this.user_t = user_t;
	}

	public group_task_desc getGrptask_t() {
		return grptask_t;
	}

	public void setGrptask_t(group_task_desc grptask_t) {
		this.grptask_t = grptask_t;
	}
	
	

}
