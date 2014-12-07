package gardenpeople.servlet;

import gardenpeople.dao.GardenerDAO;
import gardenpeople.model.Gardener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		List<Gardener> gardeners = new ArrayList<Gardener>();
		if (request.getParameter("name") != null){
	        String tradename = (String) request.getParameter("name");
	        System.out.print(tradename);
			GardenerDAO gardenerDAO= new GardenerDAO();
			gardeners = gardenerDAO.getGardenersWithProfiles(tradename);
			System.out.println("no gardeners: "+ gardeners.size());


		}
		else if (request.getParameter("lat") != null){
	        float lat = Float.parseFloat(request.getParameter("lat"));
	        float lng = Float.parseFloat(request.getParameter("lng"));
	       // int radius = Integer.parseInt(request.getParameter("radius"));

			GardenerDAO gardenerDAO= new GardenerDAO();

			gardeners  = gardenerDAO.getGardenersWithProfiles(lat, lng);
			


		}
		request.setAttribute("gardeners", gardeners);
		request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);

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
