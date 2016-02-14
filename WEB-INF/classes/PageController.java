
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.util.*;

public class PageController extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Session Tracking Example";
    HttpSession session = request.getSession(true);
    

    Integer PageNo = (Integer)session.getAttribute("PageNo");
    String input = request.getParameter("input");
    String display = request.getParameter("display");

    if(input != null){
      if(input.equals("next")){
        PageNo+=1;
        session.setAttribute("PageNo", PageNo);
      }
      else if(input.equals("back") && PageNo>0){
        PageNo-=1;
        session.setAttribute("PageNo", PageNo);
      }
      else{
        session.setAttribute("PageNo", PageNo);
      }
    }

    if(display != null){
      session.setAttribute("display", Integer.parseInt(display));
      session.setAttribute("PageNo", 0);
    }




    // the following two statements show how to retrieve parameters in
    // the request.  The URL format is something like:
    //http://localhost:8080/project2/servlet/ShowSession?myname=Chen%20Li
    request.getRequestDispatcher("/Movielist.jsp").forward(request, response);

  }
  
/** Handle GET and POST requests identically. */
    public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
      doGet(request, response);
  }
}

