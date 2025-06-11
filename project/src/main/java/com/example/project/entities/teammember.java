package com.example.project.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class teammember {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private webuser user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private projectgroup group;

  

	public teammember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public teammember(webuser user, projectgroup group) {
		super();
		
		this.user = user;
		this.group = group;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public webuser getUser() {
		return user;
	}

	public void setUser(webuser user) {
		this.user = user;
	}

	public projectgroup getGroup() {
		return group;
	}

	public void setGroup(projectgroup group) {
		this.group = group;
	}


    
}
