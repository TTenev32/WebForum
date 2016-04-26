package com.swift.acad.entities;

import java.util.Date;

public class Subforum{

	int subforum_id;
	String subforum_name;
	Date created_at;
	
	





	public Subforum(int subforum_id, String subforum_name) {
		this.subforum_id = subforum_id;
		this.subforum_name = subforum_name;
	}



	public Subforum(int subforum_id, String subforum_name, Date created_at2) {

		this.subforum_id = subforum_id;
		this.subforum_name = subforum_name;
		created_at = created_at2;
	}



	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public int getSubforum_id() {
		return subforum_id;
	}
	public void setSubforum_id(int subforum_id) {
		this.subforum_id = subforum_id;
	}
	public String getSubforum_name() {
		return subforum_name;
	}
	public void setSubforum_name(String subforum_name) {
		this.subforum_name = subforum_name;
	}
	

}
