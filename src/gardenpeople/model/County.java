package gardenpeople.model;

import java.util.Comparator;

public class County implements Comparable<County>{
	
	private int id;
	private String name;
	
	public County(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	

	@Override
	public int compareTo(County county) {
		return name.compareTo(county.name);
	}
	
	

}
