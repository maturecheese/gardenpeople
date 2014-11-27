package gardenpeople.servlet;

import gardenpeople.dao.CountyDAO;
import gardenpeople.dao.UserDAO;
import gardenpeople.model.County;
import gardenpeople.model.GardenOwner;
import gardenpeople.model.Gardener;
import gardenpeople.model.User;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int GARDEN_OWNER = 0;
	private static final int GARDENER = 1;   
	@Resource(name = "jdbc/testDB")
    DataSource ds;
  
    public RegisterServlet() {
        super();
        
    }

   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<County> counties = (ArrayList<County>) getServletContext().getAttribute("counties");
		request.setAttribute("counties", counties);
		request.getRequestDispatcher( "/WEB-INF/Register.jsp" ).forward(
	            request, response );
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> errors = new ArrayList<String>();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String account = request.getParameter("accountTypeRadios");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String house = request.getParameter("house");
		String street = request.getParameter("street");
		String postCode = request.getParameter("postCode");
		String county = request.getParameter("county");
		
		
		System.out.println(county);
		
		if (username == null || username.length() < 4 ){
			errors.add("username must be at least 4 character long");
		}
		if (email == null || username.length() < 4 || !email.contains("@")){
			errors.add("invalid email");
		}
		if (password == null || password.length() < 4 ){
			errors.add("password must be at least 4 characters");
		}
		
		if (errors.isEmpty()){
			boolean success;
			UserDAO userDao = new UserDAO();
			User user = null;
			if (account.equals("gardenOwner")){
				user = new GardenOwner(username, email, password);
			}else{
				user = new Gardener(username, email, password);
			}
			
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setHouseNumberName(house);
			user.setStreet(street);
			user.setPostcode(postCode);
			if (!county.equals("0")){
				System.out.println("setting county");
				user.setCounty(Integer.parseInt(county));
			}
			success = userDao.addUser(user);
			if(!success){
				errors.add("sorry that username is already in use, please try another");
			}else{
				request.setAttribute("user", user);
				request.getRequestDispatcher( "/WEB-INF/RegisterConfirmation.jsp" ).forward(
			            request, response );
				return;
				
			}
		}
		
		request.setAttribute("username", username);
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.setAttribute("errors", errors);
		
		doGet(request, response);
		
	
	}
	
	
	
	

}
