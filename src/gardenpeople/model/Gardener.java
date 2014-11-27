package gardenpeople.model;

import java.util.HashMap;

public class Gardener extends User{
	
	private String tradeName;
	private String description;
	private HashMap<String, Boolean> checkList;
	
	
	public Gardener(String username, String email){
		super(username, email);
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



	public HashMap<String, Boolean> getCheckList() {
		return checkList;
	}



	public void setCheckList(HashMap<String, Boolean> checkList) {
		this.checkList = checkList;
	}



	public Gardener(String username, String email, String password){
		super(username, email, password);
	}

}
