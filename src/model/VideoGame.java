

package model;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import exception.TreeEmptyException;
import exception.UsersNotExistException;
import exception.ScoreNotExistException;
import exception.RepeatedUserException;


@SuppressWarnings("serial")
public class VideoGame implements Serializable{

	public static final int LONG_WINDOW=621;
	public static final int WIDTH_WINDOW=360;
	
	private User user;
	private ABBUser treeUser;
	
	private EnemyBoss eB;

	private EnemyList  list;
		
	/**
	 *builder of the class Game
	 */
	public VideoGame(){
		loadEnemy();
	    loadBoss();  
	}    

	/**
	 * Method that loads the users of a serialized file
	 * @param name != null
	 * @param age!= null
	 * @param gender != null
	 * @throws RepeatedUserException If you try to add the user, find an existing user with the same name
	 */
	public void loadUser(String name, int age, String gender) throws RepeatedUserException{
		try {
			ObjectInputStream loadUsers=new ObjectInputStream(new FileInputStream("./data/treeData.dat"));
			treeUser=(ABBUser)loadUsers.readObject();
			loadUsers.close();
		}catch(Exception e) {
			treeUser=new ABBUser();
		}finally {
			user= new User(name, age, gender);
			treeUser.add(treeUser.getRoot(), user);
		}
	}
	
