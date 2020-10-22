package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLHelper {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/mydb",user="root",pwd="xxx"; 
	//pwd改为自己的密码
	private static Connection conn=null;
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static ResultSet executeQuery(String sql) {
		ResultSet rs=null;
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			Statement cmd=conn.createStatement();
			rs=cmd.executeQuery(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	public static void closeConnection() {
		try {
			if(conn!=null && !conn.isClosed())
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//带参数的sql语句，一个就可以代替传统的增删改！!!
		public static int executeUpdateByParameters(String sql,Object... params) {
			int r=0;
			try {
				Connection conn = DriverManager.getConnection(url, user, pwd);
				PreparedStatement pcmd=conn.prepareStatement(sql);
				for(int i=0;i<params.length;i++) {
					Object param=params[i];
					if(param instanceof Integer)
						pcmd.setInt(i+1,new Integer(param.toString()));
					else if(param instanceof Double)
						pcmd.setDouble(i+1, new Double(param.toString()));
					else if(param instanceof Float)
						pcmd.setFloat(i+1, new Float(param.toString()));
					else if(param instanceof String)
						pcmd.setString(i+1, param.toString());
					else pcmd.setObject(i+1, param);
				}
				r=pcmd.executeUpdate();
				conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return r;
		}
		//不想在dao中看到数据库操作，不要返回结果集！返回List吧！
		public static List<Object[]> executeQueryAsList(String sql){
			List<Object[]> list=new ArrayList<Object[]>();
			ResultSet rs=null;
			try {
				conn = DriverManager.getConnection(url, user, pwd);
				Statement cmd=conn.createStatement();
				rs=cmd.executeQuery(sql);
				int cols=rs.getMetaData().getColumnCount();
				while(rs.next()) {
					//每一行记录就是一个Object数组
					Object[] arr=new Object[cols];
					for(int i=0;i<arr.length;i++) {
						arr[i]=rs.getObject(i+1);
					}
					list.add(arr);  //将结果集的每一行都加入list
				}
				conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return list;
		}
}
