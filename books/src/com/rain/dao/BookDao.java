package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.rain.bean.AdminBean;
import com.rain.bean.BookBean;
import com.rain.bean.HistoryBean;
import com.rain.util.C3P0Utils;

import javax.sql.DataSource;

public class BookDao
{
	private DataSource dataSource;
	public BookDao(){this.dataSource=C3P0Utils.getDataSource();}
	public void addBook(String card, String name, String type, String autho, String press, int num) //添加新书
	{

		String sql = "insert into book(card,name,type,autho,press,num,times) values(?,?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, card);
			stm.setString(2, name);
			stm.setString(3, type);
			stm.setString(4, autho);
			stm.setString(5, press);
			stm.setInt(6, num);
			stm.setInt(7, 0);
			rs = stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<BookBean> get_ListInfo() //查所有书
	{
		ArrayList<BookBean> tag_Array = new ArrayList<BookBean>();
		String sql = "select * from book";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				BookBean tag = new BookBean();
				tag.setBid(rs.getInt("bid"));
				tag.setName(rs.getString("name"));
				tag.setCard(rs.getString("card"));
				tag.setType(rs.getString("type"));
				tag.setAutho(rs.getString("autho"));
				tag.setPress(rs.getString("press"));
				tag.setNum(rs.getInt("num"));
				tag.setTimes(rs.getInt("times"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public ArrayList<BookBean> get_ListInfo2() //查所有书按次数排序
	{
		ArrayList<BookBean> tag_Array = new ArrayList<BookBean>();

		String sql = "select * from book order by times desc";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				BookBean tag = new BookBean();
				tag.setBid(rs.getInt("bid"));
				tag.setName(rs.getString("name"));
				tag.setCard(rs.getString("card"));
				tag.setType(rs.getString("type"));
				tag.setAutho(rs.getString("autho"));
				tag.setPress(rs.getString("press"));
				tag.setNum(rs.getInt("num"));
				tag.setTimes(rs.getInt("times"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public ArrayList<HistoryBean> get_HistoryListInfo(int status, String aid) //查某用户的借还记录
	{
		ArrayList<HistoryBean> tag_Array = new ArrayList<HistoryBean>();
		String sql = "select * from history where aid=" + aid + " and status=" + status;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				HistoryBean tag = new HistoryBean();
				tag.setHid(rs.getInt("hid"));
				tag.setAid(rs.getInt("aid"));
				tag.setBid(rs.getInt("bid"));
				tag.setBookname(rs.getString("bookname"));
				tag.setCard(rs.getString("card"));
				tag.setAdminname(rs.getString("adminname"));
				tag.setUsername(rs.getString("username"));
				tag.setBegintime(rs.getString("begintime"));
				tag.setEndtime(rs.getString("endtime"));
				tag.setStatus(rs.getInt("status"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public ArrayList<HistoryBean> get_HistoryListInfo2(int status) //查所有借还记录
	{
		ArrayList<HistoryBean> tag_Array = new ArrayList<HistoryBean>();
		String sql = "select * from history where status=" + status;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				HistoryBean tag = new HistoryBean();
				tag.setHid(rs.getInt("hid"));
				tag.setAid(rs.getInt("aid"));
				tag.setBid(rs.getInt("bid"));
				tag.setBookname(rs.getString("bookname"));
				tag.setCard(rs.getString("card"));
				tag.setAdminname(rs.getString("adminname"));
				tag.setUsername(rs.getString("username"));
				tag.setBegintime(rs.getString("begintime"));
				tag.setEndtime(rs.getString("endtime"));
				tag.setStatus(rs.getInt("status"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public BookBean get_BookInfo(int bid)//根据id查书信息
	{
		BookBean tag = new BookBean();

		String sql = "select * from book where bid=" + bid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				tag.setBid(rs.getInt("bid"));
				tag.setName(rs.getString("name"));
				tag.setCard(rs.getString("card"));
				tag.setType(rs.getString("type"));
				tag.setAutho(rs.getString("autho"));
				tag.setPress(rs.getString("press"));
				tag.setTimes(rs.getInt("times"));
				tag.setNum(rs.getInt("num"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag;
	}
	public int get_BookInfo1(int hid)//根据hid查书信息
	{
		int tag = 0;

		String sql = "select * from history where hid=" + hid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				tag=rs.getInt("bid");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag;
	}
	public void updateBook(int bid, String card, String name, String type, String autho, String press, int num) //更新书信息
	{
		String sql = "update book set name=?,card=?,type=?,autho=?,press=?,num=? where bid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, card);
			stm.setString(3, type);
			stm.setString(4, autho);
			stm.setString(5, press);
			stm.setInt(6, num);
			stm.setInt(7, bid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void deleteBook(int bid)//删除书
	{
		String sql = "delete from book where bid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, bid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<BookBean> getLikeList(String name) //搜索书
	{
		ArrayList<BookBean> tag_Array = new ArrayList<BookBean>();
		String sql = "select * from book where name like '%" + name + "%' or autho like '%" + name + "%' or type like '%" + name + "%'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next())
			{
				BookBean tag = new BookBean();
				tag.setBid(rs.getInt("bid"));
				tag.setName(rs.getString("name"));
				tag.setCard(rs.getString("card"));
				tag.setType(rs.getString("type"));
				tag.setAutho(rs.getString("autho"));
				tag.setPress(rs.getString("press"));
				tag.setTimes(rs.getInt("times"));
				tag.setNum(rs.getInt("num"));
				tag_Array.add(tag);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tag_Array;
	}
	public int borrowBook3(String aid)
	{
		String sql = "select count(*) from history where aid=? and status=1";
		PreparedStatement stm = null;
		int num=0;
		ResultSet rs=null;
		try{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1,aid);
			rs = stm.executeQuery();
			while (rs.next())
			{
				num= rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(num);
		return num;
	}
	public void borrowBook(int bid, AdminBean adminbean) //借书
	{
		BookBean bookbean;
		bookbean = this.get_BookInfo(bid);
		int n=bookbean.getNum();
		if(n==0)
		{
			return;
		}
		// 生成日期的功能
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		// 生成借阅开始日期
		String begintime = "" + year + "-" + month + "-" + day;
		month = month + 1;
		// 生成截止还书日期
		String endtime = "" + year + "-" + month + "-" + day;
		String sql = "insert into history(aid,bid,card,bookname,adminname,username,begintime,endtime,status) values(?,?,?,?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try
		{

			stm = conn.prepareStatement(sql);
			stm.setInt(1, adminbean.getAid());
			stm.setInt(2, bookbean.getBid());
			stm.setString(3, bookbean.getCard());
			stm.setString(4, bookbean.getName());
			stm.setString(5, adminbean.getUsername());
			stm.setString(6, adminbean.getName());
			stm.setString(7, begintime);
			stm.setString(8, endtime);
			stm.setInt(9, 1);
			rs = stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		String sql2 = "update book set times=?,num=? where bid=?";
		PreparedStatement stm2 = null;
		try
		{
			stm2 = conn.prepareStatement(sql2);
			stm2.setInt(1, bookbean.getTimes() + 1);
			stm2.setInt(2,bookbean.getNum()-1);
			stm2.setInt(3, bookbean.getBid());
			stm2.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		String sql3 = "update admin set times=? where aid=?";
		PreparedStatement stm3 = null;
		try
		{
			stm3 = conn.prepareStatement(sql3);
			stm3.setInt(1, adminbean.getTimes() + 1);
			stm3.setInt(2, adminbean.getAid());
			stm3.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void borrowBook2(int hid)//还书
	{
		// 生成日期
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		// 生成还书日期
		String endtime = "" + year + "-" + month + "-" + day;
		String sql = "update history set endtime=?,status=? where hid=?";
		BookBean bookBean;
		bookBean = this.get_BookInfo(get_BookInfo1(hid));
		String sql1= "update book set num=? where bid=?";
		PreparedStatement stm = null;
		PreparedStatement stm1= null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, endtime);
			stm.setInt(2, 0);
			stm.setInt(3, hid);
			stm.executeUpdate();
			Connection conn1 = dataSource.getConnection();
			stm1 = conn1.prepareStatement(sql1);
			stm1.setInt(1,bookBean.getNum()+1);
			stm1.setInt(2,bookBean.getBid());
			stm1.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void AddTime(int hid, String endtime) //延期
	{

		String sql = "update history set endtime=? where hid=?";
		PreparedStatement stm = null;
		try
		{
			Connection conn = dataSource.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, endtime);
			stm.setInt(2, hid);
			stm.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
