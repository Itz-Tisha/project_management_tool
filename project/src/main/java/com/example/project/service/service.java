package com.example.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.dao.Dao;
import com.example.project.entities.chat;
import com.example.project.entities.group_task_desc;
import com.example.project.entities.notifications;
import com.example.project.entities.projectgroup;
import com.example.project.entities.task;
import com.example.project.entities.webuser;

import jakarta.transaction.Transactional;

@Service
public class service {
	Dao d;
	service(Dao d){
		this.d = d;
	}
	
	/* add via sign-up form */
	public boolean adduser(webuser user) {
		return d.adduser(user);
	}
	/* login user */
	public boolean loginuser(String email,String password) {
		return d.loginuser(email,password);
	}
	/*find user by email and password */
	public webuser finduser(String email,String password) {
		return d.finduser(email,password);
	}
	/* notification */
	public List<notifications> givenoti(webuser u){
   	return d.givenoti(u);
    }
	/* create group */
	public void makegrp(projectgroup g,webuser u) {
		d.makegrp(g,u);
	}
	/*find group which created by user u*/
	public List<projectgroup> findgrpbyuser(webuser u){
		return d.findgrpbyuser( u);
	}
	/* find group in which user u as a team-member */
	public List<projectgroup> findgrp(webuser u){
		return d.findgrp( u);
	}
	/* list of people */
	public List<webuser> people(String email,Long id){
		return d.people(email,id);
	}
	/*send invitations */
	public void send_invitation(Long uid,Long id) {
		d.send_invitation(uid,id);
	}
	/* add people in group */
	public void addpeople(Long uid,Long id) {
		d.addpeople(uid,id);
	}
	/*view team-member */
	public List<webuser> view_memeber(Long gid){
		System.out.println("hiservice");
		return d.view_memeber(gid);
	}
	/*set task for group */
	public void settask(Long tid,Long uid,Long gid) {
		d.settask(tid,uid,gid);
	}
	/*add task for group */
	public void add_task(Long gid,String name) {
		d.add_task(gid,name);
	}
	
	/*assig task to team-member*/
	public List<group_task_desc> tasks(Long gid){
		return d.tasks(gid);
	}
	
	/* task which not assign to team-member */
	public List<group_task_desc> remain_task(Long gid){
		return d.remain_task(gid);
	}
	/*team-member who not have any task */
	public List<webuser> remain_user(Long gid){
		return d.remain_user(gid);
	}
	/*view assign-task */
	public List<task> view_assign_task(Long gid){
		return d.view_assign_task(gid);
	}
	
	/* group live chat */
	public List<chat> grp_chat(Long uid,Long gid){
		return d.grp_chat(uid,gid);
	}
	/*send message */
	public void sendmsg(Long uid,Long gid,String msg,MultipartFile file) throws IOException {
		d.sendmsg(uid, gid, msg, file);
	}
	
	
    public chat downloadfile(Long cid) {
   	 return d.downloadfile(cid);
    }
    public void leavegrp(Long uid,Long gid) {
    	d.leavegrp(uid, gid);
    }
    public void deletegrp(Long gid) {
    	d.deletegrp(gid);
    }
    public void delete_task(Long taskid) {
    	d.delete_task(taskid);
    }
}
