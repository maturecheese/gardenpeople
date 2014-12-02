package gardenpeople.servlet;

import gardenpeople.dao.PublicProfileDAO;
import gardenpeople.exception.UserFriendlySQLException;
import gardenpeople.model.Gardener;
import gardenpeople.model.PublicProfile;
import gardenpeople.model.User;
import gardenpeople.validator.ProfileValidator;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/profile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditProfileServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gardener gardener = checkSession(request);
		if(gardener == null){
			response.sendRedirect("home");
			return;
		}
		setProfile(gardener, request);

		/*System.out.println(gardener.getPublicProfile().getLatitude());
		System.out.println(gardener.getPublicProfile().getLongitude());*/

		request.getRequestDispatcher( "/WEB-INF/profile.jsp" ).forward(
	            request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gardener gardener = checkSession(request);
		if(gardener == null){
			response.sendRedirect("home");
			return;
		}
		//System.out.println(gardener.getUsername() + "  username");
		Enumeration<String> parameterNames = request.getParameterNames();
		PublicProfile newProfile = new PublicProfile(gardener.getPublicProfile());

		while(parameterNames.hasMoreElements()){
			String parameterName = parameterNames.nextElement();
			String parameterValue = request.getParameter(parameterName);
			newProfile.setFromParameterName(parameterName, parameterValue);
		}
		ProfileValidator profileValidator = new ProfileValidator();
		profileValidator.checkProfile(newProfile);

		if(profileValidator.getErrors().size() > 0){
			for (String error : profileValidator.getErrors()){
				System.out.println(error);
			}
			request.setAttribute("errors", profileValidator.getErrors());
			gardener.setPublicProfile(newProfile);
			request.getRequestDispatcher( "/WEB-INF/profile.jsp" ).forward(
					request, response );
			return;
		}


		PublicProfileDAO publicProfileDAO= new PublicProfileDAO();

		try {
			if(gardener.getPublicProfile().getUsername() == null){
                publicProfileDAO.addProfile(newProfile);
            }else{
                publicProfileDAO.editProfile(gardener.getUsername(),newProfile);
            }
		} catch (UserFriendlySQLException e) {
			//TODO add error msg to request attribute
			System.out.println("AN SQL ERROR HAS OCCURRED!!!");
			request.getRequestDispatcher( "/WEB-INF/profile.jsp" ).forward(
					request, response );
			return;

		}
		gardener.setPublicProfile(newProfile);
		gardener.setProfileRecordedOnDatabase(true);
		String msg = profileValidator.getConfirmationWithTime();
		request.setAttribute("confirmation", msg);
		request.getRequestDispatcher( "/WEB-INF/profile.jsp" ).forward(
				request, response );



	}

	private Gardener checkSession(HttpServletRequest request){
		if (request.getSession(false) == null ||request.getSession(false).getAttribute("user") == null){
			System.out.println("not logged in");

			return null;
		}
		User user = (User) request.getSession(false).getAttribute("user");
		if(!user.isGardener()){
			return null;
		}

		return (Gardener) request.getSession(false).getAttribute("user");
	}

	private void setProfile(Gardener gardener, HttpServletRequest request) {

		PublicProfileDAO publicProfileDAO = new PublicProfileDAO();
		PublicProfile publicProfile = publicProfileDAO.getProfile(gardener.getUsername());
		if (publicProfile == null) {
			publicProfile = new PublicProfile(gardener.getUsername());

		}else{
			gardener.setProfileRecordedOnDatabase(true);
		}
		gardener.setPublicProfile(publicProfile);
		//request.getSession(true).setAttribute("profile", publicProfile);
	}

}
