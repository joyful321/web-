package ztreeTest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.City;

public class DBUtil {
	//数据库参数配置文件名
	private static final String JDBCPROPERTY="jdbc.properties";
	//准备数据库的四大参数
	private static String DBDRIVER="";
	private static String DBURL="";
	private static String DBUSER="";
	private static String PASSWORD="";
	
	//准备数据库连接对象
	private Connection conn;
	
	static {
		Properties property=new Properties();
		InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("resourse/"+JDBCPROPERTY);
		try {
			//加载资源
			property.load(new InputStreamReader(is, "utf-8"));
			is.close();
			DBDRIVER=property.getProperty("DBDRIVER");
			DBURL=property.getProperty("DBURL");
			DBUSER=property.getProperty("DBUSER");
			PASSWORD=property.getProperty("PASSWORD");
			//加载驱动
			Class.forName(DBDRIVER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//构造方法，实例化对象时创建连接对象
	public DBUtil() throws Exception {
		this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
	}
//	//直接返回实例化对象时创建的连接对象
//	public  Connection getConnection() {
//		return this.conn;
//	}
	
	public List<City> query(int parentId) throws Exception{
		String sql="select * from city where parentId="+parentId;
		PreparedStatement pstmt= conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		City city=null;
		List <City> list=new ArrayList<City>();
		while(rs.next()){
			city=new City();
			int temp=rs.getInt("id");
			String sql1="select count(*) from city where parentId="+temp;
			PreparedStatement pstmt1= conn.prepareStatement(sql1);
			ResultSet rs1=pstmt1.executeQuery();
			city.setId(rs.getInt("id"));
			city.setName(rs.getString("name"));
			city.setParentId(rs.getInt("parentId"));
			System.out.println(city.toString());
			//>1的话设置true 否则设置为0； isParent
			int count=0;
			while(rs1.next()) {
				count=rs1.getInt(1);
				if(count==0) city.setIsParent(false);
			}
			if(rs.getString("icon")!=""&&rs.getString("icon")!=null){
				city.setIcon(rs.getString("icon"));
			}else{
				city.setIconOpen(rs.getString("iconOpen"));
				city.setIconClose(rs.getString("iconClose"));
			}
			list.add(city);
		}
		return list;
	}
	
	public boolean update(City city) {
		boolean flag = false;
		try {
			String sql = "update city set name =?";
			sql += " where id =? ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(city.getName()+"((((((((((");
			pst.setString(1, city.getName());
			pst.setLong(2, city.getId());
			
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
