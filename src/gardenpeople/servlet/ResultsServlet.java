package gardenpeople.servlet;

import gardenpeople.dao.UserDAO;
import gardenpeople.model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Results")
public class ResultsServlet extends HttpServlet {
    //@EJB
	private static final long serialVersionUID = 1L;
 
    public ResultsServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("name") != null){
	        String nameStr = request.getParameter("name");
	        
	        UserDAO userDAO = new UserDAO();
			List<User> results  = userDAO.findGardenerByName(nameStr);
			
	        
	        request.setAttribute("allGardeners", results);
	        request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response); 
	        
	        
/*
			ArrayList<String> list = new ArrayList<String>();
			list.add("A");
			list.add("B");
			list.add("C");
	        request.setAttribute("test", list);
	        request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response); */
			//response.getWriter().print(nameStr);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if (request.getParameter("searchbyname") != null){
	        /*String nameStr = request.getParameter("searchterm");
	        
	        UserDAO userDAO = new UserDAO();
			List<User> results  = userDAO.findGardenerByName(nameStr);
			
	        
	        request.setAttribute("allGardeners", results);
	        request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response); */
	        
	        
/*
			ArrayList<String> list = new ArrayList<String>();
			list.add("A");
			list.add("B");
			list.add("C");
	        request.setAttribute("test", list);
	        request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response); */
			//response.getWriter().print(nameStr);
		//}
	}

}
