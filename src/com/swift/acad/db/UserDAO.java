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
import com.swift.acad.entities.User;

public class UserDAO {
	
	public boolean authentiate(User user) throws ClassNotFoundException, SQLException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from users where user_name=? and password=?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, user.getUser_name());
		ps.setString(2, user.getPassword());
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return true;
		}
		return false;
	}
	
	public void addUser(User user) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		
		PreparedStatement ps = con.prepareStatement("instert into users (user_name, password, email, created_at) values(?, ?, ?, now())");
		
		ps.setString(1, user.getUser_name());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	public User getUser(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from users where user_id=? order by user_id";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		User user = null;
		
		if (rs.next()){
			int user_id = rs.getInt("user_id");
			String user_name = rs.getString("user_name");
			String password = rs.getString("password");
			String email = rs.getString("email");
			Date created_at = rs.getDate("created_at");
			user =  new User(user_id, user_name, password, email, created_at);
		}
		rs.close();
		ps.close();
		
		return user;
	}
	
	public User getUser(String username) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from users where user_name=? order by user_id";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		User user = null;
		
		if (rs.next()){
			int user_id = rs.getInt("user_id");
			String user_name = rs.getString("user_name");
			String password = rs.getString("password");
			String email = rs.getString("email");
			Date created_at = rs.getDate("created_at");
			user =  new User(user_id, user_name, password, email, created_at);
		}
		rs.close();
		ps.close();
		
		return user;
	}
	
	public List<User> getAll() throws SQLException, ClassNotFoundException{
		List<User> users = new ArrayList<User>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from users order by user_id";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while (rs.next()){
			int user_id = rs.getInt("user_id");
			String user_name = rs.getString("user_name");
			String password = rs.getString("password");
			String email = rs.getString("email");
			Date created_at = rs.getDate("created_at");
			
			User user = new User(user_id, user_name, password, email, created_at);
			users.add(user);
		}
		
		rs.close();
		s.close();
		
		return users;
	}
	
	public int updateUser(User user) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "update users set user_name=?, password=?, email=?, role_id=? where user_id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, user.getUser_name());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		ps.setInt(4, user.getRole());
		ps.setInt(5, user.getUser_id());
		
		int updated = ps.executeUpdate();
		ps.close();
		
		return updated;
		
	}
	
	public int deleteUser(User user) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "delete from users where user_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, user.getUser_id());
		
		int deleted = ps.executeUpdate();
		ps.close(); 
		return deleted;
		
	}
	
	public List<Thread> getUserThreads(User user) throws SQLException, ClassNotFoundException{
		List<Thread> threads = new ArrayList<Thread>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from threads where user_id="+ user.getUser_id() +" order by thread_id";
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery(sql);
		
		while(rs.next()){
			int thread_id = rs.getInt("thread_id");
			String thread = rs.getString("thread");
			Date created_at = rs.getDate("created_at");
			
			Thread th = new Thread(thread_id, thread, created_at);
			threads.add(th);
		}
		rs.close();
		s.close();
		
		
		return threads;
		
	}

	public Thread getUserThread(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from threads where user_id=? order by thread_id";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Thread th = null;
		if(rs.next()){
			String thread = rs.getString("thread");
			Date created_at = rs.getDate("created_at");
			th = new Thread(thread, created_at);
		}
		rs.close();
		ps.close();
		
		
		return th;
	}
	
	public List<Message> getUserMessages(User user) throws SQLException, ClassNotFoundException{
		List<Message> messages = new ArrayList<Message>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from messages where user_id="+ user.getUser_id() +" order by message_id";
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery(sql);
		
		while(rs.next()){
			int message_id = rs.getInt("message_id");
			String message = rs.getString("message");
			Date created_at = rs.getDate("created_at");
			
			Message msg = new Message(message_id, message, created_at);
			messages.add(msg);
		}
		rs.close();
		s.close();
		
		
		return messages;
		
	}
	
	public Message getUserMessage(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from messages where user_id=? order by thread_id";
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
