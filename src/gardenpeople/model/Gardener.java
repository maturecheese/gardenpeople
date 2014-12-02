package gardenpeople.model;

import java.util.HashMap;

public class Gardener extends User{
	private boolean isProfileRecordedOnDatabase = false;
	
	private String tradeName;


	private String description;

	private PublicProfile publicProfile;


	public Gardener(String username, String email){
		super(username, email);
	}

	public Gardener (PublicProfile publicProfile){
		this.publicProfile = publicProfile;
	}
	public Gardener (User user){
		super(user);
	}


	public PublicProfile getPublicProfile() {
		return publicProfile;
	}

	public void setPublicProfile(PublicProfile publicProfile) {
		this.publicProfile = publicProfile;
	}

	public String getTradeName() {
		return tradeName;
	}



	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isProfileRecordedOnDatabase() {
		return isProfileRecordedOnDatabase;
	}

	public void setProfileRecordedOnDatabase(boolean isProfileRecordedOnDatabase) {
		this.isProfileRecordedOnDatabase = isProfileRecordedOnDatabase;
	}

	public Gardener(String username, String email, String password){
		super(username, email, password);
	}

}
