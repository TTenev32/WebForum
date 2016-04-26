package com.swift.acad.entities;

import java.util.Date;
import java.util.List;

public class User {
	
	public User(int user_id, String user_name, String password, String email, Date created_at) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.email = email;
		this.created_at = created_at;
	}
	
	int user_id;
	String user_name;
	String password;
	String email;
	Date created_at;
	List<Thread> threads;
	List<Message> messages;
	int role_id;

	public User(String user_name, String password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}
	
	public User(String user_name) {
		super();
		this.user_name = user_name;
	}

	public int getRole() {
		return role_id;
	}
	public void setRole(int role_id) {
		this.role_id = role_id;
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
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
