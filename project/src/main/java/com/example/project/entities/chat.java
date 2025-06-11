package com.example.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@ManyToOne
	@JoinColumn(name="user_chat")
	webuser user;
	
	@ManyToOne
	@JoinColumn(name="grp_chat")
	public projectgroup grp;
	
    private String fileName;
    
    @Lob
    private byte[] fileData; 

    private String fileType; 

    private boolean isFile;
	
	
	
	public String msg;

	public chat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public chat(webuser user, projectgroup grp, String msg) {
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	
	
}
