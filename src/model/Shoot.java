package model;

import java.io.Serializable;

import interfaces.InterfaceReloadE;
import interfaces.InterfaceReloadP;


@SuppressWarnings("serial")
public class Shoot implements Serializable, InterfaceReloadP, InterfaceReloadE{

	private int length;
	private int width;
	
	private int x;
	private int y;
	
	private int damage;
	
	/**
	 * Constructor of the class Shoot
	 * @param x != null, position x of the Shoot
	 */
	public Shoot(int x) {
		length=50;
		width=20;
		y=VideoGame.LONG_WINDOW-165;
		setDamage(250);
	}
	
	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Method that sets the position and shot of the enemy in a default position
	 */
	@Override
	public void reloadE() {
		setY(35);
	}
	
	/**
	 * Method that sets the position and shot of the character in a default position
	 */
	@Override
	public void reloadP() {
		setY(VideoGame.LONG_WINDOW-165);
	}
	
	/**
	 * Method that changes the position of the shot -50 points
	 */
	public void moveP() {
		y-=50;
	}

	/**
	 * Method that changes the position of the shot +50 pixels
	 */
	public void moveE() {
		y+=50;
	}
	
}
