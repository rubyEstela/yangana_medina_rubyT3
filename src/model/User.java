package model;

public class User {
	private  String name;
	
	
	public User(String name) {
		
		this.name = name ;
		
	}
	
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	public int compareTo(User toInsert) {
		// TODO Auto-generated method stub
		return 0;
	}

}
