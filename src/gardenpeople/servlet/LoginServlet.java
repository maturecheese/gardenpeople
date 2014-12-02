package gardenpeople.servlet;

import gardenpeople.dao.PublicProfileDAO;
import gardenpeople.dao.UserDAO;
import gardenpeople.model.GardenOwner;
import gardenpeople.model.Gardener;
import gardenpeople.model.PublicProfile;
import gardenpeople.model.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("inputUsername");
		String password = request.getParameter("inputPassword");
		UserDAO userDAO = new UserDAO();
		User user  = userDAO.login(username, password);

		if (user == null ){
			request.setAttribute("loginError", "username or password incorrect");
			request.getRequestDispatcher("/WEB-INF/home.jsp" ).forward(
					request, response );
			return;
		}else{
			if(user.isGardener()){
				user = new Gardener(user);
			}else{
				user = new GardenOwner(user);
			}
		}

		request.getSession(true).setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/home.jsp" ).forward(
				request, response );

	}

}
