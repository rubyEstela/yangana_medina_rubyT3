package model;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import interfaces.InterfaceFire;
import interfaces.InterfaceReloadE;

public class EnemyA extends Enemy implements Serializable, InterfaceReloadE, InterfaceFire {

	private static final long serialVersionUID = 1L;

	private Timer timer;
	
	private TimerTask tT;
	
	private Shoot shoot;

	private boolean shooting;
	
	private long delay;
	
	private long period;
	
	/**
	 * Constructor of the class EnemyA 
	 * @param posX != null, posX position x of EnemyA
	 * @param posY != null, posY position y of EnemyA
	 * @param points != null, points points given by the EnemyA when he is hit
	 * @param image != null, image path of the image that the EnemyA will have in the interface
	 * @param aLive != null, boolean of confirmation to know if the enemy is still alive or not
	 * @param addres != null, address char that indicates the direction of movement of the Enemy
	 */				 
	public EnemyA(int posX, int posY, int points, String image, boolean alive, char address) {
		super(posX, posY, points, image, alive, address);
		
		shoot=new Shoot(0);
		
		this.shooting=false;
		
		delay=3000;
		period=5000;
		
		shootEnemy();
	}
	
	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * @return the tT
	 */
	public TimerTask gettT() {
		return tT;
	}

	/**
	 * @param tT the tT to set
	 */
	public void settT(TimerTask tT) {
		this.tT = tT;
	}

	/**
	 * @return the shoot
	 */
	public Shoot getShoot() {
		return shoot;
	}

	/**
	 * @param shoot the shoot to set
	 */
	public void setShoot(Shoot shoot) {
		this.shoot = shoot;
	}

	/**
	 * @return the disparando
	 */
	public boolean isShooting() {
		return shooting;
	}

	/**
	 * @param disparando the disparando to set
	 */
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	/**
	 * @return the delay
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * @param delay the delay to set
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}

	/**
	 * @return the period
	 */
	public long getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(long period) {
		this.period = period;
	}


	/**
	 * method that makes the EnemyA fire
	 */
	public void shootEnemy() {
		timer=new Timer();
		tT=new TimerTask() {
			@Override
			public void run() {
				if(shooting==false) {
					shooting=true;
					fire();
				}
			}
		};
		timer.schedule(tT, getDelay(), getPeriod());	
	}
	
	/**
	 * Method that calculates if it is time to reload the bullet of EnemyA
	 */
	@Override
	public void reloadE() {
		if(getShoot().getY()>VideoGame.LONG_WINDOW-40) {
			shooting=false;
			shoot.reloadE();
		}
	}
	
	/**
	 * method that calculates the position of the bullet of EnemyA
	 */
	@Override
	public void fire() {
		if(shooting=true) {
			shoot.moveE();			
			shoot.setY(getPosY());
			shoot.setX(getPosX());
			reloadE();
		}
	}
	
	/**
	 * Method toString() of the EnemigoA
	 */
	@Override
	public String toString() {
		return "EnemyA [timer=" + timer + ", tT=" + tT + ", shoot=" + shoot + ", shooting=" + shooting + ", delay="
				+ delay + ", period=" + period + "]";
	}
	


}
