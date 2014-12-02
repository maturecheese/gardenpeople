package gardenpeople.servlet;

import gardenpeople.dao.ImageDAO;
import gardenpeople.dao.PublicProfileDAO;
import gardenpeople.model.Gardener;
import gardenpeople.model.ProfileImage;
import gardenpeople.model.PublicProfile;
import gardenpeople.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark-i5 on 01/12/2014.
 */
@WebServlet(urlPatterns = {"/photos"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,    // 10 MB
        maxFileSize = 1024 * 1024 * 50,          // 50 MB
        maxRequestSize = 1024 * 1024 * 100)       // 100 MB
public class ProfilePhotosServlet extends HttpServlet {

    private static final String UPLOAD_PATH = "G:\\JavaEE_Dev\\imageServer\\images";
    private static final String ACCESS_ROOT = "/images/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //if he ain't a gardener kick him out
        Gardener gardener = checkSession(request);
        if (gardener == null) {
            response.sendRedirect("home");
            return;
        }
        setProfile(gardener, request);
        ImageDAO imageDAO = new ImageDAO();

        ArrayList<ProfileImage> images = imageDAO.getImages(gardener.getAutoIncrementID());
        gardener.getPublicProfile().setImages(images);

        request.getRequestDispatcher("/WEB-INF/photos.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gardener gardener = checkSession(request);
        if (gardener == null) {
            response.sendRedirect("home");
            return;
        }
        // creates the save directory if it does not exists
        User user = (User) request.getSession(false).getAttribute("user");
        File fileSaveDir = new File(UPLOAD_PATH);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());

        String fileName = null;
        //Get all the parts from request and write it to the file on server
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            part.write(fileSaveDir.getAbsolutePath() + File.separator + user.getAutoIncrementID() + "__" + fileName);
        }
        ImageDAO imageDAO = new ImageDAO();

        imageDAO.addImagePath(user.getAutoIncrementID(), ACCESS_ROOT + user.getAutoIncrementID() + "__" + fileName);
        request.setAttribute("message", fileName + " File uploaded successfully!");
        doGet(request, response);

        //request.getRequestDispatcher("/WEB-INF/photos.jsp").forward(request, response);


    }


    private Gardener checkSession(HttpServletRequest request) {
        if (request.getSession(false) == null || request.getSession(false).getAttribute("user") == null) {
            System.out.println("not logged in");

            return null;
        }
        User user = (User) request.getSession(false).getAttribute("user");
        if (!user.isGardener()) {
            return null;
        }

        return (Gardener) request.getSession(false).getAttribute("user");
    }

    private void setProfile(Gardener gardener, HttpServletRequest request) {


        if(gardener.getPublicProfile() == null){
            PublicProfileDAO publicProfileDAO = new PublicProfileDAO();
            PublicProfile publicProfile = publicProfileDAO.getProfile(gardener.getUsername());
            if (publicProfile == null){
                publicProfile = new PublicProfile(gardener.getUsername());
            }else{
                gardener.setPublicProfile(publicProfile);
            }
            gardener.setPublicProfile(publicProfile);
        }

        /*PublicProfileDAO publicProfileDAO = new PublicProfileDAO();
        PublicProfile publicProfile = publicProfileDAO.getProfile(gardener.getUsername());
        if (publicProfile == null) {
            publicProfile = new PublicProfile(gardener.getUsername());

        } else {
            gardener.setProfileRecordedOnDatabase(true);
        }
        gardener.setPublicProfile(publicProfile);*/

    }

    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

}
