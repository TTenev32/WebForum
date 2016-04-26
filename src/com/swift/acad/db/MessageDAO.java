package com.swift.acad.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.swift.acad.entities.Message;


public class MessageDAO {
	
	public void addMessage(Message msg) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		
		PreparedStatement ps = con.prepareStatement("instert into messages (message_name, created_at) values(?, now())");
		
		ps.setString(1, msg.getMessage());

		ps.executeUpdate();
		ps.close();
		
	}
	
	public Message getMessage(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from messages where message_id=? order by message_id";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Message msg = null;
		
		if (rs.next()){
			String message = rs.getString("message");
			Date created_at = rs.getDate("created_at");
			msg =  new Message(message, created_at);
		}
		rs.close();
		ps.close();
		
		return msg;
	}
	
	public List<Message> getAll() throws SQLException, ClassNotFoundException{
		List<Message> msgs = new ArrayList<Message>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from messages order by message_id";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while (rs.next()){
			String message = rs.getString("message");
			Date created_at = rs.getDate("created_at");
			
			Message msg = new Message(message, created_at);
			msgs.add(msg);
		}
		
		rs.close();
		s.close();
		
		return msgs;
	}
	
	public int updateMessage(Message msg) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "update messages set message=? where message_id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, msg.getMessage());
		ps.setInt(2, msg.getMessage_id());
		
		int updated = ps.executeUpdate();
		ps.close();
		
		return updated;
		
	}
	
	public int deleteMessage(Message msg) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "delete from messages where message_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, msg.getMessage_id());
		
		int deleted = ps.executeUpdate();
		ps.close(); 
		return deleted;
		
	}

}
