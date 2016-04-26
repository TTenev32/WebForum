package com.swift.acad.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.swift.acad.entities.Forum;
import com.swift.acad.entities.Subforum;
import com.swift.acad.entities.Thread;

public class ForumDAO {
	
	public void addForum(Forum forum) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		
		PreparedStatement ps = con.prepareStatement("instert into forums (forum_name, created_at) values(?, now())");
		
		ps.setString(1, forum.getForum_name());
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	public Forum getForum(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from forums where forum_id=? order by forum_id";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Forum forum = null;
		
		if (rs.next()){
			int forum_id = rs.getInt("forum_id");
			String forum_name = rs.getString("forum_name");
			Date created_at = rs.getDate("created_at");
			forum = new Forum(forum_id, forum_name, created_at);
		}
		rs.close();
		ps.close();
		
		return forum;
	}
	
	public List<Forum> getAll() throws SQLException, ClassNotFoundException{
		List<Forum> forums = new ArrayList<Forum>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from forums order by forum_id";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while (rs.next()){
			int forum_id = rs.getInt("forum_id");
			String forum_name = rs.getString("forum_name");
			Date created_at = rs.getDate("created_at");
			
			Forum forum = new Forum(forum_id, forum_name, created_at);
			forums.add(forum);
		}
		
		rs.close();
		s.close();
		
		return forums;
	}
	
	public int updateForum(Forum forum) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "update forums set forum_name=? where forum_id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, forum.getForum_name());
		ps.setInt(2, forum.getForum_id());
		
		int updated = ps.executeUpdate();
		ps.close();
		
		return updated;
		
	}
	
	public int deleteForum(Forum forum) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "delete from forums where forum_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, forum.getForum_id());
		
		int deleted = ps.executeUpdate();
		ps.close(); 
		return deleted;
		
	}
	
	public List<Thread> getForumThreads(int id) throws SQLException, ClassNotFoundException{
		List<Thread> threads = new ArrayList<Thread>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from threads where forum_id=? order by thread_id";
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
	
	public Thread getForumThread(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from threads where forum_id=? order by thread_id";
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
	
	public List<Subforum> getForumSubforums(int id) throws SQLException, ClassNotFoundException{
		List<Subforum> sfs = new ArrayList<Subforum>();
		Connection con = DBManager.getInstance().open();
		String sql = "select * from subforums where forum_id=? order by subforum_id";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			int subforum_id = rs.getInt("subforum_id");
			String subforum_name = rs.getString("subforum_name");
			Date created_at = rs.getDate("created_at");
			
			Subforum sf = new Subforum(subforum_id, subforum_name, created_at);
			sfs.add(sf);
		}
		rs.close();
		ps.close();
		
		
		return sfs;
		
	}
	
	public Subforum getForumSubforum(int id) throws SQLException, ClassNotFoundException{
		Connection con = DBManager.getInstance().open();
		String sql = "select * from subforums where forum_id=? order by subforum_id";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Subforum sf = null;
		if(rs.next()){
			int subforum_id = rs.getInt("subforum_id");
			String subforum_name = rs.getString("subforum_name");
			Date created_at = rs.getDate("created_at");
			sf = new Subforum(subforum_id, subforum_name, created_at);
		}
		rs.close();
		ps.close();
		
		
		return sf;
	}

}
