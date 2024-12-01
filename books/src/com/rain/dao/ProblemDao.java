package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rain.bean.AdminBean;
import com.rain.bean.ProblemBean;
import com.rain.util.C3P0Utils;

import javax.sql.DataSource;

public class ProblemDao
{
	private DataSource dataSource;
	public ProblemDao(){this.dataSource=C3P0Utils.getDataSource();}
	public ArrayList<ProblemBean> get_ListInfo() //查所有问题
	{
		ArrayList<ProblemBean> tag_Array = new ArrayList<ProblemBean>();
		String sql = "select * from problem";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				ProblemBean tag = new ProblemBean();
				tag.setPid(rs.getInt("pid"));
				tag.setAid(rs.getInt("aid"));
				tag.setName(rs.getString("name"));
				tag.setPage(rs.getString("page"));
				tag.setBody(rs.getString("body"));
				tag.setPhone(rs.getString("phone"));
				tag.setStatus(rs.getString("status"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public ArrayList<ProblemBean> get_ListInfo2(String aid) //查某人的所有问题
	{
		ArrayList<ProblemBean> tag_Array = new ArrayList<ProblemBean>();
		String sql = "select * from problem where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				ProblemBean tag = new ProblemBean();
				tag.setPid(rs.getInt("pid"));
				tag.setAid(rs.getInt("aid"));
				tag.setName(rs.getString("name"));
				tag.setPage(rs.getString("page"));
				tag.setBody(rs.getString("body"));
				tag.setPhone(rs.getString("phone"));
				tag.setStatus(rs.getString("status"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public void addProblem(AdminBean adminbean, String name, String page, String body, String phone) //添加问题
	{
		String sql = "insert into problem(aid,name,page,body,phone) values(?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, adminbean.getAid());
			stm.setString(2, name);
			stm.setString(3, page);
			stm.setString(4, body);
			stm.setString(5, phone);
			rs = stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteProblem(int pid)//删除问题
	{
		String sql = "delete from problem where pid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, pid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updateProblem(int pid, String status)//修改解决状态
	{
		String sql = "update problem set status=? where pid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, status);
			stm.setInt(2, pid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<ProblemBean> getLikeList(String name) //搜索问题
	{
		ArrayList<ProblemBean> tag_Array = new ArrayList<ProblemBean>();
		String sql = "select * from problem where name like '%" + name + "%' or page like '%" + name + "%' or body like '%" + name + "%' or status like '%" + name + "%'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				ProblemBean tag = new ProblemBean();
				tag.setPid(rs.getInt("pid"));
				tag.setAid(rs.getInt("aid"));
				tag.setName(rs.getString("name"));
				tag.setPage(rs.getString("page"));
				tag.setBody(rs.getString("body"));
				tag.setPhone(rs.getString("phone"));
				tag.setStatus(rs.getString("status"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
}
