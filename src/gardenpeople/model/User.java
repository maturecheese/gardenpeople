package gardenpeople.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	protected String username;
	protected String email;
	protected String password;
	
	
	protected String firstName;
	protected String lastName;
	

	protected String houseNumberName;
	protected String street;
	protected int county;
	protected String postcode;
	
	
	

	public User(String username, String email) {
		
		this.username = username;
		this.email = email;
		
	}
	
	
	public User(String username, String email, String password) {
		
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	
	public String getLastName() {
		return lastName;
	}
	
	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHouseNumberName() {
		return houseNumberName;
	}
	public void setHouseNumberName(String houseNumber) {
		this.houseNumberName = houseNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getCounty() {
		return county;
	}
	public void setCounty(int county) {
		this.county = county;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getHashPassword(){
		
		return sha1(this.password);
			
	}
	public boolean isGardener(){
		return (this instanceof Gardener);
	}
	
	
	public static String sha1(String s) {
	    try {
			return byteArray2Hex(MessageDigest.getInstance("SHA1").digest(s.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    return null;
	}

	private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	private static String byteArray2Hex(byte[] bytes) {
	    StringBuilder sb = new StringBuilder(bytes.length * 2);
	    for (final byte b : bytes) {
	        sb.append(hex[(b & 0xF0) >> 4]);
	        sb.append(hex[b & 0x0F]);
	    }
	    return sb.toString();
	}
	
	
	
	
	
	
	
	
}
