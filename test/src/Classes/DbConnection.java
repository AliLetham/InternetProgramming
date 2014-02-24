package Classes;

import java.sql.*;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class DbConnection {
	private static Connection database;
	 
	 @Resource(name="jdbc/Hermes")
	 
	 private static DataSource ds;
	// http://docs.oracle.com/javase/tutorial/jdbc/basics/sqldatasources.html
	public static Connection getDatabase(){
		
		Context ctx = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Hermes");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			database =  ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return database;
	}
	
	
	
	
}
