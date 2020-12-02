package model;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class EnemyList implements Serializable{

	private Enemy first;
	
	private int size;
	
	
	public EnemyList() {
		first = null;
		size=0;
	}
	
	/**
	 * @return the head
	 */
	public Enemy getFirst() {
		return first;
	}

	/**
	 * @param head the head to set
	 */
	public void setFirst(Enemy first) {
		this.first = first;
	}

	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * method that adds an enemy to the list of enemies
	 * @param new != null
	 */
	public void add(Enemy newE) {
		if(first==null) {
			first=newE;
		}else {

			Enemy temp=first;
			while(temp.getNext()!=null) {
				temp=temp.getNext();
			}
			temp.setNext(newE);
		}
		setSize(size()+1);
	}
	
	/**
	 * Method that cleans the list of enemies, making the head 
	 * lose the references of their next and then makes the value of the head =null 
	 */
	public void clear() {
		first.setNext(null);
		first=null;
		setSize(0);
	}
	
	/**
	 * Method that returns an Enemy given by the index parameter
	 * @param index != null
	 * @return The enemy in the position of the index parameter
	 */
	public Enemy get(int index) {
		int c=0;
		Enemy temp=first;
		while(c<index) {
			if(temp.getNext()!=null) {
				temp=temp.getNext();
			}
			c++;
		}
		return temp;
	}
	
	
}
