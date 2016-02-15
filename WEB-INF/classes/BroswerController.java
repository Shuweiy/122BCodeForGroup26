
/* A servlet to display the contents of the MySQL movieDB database */
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList.*;



public class BroswerController extends HttpServlet
{
    private static ParseResult parResult;

    private static Connection connection;
    private static Statement action1;
    private static Statement action2;
    private static Statement action3;
    private ArrayList<Integer> movieL;

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


        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
        
        try
           {
              //Class.forName("org.gjt.mm.mysql.Driver");
              Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement

              Statement action1 = dbcon.createStatement();
              Statement action2 = dbcon.createStatement();
              Statement action3 = dbcon.createStatement();

              JDBC jdbc = new JDBC(action1,action2,action3);



              String input = request.getParameter("input");
              
            
              
              ArrayList<Integer> movie_ids = new ArrayList();

               if(input.length()>1){

               ResultSet result = action1.executeQuery("SELECT id FROM movies WHERE title LIKE'"+input+"%';");
                while(result.next())
                {
                  movie_ids.add(result.getInt(1));
                }
                result.close();
                movieL = movie_ids;              
              }


              else{
                        
               movieL = jdbc.GetMovieIDsByTitleFirstChar(input);


              }

              HttpSession session = request.getSession();


              action1.close();
              dbcon.close();
              session.setAttribute("PageNo", 0);
              session.setAttribute("display", 10);
              session.setAttribute("Movielist", movieL);
              request.getRequestDispatcher("/Movielist.jsp").forward(request, response);

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
                            "<P>SQL error in doGethere: " +
                            ex.getMessage()+"</P></BODY></HTML>");
                return;
            }



         out.close();
         
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {

       doGet(request, response);
       

    }

   public static ArrayList<Integer> GetMovieIDsByTitleFirstChar(String title_first_char,Statement action1) throws Exception
  {
    ArrayList<Integer> movie_ids = new ArrayList();
    
    ResultSet result = action1.executeQuery("SELECT id FROM movies WHERE title LIKE'"+title_first_char+"%';");
    while(result.next())
    {
      movie_ids.add(result.getInt(1));
    }
    result.close();
    
    return movie_ids;
  }


    public static ArrayList<Integer> GetMovieIDsByYear(int year) throws Exception
    {
      ArrayList<Integer> movie_ids = new ArrayList<Integer>();
      
      ResultSet result = action1.executeQuery("SELECT id FROM movies WHERE year="+year+";");
      while(result.next())
      {
        movie_ids.add(result.getInt(1));
      }
      result.close();
      
      return movie_ids;
    }
    
 

  }
