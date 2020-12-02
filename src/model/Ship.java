package model;

import java.io.Serializable;

import interfaces.InterfaceFire;
import interfaces.InterfaceReloadP;

@SuppressWarnings("serial")
public class Ship implements Serializable, InterfaceReloadP, InterfaceFire {
	

	public static final String RIGHT="img/nave.PNG";
	public static final String LEFT="img/nave.PNG";
	private String image;
		
	private int longer;
	private int width;
	
	private int posX;
	private int posY;
	
	private boolean shooting;
	
	private Shoot bullet;
	/**
	 * Constructor of the Ship class
	 * @param imagen != null
	 */
	public Ship(String image) {
		this.image=image;
		
		posX=(VideoGame.WIDTH_WINDOW/2)-getWidth();
		posY=(VideoGame.LONG_WINDOW-150);
		
		longer=90;
		width=60;
		
		this.shooting= false;
		bullet=new Shoot(0);
		reloadP();
	}
	
	public static String getRight() {
		return RIGHT;
	}

	public static String getLeft() {
		return LEFT;
	}

	public void reloadP() {
		if(bullet.getY()<5) {
			shooting=false;
			bullet.reloadP();
		}
	}
	
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the longer
	 */
	public int getLonger() {
		return longer;
	}

	/**
	 * @param longer the longer to set
	 */
	public void setLonger(int longer) {
		this.longer = longer;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param ancho the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
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
	 * @return the shooting
	 */
	public boolean isShooting() {
		return shooting;
	}

	/**
	 * @param shooting the Shooting to set
	 */
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	
	/**
	 * @return the bullet
	 */
	public Shoot getBullet() {
		return bullet;
	}

	/**
	 * @param bullet the bullet to set
	 */
	public void setBullet(Shoot bullet) {
		this.bullet = bullet;
	}

	/**
	 * This method makes the avatar appear in default position
	 */
	public void reappear() {
		this.setPosX(VideoGame.LONG_WINDOW/2);
	}
	
	/**
	 *this method increases the position X of the avatar 5 points 
	 */
	public void moveRight() {
		setImage(RIGHT);
		if(getPosX()<=VideoGame.WIDTH_WINDOW) {
			posX+=5;
		}
	}
	
	/**
	 * this method decreases the position X of the avatar 5 points 
	 */
	public void moveLeft() {
		setImage(LEFT);
		if(getPosX()<=VideoGame.WIDTH_WINDOW) {
			posX-=5;
		}
	}
	
	/**
	 * this method makes the character shoot 
	 */

	@Override
	public void fire() {
		if(isShooting()==true) {
			bullet.moveP();
		}
		
	}	

}
