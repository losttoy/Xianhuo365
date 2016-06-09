/**
 * @Title        DataBaseUtil.java
 * @Package      kb.base.db
 * @Description  数据库工具类
 *
 * @author       Will
 * @designer     (模块设计人)
 * @reviewer     (代码检视人)
 * @version      1.0,2016年5月9日
 *
 * @ReqPresenter 需求提交人:内部工具
 *
 * @UpdateHist   1.0,2016年5月9日 Will Created
 ****************
 *               1.1,2016年5月29日 Will Update
 *                          修改原因:在Ubuntu机器下安装MySQL服务，
 *                          并相应的建立数据库、用户测试
 *                          需求提交人:内部工具
 *                          代码检视人:none
 *               1.2,2016年6月5日 Will Update
 *                          修改原因:支持SqlServer的JDBC方式和JNDI方式
 *                                 修正关闭对象时置null不成功的问题。
 *                          需求提交人:内部工具
 *                          代码检视人:none
 ****************
 *
 * CopyRight 2016 LostToy. All rights reserved.
 */
package kb.base.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @ClassName:   DataBaseUtil
 * @Description: 数据库工具类
 * @author       Will
 * @date         2016年5月9日 下午8:16:24
 */
public class DataBaseUtil {
	 
	private static InitialContext context =null;

	static {
		try {
			if(null == context) {
				context = new InitialContext();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建一个新的实例 DataBaseUtil.
	 *
	 */
	public DataBaseUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @Title:       getConnectionDS
	 * @Description: 使用DataSource方法获取数据库连接
	 *               需在Web容器中才能正常使用
	 *               1.建立动态WEB工程，设置编码格式为UTF-8
	 *               2.拷贝SRC
	 *               3.添加LIB库到BUILD PATH
	 *               4.增加JSP文件，增加下面这几行测试代码
	 *               <%@ page import="kb.base.db.DataBaseUtil"%>
	 *               <%@ page import="java.sql.Connection"%>
	 *               
	 *               <%
	 *               	Connection conn = DataBaseUtil.getConnectionDS("shiyu");
	 *               	DataBaseUtil.dbConnectionPrcDemo(conn);
	 *               	DataBaseUtil.closeConnection(conn);
	 *               %>
	 *               5.维护WebContent\META-INF\context.xml
	 *               6.测试可用
	 * @param        dbName
	 * @return       Connection
	 * @throws
	 */
	public static Connection getConnectionDS(String dbName) {
		try {
			if (null == context) {
				context = new InitialContext();
			}
			DataSource dataSource = (DataSource)
					context.lookup("java:comp/env/" + dbName);
			return dataSource.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @Title:       getConnectionJDBC
	 * @Description: 使用JDBC方法获取数据库连接
	 * @param        driver
	 * @param        url
	 * @param        username
	 * @param        password
	 * @return       Connection
	 * @throws
	 */
	public static Connection getConnectionJDBC(String driver, String url,
			String username, String password) {
		try {
			Class.forName(driver);
		    Connection con =
		             DriverManager.getConnection(url, username, password);
		    return con;
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @Title:       closeConnection
	 * @Description: 关闭数据库连接
	 * @param        connection
	 * @return       void
	 * @throws
	 */
	public static void closeConnection(Connection connection) {
	    if (null != connection) {
	    	try {
	    		connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	}
	
	/**
	 * @Title:       closeResultSet
	 * @Description: 关闭结果集
	 * @param        rs
	 * @return       void
	 * @throws
	 */
	public static void closeResultSet(ResultSet rs) {
	    if (null != rs) {
	    	try {
	    		rs.close();
			  } catch (SQLException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
	    }
	}
	
	/**
	 * @Title:       closePreparedStatement
	 * @Description: 关闭预声明
	 * @param        pstmt
	 * @return       void
	 * @throws
	 */
	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @Title:       closeStatement
	 * @Description: 关闭声明
	 * @param        stmt
	 * @return       void
	 * @throws
	 */
	public static void closeStatement(Statement stmt) {
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @Title:       dbConnectionPrcDemo
	 * @Description: 数据库连接处理DEMO
	 * @param        connection
	 * @return       void
	 * @throws
	 */
	public static void dbConnectionPrcDemo(final Connection connection) {
		
		if (null == connection) {
			System.out.println("数据库连接没有成功建立，不能使用!");
			return;
		}
		
		String INSERT_SQL = "insert into log values(?,?,?)";

		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(INSERT_SQL);
			pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(2, "zjut");
			pstmt.setString(3, "日志内容");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//如果有ResultSet，关闭ResultSet
			//closeResultSet(rs);
		  //rs = null;
			
			//关闭声明
			closePreparedStatement(pstmt);
			pstmt = null;
		}
		
	}
	
	public static void main(String[] args) {/*
		
		//1.JDBC测试
		//SQLSERVER
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    String url = "jdbc:sqlserver://localhost:1433;databaseName=XYFOOD_TEST";
	    String username = "sa";
	    String password = "*";
	    //MYSQL
		driver = "com.mysql.jdbc.Driver";
	    url = "jdbc:mysql://localhost:3306/zhuwei";
	    username = "testuser";
	    password = "123456";
		
	    Connection conn = getConnectionJDBC(driver, url, username, password);
	    dbConnectionPrcDemo(conn);
	    closeConnection(conn);*/
	    
	    //2.DataSource测试
	    //须在Web容器中才能正常使用
		  Connection conn = getConnectionDS("sqlserver/default");
	    dbConnectionPrcDemo(conn);
	    closeConnection(conn);
	    conn = null;
	    
	}
}
