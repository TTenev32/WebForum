package com.swift.acad.entities;

import java.util.Date;
import java.util.List;

public class Thread {

	private int thread_id;
	private String thread_name;
	private List<Message> messages;
	private Date created_at;
	private Date updated_at;
	private int user_id;
	
	
	public Thread(int thread_id, String thread_name, Date created_at, int user_id) {
		this.thread_id = thread_id;
		this.thread_name = thread_name;
		this.created_at = created_at;
		this.user_id = user_id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public Thread(String thread_name, Date created_at) {
		super();
		this.thread_name = thread_name;
		this.created_at = created_at;
	}


	public Thread(int thread_id, String thread_name, Date created_at) {
		this.thread_id = thread_id;
		this.thread_name = thread_name;
		this.created_at = created_at;
	}
	
	
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public int getThread_id() {
		return thread_id;
	}
	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}
	public String getThread_name() {
		return thread_name;
	}
	public void setThread_name(String thread_name) {
		this.thread_name = thread_name;
	}
	
	
}
