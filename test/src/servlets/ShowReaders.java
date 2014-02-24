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

import objects.Member;

/**
 * Servlet implementation class ShowReaders
 */
@WebServlet("/ShowReaders")
public class ShowReaders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowReaders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 Member currentMember = (Member)request.getSession().getAttribute("user");
		 String currentMemberString = currentMember.getUsername();
	
		ArrayList<Member> readersArray = new ArrayList<Member>();
	    Connection database = Classes.DbConnection.getDatabase();
	    PreparedStatement ps2;
		try {
			ps2 = database.prepareStatement( "SELECT ReadingUser from readinglinkingtable where ReadUser = '"+currentMemberString+"';");
			ResultSet rs = ps2.executeQuery();
			 while(rs.next()){  
				   Member members=new Member();  
				   members.setUsername(rs.getString(1));  
				   readersArray.add(members);  
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
		
		request.setAttribute("readerList", readersArray);
		request.getRequestDispatcher("/Readers.jsp").forward(request, response);
	

		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
