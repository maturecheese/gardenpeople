package gardenpeople.servlet;

import gardenpeople.dao.UserDAO;
import gardenpeople.exception.UserFriendlySQLException;
import gardenpeople.model.GardenOwner;
import gardenpeople.model.Gardener;
import gardenpeople.model.PublicProfile;
import gardenpeople.model.User;
import gardenpeople.validator.UserValidator;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

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

		/*if(request.getAttribute("editedUser") != null){
			request.setAttribute("user", request.getAttribute("editedUser"));
		}else{
			request.setAttribute("user", request.getSession(false).getAttribute("user"));
		}*/

		request.getRequestDispatcher( "/WEB-INF/details.jsp" ).forward(
	            request, response );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false).getAttribute("user") == null){
			System.out.println("not logged in");
			response.sendRedirect("home");
			return;
		}

		User user = (User) request.getSession(false).getAttribute("user");
		User editedUser = null;
		if (user.isGardener()){
			editedUser = new Gardener(user);
		}else{
			editedUser = new GardenOwner(user);
		}
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String parameterName = parameterNames.nextElement();
			String parameterValue = request.getParameter(parameterName);
			editedUser.setFromParameterName(parameterName, parameterValue);
		}

		UserValidator v = new UserValidator();
		boolean editWithPasswords = editedUser.hasSetPasswords();
		System.out.println(editWithPasswords);
		if(editWithPasswords){

			v.checkUserWithPasswords(editedUser);
		}else{
			v.checkUserWithoutPasswords(editedUser);
		}

		request.getSession(false).setAttribute("user", editedUser);
		if(v.getErrors().size()> 0){
			request.setAttribute("errors", v.getErrors());
			request.getRequestDispatcher( "/WEB-INF/details.jsp" ).forward(
					request, response);
			return;
		}

		ArrayList<String> errors = new ArrayList<String>();
		UserDAO userDao = new UserDAO();
		try {
			if (editWithPasswords) {
                userDao.editUserDetailsWithPassword(editedUser);

            }else {
                userDao.editUserDetailsWithoutPassword(editedUser);
            }
		} catch (UserFriendlySQLException e) {
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			request.getRequestDispatcher( "/WEB-INF/details.jsp" ).forward(
					request, response);
			return;
		}
		///hopefully everything went ok ....
		request.setAttribute("confirmation", v.getConfirmationWithTime());
		request.getRequestDispatcher( "/WEB-INF/details.jsp" ).forward(
				request, response );

	}


	


}
