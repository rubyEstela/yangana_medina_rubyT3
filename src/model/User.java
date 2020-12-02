package model;

import java.io.Serializable;

import exception.RepeatedUserException;



@SuppressWarnings("serial")

public class User implements Serializable, Comparable<User>{
	
	private Ship avatar;
	
	private String name;
	private int age;
	private String gender; 
	
	private int live;
	private int point;
	private int level;
	
	private User right;
	private User left;
		
	/**
	 * Constructor of the class User
	 * @param nombre != null
	 * @param age != null
	 * @param gender != null
	 */
	public User(String name, int age, String gender) {
		avatar=new Ship(Ship.LEFT);

		
		this.live = 2;
		this.point = 0;
		this.level = 1;
		this.name = name ;
		this.age= age ;
		this.gender = gender;
		
		right = null;
		left = null;
	}
	/**
	 * @return the name
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the  gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the lives
	 */

	public int getLive() {
		return live;
	}

	/**
	 * @param live the lives to set
	 */
	public void setLive(int live) {
		this.live = live;
	}

	/**
	 * @return the points
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the points to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the right
	 */
	public User getRight() {
		return right;
	}

	/**
	 * @param right the right to set 
	 */
	public void setRight(User right) {
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public User getLeft() {
		return left;
	}

	/**
	 * @param left the left to set 
	 */
	public void setLeft(User left) {
		this.left = left;
	}
	
	/**
	 * @return the avatar
	 */
	public Ship getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(Ship avatar) {
		this.avatar = avatar;
	}

	
	/**
	 * This recursive method inserts a new to the tree
	 * @param nuevo != null
	 * @throws RepeatedNameException if you find a new user with the same id
	 */
	public void insert( User nuevo ) throws RepeatedUserException{
		
		if(this.name.compareTo(nuevo.name)==0) {
			
			throw new RepeatedUserException(nuevo.name);
			
		}else if(this.name.compareTo(nuevo.name)<0) {
			
			if(this.right==null) {
    			this.right=nuevo;
    		}else {
    			this.right.insert(nuevo);
    		}
		}else {
			
			if(this.left==null) {
    			this.left=nuevo;
    		}else { 
    			this.left.insert(nuevo);
    		}	
		}	
	}
	
	/**
	 * This method causes the user to lose lives abd makes it reappear in a new position
	 */
	public void loseLive() {
		this.setLive(this.getLive()-1);
		avatar.reappear();
	}
	
	/**
	 * @param o != null 
	 * @return 1 if the current user has more points then the one that was passed of parameter
	 * @return -1 if the current user has less points then the one that was passed of parameter
	 * @return 0 if the user and the parameter have the same score  
	 */
	public int compareTo(User o) {
		if(this.getPoint()<o.getPoint()) {
			return 1;
		}else if(this.getPoint()>o.getPoint()){
			return -1;
		}else {
			return 0;	
		}
	}
	/**
	 * Activate the method of moving the avatar to the right
	 */
	public void moveRight() {
		avatar.moveRight();
	}
	/**
	 * Activate the method of moving the avatar to the left
	 */
	public void moveLeft() {
		avatar.moveLeft();
	}
	/**
	 * check if the user is a sheet 
	 * @return true or false
	 */
	public boolean isSheet() {
		return getRight()==null && getLeft()==null;
	}
	/**
	 *this method removes a user from the binary search tree
	 * @param aName !=null
	 * @return the new user in the root
	 */
	public User remove(String aName) {
		if(isSheet()) {
			return null;
		}
		if(aName.equalsIgnoreCase(name)) {
			if(left==null)
				return right;
			if(right==null)
				return left;
			User successor=right.getLess();
			right = right.remove(successor.name);
			successor.left=left;
			successor.right=right;
			return successor;
		}
		else if(name.compareToIgnoreCase(aName)>0) {
			left=left.remove(aName);
		}else {
			if(right!=null)
				right=right.remove(aName);
		}
		return this;
	}
	/**
	 * this method return the less user in the Left
	 * @return User less in the Left
	 */
	public User getLess() {
        return (left==null) ? this : left.getLess();
    }
	


	@Override
	public String toString() {
		return "User [name=" + name + ",  age=" + age + ", gender=" + gender
				+ ", point=" + point + "]";
	}
	
	
	
}


