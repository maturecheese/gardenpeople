package gardenpeople.servlet;

import gardenpeople.dao.CountyDAO;
import gardenpeople.model.County;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns ={"","/home"} )
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HomeServlet() {
        super();
        
    }
    @Override
    public void init() throws ServletException {
    	CountyDAO countyDAO = new CountyDAO();
		ArrayList<County>  counties =countyDAO.findAll();
		getServletContext().setAttribute("counties", counties);
		
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher( "/WEB-INF/home.jsp" ).forward(
	            request, response );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
