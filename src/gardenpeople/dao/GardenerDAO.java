package gardenpeople.dao;

import gardenpeople.model.Gardener;
import gardenpeople.model.ProfileImage;
import gardenpeople.model.PublicProfile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark-i5 on 05/12/2014.
 */
public class GardenerDAO extends DAO {

    public List<Gardener> getGardenersWithProfiles(String name){

        List<Gardener> gardeners = new ArrayList<Gardener>();



        try {
            /*String query = "Select *, COUNT(r.id) as num_reviews " +
                    "FROM profiles p LEFT JOIN users u ON p.username = u.username " +
                    "LEFT JOIN reviews r ON p.username = r.gardener_userame " +
                    "WHERE tradename LIKE ? " +
                    "GROUP BY p.username";*/

            String query = "Select *, COUNT(r.id) as num_reviews, AVG(Cast(r.score as decimal(2,1))) as avg_rating "+
                            "FROM profiles p JOIN users u ON p.username = u.username "+
                            "LEFT JOIN reviews r ON p.username = r.gardener_username "+
                            "WHERE tradename LIKE ? "+
                            "GROUP BY p.username " +
                            "ORDER BY avg_rating DESC";


            connection = getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, '%' + name + '%');

            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {

                System.out.println("num reviews" +resultSet.getString("num_reviews"));
                Gardener gardener = new Gardener(resultSet.getString("username"), resultSet.getString("email"));

                gardener.setFirstName(resultSet.getString("first_name"));
                gardener.setLastName(resultSet.getString("last_name"));
                gardener.setReviewCount(resultSet.getInt("num_reviews"));
                gardener.setAvgReviewScore(resultSet.getFloat("avg_rating"));
                gardener.setPhone(resultSet.getString("phone"));
                gardener.setPostcode(resultSet.getString("postcode"));
                gardener.setAutoIncrementID(resultSet.getInt("id"));

                PublicProfile publicProfile = new PublicProfile(resultSet.getString("username"));
                publicProfile.setTradename(resultSet.getString("tradename"));
                publicProfile.setDescription(resultSet.getString("description"));
                publicProfile.setLatitude(resultSet.getFloat("lat"));
                publicProfile.setLongitude(resultSet.getFloat("lng"));
                publicProfile.setRadius(resultSet.getInt("radius"));
                publicProfile.setRhs1(resultSet.getBoolean("rhs_1"));
                publicProfile.setRhs2(resultSet.getBoolean("rhs_2"));
                publicProfile.setRhs3(resultSet.getBoolean("rhs_3"));
                publicProfile.setRhsMaster(resultSet.getBoolean("rhs_master"));
                publicProfile.setMaintenanceOffered(resultSet.getBoolean("maintenance"));
                publicProfile.setDesignOffered(resultSet.getBoolean("design"));
                publicProfile.setTreeSurgeryOffered(resultSet.getBoolean("treesurgery"));
                publicProfile.setWaterFeaturesOffered(resultSet.getBoolean("waterfeatures"));
                publicProfile.setFencingOffered(resultSet.getBoolean("maintenance"));
                publicProfile.setMaintenanceOffered(resultSet.getBoolean("fencing"));
                publicProfile.setPavingOffered(resultSet.getBoolean("paving"));
                publicProfile.setDeckingOffered(resultSet.getBoolean("decking"));
                publicProfile.setGoogleLocation(resultSet.getString("google_location"));
                publicProfile.setProfileImage( new ProfileImage(resultSet.getString("personal_photo_path")));
                // publicProfile.setRecordedOnDatabase(true);
                // publicProfile.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                //Assuming you have a user object

                gardener.setPublicProfile(publicProfile);

                gardeners.add(gardener);
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
        return  gardeners;
    }

    public Gardener getGardenerWithProfile(long id){


        Gardener gardener = null;


        try {


            String query = "Select *, COUNT(r.id) as num_reviews, AVG(Cast(r.score as decimal(2,1))) as avg_rating "+
                    "FROM profiles p JOIN users u ON p.username = u.username "+
                    "LEFT JOIN reviews r ON p.username = r.gardener_username "+
                    "WHERE u.id=? "+
                    "GROUP BY p.username ";



            connection = getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setLong(1, id);

            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                gardener = new Gardener();
                System.out.println("num reviews" +resultSet.getString("num_reviews"));
                gardener.setUsername(resultSet.getString("username"));
                gardener.setEmail(resultSet.getString("email"));
                gardener.setFirstName(resultSet.getString("first_name"));
                gardener.setLastName(resultSet.getString("last_name"));
                gardener.setReviewCount(resultSet.getInt("num_reviews"));
                gardener.setAvgReviewScore(resultSet.getFloat("avg_rating"));
                gardener.setPhone(resultSet.getString("phone"));
                gardener.setPostcode(resultSet.getString("postcode"));
                gardener.setAutoIncrementID(resultSet.getInt("id"));

                PublicProfile publicProfile = new PublicProfile(resultSet.getString("username"));
                publicProfile.setTradename(resultSet.getString("tradename"));
                publicProfile.setDescription(resultSet.getString("description"));
                publicProfile.setLatitude(resultSet.getFloat("lat"));
                publicProfile.setLongitude(resultSet.getFloat("lng"));
                publicProfile.setRadius(resultSet.getInt("radius"));
                publicProfile.setRhs1(resultSet.getBoolean("rhs_1"));
                publicProfile.setRhs2(resultSet.getBoolean("rhs_2"));
                publicProfile.setRhs3(resultSet.getBoolean("rhs_3"));
                publicProfile.setRhsMaster(resultSet.getBoolean("rhs_master"));
                publicProfile.setMaintenanceOffered(resultSet.getBoolean("maintenance"));
                publicProfile.setDesignOffered(resultSet.getBoolean("design"));
                publicProfile.setTreeSurgeryOffered(resultSet.getBoolean("treesurgery"));
                publicProfile.setWaterFeaturesOffered(resultSet.getBoolean("waterfeatures"));
                publicProfile.setFencingOffered(resultSet.getBoolean("maintenance"));
                publicProfile.setMaintenanceOffered(resultSet.getBoolean("fencing"));
                publicProfile.setPavingOffered(resultSet.getBoolean("paving"));
                publicProfile.setDeckingOffered(resultSet.getBoolean("decking"));
                publicProfile.setGoogleLocation(resultSet.getString("google_location"));
                publicProfile.setProfileImage( new ProfileImage(resultSet.getString("personal_photo_path")));
                // publicProfile.setRecordedOnDatabase(true);
                // publicProfile.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                //Assuming you have a user object

                gardener.setPublicProfile(publicProfile);


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
        return  gardener;
    }


    public List<Gardener> getGardenersWithProfiles(float lat, float lng){

        List<Gardener> gardeners = new ArrayList<Gardener>();

        try {
            String query = "SELECT *, ( 3959 * acos( cos( radians(?) ) *" +
                    " cos( radians(lat ) ) * cos( radians(lng) - radians(?)) + sin(radians(?)) " +
                    " * sin( radians(lat)))) AS distance, COUNT(r.id) as num_reviews, AVG(Cast(r.score as decimal(2,1))) as avg_rating  " +
                    "FROM profiles p JOIN users u ON p.username = u.username " +
                    "LEFT JOIN reviews r ON p.username = r.gardener_username " +
                    "GROUP BY p.username " +
                    "HAVING (distance < radius ) " +
                    "ORDER BY avg_rating DESC";





            connection = getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setFloat(1, lat);
            pStatement.setFloat(2, lng);
            pStatement.setFloat(3, lat);

            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Gardener gardener = new Gardener(resultSet.getString("username"), resultSet.getString("email"));
                gardener.setPhone(resultSet.getString("phone"));
                gardener.setReviewCount(resultSet.getInt("num_reviews"));
                gardener.setAvgReviewScore(resultSet.getFloat("avg_rating"));
                gardener.setFirstName(resultSet.getString("first_name"));
                gardener.setLastName(resultSet.getString("last_name"));
                gardener.setPostcode(resultSet.getString("postcode"));
                gardener.setAutoIncrementID(resultSet.getInt("id"));

                PublicProfile publicProfile = new PublicProfile(resultSet.getString("username"));
                publicProfile.setTradename(resultSet.getString("tradename"));
                publicProfile.setDescription(resultSet.getString("description"));
                publicProfile.setLatitude(resultSet.getFloat("lat"));
                publicProfile.setLongitude(resultSet.getFloat("lng"));
                publicProfile.setRadius(resultSet.getInt("radius"));
                publicProfile.setRhs1(resultSet.getBoolean("rhs_1"));
                publicProfile.setRhs2(resultSet.getBoolean("rhs_2"));
                publicProfile.setRhs3(resultSet.getBoolean("rhs_3"));
                publicProfile.setRhsMaster(resultSet.getBoolean("rhs_master"));
                publicProfile.setMaintenanceOffered(resultSet.getBoolean("maintenance"));
                publicProfile.setDesignOffered(resultSet.getBoolean("design"));
                publicProfile.setTreeSurgeryOffered(resultSet.getBoolean("treesurgery"));
                publicProfile.setWaterFeaturesOffered(resultSet.getBoolean("waterfeatures"));
                publicProfile.setFencingOffered(resultSet.getBoolean("maintenance"));
                publicProfile.setMaintenanceOffered(resultSet.getBoolean("fencing"));
                publicProfile.setPavingOffered(resultSet.getBoolean("paving"));
                publicProfile.setDeckingOffered(resultSet.getBoolean("decking"));
                publicProfile.setGoogleLocation(resultSet.getString("google_location"));
                publicProfile.setProfileImage( new ProfileImage(resultSet.getString("personal_photo_path")));

                gardener.setPublicProfile(publicProfile);
                // publicProfile.setRecordedOnDatabase(true);
                // publicProfile.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                //Assuming you have a user object

                gardeners.add(gardener);
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
        return  gardeners;

    }





}
