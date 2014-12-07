package gardenpeople.servlet;

import gardenpeople.dao.GardenerDAO;
import gardenpeople.dao.ImageDAO;
import gardenpeople.dao.ReviewDAO;
import gardenpeople.model.Gardener;
import gardenpeople.model.ProfileImage;
import gardenpeople.model.Review;
import gardenpeople.model.User;
import gardenpeople.validator.ReviewValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by mark-i5 on 06/12/2014.
 */
@WebServlet("/showProfile")
public class ShowProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //leave review

        String searchTerm =  request.getParameter("search_term");
        if(request.getSession(false) != null){
            User user = (User) request.getSession(false).getAttribute("user");
            if(user != null){

                Review review = new Review();
                review.setAuthorUsername(user.getUsername());
                review.setGardenerUsername(request.getParameter("gardener_username"));
                review.setRating(Integer.parseInt(request.getParameter("rating")));
                review.setText(request.getParameter("comment"));

                ReviewValidator reviewValidator = new ReviewValidator();
                reviewValidator.checkReview(review);

                if(reviewValidator.getErrors().size() > 0){

                }


                ReviewDAO reviewDAO= new ReviewDAO();
                reviewDAO.addReview(review);


                if (searchTerm != null){
                    searchTerm = searchTerm.substring(1);
                }

                response.sendRedirect("../" + searchTerm);
                return;
            }



        }
        response.sendRedirect("../" + searchTerm);
        return;
       // System.out.println("WTF");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long gardenerID;

        String searchTerm = null;
        String lat = "";
        String lng = "";
        String name = "";
        try {
            if(request.getHeader("referer") != null){

                String refererURI = new URI(request.getHeader("referer")).getPath();
                name = request.getParameter("name");
                lat = request.getParameter("lat");
                lng = request.getParameter("lng");
                searchTerm = rebuildSearchUrl(refererURI,lat,lng,name);
            }
            System.out.println(searchTerm);



            System.out.println(request.getParameter("search_term"));
            gardenerID = Long.parseLong( request.getParameter("id"));
        } catch (NumberFormatException | URISyntaxException e) {
            response.sendRedirect("home");
            return;

        }

        GardenerDAO gardenerDAO = new GardenerDAO();
        Gardener gardener = gardenerDAO.getGardenerWithProfile(gardenerID);

        if(gardener!= null){
            System.out.println("found gardener");
            ImageDAO imageDAO = new ImageDAO();
            ArrayList<ProfileImage> images = imageDAO.getImages(gardenerID);
            gardener.getPublicProfile().setImages(images);

            ReviewDAO reviewDAO = new ReviewDAO();
            System.out.println("username "+ gardener.getUsername());
            ArrayList<Review> reviews = reviewDAO.getReviews(gardener.getUsername());
            System.out.println("num review " + reviews.size());
            gardener.setReviews(reviews);

            request.setAttribute("searchTerm", searchTerm);
            request.setAttribute("lat", lat);
            request.setAttribute("lng", lng);
            request.setAttribute("name", name);
            request.setAttribute("gardener", gardener);
            request.getRequestDispatcher("/WEB-INF/showProfile.jsp").forward(request,response);
        }



    }



    private String rebuildSearchUrl(String referer, String lat, String lng, String name){

        if(referer != null){

            if(lat != null && lat.length() > 0 ){
                referer += "?lat=" +lat;
                referer += "&lng=" +lng;
            }
            else if(name != null && name.length()>0){
                referer += "?name=" +name;
            }
            return referer;
        }
        return null;
    }
}
