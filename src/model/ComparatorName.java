package model;

import java.util.Comparator;


public class ComparatorName implements Comparator<User>{
	/**
	 *this method is responsible for comparing two users
	 * @param user1 != null
	 * @param  user2 != null
	 * @return user
	 */
	
	@Override
	public int compare(User user1, User user2) {
		return user1.getName().compareToIgnoreCase(user2.getName());
	}

	


	

}

