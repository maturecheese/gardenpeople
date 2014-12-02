package gardenpeople.dao;

import gardenpeople.exception.UserFriendlySQLException;
import gardenpeople.model.ProfileImage;
import gardenpeople.model.PublicProfile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark-i5 on 01/12/2014.
 */
public class ImageDAO extends DAO {

    public boolean addImagePath(long id, String path)  {

        int updated = 0;
        String query = "INSERT INTO images (id,path)"
                +" VALUES (?,?) ON DUPLICATE KEY UPDATE id=VALUES(id), path=VALUES(path)";

        try {
            System.out.println(query);
            connection = getConnection();
            pStatement = connection.prepareStatement(query);
            pStatement.setLong(1, id);
            pStatement.setString(2, path);

            updated = pStatement.executeUpdate();


        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
           //throw new  UserFriendlySQLException(e.getMessage());

        } finally {
            try {
                if (pStatement != null)
                    pStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (updated > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<ProfileImage> getImages(long id){

        ArrayList<ProfileImage> images = new ArrayList<ProfileImage>();

        try {
            String query = "Select * FROM images WHERE id=?";
            connection = getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setLong(1, id);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()){
                images.add(new ProfileImage(resultSet.getString("path")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pStatement != null)
                    pStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  images;

    }
}
