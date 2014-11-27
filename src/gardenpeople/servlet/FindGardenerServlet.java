package gardenpeople.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FindGardener")
public class FindGardenerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public FindGardenerServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher( "/WEB-INF/findgardener.jsp" ).forward(
	            request, response );
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("searchbyname") != null){
			response.getWriter().print("hfhdfhe");
		}
	}

}
