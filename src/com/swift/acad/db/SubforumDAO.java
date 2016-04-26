package com.swift.acad.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.swift.acad.entities.Subforum;
import com.swift.acad.entities.Thread;

public class SubforumDAO{

	public void addSubForum(Subforum sf) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		
		PreparedStatement ps = con.prepareStatement("instert into subforums (subforum_name, created_at) values(?, now())");
		
		ps.setString(1, sf.getSubforum_name());
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	public Subforum geSubtForum(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from subforums where subforum_id=? order by subforum_id";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Subforum sf = null;
		
		if (rs.next()){
			int subforum_id = rs.getInt("subforum_id");
			String subforum_name = rs.getString("subforum_name");
			Date created_at = rs.getDate("created_at");
			sf = new Subforum(subforum_id,subforum_name, created_at);
		}
		rs.close();
		ps.close();
		
		return sf;
	}
	
	public List<Subforum> getAll() throws SQLException, ClassNotFoundException{
		List<Subforum> sfs = new ArrayList<Subforum>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from subforums order by subforum_id";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while (rs.next()){
			int subforum_id = rs.getInt("subforum_id");
			String subforum_name = rs.getString("subforum_name");
			Date created_at = rs.getDate("created_at");
			
			Subforum sf = new Subforum(subforum_id, subforum_name, created_at);
			sfs.add(sf);
		}
		
		rs.close();
		s.close();
		
		return sfs;
	}
	
	public int updateSubforums(Subforum sf) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "update subforums set subforum_name=? where subforum_id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, sf.getSubforum_name());
		ps.setInt(2, sf.getSubforum_id());
		
		int updated = ps.executeUpdate();
		ps.close();
		
		return updated;
		
	}
	
	public int deleteSubforum(Subforum sf) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "delete from subforums where subforum_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, sf.getSubforum_id());
		
		int deleted = ps.executeUpdate();
		ps.close(); 
		return deleted;
		
	}
	
	public List<Thread> getSuborumThreads(int id) throws SQLException, ClassNotFoundException{
		List<Thread> threads = new ArrayList<Thread>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from threads where subforum_id=? order by thread_id";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			int thread_id = rs.getInt("thread_id");
			String thread = rs.getString("thread_name");
			Date created_at = rs.getDate("created_at");
			int user_id = rs.getInt("user_id");
			
			Thread th = new Thread(thread_id, thread, created_at, user_id);
			threads.add(th);
		}
		rs.close();
		ps.close();
		
		
		return threads;
		
	}
	
	public Thread getSubforumThread(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from threads where subforum_id=? order by thread_id";
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

}
