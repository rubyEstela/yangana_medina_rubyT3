package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")

public class VideoGame  implements Serializable{
	public static final int LONG_WINDOW=621;
	public static final int WIDTH_WINDOW=360;
	
	private User user;
	private ABBUser treeUser;
	
	private EnemyBoss eB;

	private EnemyList  list;
	

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
	
	public void metoPrueba() {
		System.out.print("hola mundo");
	}
	
	public EnemyList getList() {
		return list;
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
	 * Method looking for in an ordered arrangement a user with a score equal to the parameter value
	 * @param name != null, Parameter with the score to look for 
	 * @return el toString() of the user found with the specified score
	 * @throws UsersNotExistException if in the arrangement there is no user with a score equal to the specified parameter
	 */
	public String searchNameBinary (String name) {
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
			
		}else {
			return users.get(half).toString();
		}
		return name;
	}
	
	

	private ArrayList<User> arrayUser() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(EnemyList list) {
		this.list = list;
	
	

}
}
