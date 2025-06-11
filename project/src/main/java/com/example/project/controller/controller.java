package com.example.project.controller;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.entities.chat;
import com.example.project.entities.group_task_desc;
import com.example.project.entities.notifications;
import com.example.project.entities.projectgroup;
import com.example.project.entities.task;
import com.example.project.entities.webuser;
import com.example.project.service.service;

import jakarta.servlet.http.HttpSession;

@Controller
public class controller {
  service s;
  controller(service s){
	  this.s = s;
  }
  
	  @GetMapping("/")
	  public String homepage(HttpSession session,Model m) {
		  webuser u =(webuser)session.getAttribute("user");
		  List<projectgroup> gs = s.findgrpbyuser(u);
		  List<projectgroup> grp = s.findgrp(u);
		 
		  m.addAttribute("gs",gs);
		  m.addAttribute("grp",grp);
		  
		  m.addAttribute("user",u);
		  return "home";
	  }
	  
	  @GetMapping("/asamember")
	  public String asamember(HttpSession session,Model m) {
		  webuser u =(webuser)session.getAttribute("user");
		  
		  List<projectgroup> grp = s.findgrp(u);
		 
		  
		  m.addAttribute("grp",grp);
		  
		  m.addAttribute("user",u);
		  return "asamember";
	  }
	   
	  @GetMapping("/notification")
	  public String notificayion(HttpSession session,Model m) {
		  webuser u =(webuser)session.getAttribute("user");
		  List<notifications> n = s.givenoti(u);
		  m.addAttribute("n",n);
		  m.addAttribute("user",u);
		  return "notifications";
	  }
	  
	  /*Sign-up and login*/
	  
	 @GetMapping("/sign_form")
	 public String signpage(Model m) {
		 m.addAttribute("user" , new webuser());
		 return "signup";
	 }
	 
	 @PostMapping("/adduser")
	 public String adduser(@ModelAttribute webuser user,Model m) {
		 boolean f = s.adduser(user);
		 if(f) {
			 
			 return "login";
		 }
		 m.addAttribute("user",new webuser());
		 return "signup";
		 
	 }
	 
	 @GetMapping("/loginform")
	 public String loginform() {
		 return "login";
	 }
	 
	 @GetMapping("/login")
	 public String loginuser(@RequestParam String email,@RequestParam String password,HttpSession session) {
		 boolean flag = s.loginuser(email,password);
		 if(flag) {
			 webuser user = s.finduser(email,password);
			 session.setAttribute("user", user);
			 return "redirect:/";
		 }
		 return "login";
		 
	 }
	 
	 /*create group*/
	 
	 @GetMapping("/creategroup")
	 public String creategroup(Model m) {
		 m.addAttribute("grp",new projectgroup());
		 return "group_form";
		 
	 }
	 @PostMapping("/makegrp")
	 public String makegrp(projectgroup g,Model m,HttpSession session) {
		 webuser u = (webuser)session.getAttribute("user");
		 s.makegrp(g,u);
		 return "redirect:/";
	 }
	 
	 /* list of available people for add as a team-member */
	 @GetMapping("/list_of_people/{id}")
	 public String list_of_people(@PathVariable("id") Long id,HttpSession session,Model m){
		 webuser u = (webuser)session.getAttribute("user");
		 List<webuser> user =  s.people(u.getEmail(),id);
		 m.addAttribute("user",user);
		 m.addAttribute("g_id",id);
		 return "list_of_people";
	 }
	 
	/* send Invitation to people */
	 @GetMapping("/send_invitation/{u_id}/{id}")
	 public String send_invitation(@PathVariable("u_id") Long uid,@PathVariable("id") Long id) {
		 s.send_invitation(uid,id);
		 return "redirect:/";
	 }
	 
