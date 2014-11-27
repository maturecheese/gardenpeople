package gardenpeople.servlet;

import gardenpeople.dao.UserDAO;
import gardenpeople.model.County;
import gardenpeople.model.GardenOwner;
import gardenpeople.model.Gardener;
import gardenpeople.model.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditDetails
 */
@WebServlet("/details")
public class EditDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditDetailsServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false).getAttribute("user") == null){
			System.out.println("not logged in");
			response.sendRedirect("home");
			return;
		}
		
		ArrayList<County> counties = (ArrayList<County>) getServletContext().getAttribute("counties");
		request.setAttribute("counties", counties);
		request.getRequestDispatcher( "/WEB-INF/details.jsp" ).forward(
	            request, response );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false).getAttribute("user") == null){
			System.out.println("not logged in");
			response.sendRedirect("home");
			return;
		}
		
		
		ArrayList<String> errors = new ArrayList<String>();
		String confirmationMessage = null;
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String house = request.getParameter("house");
		String street = request.getParameter("street");
		String postCode = request.getParameter("postCode");
		String county = request.getParameter("county");
		
		System.out.println(password1.length());
		System.out.println("!sgsd "+county);
		
		
		if (email == null || email.length() < 4 || !email.contains("@")){
			errors.add("invalid email");
		}
		boolean newPasswordSet = (password1 != null && password1.length() !=0 );
		if(newPasswordSet && !checkPasswords(password1, password2)){
			errors.add("new password must be at least 4 characters and both password fields must match");
		}
		
		
		if (errors.isEmpty()){
			User user = (User) request.getSession().getAttribute("user");
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setHouseNumberName(house);
			user.setStreet(street);
			user.setPostcode(postCode);
			if(county != null){
				user.setCounty(Integer.parseInt(county));
			}
			UserDAO userDao = new UserDAO();
			boolean updateSuccess;
			if(newPasswordSet){
				user.setPassword(password1);
				updateSuccess = userDao.editUserDetailsWithPassword(user);
			}else{
				updateSuccess = userDao.editUserDetailsWithoutPassword(user);
			}
			
			if( !updateSuccess){
				errors.add("there was an error saving these details");
			}else{
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				confirmationMessage = "sucessfully saved changes at " + dateFormat.format(date);
				request.setAttribute("confirmation", confirmationMessage);
				request.getSession().setAttribute("user", user);
			}
		}
		
		request.setAttribute("errors", errors);
		doGet(request, response);
	}
	
	private boolean checkPasswords(String password1, String password2){
		if(password1 != null && password1.length() !=0 ){
			if ( password1.length() < 4 || !password1.equals(password2)){
				System.out.println("password1 != null && password1.length() !=0 ");
				return false;
			}
			
		}
		return true;
	}

}
