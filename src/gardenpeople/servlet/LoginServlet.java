package gardenpeople.servlet;

import gardenpeople.dao.UserDAO;
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
		User user  = userDAO.findUser(username, password);
		//System.out.println(username + "kfdgkdn");
		//response.getWriter().print(username);

		if (user == null ){
			request.setAttribute("loginError", "username or password incorrect");
		}
		request.getSession(true).setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/home.jsp" ).forward(
				request, response );
		//responsesendRedirect("home");
		//request.getRequestDispatcher("/home").forward(request, response);
		
		
	}

}