	 /* add a person as a team-member */
	 @GetMapping("/add_people/{u_id}/{id}")
	 public String add_people(@PathVariable("u_id") Long uid,@PathVariable("id") Long id) {
		 s.addpeople(uid,id);
		 return "redirect:/";
	 }
	 
	 /* view team-member */
	 @GetMapping("/view_member/{gid}")
	 public String view_memeber(@PathVariable("gid") Long gid,Model m) {
		 List<webuser> u = s.view_memeber(gid);
		 m.addAttribute("u",u);
		 return "grp_member";
	 }
	 
	 /* set task for group project*/
	 /* task form*/
	 @GetMapping("/set_task/{gid}")
	 public String set_task(@PathVariable Long gid,Model m) {
		 m.addAttribute("gid",gid);
		 List<group_task_desc> t = s.tasks(gid);
		 m.addAttribute("t",t);
		 return "add_task_form";
	 }
	 /* add task*/
	 @PostMapping("add_task/{gid}")
	 public String add_task(@PathVariable Long gid,@RequestParam String name,Model m) {
		 s.add_task(gid,name);
		 return "redirect:/set_task/{gid}";
	 }
	 
	 
	 /* assign task-form to team - member */
	 @GetMapping("assign_task_form/{gid}")
	 public String assign_task_form(@PathVariable Long gid,Model m) {
		 List<group_task_desc> t = s.remain_task(gid);
		 m.addAttribute("t",t);
	     List<webuser> u = s.remain_user(gid);
		 m.addAttribute("u",u);
		 m.addAttribute("gid",gid);
		 return "assign_task_form";
		 
	 }
	 /* assign task */
	 @PostMapping("/add_task_for_user/{gid}")
	 public String add_task_for_user(@RequestParam Long tid,@RequestParam Long uid,@PathVariable Long gid) {
		 s.settask(tid,uid,gid);
		 return "redirect:/";
	 }
	 
	 /* view assigned - task */
	 @GetMapping("/view_assign_task/{gid}")
	 public String view_assign_task(@PathVariable Long gid,Model m) {
		 List<task> t = s.view_assign_task(gid);
		 m.addAttribute("t",t);
		 return "view_assign_task";
	 }
	 
	 
	
	 /* live chat - page*/
	 @GetMapping("/live_chat/{uid}/{gid}")
	 public String live_chat(@PathVariable Long uid,@PathVariable Long gid,Model m) {
		 List<chat> c =  s.grp_chat(uid,gid);
		  m.addAttribute("chat",c);
		  return "live_chat_page";
	 }
	 
	 /* send file in live chat*/
	 @PostMapping("/send_msg/{uid}/{gid}")
	 public String sendMessage(@PathVariable Long uid,
	                           @PathVariable Long gid,
	                           @RequestParam(value = "msg", required = false) String msg,
	                           @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		 
		 s.sendmsg(uid,gid,msg,file);
	     return "redirect:/live_chat/" + uid + "/" + gid;
	 }
	
	 /* download file */
	 @GetMapping("/download/{id}")
	 public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
	     chat c = s.downloadfile(id);
	     return ResponseEntity.ok()
	             .contentType(MediaType.parseMediaType(c.getFileType()))
	             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + c.getFileName() + "\"")
	             .body(c.getFileData());
	 }
	 
	 
	 /* delete particular task */
	 @PostMapping("delete_task/{taskId}")
	 public String delete_task(@PathVariable Long taskId) {
		 s.delete_task(taskId);
		 return "redirect:/";
	 }
	 
	 
	 /* leave from group */
	 @GetMapping("/leavegrp/{uid}/{gid}")
	 public String leavegrp(@PathVariable Long uid,@PathVariable Long gid) {
		 s.leavegrp(uid,gid);
		 return "redirect:/";
	 }
	
	 /*delete group */
	 @GetMapping("deletegrp/{gid}")
	 public String deletegrp(@PathVariable Long gid) {
		 s.deletegrp(gid);
		 return "redirect:/";
	 }
	 

}
