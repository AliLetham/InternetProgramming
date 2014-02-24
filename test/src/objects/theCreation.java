package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Classes.DbConnection;

public class theCreation {

	
	 public static void createDatabase(){
		 Connection conn = DbConnection.getDatabase();
	     String sqlcreateSchema="Create database if not exists aletham;";
	     try{
	      java.sql.Statement statement=conn.createStatement();
	      statement.execute(sqlcreateSchema);
	      conn.close();
	     }catch (Exception et){
	      System.out.println("Can not create schema ");
	     }
	     createMembers();
	     createLetters();
	     createReading();

	   
	   
	    }
	 
	 public static void createReading()
	   {
		 Connection conn = DbConnection.getDatabase();
	    PreparedStatement query = null;
	    try {
	     query = conn.prepareStatement("DROP TABLE IF EXISTS `readinglinkingtable`;");
	  
	    query.executeUpdate();
	    PreparedStatement q = null;
	    q = conn.prepareStatement("CREATE TABLE `readinglinkingtable` ("
	  +"`ReadID` int(11) NOT NULL AUTO_INCREMENT,"
	  +"`ReadUser` varchar(20) NOT NULL,"
	  +"`ReadingUser` varchar(20) NOT NULL,"
	  +"PRIMARY KEY (`ReadID`),"
	  +"KEY `ReadUser_idx` (`ReadUser`),"
	  +"KEY `ReadingUser_idx` (`ReadingUser`),"
	  +"CONSTRAINT `ReadingUser` FOREIGN KEY (`ReadingUser`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
	  +"CONSTRAINT `ReadUser` FOREIGN KEY (`ReadUser`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION"
	+") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
	    q.executeUpdate();
	    } catch (SQLException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	    }
	   }
	   
	   public static void createLetters()
	   {
	    
	    Connection conn = DbConnection.getDatabase();
	    try {
	     PreparedStatement query = null;
	     query = conn.prepareStatement("DROP TABLE IF EXISTS `letters`;");
	     query.executeUpdate();
	     PreparedStatement query2 = null;
	        query2 = conn.prepareStatement("CREATE TABLE `letters` ("
	         +"`LetterID` int(11) NOT NULL AUTO_INCREMENT,"
	         +"`LetterUser` varchar(20) NOT NULL,"
	         +"`Letter` varchar(250) NOT NULL,"
	         +"`LetterDateTime` bigint(20) NOT NULL,"
	         +"PRIMARY KEY (`LetterID`)"
	       +") ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;");
	     query2.executeUpdate();
	    } catch (SQLException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	     return;
	    }
	    try {
	     conn.close();
	    } catch (SQLException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	    }
	   
	   }
	   
	   public static void createMembers()
	   {
		   Connection conn = DbConnection.getDatabase();
	    try {
	     PreparedStatement query2 = null;
	     query2 = conn.prepareStatement("DROP TABLE IF EXISTS `readinglinkingtable`;");
	     query2.executeUpdate();
	     PreparedStatement query = null;
	     query = conn.prepareStatement("DROP TABLE IF EXISTS `users`;");
	     query.executeUpdate();
	     PreparedStatement q = null;
	     q = conn.prepareStatement("CREATE TABLE `users` ("
	  +"`username` varchar(20) NOT NULL,"
	  +"`password` varchar(20) NOT NULL,"
	  +"PRIMARY KEY (`username`)"
	+") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
	     q.executeUpdate();
	    } catch (SQLException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	     }
	    }
}
