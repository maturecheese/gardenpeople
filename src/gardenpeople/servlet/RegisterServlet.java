package gardenpeople.servlet;

import gardenpeople.dao.UserDAO;
import gardenpeople.exception.UserFriendlySQLException;
import gardenpeople.model.County;
import gardenpeople.model.User;
import gardenpeople.validator.UserValidator;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<County> counties = (ArrayList<County>) getServletContext().getAttribute("counties");
        request.setAttribute("counties", counties);
        request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(
                request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password1"));
        user.setRepeatedPassword(request.getParameter("password2"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setHouseNumberName(request.getParameter("house"));
        user.setPostcode(request.getParameter("postcode"));
        user.setStreet(request.getParameter("street"));
        //user.setCounty(Integer.parseInt(request.getParameter("county")));
        user.setAccountType(request.getParameter("accountTypeRadios"));



        UserValidator v = new UserValidator();
        v.checkUserWithPasswords(user);

        if (v.getErrors().size() > 0) {
            request.setAttribute("errors", v.getErrors());
            request.setAttribute("user", user);

            request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(
                    request, response);
            return;

        }

        UserDAO userDao = new UserDAO();

        try {

            userDao.addUser(user);
        } catch (UserFriendlySQLException e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());

            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            doGet(request, response);
            return;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/RegisterConfirmation.jsp").forward(
                request, response);


    }



}



