package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rain.bean.TypeBean;
import com.rain.util.C3P0Utils;
import com.rain.util.C3P0Utils;

import javax.sql.DataSource;


public class TypeDao
{
	private DataSource dataSource;
	public TypeDao(){this.dataSource=C3P0Utils.getDataSource();}
	public ArrayList<TypeBean> get_ListInfo()//获得书类
	{
		ArrayList<TypeBean> tag_Array = new ArrayList<TypeBean>();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			String sql = "select * from booktype";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				TypeBean tag = new TypeBean();
				tag.setTid(rs.getInt("tid"));
				tag.setName(rs.getString("name"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public void updateTypeBook(int tid, String name)//更新书类
	{

		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			String sql = "update booktype set name=? where tid=?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setInt(2, tid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void addBookType(String name) //添加书类
	{


		int rs = 0;
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			String sql = "insert  into booktype(name) values(?)";
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			rs = stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void deleteBookType(int tid) //删除书类
	{
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			String sql = "delete from booktype where tid=?";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, tid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
