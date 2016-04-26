package com.swift.acad.entities;

import java.util.Date;

public class Message {
	private int message_id;
	private String message;
	private Date created_at;
	private Date updated_at;
	private int thread_id;
	
	
	public Message(int message_id, String message, Date created_at, int thread_id, int user_id) {
		super();
		this.message_id = message_id;
		this.message = message;
		this.created_at = created_at;
		this.thread_id = thread_id;
		this.user_id = user_id;
	}
	public Message(String message, Date created_at) {
		super();
		this.message = message;
		this.created_at = created_at;
	}
	public Message(int message_id, String message, Date created_at) {
		this.message_id = message_id;
		this.message = message;
		this.created_at = created_at;
	}
	private int user_id;
	
	public int getThread_id() {
		return thread_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	

}
