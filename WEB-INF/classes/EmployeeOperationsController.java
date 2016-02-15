import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EmployeeOperationsController extends HttpServlet
{
    private static Connection connection;	

    // Use http GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	handleRequest(request, response);
    }

    // Use http POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	handleRequest(request, response);
    }

    // handle request and response
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	// log into the database
    	String loginUser = "root";
    	String loginPasswd = "";
    	String loginURL = "jdbc:mysql://localhost:3306/moviedb";

    	// output stream to STDOUT
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();

    	// 
    	HttpSession session = request.getSession();

    	try
    	{
    		class.forName("com.mysql.jdbc.Driver").newInstance();
    		Connection dbcon = DriverManager.getConnection(loginURL, loginUser, loginPasswd);
    		Statement action = dbcon.createStatement();

    		Integer type = request.getParameter("type");

    		switch(type)
    		{
    			case 0:		// insert a star
    				break;
    			case 1:		// insert a movie
    				break;
    			case 2:		// show metadata
    				break;
    			default:
    				break;
    		}

    		action.close();
    		dbcon.close();
    		/*session.setAttribute();
    		session.setAttribute();
    		*/
    		request.getRequestDispatcher("/EmployeeOperations.jsp").forward(request, response);
    	}
    	catch(SQLException ex)
    	{
    		while(ex != null)
    		{
    			System.out.println("SQL Exception:  " + ex.getMessage());
    			ex = ex.getNextException();
    		}
    	}
    	catch(java.lang.Exception ex)
    	{
            out.println("<HTML>" + "<HEAD><TITLE>" + "MovieDB: Error" + "</TITLE></HEAD>\n<BODY>" +
			"<P>SQL error in doGethere: " + ex.getMessage()+"</P></BODY></HTML>");
			return;
    	}

    	out.close();
    }
}