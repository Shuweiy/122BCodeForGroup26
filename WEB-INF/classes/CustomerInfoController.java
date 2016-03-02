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



public class CustomerInfoController extends HttpServlet
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



        if(action != null && action.equals("login")){

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

                String sqlEmailExist = "SELECT EXISTS(SELECT * FROM customers WHERE email='"+email+"');";


                  ResultSet result = action1.executeQuery(sqlEmailExist);

       

                  if(result.next())
                  {

                    if(result.getString(1)=="false")
                    {
                
                      result.close();
                      response.sendRedirect("/fabflix/index.jsp"); 
                    }
                    else
                    {
                      result.close();
                      String sqlPasswordTrue = "SELECT password FROM customers WHERE email='"+email+"';";
                      ResultSet result2 = action2.executeQuery(sqlPasswordTrue);
                      if(result2.next())
                      {
                        if(result2.getString(1).equals(password))
                        {
                          request.getRequestDispatcher("/Search.jsp").forward(request, response);
                        }
                        else
                        {
                          response.sendRedirect("/fabflix/index.jsp"); 
                        }
                      }
                      result2.close();
                    }
                  }     
                  response.sendRedirect("/fabflix/index.jsp");                           
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

        HttpSession session = request.getSession();

          try
              {

             
              //Class.forName("org.gjt.mm.mysql.Driver");
              Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement action1 = dbcon.createStatement();

              Statement action2 = dbcon.createStatement();

              String firstname = request.getParameter("firstname");
              String lastname = request.getParameter("lasrname");
              String cardnumber = request.getParameter("cardnumber");
              String expirationdate = request.getParameter("expirationdate");

              String sql = "SELECT EXISTS(SELECT * FROM creditcards WHERE first_name='"+firstname+"' AND last_name='"+lastname+"' AND id="+cardnumber+" AND expiration='"+expirationdate+"');";

       
              boolean bool = false;
              ResultSet result = action1.executeQuery(sql);

              if(result.next())
              {
                if(result.getInt(1)==1)
                {
                  bool=true;

                }
              }
              result.close(); 

    
              
     
              // get customer_id
              Integer customer_id = null;
              String sqlCustomerID = "SELECT id FROM customers WHERE first_name='"+firstname+"' AND last_name='"+lastname+"';";
              ResultSet resultCustomerID = action1.executeQuery(sqlCustomerID);
              if(resultCustomerID.next())
              {
                  customer_id=resultCustomerID.getInt(1);
              }
              resultCustomerID.close();

 


   
              // // get sale_date
              SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
              Calendar cal = Calendar.getInstance();
              String sale_date = df.format(cal.getTime());


              if(bool){
                  ArrayList<String> previousItems = (ArrayList<String>)session.getAttribute("previousItems");

                  for (String movie_id : previousItems){
                    String Salesql = "INSERT INTO sales(customer_id, movie_id, dale_date) VALUES("+customer_id+", "+movie_id+", '"+sale_date+"');";
                  
                  action2.executeUpdate(Salesql);
                  request.getRequestDispatcher("/succeed.html").forward(request, response);

                  }

              }
              else{

                request.getRequestDispatcher("/fail.html").forward(request, response);
              }


          } 
            catch (SQLException ex) {
                  while (ex != null) {
                        System.out.println ("SQL Exception:  " + ex.getMessage ());
                        ex = ex.getNextException ();
                    }  // end while
                }  // end catch SQLException

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

   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
       

    }
}    
