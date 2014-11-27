package gardenpeople.dao;

import gardenpeople.db.ConnectionFactory;
import gardenpeople.model.GardenOwner;
import gardenpeople.model.Gardener;
import gardenpeople.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


 
public class UserDAO {
    Connection connection;
    Statement stmt;
    private static String accountType = "account_type";
    
  
    private int noOfRecords;
         
    public UserDAO() { }
     
    private static Connection getConnection()throws SQLException,ClassNotFoundException {
        
    	Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }
    
    public boolean editUserDetailsWithPassword(User user){
    	int updated =0;
    	String query = "UPDATE users SET email=? , password=? , first_name=? ,"
    			+ " last_name=? , house=? , street=? , postCode=? , county=?"
                + " WHERE username=?";
    	try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1, user.getEmail());
			pStatement.setString(2, user.getHashPassword());
			pStatement.setString(3, user.getFirstName());
			pStatement.setString(4, user.getLastName());
			pStatement.setString(5, user.getHouseNumberName());
			pStatement.setString(6, user.getStreet());
			pStatement.setString(7, user.getPostcode());
			pStatement.setInt(8, user.getCounty());
			pStatement.setString(9, user.getUsername());
			updated = pStatement.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
        {
            try {
                if(stmt != null)
                    stmt.close();
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
    public boolean editUserDetailsWithoutPassword(User user){
    	int updated =0;
    	String query = "UPDATE users SET email=? , first_name=? ,"
    			+ " last_name=? , house=? , street=? , postCode=? , county=?"
                + " WHERE username=?";
    	try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1, user.getEmail());
			pStatement.setString(2, user.getFirstName());
			pStatement.setString(3, user.getLastName());
			pStatement.setString(4, user.getHouseNumberName());
			pStatement.setString(5, user.getStreet());
			pStatement.setString(6, user.getPostcode());
			pStatement.setInt(7, user.getCounty());
			pStatement.setString(8, user.getUsername());
			updated = pStatement.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
        {
            try {
                if(stmt != null)
                    stmt.close();
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
    
    public User findUser( String username, String password){
    	String query = "Select * FROM users WHERE username=? and password=?";
    	User user = null;
    	try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1, username);
			pStatement.setString(2, User.sha1(password));
			ResultSet resultSet = pStatement.executeQuery();
			resultSet.last();
			int row = resultSet.getRow();
			//System.out.println(row);
			if(row >0){
				String type = resultSet.getString(accountType);
				if(type.equals("garden_owner")){
					user = new GardenOwner(resultSet.getString("username"),resultSet.getString("email"));
				}else{
					user = new Gardener(resultSet.getString("username"),resultSet.getString("email"));
				}
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setHouseNumberName(resultSet.getString("house"));
				user.setStreet(resultSet.getString("street"));
				user.setPostcode(resultSet.getString("postcode"));
				user.setCounty(resultSet.getInt("county"));
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
        {
            try {
                if(stmt != null)
                    stmt.close();
                if(connection != null)
                    connection.close();
                } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    	return user;
    	
    }
    
    public List<User> findGardenerByName( String name){
    	String query = "Select * FROM users WHERE first_name=? OR last_name=? and account_type = \"gardener\"";
    	List<User> ll = new ArrayList<User>();
    	
    	
    	try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1, name);
			pStatement.setString(2, name);

	    	ResultSet resultSet = pStatement.executeQuery();

	    	while (resultSet.next()) {
				User user = new Gardener(resultSet.getString("username"),resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setHouseNumberName(resultSet.getString("house"));
				user.setStreet(resultSet.getString("street"));
				user.setPostcode(resultSet.getString("postcode"));
				user.setCounty(resultSet.getInt("county"));

	    	  //Assuming you have a user object

	    	  ll.add(user);
	    	}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
        {
            try {
                if(stmt != null)
                    stmt.close();
                if(connection != null)
                    connection.close();
                } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    	return ll;
    	
    }
    
    public boolean addUser(User user){
    	
    	int updated = 0;
    	String query = "INSERT INTO users (username,email,password"
    			+ ",account_type,first_name,last_name,house,street,postCode,county) VALUES (?,?,?,?,?,?,?,?,?,?)";
    	
    	try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1, user.getUsername());
			pStatement.setString(2, user.getEmail());
			System.out.println(user.getHashPassword() + '\t' + user.getHashPassword().length());
			pStatement.setString(3, user.getHashPassword());
			if (user instanceof GardenOwner){
				pStatement.setString(4, "garden_owner");
			}else{
				pStatement.setString(4, "gardener");
			}
			pStatement.setString(5, user.getFirstName());
			pStatement.setString(6, user.getLastName());
			pStatement.setString(7, user.getHouseNumberName());
			pStatement.setString(8, user.getStreet());
			pStatement.setString(9, user.getPostcode());
			pStatement.setInt(10, user.getCounty());
			updated = pStatement.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
        {
            try {
                if(stmt != null)
                    stmt.close();
                if(connection != null)
                    connection.close();
                } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    	if(updated> 0){
    		return true;
    	}
    	return false;
    	
    }
     
    public List<User> viewAllEmployees( int offset, int noOfRecords){
       return null;
    }
 
    public int getNoOfRecords() {
        return noOfRecords;
    }
}