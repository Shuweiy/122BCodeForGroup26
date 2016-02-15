/* A servlet to display the contents of the MySQL movieDB database */

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



public class EmployeeController extends HttpServlet
{

    private static Connection connection;
    private static Statement action1;
    private static Statement action2;
    private static Statement action3;

    public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
      {
        String loginUser = "root";
        String loginPasswd = "";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
        PrintWriter out = response.getWriter();
        out.println("<HTML>" +
                        "<HEAD><TITLE></HEAD></TITle>");

        response.setContentType("text/html");    // Response mime type
        String action = request.getParameter("action");



        if(action.equals("login")){

          String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
          System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
          // Verify CAPTCHA.
          boolean valid = VerifyUtils.verify(gRecaptchaResponse);
          if (!valid) {
          //errorString = "Captcha invalid!";
                  out.println("<HTML>" +
                  "<HEAD><TITLE>" +
                  "MovieDB: Error" +
                  "</TITLE></HEAD>\n<BODY>" +
                  "<P>Recaptcha WRONG!!!! </P></BODY></HTML>");
                  return;
              }

          try{

             String email = request.getParameter("email");

             String password = request.getParameter("password");

             Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement action1 = dbcon.createStatement();
              Statement action2 = dbcon.createStatement();

                String sqlEmailExist = "SELECT EXISTS(SELECT * FROM employees WHERE email='"+email+"');";


                  ResultSet result = action1.executeQuery(sqlEmailExist);

       

                  if(result.next())
                  {

                    if(result.getString(1)=="false")
                    {
                
                      result.close();
                      response.sendRedirect("/fabflix/EmployeeController.jsp"); 
                    }
                    else
                    {
                      result.close();
                      String sqlPasswordTrue = "SELECT password FROM employees WHERE email='"+email+"';";
                      ResultSet result2 = action2.executeQuery(sqlPasswordTrue);
                      if(result2.next())
                      {
                        if(result2.getString(1).equals(password))
                        {
                          request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
                        }
                        else
                        {
                          response.sendRedirect("/fabflix/_dashboard.jsp"); 
                        }
                      }
                      result2.close();
                    }
                  }     
                  response.sendRedirect("/fabflix/_dashboard.jsp");                           
          }

          
            catch(java.lang.Exception ex)
                {
                    out.println("<HTML>" +
                                "<HEAD><TITLE>" +
                                "MovieDB: Error" +
                                "</TITLE></HEAD>\n<BODY>" +
                                "<P>SQL error in doGet: " +
                                ex.getMessage() + "</P></BODY></HTML>");
                    return;
            }          

        }



        // Output stream to STDOUT

       
      } 

   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
       

    }
}    