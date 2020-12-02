package model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Enemy implements Serializable{

	private int posX;
	private int posY;
	
	private int points;
	
	private char address;
	
	private String imagen;

	private boolean alive;
		
	private Enemy next;
	
	/**
	 * Constructor of the class Enemy
	 * @param posX != null, posX position x of Enemy
	 * @param posY != null, posY position y of Enemy
	 * @param points != null, points points given by the Enemy when he is hit
	 * @param image != null, image path of the image that the Enemy will have in the interface
	 * @param Alive != null, Alive boolean of confirmation to know if the enemy is still alive or not
	 * @param adress != null, address char that indicates the direction of movement of the Enemy
	 */
	public Enemy(int posX, int posY, int points, String imagen, boolean alive, char address) {
		this.posX = posX;
		this.posY = posY;
		this.points = points;
		this.imagen = imagen;
		this.alive = alive;
		this.address=address;
		next= null;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the adress
	 */
	public char getAddress() {
		return address;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAddress(char address) {
		this.address = address;
	}

	/**
	 * @return the image
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param image the image to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the alive
	 */
	public boolean isALive() {
		return alive;
	}

	/**
	 * @param Alive the Alive to set
	 * @return 
	 */
	public void setALive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the next
	 */
	public Enemy getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Enemy next) {
		this.next = next;
	}

	public void moveD() {
		posX+=5;
	}
	
	public void moveI() {
		posX-=5;
	}

	public void cambiarSiguiente(Enemy head) {
		// TODO Auto-generated method stub
		next = head;
	}
	
	
	
}

