package com.swift.acad.entities;

import java.util.Date;
import java.util.List;

public class Forum  {
	private int forum_id;
	private String forum_name;
	private Date created_at;
	private List<Subforum> subforums;
	private List<Thread> threads;
	
	
	
	
	public Forum(String forum_name, Date created_at) {
		super();
		this.forum_name = forum_name;
		this.created_at = created_at;
	}
	public Forum(int forum_id, String forum_name, Date created_at) {
		super();
		this.forum_id = forum_id;
		this.forum_name = forum_name;
		this.created_at = created_at;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public List<Thread> getThreads() {
		return threads;
	}
	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}
	public List<Subforum> getSubforums() {
		return subforums;
	}
	public void setSubforums(List<Subforum> subforums) {
		this.subforums = subforums;
	}
	public Forum(int forum_id, String forum_name) {
		this.forum_id = forum_id;
		this.forum_name = forum_name;
	}
	public int getForum_id() {
		return forum_id;
	}
	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}
	public String getForum_name() {
		return forum_name;
	}
	public void setForum_name(String forum_name) {
		this.forum_name = forum_name;
	}	
	
}
