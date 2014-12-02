package gardenpeople.dao;

import gardenpeople.exception.UserFriendlySQLException;
import gardenpeople.model.GardenOwner;
import gardenpeople.model.Gardener;
import gardenpeople.model.PublicProfile;



import gardenpeople.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mark-i5 on 30/11/2014.
 */
public class PublicProfileDAO extends DAO {

    public PublicProfile getProfile(String username){
        PublicProfile publicProfile = null;
        try {
            String query = "Select * FROM profiles WHERE username=?";
            connection = getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, username);
            ResultSet resultSet = pStatement.executeQuery();
            resultSet.last();
            int row = resultSet.getRow();
            System.out.println(row + " result");
            if(row >0){
                publicProfile = new PublicProfile(username);
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
                publicProfile.setRecordedOnDatabase(true);
                publicProfile.setUpdatedAt(resultSet.getTimestamp("updated_at"));

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
        return  publicProfile;
    }

    public boolean addProfile(PublicProfile publicProfile) throws UserFriendlySQLException{

        int updated = 0;
        String query = "INSERT INTO profiles (username,tradename,description"
                + ",lat,lng,radius,rhs_1,rhs_2,rhs_3,rhs_master,maintenance"
                + ",design,treesurgery,waterfeatures,fencing,paving,decking,google_location,updated_at)"
                +" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            System.out.println(query);
            connection = getConnection();
            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, publicProfile.getUsername());
            pStatement.setString(2, publicProfile.getTradename());

            pStatement.setString(3, publicProfile.getDescription());

            pStatement.setFloat(4, publicProfile.getLatitude());
            pStatement.setFloat(5, publicProfile.getLongitude());
            pStatement.setInt(6, publicProfile.getRadius());
            pStatement.setInt(7, boolToInt(publicProfile.isRhs1()));
            pStatement.setInt(8, boolToInt(publicProfile.isRhs2()));
            pStatement.setInt(9, boolToInt(publicProfile.isRhs3()));
            pStatement.setInt(10, boolToInt(publicProfile.isRhsMaster()));
            pStatement.setInt(11, boolToInt(publicProfile.isMaintenanceOffered()));
            pStatement.setInt(12, boolToInt(publicProfile.isDesignOffered()));
            pStatement.setInt(13, boolToInt(publicProfile.isTreeSurgeryOffered()));
            pStatement.setInt(14, boolToInt(publicProfile.isWaterFeaturesOffered()));
            pStatement.setInt(15, boolToInt(publicProfile.isFencingOffered()));
            pStatement.setInt(16, boolToInt(publicProfile.isPavingOffered()));
            pStatement.setInt(17, boolToInt(publicProfile.isDeckingOffered()));
            pStatement.setString(18,publicProfile.getGoogleLocation());
            pStatement.setTimestamp(19,new java.sql.Timestamp(new java.util.Date().getTime()));

            updated = pStatement.executeUpdate();


        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  UserFriendlySQLException(e.getMessage());

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
    private int boolToInt(boolean value){
        if (value){
            return 1;
        }
        return 0;
    }

    public boolean editProfile(String username, PublicProfile newProfile)throws UserFriendlySQLException{
        int updated =0;
        String query = "UPDATE profiles SET tradename=? , description=? ,"
                + " lat=? , lng=? , radius=? , rhs_1=? , rhs_2=?,"
                + " rhs_3=? , rhs_master=? , maintenance=? , design=? , treesurgery=?,"
                + " waterfeatures=? , fencing=? , paving=? , decking=? , google_location=?,"
                + " updated_at=? "
                + " WHERE username=?";
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, newProfile.getTradename());
            pStatement.setString(2, newProfile.getDescription());
            pStatement.setFloat(3, newProfile.getLatitude());
            pStatement.setFloat(4, newProfile.getLongitude());
            pStatement.setInt(5, newProfile.getRadius());
            pStatement.setInt(6, boolToInt(newProfile.isRhs1()));
            pStatement.setInt(7, boolToInt(newProfile.isRhs2()));
            pStatement.setInt(8, boolToInt(newProfile.isRhs3()));
            pStatement.setInt(9, boolToInt(newProfile.isRhsMaster()));
            pStatement.setInt(10, boolToInt(newProfile.isMaintenanceOffered()));
            pStatement.setInt(11, boolToInt(newProfile.isDesignOffered()));
            pStatement.setInt(12, boolToInt(newProfile.isTreeSurgeryOffered()));
            pStatement.setInt(13, boolToInt(newProfile.isWaterFeaturesOffered()));
            pStatement.setInt(14, boolToInt(newProfile.isFencingOffered()));
            pStatement.setInt(15, boolToInt(newProfile.isPavingOffered()));
            pStatement.setInt(16, boolToInt(newProfile.isDeckingOffered()));
            pStatement.setString(17, newProfile.getGoogleLocation());
            pStatement.setTimestamp(18, new java.sql.Timestamp(new java.util.Date().getTime()));
            pStatement.setString(19, username);
            updated = pStatement.executeUpdate();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  UserFriendlySQLException(e.getMessage());

        }finally
        {
            try {
                if(pStatement != null)
                    pStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (updated >0){
            return true;
        }
        return false;
    }

	public List<PublicProfile> findGardenerByPos(float lat, float lng, int radius) {
		// TODO Auto-generated method stub
		List<PublicProfile> ll = new ArrayList<PublicProfile>();
		
    	
		
        try {
            String query = "SELECT username, ( 3959 * acos( cos( radians(?) ) * cos( radians(lat ) ) * cos( radians(lng) - radians(?)) + sin(radians(?))  * sin( radians(lat)))) AS distance, radius FROM profiles  HAVING (distance < radius AND distance < ?) ORDER BY distance;";
            connection = getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setFloat(1, lat);
            pStatement.setFloat(2, lng);
            pStatement.setFloat(3, lat);
            pStatement.setInt(4, radius);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
				PublicProfile user = new PublicProfile(resultSet.getString("username"));

	    	  //Assuming you have a user object

	    	  ll.add(user);
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
        return  ll;
	}

}



