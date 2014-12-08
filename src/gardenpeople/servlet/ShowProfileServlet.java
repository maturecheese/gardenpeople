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
import java.util.Date;

/**
 * Created by mark-i5 on 06/12/2014.
 */
@WebServlet("/showProfile")
public class ShowProfileServlet extends HttpServlet {

    private String referer ="/Results";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //leave review

        ArrayList<String> errors = new ArrayList<String>();
        Long gardenerID  = getGardenerID(request);
        String searchTerm =  request.getParameter("search_term");
        System.out.println("gardener_id = " +gardenerID);
        if(gardenerID == null){
            response.sendRedirect("home");
            return;
        }
        Gardener gardener = getGardenerWithImagesAndReviews(gardenerID);
        request.setAttribute("gardener", gardener);
        request.setAttribute("searchTerm", searchTerm);

        if(request.getSession(false) == null || request.getSession(false).getAttribute("user") == null){
            errors.add("you must be logged in to add a review");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/showProfile.jsp").forward(request, response);
            return;
        }

        User user = (User) request.getSession(false).getAttribute("user");


        Review review = new Review();
        review.setAuthorUsername(user.getUsername());
        review.setGardenerUsername(request.getParameter("gardener_username"));

        try { review.setRating(Integer.parseInt(request.getParameter("rating")));
        } catch (NumberFormatException e) {review.setRating(0);}

        review.setText(request.getParameter("comment"));

        ReviewValidator reviewValidator = new ReviewValidator();
        reviewValidator.checkReview(review);

        if(reviewValidator.getErrors().size() > 0){
            request.setAttribute("errors", reviewValidator.getErrors());
            request.getRequestDispatcher("/WEB-INF/showProfile.jsp").forward(request, response);
            return;
        }

        review.setCreatedAt(new Date());
        gardener.getReviews().add(0, review);
        request.setAttribute("confirmation", reviewValidator.getConfirmationWithTime());
        request.getRequestDispatcher("/WEB-INF/showProfile.jsp").forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String searchTerm = getSearchTerm(request);// get parameters necessary to link back to the exact search from the view profile page
        Long gardenerID  = getGardenerID(request);
        if(gardenerID == null){
            response.sendRedirect("home");
            return;
        }
        Gardener gardener = getGardenerWithImagesAndReviews(gardenerID);
        if(gardener!= null){

             request.setAttribute("searchTerm", searchTerm);
            request.setAttribute("lat", request.getParameter("lat"));
            request.setAttribute("lng", request.getParameter("lng"));
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("gardener", gardener);
            request.getRequestDispatcher("/WEB-INF/showProfile.jsp").forward(request,response);
        }



    }


    private Long getGardenerID(HttpServletRequest request){
        Long gardenerID = null;
        try {
            gardenerID = Long.parseLong( request.getParameter("id"));

        } catch (NumberFormatException  e) {}
        try {
            gardenerID = Long.parseLong( request.getParameter("gardener_id"));

        } catch (NumberFormatException  e) {}
        return  gardenerID;
    }

    private Gardener getGardenerWithImagesAndReviews(long gardenerID){

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


        }
        return gardener;
    }

    private String getSearchTerm(HttpServletRequest request){
        String searchTerm = null;
        if(request.getHeader("referer") != null){

            String refererURI = null;
            try {
                refererURI = new URI(request.getHeader("referer")).getPath();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            String name = request.getParameter("name");
            String lat = request.getParameter("lat");
            String lng = request.getParameter("lng");
            searchTerm = rebuildSearchUrl(refererURI,lat,lng,name);
        }
        System.out.println(searchTerm);
        return searchTerm;
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
