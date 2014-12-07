package gardenpeople.servlet;

import gardenpeople.dao.ImageDAO;
import gardenpeople.dao.PublicProfileDAO;
import gardenpeople.model.Gardener;
import gardenpeople.model.PublicProfile;
import gardenpeople.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/PersonalPhoto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,    // 3 MB
        maxFileSize = 1024 * 1024 * 10,          //3 MB
        maxRequestSize = 1024 * 1024 * 10)      // 3 MB
public class PersonalPhotoUploadServlet extends HttpServlet {


    //From http://www.journaldev.com/2122/servlet-3-file-upload-using-multipartconfig-annotation-and-part-interface


    private static final String UPLOAD_PATH = "G:\\JavaEE_Dev\\imageServer\\images";
    private static final String ACCESS_ROOT = "/images/";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Gardener gardener = (Gardener) request.getSession(false).getAttribute("user");
        File fileSaveDir = new File(UPLOAD_PATH);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());

        String fileName = null;
        String name;
        //Get all the parts from request and write it to the file on server
        for (Part part : request.getParts()) {

           name= getFileName(part);
            if(name.length()>0){
                fileName = (getFileName(part).equals("")) ? name : getFileName(part);
                part.write(fileSaveDir.getAbsolutePath() + File.separator + gardener.getAutoIncrementID() + "__" + name);

            }
        }

        PublicProfileDAO publicProfileDAO = new PublicProfileDAO();
        String imagePath = ACCESS_ROOT + gardener.getAutoIncrementID() + "__" + fileName;
        System.out.println(imagePath);
        boolean success = publicProfileDAO.editProfile(gardener.getUsername(), imagePath );

        if(!success){
            ArrayList<String> errors = new ArrayList<String>();
            errors.add("you must complete the form and save a profile before adding a personal photo");
            request.getSession().setAttribute("errors", errors);
        }

        response.sendRedirect("./profile");
        //request.getRequestDispatcher("./profile").forward(request, response);


    }



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
