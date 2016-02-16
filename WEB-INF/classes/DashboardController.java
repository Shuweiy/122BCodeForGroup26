
/* A servlet to display the contents of the MySQL movieDB database */
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList.*;



public class DashboardController extends HttpServlet
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
        HttpSession session = request.getSession();

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
              String action = request.getParameter("action");

              String first_name = request.getParameter("first_name");
              String last_name = request.getParameter("last_name");

              String Notification = ""; 
              if(action.equals("addstar"))
              {
                

                String sql = "INSERT INTO stars(first_name, last_name) VALUES('"+first_name+"', '"+last_name+"');";

                action1.executeUpdate(sql);

                Notification = "Start insert successfully";

                action1.close();
                dbcon.close();
                session.setAttribute("Notification", Notification);
                response.sendRedirect("/fabflix/InsertStar.jsp"); 
                
                return;
              }

              if(action.equals("addmovie"))
              {

              }

              if(action.equals("metadata"))
              {
                  String sql = "INSERT INTO stars(first_name, last_name) VALUES('"+first_name+"', '"+last_name+"');";


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


  public static ArrayList<Integer> GetMovieIDsByGenre(String genre, Statement action1) throws Exception
  {
    ArrayList<Integer> movie_ids = new ArrayList<Integer>();
      
    String sql = "SELECT genres_in_movies.id FROM genres_in_movies WHERE genres_in_movies.genre_id=(SELECT genres.id FROM genres WHERE genres.name='"+genre+"');";

    ResultSet result = action1.executeQuery(sql);
    while(result.next())
    {
      movie_ids.add(result.getInt(1));
    }      result.close();
      
    return movie_ids;
    }
}
    
 