	/**
	 * Method that serializes the binary tree of the users' ship
	 */
	public void saveUser() {
		try {
			ObjectOutputStream salvar=new ObjectOutputStream(new FileOutputStream("./data/treeData.dat"));
			salvar.writeObject(treeUser);
			salvar.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the list
	 */
	
	public EnemyList getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(EnemyList list) {
		this.list = list;
	}

	/**
	 * @return the LONG_WINDOW
	 */
	public static int getLongWindow() {
		return LONG_WINDOW;
	}

	/**
	 * @return the WIDTH_WINDOW
	 */
	public static int getWidthWindow() {
		return WIDTH_WINDOW;
	}


	/**
	 * Method that loads enemies from a serialized file
	 */
	public void loadEnemy() {
		try {
			ObjectInputStream load=new ObjectInputStream(new FileInputStream("./data/enemys.dat"));
			list =(EnemyList) load.readObject();
			load.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method that reads a text file and instance a new EnemyBoss
	 * with the values obtained from the text file
	 */
	public void loadBoss() {
		try {
			BufferedReader reader=new BufferedReader(new FileReader("src/data/Boss.txt"));
			String linea=reader.readLine();
			String[] arr=linea.split(",");
			eB=new EnemyBoss(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), arr[3], Boolean.parseBoolean(arr[4]), arr[5].charAt(0));
			reader.close();
		} catch (Exception e) {

		}
	}
	
	/**
	 * Method that looks for the highest score of all users
	 * @return the highest score among all users
	 * @throws TreeEmptyException If you try to call this method when the tree is empty
	 */
	public int SearchHigherScore() throws TreeEmptyException {
		int higher=0;
		ArrayList<User> users=arrayUser();
		if(users.size()==0) {
			throw new TreeEmptyException();
		}else {
			for(int i=1;i<users.size();i++) {
				User toInsert=(User)users.get(i);
				boolean finished=false;
				for(int j=i;j>0 && !finished;j--) {
					User current=(User)users.get(j-1);
					if(current.compareTo(toInsert)>0) {
						users.set(j, current);
						users.set(j-1, toInsert);
					}else {
						finished=true;
					}
				}
			}
			higher=users.get(0).getPoint();
			return higher;
		}
	}
	
	/**
	 * Method that orders an arrangement according to the names of the objects in it
	 * @return ArrayList sorted according to user names
	 */
	public ArrayList<User> orderName(){
		ArrayList<User> users= arrayUser();
		ComparatorName cN=new ComparatorName();
		for(int i=1;i<users.size();i++) {
			for(int j=i;j>0 && cN.compare(users.get(j-1), users.get(j))>0;j--) {
				User temp=users.get(j);
				users.set(j, users.get(j-1));
				users.set(j-1, temp);
			}
		}
		return users;
	}
	
	/**
	 * Method that orders an arrangement according to the points of the objects in it 
	 * @return ArrayList sorted according to user points
	 */
	public ArrayList<User> orderPoint() {
		ArrayList<User> users=arrayUser();
		for(int i=1;i<users.size();i++) {
			User toInsert=(User)users.get(i);
			boolean finished=false;
			for(int j=i;j>0 && !finished;j--) {
				User current=(User)users.get(j-1);
				if(current.compareTo(toInsert)>0) {
					users.set(j, current);
					users.set(j-1, toInsert);
				}else {
					finished=true;
				}
			}
		}
		return users;
	}
	
	 /**
     * Organize the list of users by gender using the bubble algorithm. <br>
     * <b>post: </b>The list of users is sorted by gender (descending order).
     */
    public ArrayList<User>  orderUsersByGenderFalling( ) {
		ArrayList<User> users=arrayUser();

        for( int i = users.size(); i > 0; i-- ){
            for( int j = 0; j < i - 1; j++ ){
                User u1 = (User)users.get(j);
                User u2 = (User)users.get(j + 1);
               if( u1.getGender().compareTo(u2.getGender())<0) {
                    users.set(j,u2);
                    users.set(j+1,u1);
                }
            }
        }
        return users;
    }
    
    /**
     * Organize the list of users by gender using the bubble algorithm. <br>
     * <b>post: </b>The list of users is sorted by gender (ascending order).
     */
    public  ArrayList<User> orderUsersByGender( ) {
		ArrayList<User> users=arrayUser();

        for( int i = users.size(); i > 0; i-- ){
            for( int j = 0; j < i - 1; j++ ){
                User u1 = (User)users.get(j);
                User u2 = (User)users.get(j + 1);
               if( u1.getGender().compareTo(u2.getGender())>0) {
                    users.set(j,u2);
                    users.set(j+1,u1);
                }
            }
        }
       return users;
    }
    
    /**
     * Organize the list of users by age using the selection algorithm. <br>
     * <b>post: </b>The user list is sorted by age (ascending order).
     */
    public ArrayList<User> orderUsersByAge( ){
		ArrayList<User> users=arrayUser();
        int initial;
        for( initial = 0; initial <users.size( ); initial++ ){
            int minorPosition = initial;
            User minorUser = ( User )users.get( initial );
            for( int i = initial + 1; i < users.size( ); i++ ){
                User userPosition = (User)users.get( i );
                if(userPosition.getAge() < minorUser.getAge()){
                    minorUser = userPosition;
                    minorPosition = i;
                }
            }
            if( minorPosition != initial )
            {
                User temp = (User)users.get( initial );
                users.set(initial,minorUser);
                users.set(minorPosition, temp);
            }
        }
       return users;
    }
    
    /**
     * Organize the list of users by age using the selection algorithm. <br>
     * <b>post: </b>The user list is sorted by age (descending order).
     */
    public ArrayList<User> orderUsersByAgeFalling( ){
		ArrayList<User> users=arrayUser();
        int initial;
        for( initial = 0; initial <users.size( ); initial++ ){
            int minorPosition = initial;
            User minorUser = ( User )users.get( initial );
            for( int i = initial + 1; i < users.size( ); i++ ){
                User userPosition = (User)users.get( i );
                if(userPosition.getAge() > minorUser.getAge()){
                    minorUser = userPosition;
                    minorPosition = i;
                }
            }
            if( minorPosition != initial )
            {
                User temp = (User)users.get( initial );
                users.set(initial,minorUser);
                users.set(minorPosition, temp);
            }
        }
       return users;
    }
    
    
	
    /**
	 * Method that takes an arrangement and converts it to a single String type object. 
	 * @param arreglo != null
	 * @return String that contains all toString() of the objects in the array parameter
	 */
	public String arrayToString(ArrayList<User> array) {
		String end="";
		for(int m=0;m<array.size();m++) {
			int j=m+1;
			end+=""+j+")"+array.get(m).toString()+" ";
			end+="\n";
		}
		return end;
	}
	
	/**
	 * Recursive method that takes an arrangement and turns it into a single String type object.
	 * @param arreglo != null, The arrangement to convert 
	 * @param i != null, position of the arrangement to be taken
	 * @param j != null, counter
	 * @param end != null, String acomulator 
	 * @return String type object with all toString() of the array objects separated by "\n" 
	 */
	public String arrayToString(ArrayList<User> array, int i, int j, String end) {
		if(i<array.size()) {
			j=i+1;
			end+=""+j+")"+array.get(i).toString()+" ";
			end+="\n";
			return arrayToString(array, i++, j, end);
		}else {
			return end;
		}
	}
	
	/**
	 * Method looking for in an ordered arrangement a user with a score equal to the parameter value
	 * @param valor != null, Parameter with the score to look for 
	 * @return el toString() of the user found with the specified score
	 * @throws ScoreNotExistException if in the arrangement there is no user with a score equal to the specified parameter
	 */
	public String searchBinary (int value) throws ScoreNotExistException{
		boolean found=false;
		int start=0;
		int half=0;
		ArrayList<User> users=orderPoint();
		int end=users.size()-1;
		while(start<=end && !found){
				half=(start+end)/2;
			if(users.get(half).getPoint()==value){
				found=true;
			}else if(users.get(half).getPoint()<value){
				end=half-1;
			}else{
				start=half+1;
			}
		}if(found==false) {
			throw new ScoreNotExistException(value); 
		}else {
			return users.get(half).toString();
		}
	}
	
	/**
	 * Method looking for in an ordered arrangement a user with a score equal to the parameter value
	 * @param name != null, Parameter with the score to look for 
	 * @return el toString() of the user found with the specified score
	 * @throws UsersNotExistException if in the arrangement there is no user with a score equal to the specified parameter
	 */
	public String searchNameBinary (String name) throws UsersNotExistException{
		boolean found=false;
		int start=0;
		int half=0;
		ArrayList<User> users=orderName();
		int end=users.size()-1;
		while(start<=end && !found){
				half=(start+end)/2;
			if(users.get(half).getName().compareToIgnoreCase(name)==0){
				found=true;
			}else if(users.get(half).getName().compareToIgnoreCase(name)<0){
				end=half-1;
			}else{
				start=half+1;
			}
		}if(found==false) {
			throw new UsersNotExistException(name); 
		}else {
			return users.get(half).toString();
		}
	}
	
	/**
	 * Method that converts a binary search tree to a user-type ArrayList
	 * @return ArrayList user type with binary search tree content 
	 
	 */
	public ArrayList<User> arrayUser(){
		ArrayList<User> names = new ArrayList<User>();
		toArray(getTreeUser().getRoot(), names);
		return names;
	}
	
	/**
	 * A recursive method that runs through the binary search tree and adds each element to an ArrayList of User type. 
	 * @param current current User in which the path of the binary search tree goes
	 * @param nombres != null 
	 */
	public void toArray(User current, ArrayList<User> nombres){
		if(current!=null ) {
			if(current.isSheet()) {
				nombres.add(current);
			}else {
				nombres.add(current);
				toArray(current.getLeft(), nombres);
				toArray(current.getRight(), nombres);
			}
		}
	}
	

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the treeUser
	 */
	public ABBUser getTreeUser() {
		return treeUser;
	}

	/**
	 * @param treeUser the treeUser to set
	 */
	public void setTreeUser(ABBUser treeUser) {
		this.treeUser = treeUser;
	}

	/**
	 * @return the eB
	 */
	public EnemyBoss geteB() {
		return eB;
	}

	/**
	 * @param eB the eB to set
	 */
	public void seteB(EnemyBoss eB) {
		this.eB = eB;
	}


	
	
	
	
	
	
}
