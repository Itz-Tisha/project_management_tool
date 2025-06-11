package com.example.project.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.entities.chat;
import com.example.project.entities.group_task_desc;
import com.example.project.entities.notifications;
import com.example.project.entities.projectgroup;
import com.example.project.entities.task;
import com.example.project.entities.teammember;
import com.example.project.entities.webuser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class Dao {
     EntityManager e;
     Dao(EntityManager e){
    	 this.e = e;
     }
     
     @Transactional
     public boolean adduser(webuser user) {
    	 String email = user.getEmail();
    		String q = "SELECT u.name FROM webuser u WHERE u.email=:email";
    		List<String> u = e.createQuery(q).setParameter("email",email).getResultList();
    	
    	 if(!u.isEmpty()) {
    		 return false;
    	 }
    	 System.out.println("hii");
    	 e.persist(user);
    	 return true;
    	 
     }
     
     @Transactional
     public boolean loginuser(String email,String password) {
    	 String s = "SELECT u.name FROM webuser u WHERE u.email=:email AND u.password=:password";
    	 Query q = e.createQuery(s);
    	 List<webuser> u = q.setParameter("email", email).setParameter("password", password).getResultList();
    	 if(u.isEmpty()) return false;
    	 return  true;
     }
     
     
     @Transactional
     public webuser finduser(String email,String password) {
    	 String s = "SELECT u FROM webuser u WHERE u.email=:email AND u.password=:password";
    	 Query q = e.createQuery(s);
    	 webuser u = (webuser)q.setParameter("email", email).setParameter("password", password).getSingleResult();
    	 return u;
     }
     @Transactional
     public void makegrp(projectgroup g,webuser u) {
    	 webuser u1 = e.find(webuser.class, u.getWebuser_id());
    	 g.setUser(u1);
    	 u1.getGrp().add(g);
    	 e.persist(g);
     }
     
     @Transactional
     public List<projectgroup> findgrpbyuser(webuser u) {
         String s = "SELECT p FROM projectgroup p WHERE p.user = :u";
         Query q = e.createQuery(s, projectgroup.class);
         q.setParameter("u", u);
         return q.getResultList();
     }
     
     
     @Transactional
     public List<projectgroup> findgrp(webuser u) {
         String s = "SELECT t.group FROM teammember t WHERE t.user.webuser_id=:id";
         Query q = e.createQuery(s, projectgroup.class);
         q.setParameter("id", u.getWebuser_id());
         return q.getResultList();
     }
     
     
     @Transactional
     public List<webuser> people(String email, Long groupId) {
         // Get users already in the group
         String inGroupQuery = "SELECT t.user FROM teammember t WHERE t.group.grp_id = :id";
         List<webuser> usersInGroup = e.createQuery(inGroupQuery, webuser.class)
                                       .setParameter("id", groupId)
                                       .getResultList();
         String availableUsersQuery = "SELECT u FROM webuser u WHERE u.email != :email AND u NOT IN :inGroup";
         return e.createQuery(availableUsersQuery, webuser.class)
                 .setParameter("email", email)
                 .setParameter("inGroup", usersInGroup)
                 .getResultList();
     }

     @Transactional
     public void addpeople(Long uid,Long id) {
    	 projectgroup p =e.find(projectgroup.class, id);
    	 webuser user = e.find(webuser.class,uid);
    	 teammember team = new teammember();
    	team.setGroup(p);
    	team.setUser(user);
    	user.getMemberships().add(team);
    	p.getMembers().add(team);
    	e.persist(team);
    	String s2 = "DELETE FROM notifications  n WHERE n.user.webuser_id=:uid AND n.grp.grp_id=:id";
    	e.createQuery(s2).setParameter("uid", uid).setParameter("id", id).executeUpdate();
     }
     
     @Transactional
     public List<notifications> givenoti(webuser u){
    	 String s = "SELECT n FROM notifications n WHERE n.user.webuser_id=:id";
    	 return e.createQuery(s,notifications.class).setParameter("id", u.getWebuser_id()).getResultList();
     }
     
     @Transactional
     public void send_invitation(Long uid,Long id) {
    	
    	 notifications n = new notifications();
    	 webuser u = e.find(webuser.class,uid);
    	 projectgroup g = e.find(projectgroup.class, id);
    	 n.setGrp(g);
    	 n.setMsg("Sent You Invitation");
    	 n.setUser(u);
    	 e.persist(n);
     }
     
     @Transactional
     public List<webuser> view_memeber(Long gid){
    	 String s = "SELECT t.user FROM teammember t WHERE t.group.grp_id=:gid";
    	 TypedQuery<webuser> q = e.createQuery(s, webuser.class).setParameter("gid", gid);
    	List<webuser> u = q.getResultList();
    	for(webuser a:u) {
    		System.out.println("hi");
    		System.out.println(a.getEmail());
    	}
    	return u;
     }
     
     @Transactional
     public void add_task(Long gid,String name) {
    	 group_task_desc ob = new group_task_desc();
    	 projectgroup p = e.find(projectgroup.class, gid);
    	 ob.setGrp(p);
    	 ob.setName(name);
    	 p.getGrp_task().add(ob);
    	 e.persist(ob);
    	 
     }
     
     @Transactional
     public List<group_task_desc> tasks(Long gid){
    	 String s = "SELECT g FROM group_task_desc g WHERE g.grp_assign_task.grp_id=:gid";
    	 List<group_task_desc> t = e.createQuery(s,group_task_desc.class).setParameter("gid", gid).getResultList();
    	 for(group_task_desc ta:t) {
    		 System.out.println(ta.getName());
    	 }
    	 return t;
     }

     
     @Transactional
     public void settask(Long tid,Long uid,Long gid) {
    	 task t = new task();
    	 webuser u = e.find(webuser.class, uid);
    	 projectgroup g = e.find(projectgroup.class, gid);
    	 group_task_desc task = e.find(group_task_desc.class, tid);
    	
    	 t.setGrp_t(g);
    	 t.setGrptask_t(task);
    	 t.setUser_t(u);
    	 e.persist(t);
    	
     }
     
     @Transactional
     public List<group_task_desc> remain_task(Long gid) {
         String jpql = """
             SELECT g FROM group_task_desc g
             WHERE g.grp_assign_task.grp_id = :gid
             AND g.id NOT IN (
                 SELECT t.grptask_t.id FROM task t
                 WHERE t.grp_t.grp_id = :gid
             )
         """;

         return e.createQuery(jpql, group_task_desc.class)
                 .setParameter("gid", gid)
                 .getResultList();
     }
    
     @Transactional
     public List<webuser> remain_user(Long gid) {
         String jpql = """
             SELECT t.user FROM teammember t
             WHERE t.group.grp_id = :gid
             AND t.user.webuser_id NOT IN (
                 SELECT tk.user_t.webuser_id FROM task tk
                 WHERE tk.grp_t.grp_id = :gid
             )
         """;

         return e.createQuery(jpql, webuser.class)
                 .setParameter("gid", gid)
                 .getResultList();
     }

     
     @Transactional
     public List<task> view_assign_task(Long gid){
    	 String s = "SELECT t FROM task t WHERE t.grp_t.grp_id= :gid";
    	 return e.createQuery(s,task.class).setParameter("gid", gid).getResultList();
     }
     
     @Transactional
     public List<chat> grp_chat(Long uid,Long gid){
    	 String s = "SELECT  c FROM chat  c WHERE   c.grp.grp_id=:gid";
    	 return e.createQuery(s,chat.class).setParameter("gid", gid).getResultList();
     }
     
     @Transactional
     public void sendmsg(Long uid,Long gid,String msg,MultipartFile file) throws IOException {
    	 chat c = new chat();
         c.setUser(e.find(webuser.class, uid));
         c.setGrp(e.find(projectgroup.class,gid));

         if (file != null && !file.isEmpty()) {
             c.setFileData(file.getBytes());
             c.setFileName(file.getOriginalFilename());
             c.setFileType(file.getContentType());
             c.setFile(true);
         } else {
             c.setMsg(msg);
             c.setFile(false);
         }

         e.persist(c);
     }
     
     
     
     @Transactional
     public chat downloadfile(Long cid) {
    	 return e.find(chat.class, cid);
     }
     
     @Transactional
     public void leavegrp(Long uid,Long gid) {
    	 String s = "DELETE FROM teammember t WHERE t.user.webuser_id=:uid AND t.group.grp_id=:gid";
    	 e.createQuery(s).setParameter("uid", uid).setParameter("gid", gid).executeUpdate();
    	 String s2 = "DELETE FROM task t WHERE t.user_t.webuser_id=:uid AND t.grp_t.grp_id=:gid";
    	 e.createQuery(s2).setParameter("uid", uid).setParameter("gid", gid).executeUpdate();
    	 String s3 = "DELETE FROM notifications n WHERE n.user.webuser_id=:uid AND n.grp.grp_id=:gid";
    	 e.createQuery(s3).setParameter("uid", uid).setParameter("gid", gid).executeUpdate();
     }
     @Transactional
     public void deletegrp(Long gid) {
    	 String s = "DELETE FROM teammember t WHERE  t.group.grp_id=:gid";
    	 e.createQuery(s).setParameter("gid", gid).executeUpdate();
    	 String s2 = "DELETE FROM task t WHERE  t.grp_t.grp_id=:gid";
    	 e.createQuery(s2).setParameter("gid", gid).executeUpdate();
    	 String s3 = "DELETE FROM notifications n WHERE  n.grp.grp_id=:gid";
    	 e.createQuery(s3).setParameter("gid", gid).executeUpdate();
    	 String s4 = "DELETE FROM group_task_desc g WHERE  g.grp_assign_task.grp_id=:gid";
    	 e.createQuery(s4).setParameter("gid", gid).executeUpdate();
    	 String s5 = "DELETE FROM chat c WHERE  c.grp.grp_id=:gid";
    	 e.createQuery(s5).setParameter("gid", gid).executeUpdate();
    	 String s6 = "DELETE FROM projectgroup g WHERE  g.grp_id=:gid";
    	 e.createQuery(s6).setParameter("gid", gid).executeUpdate();
    	 
     }
     
     @Transactional
     public void delete_task(Long taskid) {
    	 String s = "DELETE FROM task t WHERE  t.grptask_t.id=:taskid";
    	 int i =e.createQuery(s).setParameter("taskid", taskid).executeUpdate();
    	 String s2 = "DELETE FROM group_task_desc g WHERE  g.id=:taskid";
    	 e.createQuery(s2).setParameter("taskid", taskid).executeUpdate();
    	 System.out.println(taskid);
    	 System.out.println(i);
     }
}
