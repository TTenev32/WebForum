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
import com.swift.acad.entities.Thread;

public class ThreadDAO {

	public void addThread(Thread thread) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		
		PreparedStatement ps = con.prepareStatement("instert into threads (thread_name, created_at) values(?, now())");
		
		ps.setString(1, thread.getThread_name());

		ps.executeUpdate();
		ps.close();
		
	}
	
	public Thread getThread(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from threads where thread_id=? order by thread_id";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Thread th = null;
		
		if (rs.next()){
			String thread_name = rs.getString("thread_name");
			Date created_at = rs.getDate("created_at");
			th =  new Thread(thread_name, created_at);
		}
		rs.close();
		ps.close();
		
		return th;
	}
	
	public List<Thread> getAll() throws SQLException, ClassNotFoundException{
		List<Thread> ths = new ArrayList<Thread>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from threads order by thread_id";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while (rs.next()){
			String thread_name = rs.getString("thread_name");
			Date created_at = rs.getDate("created_at");
			
			Thread th = new Thread(thread_name, created_at);
			ths.add(th);
		}
		
		rs.close();
		s.close();
		
		return ths;
	}
	
	public int updateThread(Thread th) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "update threads set thread_name=? where thread_id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, th.getThread_name());
		ps.setInt(2, th.getThread_id());
		
		int updated = ps.executeUpdate();
		ps.close();
		
		return updated;
		
	}
	
	public int deleteThread(Thread th) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "delete from threads where thead_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, th.getThread_id());
		
		int deleted = ps.executeUpdate();
		ps.close(); 
		return deleted;
		
	}
	
	public List<Message> getThreadMessages(int id) throws SQLException, ClassNotFoundException{
		List<Message> messages = new ArrayList<Message>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from messages where thread_id=? order by message_id";
		PreparedStatement s = con.prepareStatement(sql);
		s.setInt(1, id);
		ResultSet rs = s.executeQuery();
		
		while(rs.next()){
			int message_id = rs.getInt("message_id");
			String message = rs.getString("message");
			Date created_at = rs.getDate("created_at");
			int thread_id = rs.getInt("thread_id");
			int user_id = rs.getInt("user_id");
			
			
			Message msg = new Message(message_id, message, created_at, thread_id, user_id);
			messages.add(msg);
		}
		rs.close();
		s.close();
		
		
		return messages;
		
	}
	
	public Message getThreadMessage(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from messages where thread_id=? order by thread_id";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Message msg = null;
		if(rs.next()){
			String message = rs.getString("message");
			Date created_at = rs.getDate("created_at");
			msg = new Message(message, created_at);
		}
		rs.close();
		ps.close();
		
		
		return msg;
	}
	
}
