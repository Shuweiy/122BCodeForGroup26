import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*; 
import java.util.Date.*;

public class ShoppingController extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    
    HttpSession session = request.getSession();

    String action = request.getParameter("action");
    ArrayList previousItems = (ArrayList)session.getAttribute("previousItems");

    if(action != null){
      if(action.equals("check")){
      request.getRequestDispatcher("/ShoppingCart.jsp").forward(request, response);
      }
      if(action.equals("remove")){
        String item_id = request.getParameter("item_id");
        previousItems.remove(item_id);
        session.setAttribute("previousItems", previousItems);
        request.getRequestDispatcher("/ShoppingCart.jsp").forward(request, response);
      }
     } 
    synchronized(session) {
      if (previousItems == null) {
          previousItems = new ArrayList();
          session.setAttribute("previousItems", previousItems);
      }
    }

    String newItem = request.getParameter("item");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
   synchronized(previousItems) {
      if (newItem != null) {
        previousItems.add(newItem);
        session.setAttribute("previousItems", previousItems);
      }
      if (previousItems.size() == 0) {
        out.println("<I>No items</I>");
      } else {
        out.println("<UL>");
        for(int i=0; i<previousItems.size(); i++) {
          out.println("<LI>" + (String)previousItems.get(i));
        }
        out.println("</UL>");
      }
    }
    request.getRequestDispatcher("/Movielist.jsp").forward(request, response);


   // The following two statements show how this thread can access an
   // object created by a thread of the ShowSession servlet
   // Integer accessCount = (Integer)session.getAttribute("accessCount");
   // out.println("<p>accessCount = " + accessCount);

   out.println("</BODY></HTML>");
  }

}

