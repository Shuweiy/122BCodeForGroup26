
/* A servlet to display the contents of the MySQL movieDB database */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList.*;



public class SearchController extends HttpServlet
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

        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();

        try
           {
              //Class.forName("org.gjt.mm.mysql.Driver");
              Class.forName("com.mysql.jdbc.Driver").newInstance();
     
              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              // String sql = "SELECT movies.id "+ "FROM (stars_in_movies INNER JOIN stars ON stars_in_movies.star_id=stars.id) "
              //   + "INNER JOIN movies ON stars_in_movies.movie_id=movies.id "
              //   + "WHERE movies.title LIKE '%?%' AND movies.director LIKE '%?%' "
              //   + "AND stars.first_name LIKE '%?%' AND stars.last_name LIKE '%?%'";

          

              String input = request.getParameter("input");
              String title = request.getParameter("title");
              String year = request.getParameter("year");
              String director = request.getParameter("director");
              String starFN = request.getParameter("starFN");
              String starLN = request.getParameter("starLN");
              String sql = "select id from movies where title like '%"+title+"%';";
              PreparedStatement pstmt = dbcon.prepareStatement(sql);

              pstmt.execute();


              ArrayList<Integer> movie_ids = new ArrayList();
              ResultSet result1 = pstmt.getResultSet();
              while(result1.next())
              {
                movie_ids.add(result1.getInt(1));
              }
              result1.close();
            


              
              pstmt.close();
              dbcon.close();
              session.setAttribute("PageNo", 0);
              session.setAttribute("display", 10);
              session.setAttribute("Movielist", movie_ids);
              response.sendRedirect("/fabflix/Movielist.jsp");
              return;

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



         out.close();
         
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {

       doGet(request, response);
       

    }

    public static ArrayList<Integer> GetMovieIDsByTitle(String title, Statement action1) throws Exception
    {
      ArrayList<Integer> movie_ids = new ArrayList<>();
      
      ResultSet result = action1.executeQuery("SELECT id FROM movies WHERE title LIKE '%"+title+"%';");
      while(result.next())
      {
        movie_ids.add(result.getInt(1));
      }
      result.close();
      
      return movie_ids;
    }

    public static ArrayList<Integer> GetMovieIDsByYear(int year) throws Exception
    {
      ArrayList<Integer> movie_ids = new ArrayList<>();
      
      ResultSet result = action1.executeQuery("SELECT id FROM movies WHERE year="+year+";");
      while(result.next())
      {
        movie_ids.add(result.getInt(1));
      }
      result.close();
      
      return movie_ids;
    }
    
    public static ArrayList<Integer> GetMovieIDsByDirector(String director) throws Exception
    {
      ArrayList<Integer> movie_ids = new ArrayList();
      
      ResultSet result = action1.executeQuery("SELECT id FROM movies WHERE director LIKE '%"+director+"%';");
      while(result.next())
      {
        movie_ids.add(result.getInt(1));
      }
      result.close();
      
      return movie_ids;
    }

    public static ArrayList<Integer> GetMovieIDsByCombination(String title, String year, String director, String starFN, String starLN,Statement action1) throws Exception
    {
      ArrayList<Integer> movie_ids = new ArrayList();
      String sql = "SELECT movies.id "
          + "FROM (stars_in_movies INNER JOIN stars ON stars_in_movies.star_id=stars.id) "
          + "INNER JOIN movies ON stars_in_movies.movie_id=movies.id "
          + "WHERE movies.title LIKE '%"+title+"%' AND movies.year="+year+" AND movies.director LIKE '%"+director+"%' "
              + "AND stars.first_name LIKE '%"+starFN+"%' AND stars.last_name LIKE '%"+starLN+"%'";
      

      ResultSet result1 = action1.executeQuery(sql);
      while(result1.next())
      {
        movie_ids.add(result1.getInt(1));
      }
      result1.close();
      
      return movie_ids;
    }
    
    public static ArrayList<Integer> GetMovieIDsByStarsFirstName(String firstname) throws Exception
    {
      ArrayList<Integer> movie_ids = new ArrayList();
      
      ResultSet result1 = action1.executeQuery("SELECT id FROM stars WHERE first_name LIKE '%"+firstname+"%';");
      ResultSet result2 = null;
      while(result1.next())
      {
        result2 = action2.executeQuery("SELECT movie_id FROM stars_in_movies where star_id='"+Integer.toString(result1.getInt(1))+"';");
        while(result2.next())
        {
          movie_ids.add(result2.getInt(1));
        }
        result2.close();
      }
      result1.close();
      
      return movie_ids;
    }

  }