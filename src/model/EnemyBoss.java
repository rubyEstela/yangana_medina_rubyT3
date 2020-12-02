package model;

import java.io.Serializable;

public class EnemyBoss extends EnemyA implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lives;

	/**
	 * Constructor of the class Enemy Boss 
	 * @param posX != null, posX position x of the EnemyBoss
	 * @param posY != null, posY position y of EnemigoBoss
	 * @param puntos != null, points points given by the EnemyBoss when he is hit
	 * @param imagen != null, image path of the image that the EnemyBoss will have in the interface
	 * @param vivo != null, Alive boolean of confirmation to know if the EnemyBoss is still alive or not
	 * @param direccion != null, address char that indicates the direction of movement of the EnemyBoss
	 */
	public EnemyBoss(int posX, int posY, int points, String image, boolean alive, char address) {
		super(posX, posY, points, image, alive, address);
		setPoints(300);
		lives=2500;
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param lives the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}