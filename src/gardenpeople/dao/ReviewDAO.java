package gardenpeople.dao;


import gardenpeople.model.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mark-i5 on 06/12/2014.
 */
public class ReviewDAO extends DAO{

    public ArrayList<Review> getReviews(String gardenerUsername){

        ArrayList<Review> reviews = new ArrayList<Review>();

        try {
            String query = "Select * FROM reviews WHERE gardener_username=? " +
                    "ORDER BY created_at DESC";
            connection = getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, gardenerUsername);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()){
                Review review = new Review();
                review.setId(resultSet.getLong("id"));
                review.setAuthorUsername(resultSet.getString("author_username"));
                review.setGardenerUsername(resultSet.getString("gardener_username"));
                review.setText(resultSet.getString("text"));
                review.setRating(resultSet.getInt("score"));
                review.setCreatedAt(resultSet.getTimestamp("created_at"));
                reviews.add(review);
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
        return  reviews;

    }

    public boolean addReview(Review review)  {

        int updated = 0;
        String query = "INSERT INTO reviews (author_username,gardener_username,text,score)"
                +" VALUES (?,?,?,?)";
        //ON DUPLICATE KEY UPDATE id=VALUES(id), path=VALUES(path)
        try {
            System.out.println(query);
            connection = getConnection();
            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, review.getAuthorUsername());
            pStatement.setString(2, review.getGardenerUsername());
            pStatement.setString(3,review.getText());
            pStatement.setInt(4, review.getRating());

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
}
