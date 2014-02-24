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
 * Servlet implementation class Profile
 */
@WebServlet({"/profile/","/profile/*"})
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getRequestURI();
		username = username.substring(username.lastIndexOf("/") + 1);
	   
	    ArrayList<LetterObject> usersLetters = new ArrayList<LetterObject>();
	    Connection database = Classes.DbConnection.getDatabase();
	    PreparedStatement ps;
		try {
			ps = database.prepareStatement( "SELECT * from letters where LetterUser = '"+username+"'  ORDER by LetterID DESC;");
			ResultSet rs = ps.executeQuery();
			 while(rs.next()){  
				   LetterObject letterObject=new LetterObject();  
				   letterObject.setLetterID(rs.getInt(1));  
				   letterObject.setLetterUser(rs.getString(2));  
				   letterObject.setLetterContent(rs.getString(3));  
				   letterObject.setTimeDate(rs.getLong(4));  
				   usersLetters.add(letterObject);  
				  }  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("Letters", usersLetters);
		
		
		
		boolean readingCheck;
		
		ArrayList<String> readingProfile = new ArrayList<String>();
		    PreparedStatement ps2;
			try {
				ps2 = database.prepareStatement( "SELECT ReadingUser from readinglinkingtable where ReadUser = '"+username+"';");
				ResultSet rs = ps2.executeQuery();
				 while(rs.next()){   
					   readingProfile.add(rs.getString(1));  
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
			
			 Member currentMember = (Member)request.getSession().getAttribute("user");
			 String currentMemeberString = currentMember.getUsername();

			if(readingProfile.contains(currentMemeberString)){
				 readingCheck = true;
				
		
			}
			else{
				
				 readingCheck = false;
				
			}

			request.setAttribute("readingCheck", readingCheck);
			request.setAttribute("username", username);

		//request.getRequestDispatcher("/profile/"+username).forward(request, response);

		request.getRequestDispatcher("/profile.jsp").forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
