package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.LetterObject;
import objects.Member;

/**
 * Servlet implementation class Reading
 */
@WebServlet({"/reading/","/reading/*"})
public class Reading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reading() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	
		Connection database = Classes.DbConnection.getDatabase();
		String username = request.getRequestURI();

		username = username.substring(username.lastIndexOf("/") + 1);
    	objects.Member currentMember = (Member)request.getSession().getAttribute("user");
    	String currentUser = currentMember.getUsername();
	   

 		    PreparedStatement ps;
 			try {
 				ps = database.prepareStatement( "DELETE from readinglinkingtable where ReadUser = '"+username+ "' AND ReadingUser = '"+currentUser+"';");
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
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Connection database = Classes.DbConnection.getDatabase();
		String username = request.getRequestURI();

		username = username.substring(username.lastIndexOf("/") + 1);
    	objects.Member currentMember = (Member)request.getSession().getAttribute("user");
    	String currentUser = currentMember.getUsername();

	
		String insertSQL = "INSERT into readinglinkingtable VALUES(?, ?, ?);";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = database.prepareStatement(insertSQL);
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, currentUser);
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
	}

}
