package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.LetterObject;
import objects.Member;

/**
 * Servlet implementation class Letter
 */
@WebServlet({"/Letter", "/Letter/*"})
public class Letter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Letter() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String letterID = request.getRequestURI();
    	int intLetterID = Integer.parseInt(letterID.substring(letterID.lastIndexOf("/") + 1));
	   
 		    ArrayList<LetterObject> usersTweets = new ArrayList<LetterObject>();
 		    Connection database = Classes.DbConnection.getDatabase();
 		    PreparedStatement ps;
 			try {
 				ps = database.prepareStatement( "DELETE from letters where letterID = '"+intLetterID+ "';");
 				ps.executeUpdate();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			
 			}
 			try {
 				database.close();
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		    
 			request.setAttribute("Letters", usersTweets);
 			request.getRequestDispatcher("/userProfile.jsp").forward(request, response);
 		}
    	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Member currentMember = (Member)request.getSession().getAttribute("user");
		
		String requestURI = request.getRequestURI();
	   if(requestURI.equals(request.getContextPath()+"/Letter")){
		   
		    ArrayList<LetterObject> usersTweets = new ArrayList<LetterObject>();
		    Connection database = Classes.DbConnection.getDatabase();
		    PreparedStatement ps;
			try {
				ps = database.prepareStatement( "SELECT * from letters where LetterUser = '"+currentMember.getUsername()+ "' ORDER by LetterID DESC;");
				ResultSet rs = ps.executeQuery();
				 while(rs.next()){  
					   LetterObject letterObject=new LetterObject();  
					   letterObject.setLetterID(rs.getInt(1));  
					   letterObject.setLetterUser(rs.getString(2));  
					   letterObject.setLetterContent(rs.getString(3));  
					   letterObject.setTimeDate(rs.getLong(4));  
					   usersTweets.add(letterObject);  
					  }  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				database.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			request.setAttribute("Letters", usersTweets);
			request.getRequestDispatcher("/userProfile.jsp").forward(request, response);
		}
	   
	   
	   else if(requestURI.equals(request.getContextPath()+"/Letter/all")){
			ArrayList<LetterObject> usersTweets = new ArrayList<LetterObject>();
		    Connection database = Classes.DbConnection.getDatabase();
		    PreparedStatement ps;
			try {
				ps = database.prepareStatement( "SELECT * from letters ORDER by LetterID DESC;");
				ResultSet rs = ps.executeQuery();
				 while(rs.next()){  
					   LetterObject letterObject=new LetterObject();  
					   letterObject.setLetterID(rs.getInt(1));  
					   letterObject.setLetterUser(rs.getString(2));  
					   letterObject.setLetterContent(rs.getString(3));  
					   letterObject.setTimeDate(rs.getLong(4));  
					   usersTweets.add(letterObject);  
					  }  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				database.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			request.setAttribute("Letters", usersTweets);
			request.getRequestDispatcher("/allLetters.jsp").forward(request, response);
		   
	   }
	   
	   }
			   


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection database = Classes.DbConnection.getDatabase();
		String letter = request.getParameter("letter");
		
       // http://stackoverflow.com/questions/5175728/how-to-get-the-current-date-time-in-java
		long currentDateTime = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
		
    	Member currentMember = (Member)request.getSession().getAttribute("user");
    	if(currentMember == null){
    	response.sendRedirect("Login.jsp");	
    	return;
    	}
    	
		String insertSQL = "INSERT into letters VALUES(?, ?, ?, ?);";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = database.prepareStatement(insertSQL);
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, currentMember.getUsername());
			preparedStatement.setString(3, letter);
			preparedStatement.setLong(4, currentDateTime);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			database.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("Letter");	

	}

}
