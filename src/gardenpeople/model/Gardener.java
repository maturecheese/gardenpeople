package gardenpeople.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Gardener extends User{
	private boolean isProfileRecordedOnDatabase = false;
	
	private String tradeName;


	private String description;

	private PublicProfile publicProfile;
	private int reviewCount;
	private float avgReviewScore;

	private ArrayList<Review> reviews;

	public Gardener(){}

	public Gardener(String username, String email){
		super(username, email);
	}

	public Gardener (PublicProfile publicProfile){
		this.publicProfile = publicProfile;
	}
	public Gardener (User user){
		super(user);
	}
	public Gardener(String username, String email, String password){
		super(username, email, password);
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public float getAvgReviewScore() {
		return avgReviewScore;
	}

	public void setAvgReviewScore(float avgReviewScore) {
		this.avgReviewScore = avgReviewScore;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
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


}
